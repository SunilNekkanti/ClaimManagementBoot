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

import com.pfchoice.springboot.model.Target;
import com.pfchoice.springboot.repositories.specifications.TargetSpecifications;
import com.pfchoice.springboot.service.TargetService;
import com.pfchoice.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class TargetController {

	public static final Logger logger = LoggerFactory.getLogger(TargetController.class);

	@Autowired
	TargetService targetService; // Service which will do all data
											// retrieval/manipulation work

	// -------------------Retrieve All
	// Targets---------------------------------------------

	@Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_EVENT_COORDINATOR", "ROLE_CARE_COORDINATOR", "ROLE_MANAGER" })
	@RequestMapping(value = "/target/", method = RequestMethod.GET)
	public ResponseEntity<?> listAllTargets(@PageableDefault(page = 0, size = 100) Pageable pageRequest,
			@RequestParam(value = "search", required = false) String search) {

		Specification<Target> spec = new TargetSpecifications(search);
		Page<Target> targets = targetService.findAllTargetsByPage(spec, pageRequest);

		if (targets.getTotalElements() == 0) {
			System.out.println("no targets");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Page<Target>>(targets, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Target------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/target/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTarget(@PathVariable("id") int id) {
		logger.info("Fetching Target with id {}", id);
		Target target = targetService.findById(id);
		if (target == null) {
			logger.error("Target with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Target with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Target>(target, HttpStatus.OK);
	}

	// -------------------Create a
	// Target-------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/target/", method = RequestMethod.POST)
	public ResponseEntity<?> createTarget(@RequestBody Target target, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Target : {}", target);

		if (targetService.isTargetExist(target)) {
			logger.error("Unable to create. A Target with name {} already exist", target.getId());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A Target with name " + target.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		target.setCreatedBy("sarath");
		target.setUpdatedBy("sarath");
		targetService.saveTarget(target);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/target/{id}").buildAndExpand(target.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Target
	// ------------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/target/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTarget(@PathVariable("id") int id, @RequestBody Target target) {
		logger.info("Updating Target with id {}", id);

		Target currentTarget = targetService.findById(id);

		if (currentTarget == null) {
			logger.error("Unable to update. Target with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Target with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentTarget.setTargetCount(target.getTargetCount());
		currentTarget.setDescription(target.getDescription());

		targetService.updateTarget(currentTarget);
		return new ResponseEntity<Target>(currentTarget, HttpStatus.OK);
	}

	// ------------------- Delete a
	// Target-----------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/target/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTarget(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting Target with id {}", id);

		Target target = targetService.findById(id);
		if (target == null) {
			logger.error("Unable to delete. Target with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Target with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		targetService.deleteTargetById(id);
		return new ResponseEntity<Target>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Targets-----------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/target/", method = RequestMethod.DELETE)
	public ResponseEntity<Target> deleteAllTargets() {
		logger.info("Deleting All Targets");

		targetService.deleteAllTargets();
		return new ResponseEntity<Target>(HttpStatus.NO_CONTENT);
	}

}