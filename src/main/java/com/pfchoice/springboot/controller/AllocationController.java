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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pfchoice.springboot.model.Allocation;
import com.pfchoice.springboot.repositories.specifications.AllocationSpecifications;
import com.pfchoice.springboot.service.AllocationService;
import com.pfchoice.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AllocationController {

	public static final Logger logger = LoggerFactory.getLogger(AllocationController.class);

	@Autowired
	AllocationService allocationService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// Allocations---------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_EVENT_COORDINATOR", "ROLE_CARE_COORDINATOR", "ROLE_MANAGER" })
	@RequestMapping(value = "/allocation/", method = RequestMethod.GET)
	public ResponseEntity<Page<Allocation>> listAllAllocations(@PageableDefault(page = 0, size = 100) Pageable pageRequest,
			@RequestParam(value = "search", required = false) String search) {

		Specification<Allocation> spec = new AllocationSpecifications(search);
		Page<Allocation> allocationes = allocationService.findAllAllocationsByPage(spec, pageRequest);

		if (allocationes.getTotalElements() == 0) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Page<Allocation>>(allocationes, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Allocation------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/allocation/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllocation(@PathVariable("id") int id) {
		logger.info("Fetching Allocation with id {}", id);
		Allocation allocation = allocationService.findById(id);
		if (allocation == null) {
			logger.error("Allocation with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Allocation with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Allocation>(allocation, HttpStatus.OK);
	}

	// -------------------Create a
	// Allocation-------------------------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/allocation/", method = RequestMethod.POST)
	public ResponseEntity<?> createAllocation(@RequestBody Allocation allocation, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Allocation : {}", allocation);

		if (allocationService.isAllocationExist(allocation)) {
			logger.error("Unable to create. A Allocation with name {} already exist", allocation.getId());
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A Allocation with name " + allocation.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		allocation.setCreatedBy("sarath");
		allocation.setUpdatedBy("sarath");
		allocationService.saveAllocation(allocation);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/allocation/{id}").buildAndExpand(allocation.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Allocation
	// ------------------------------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/allocation/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAllocation(@PathVariable("id") int id, @RequestBody Allocation allocation) {
		logger.info("Updating Allocation with id {}", id);

		Allocation currentAllocation = allocationService.findById(id);

		if (currentAllocation == null) {
			logger.error("Unable to update. Allocation with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Allocation with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

      currentAllocation.setAllocLevel(allocation.getAllocLevel());
      currentAllocation.setPercentage(allocation.getPercentage());
      currentAllocation.setPriority(allocation.getPriority());

		allocationService.updateAllocation(currentAllocation);
		return new ResponseEntity<Allocation>(currentAllocation, HttpStatus.OK);
	}

	// ------------------- Delete a
	// Allocation-----------------------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/allocation/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllocation(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting Allocation with id {}", id);

		Allocation allocation = allocationService.findById(id);
		if (allocation == null) {
			logger.error("Unable to delete. Allocation with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Allocation with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		allocationService.deleteAllocationById(id);
		return new ResponseEntity<Allocation>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Allocations-----------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/allocation/", method = RequestMethod.DELETE)
	public ResponseEntity<Allocation> deleteAllAllocations() {
		logger.info("Deleting All Allocations");

		allocationService.deleteAllAllocations();
		return new ResponseEntity<Allocation>(HttpStatus.NO_CONTENT);
	}

}