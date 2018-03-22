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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.pfchoice.springboot.model.ClaimStatusDetail;
import com.pfchoice.springboot.repositories.specifications.ClaimStatusDetailSpecifications;
import com.pfchoice.springboot.service.ClaimStatusDetailService;
import com.pfchoice.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
@SessionAttributes({ "username", "roleId", "userId", "roleName" })
public class ClaimStatusDetailController {

	public static final Logger logger = LoggerFactory.getLogger(ClaimStatusDetailController.class);

	@Autowired
	ClaimStatusDetailService claimStatusDetailService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// ClaimStatusDetails---------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_EVENT_COORDINATOR", "ROLE_CARE_COORDINATOR", "ROLE_MANAGER" })
	@RequestMapping(value = "/claimStatusDetail/", method = RequestMethod.GET)
	public ResponseEntity<Page<ClaimStatusDetail>> listAllClaimStatusDetails(@PageableDefault(page = 0, size = 100) Pageable pageRequest,
			@RequestParam(value = "search", required = false) String search) {

		Specification<ClaimStatusDetail> spec = new ClaimStatusDetailSpecifications(search);
		Page<ClaimStatusDetail> claimStatusDetailes = claimStatusDetailService.findAllClaimStatusDetailsByPage(spec, pageRequest);

		if (claimStatusDetailes.getTotalElements() == 0) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Page<ClaimStatusDetail>>(claimStatusDetailes, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// ClaimStatusDetail------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/claimStatusDetail/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getClaimStatusDetail(@PathVariable("id") int id) {
		logger.info("Fetching ClaimStatusDetail with id {}", id);
		ClaimStatusDetail claimStatusDetail = claimStatusDetailService.findById(id);
		if (claimStatusDetail == null) {
			logger.error("ClaimStatusDetail with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("ClaimStatusDetail with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ClaimStatusDetail>(claimStatusDetail, HttpStatus.OK);
	}

	// -------------------Create a
	// ClaimStatusDetail-------------------------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/claimStatusDetail/", method = RequestMethod.POST)
	public ResponseEntity<?> createClaimStatusDetail(@RequestBody ClaimStatusDetail claimStatusDetail, UriComponentsBuilder ucBuilder) {
		logger.info("Creating ClaimStatusDetail : {}", claimStatusDetail);

		if (claimStatusDetailService.isClaimStatusDetailExist(claimStatusDetail)) {
			logger.error("Unable to create. A ClaimStatusDetail with name {} already exist", claimStatusDetail.getDescription());
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A ClaimStatusDetail with name " + claimStatusDetail.getDescription() + " already exist."),
					HttpStatus.CONFLICT);
		}
		claimStatusDetail.setCreatedBy("sarath");
		claimStatusDetail.setUpdatedBy("sarath");
		claimStatusDetailService.saveClaimStatusDetail(claimStatusDetail);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/claimStatusDetail/{id}").buildAndExpand(claimStatusDetail.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a ClaimStatusDetail
	// ------------------------------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/claimStatusDetail/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateClaimStatusDetail(@PathVariable("id") int id, @RequestBody ClaimStatusDetail claimStatusDetail,
			@ModelAttribute("username") String username) {
		logger.info("Updating ClaimStatusDetail with id {}", id);

		ClaimStatusDetail currentClaimStatusDetail = claimStatusDetailService.findById(id);

		if (currentClaimStatusDetail == null) {
			logger.error("Unable to update. ClaimStatusDetail with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. ClaimStatusDetail with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentClaimStatusDetail.setDescription(claimStatusDetail.getDescription());
      
		claimStatusDetailService.updateClaimStatusDetail(currentClaimStatusDetail);
		return new ResponseEntity<ClaimStatusDetail>(currentClaimStatusDetail, HttpStatus.OK);
	}

	// ------------------- Delete a
	// ClaimStatusDetail-----------------------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/claimStatusDetail/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteClaimStatusDetail(@PathVariable("id") int id,
			@ModelAttribute("username") String username) {
		logger.info("Fetching & Deleting ClaimStatusDetail with id {}", id);

		ClaimStatusDetail claimStatusDetail = claimStatusDetailService.findById(id);
		if (claimStatusDetail == null) {
			logger.error("Unable to delete. ClaimStatusDetail with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. ClaimStatusDetail with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		claimStatusDetail.setActiveInd('N');
		claimStatusDetail.setUpdatedBy(username);
		claimStatusDetailService.updateClaimStatusDetail(claimStatusDetail);
		return new ResponseEntity<ClaimStatusDetail>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All ClaimStatusDetails-----------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/claimStatusDetail/", method = RequestMethod.DELETE)
	public ResponseEntity<ClaimStatusDetail> deleteAllClaimStatusDetails() {
		logger.info("Deleting All ClaimStatusDetails");

		claimStatusDetailService.deleteAllClaimStatusDetails();
		return new ResponseEntity<ClaimStatusDetail>(HttpStatus.NO_CONTENT);
	}

}