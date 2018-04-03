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

import com.pfchoice.springboot.model.AllocationLevel;
import com.pfchoice.springboot.repositories.specifications.AllocationLevelSpecifications;
import com.pfchoice.springboot.service.AllocationLevelService;
import com.pfchoice.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AllocationLevelController {

	public static final Logger logger = LoggerFactory.getLogger(AllocationLevelController.class);

	@Autowired
	AllocationLevelService allocationLevelService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// AllocationLevels---------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_EVENT_COORDINATOR", "ROLE_CARE_COORDINATOR", "ROLE_MANAGER" })
	@RequestMapping(value = "/allocationLevel/", method = RequestMethod.GET)
	public ResponseEntity<Page<AllocationLevel>> listAllAllocationLevels(@PageableDefault(page = 0, size = 100) Pageable pageRequest,
			@RequestParam(value = "search", required = false) String search) {

		Specification<AllocationLevel> spec = new AllocationLevelSpecifications(search);
		Page<AllocationLevel> allocationLeveles = allocationLevelService.findAllAllocationLevelsByPage(spec, pageRequest);

		if (allocationLeveles.getTotalElements() == 0) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Page<AllocationLevel>>(allocationLeveles, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// AllocationLevel------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/allocationLevel/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllocationLevel(@PathVariable("id") int id) {
		logger.info("Fetching AllocationLevel with id {}", id);
		AllocationLevel allocationLevel = allocationLevelService.findById(id);
		if (allocationLevel == null) {
			logger.error("AllocationLevel with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("AllocationLevel with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AllocationLevel>(allocationLevel, HttpStatus.OK);
	}

	// -------------------Create a
	// AllocationLevel-------------------------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/allocationLevel/", method = RequestMethod.POST)
	public ResponseEntity<?> createAllocationLevel(@RequestBody AllocationLevel allocationLevel, UriComponentsBuilder ucBuilder) {
		logger.info("Creating AllocationLevel : {}", allocationLevel);

		if (allocationLevelService.isAllocationLevelExist(allocationLevel)) {
			logger.error("Unable to create. A AllocationLevel with name {} already exist", allocationLevel.getDescription());
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A AllocationLevel with name " + allocationLevel.getDescription() + " already exist."),
					HttpStatus.CONFLICT);
		}
		allocationLevel.setCreatedBy("sarath");
		allocationLevel.setUpdatedBy("sarath");
		allocationLevelService.saveAllocationLevel(allocationLevel);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/allocationLevel/{id}").buildAndExpand(allocationLevel.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a AllocationLevel
	// ------------------------------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/allocationLevel/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAllocationLevel(@PathVariable("id") int id, @RequestBody AllocationLevel allocationLevel,
			@ModelAttribute("username") String username){
		logger.info("Updating AllocationLevel with id {}", id);

		AllocationLevel currentAllocationLevel = allocationLevelService.findById(id);

		if (currentAllocationLevel == null) {
			logger.error("Unable to update. AllocationLevel with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. AllocationLevel with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currentAllocationLevel.setId(allocationLevel.getId());
		currentAllocationLevel.setDescription(allocationLevel.getDescription());

		allocationLevelService.updateAllocationLevel(currentAllocationLevel);
		return new ResponseEntity<AllocationLevel>(currentAllocationLevel, HttpStatus.OK);
	}

	// ------------------- Delete a
	// AllocationLevel-----------------------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/allocationLevel/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllocationLevel(@PathVariable("id") int id,
			@ModelAttribute("username") String username){
		logger.info("Fetching & Deleting AllocationLevel with id {}", id);

		AllocationLevel allocationLevel = allocationLevelService.findById(id);
		if (allocationLevel == null) {
			logger.error("Unable to delete. AllocationLevel with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. AllocationLevel with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		allocationLevel.setActiveInd('N');
		allocationLevel.setUpdatedBy(username);
		allocationLevelService.updateAllocationLevel(allocationLevel);
		return new ResponseEntity<AllocationLevel>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All AllocationLevels-----------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/allocationLevel/", method = RequestMethod.DELETE)
	public ResponseEntity<AllocationLevel> deleteAllAllocationLevels() {
		logger.info("Deleting All AllocationLevels");

		allocationLevelService.deleteAllAllocationLevels();
		return new ResponseEntity<AllocationLevel>(HttpStatus.NO_CONTENT);
	}

}