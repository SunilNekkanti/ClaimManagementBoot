package com.pfchoice.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pfchoice.springboot.model.ClaimStatus;
import com.pfchoice.springboot.repositories.specifications.ClaimStatusSpecifications;
import com.pfchoice.springboot.service.ClaimStatusService;
import com.pfchoice.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ClaimStatusController {

	public static final Logger logger = LoggerFactory.getLogger(ClaimStatusController.class);

	@Autowired
	ClaimStatusService claimStatusService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// ClaimStatuss---------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_EVENT_COORDINATOR", "ROLE_CARE_COORDINATOR", "ROLE_MANAGER" })
	@RequestMapping(value = "/claimStatus/", method = RequestMethod.GET)
	public ResponseEntity<Page<ClaimStatus>> listAllClaimStatuss(@PageableDefault(page = 0, size = 100) Pageable pageRequest,
			@RequestParam(value = "search", required = false) String search) {

		Specification<ClaimStatus> spec = new ClaimStatusSpecifications(search);
		Page<ClaimStatus> claimStatuses = claimStatusService.findAllClaimStatussByPage(spec, pageRequest);

		if (claimStatuses.getTotalElements() == 0) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Page<ClaimStatus>>(claimStatuses, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// ClaimStatus------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/claimStatus/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getClaimStatus(@PathVariable("id") int id) {
		logger.info("Fetching ClaimStatus with id {}", id);
		ClaimStatus claimStatus = claimStatusService.findById(id);
		if (claimStatus == null) {
			logger.error("ClaimStatus with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("ClaimStatus with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ClaimStatus>(claimStatus, HttpStatus.OK);
	}

	// -------------------Create a
	// ClaimStatus-------------------------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/claimStatus/", method = RequestMethod.POST)
	public ResponseEntity<?> createClaimStatus(@RequestBody ClaimStatus claimStatus, UriComponentsBuilder ucBuilder) {
		logger.info("Creating ClaimStatus : {}", claimStatus);

		if (claimStatusService.isClaimStatusExist(claimStatus)) {
			logger.error("Unable to create. A ClaimStatus with name {} already exist", claimStatus.getDescription());
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A ClaimStatus with name " + claimStatus.getDescription() + " already exist."),
					HttpStatus.CONFLICT);
		}
		claimStatus.setCreatedBy("sarath");
		claimStatus.setUpdatedBy("sarath");
		claimStatusService.saveClaimStatus(claimStatus);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/claimStatus/{id}").buildAndExpand(claimStatus.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a ClaimStatus
	// ------------------------------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/claimStatus/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateClaimStatus(@PathVariable("id") int id, @RequestBody ClaimStatus claimStatus,
			@ModelAttribute("username") String username){
		logger.info("Updating ClaimStatus with id {}", id);

		ClaimStatus currentClaimStatus = claimStatusService.findById(id);

		if (currentClaimStatus == null) {
			logger.error("Unable to update. ClaimStatus with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. ClaimStatus with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currentClaimStatus.setId(claimStatus.getId());
		currentClaimStatus.setDescription(claimStatus.getDescription());

		claimStatusService.updateClaimStatus(currentClaimStatus);
		return new ResponseEntity<ClaimStatus>(currentClaimStatus, HttpStatus.OK);
	}

	// ------------------- Delete a
	// ClaimStatus-----------------------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/claimStatus/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteClaimStatus(@PathVariable("id") int id,
			@ModelAttribute("username") String username) {
		logger.info("Fetching & Deleting ClaimStatus with id {}", id);

		ClaimStatus claimStatus = claimStatusService.findById(id);
		if (claimStatus == null) {
			logger.error("Unable to delete. ClaimStatus with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. ClaimStatus with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		claimStatus.setActiveInd('N');
		claimStatus.setUpdatedBy(username);
		claimStatusService.updateClaimStatus(claimStatus);
		return new ResponseEntity<ClaimStatus>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All ClaimStatuss-----------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/claimStatus/", method = RequestMethod.DELETE)
	public ResponseEntity<ClaimStatus> deleteAllClaimStatuss() {
		logger.info("Deleting All ClaimStatuss");

		claimStatusService.deleteAllClaimStatuss();
		return new ResponseEntity<ClaimStatus>(HttpStatus.NO_CONTENT);
	}

}