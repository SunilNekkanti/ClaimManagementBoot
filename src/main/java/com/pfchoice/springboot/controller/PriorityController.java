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

import com.pfchoice.springboot.model.Priority;
import com.pfchoice.springboot.repositories.specifications.PrioritySpecifications;
import com.pfchoice.springboot.service.PriorityService;
import com.pfchoice.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PriorityController {

	public static final Logger logger = LoggerFactory.getLogger(PriorityController.class);

	@Autowired
	PriorityService priorityService; // Service which will do all data
											// retrieval/manipulation work

	// -------------------Retrieve All
	// Prioritys---------------------------------------------

	@Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_EVENT_COORDINATOR", "ROLE_CARE_COORDINATOR", "ROLE_MANAGER" })
	@RequestMapping(value = "/priority/", method = RequestMethod.GET)
	public ResponseEntity<?> listAllPrioritys(@PageableDefault(page = 0, size = 100) Pageable pageRequest,
			@RequestParam(value = "search", required = false) String search) {

		Specification<Priority> spec = new PrioritySpecifications(search);
		Page<Priority> prioritys = priorityService.findAllPrioritysByPage(spec, pageRequest);

		if (prioritys.getTotalElements() == 0) {
			System.out.println("no prioritys");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Page<Priority>>(prioritys, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Priority------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/priority/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPriority(@PathVariable("id") int id) {
		logger.info("Fetching Priority with id {}", id);
		Priority priority = priorityService.findById(id);
		if (priority == null) {
			logger.error("Priority with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Priority with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Priority>(priority, HttpStatus.OK);
	}

	// -------------------Create a
	// Priority-------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/priority/", method = RequestMethod.POST)
	public ResponseEntity<?> createPriority(@RequestBody Priority priority, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Priority : {}", priority);

		if (priorityService.isPriorityExist(priority)) {
			logger.error("Unable to create. A Priority with name {} already exist", priority.getId());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A Priority with name " + priority.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		priority.setCreatedBy("sarath");
		priority.setUpdatedBy("sarath");
		priorityService.savePriority(priority);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/priority/{id}").buildAndExpand(priority.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Priority
	// ------------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/priority/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePriority(@PathVariable("id") int id, @RequestBody Priority priority,
			@ModelAttribute("username") String username){
		logger.info("Updating Priority with id {}", id);

		Priority currentPriority = priorityService.findById(id);

		if (currentPriority == null) {
			logger.error("Unable to update. Priority with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Priority with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currentPriority.setId(priority.getId());
		currentPriority.setCode(priority.getCode());
		currentPriority.setDescription(priority.getDescription());

		priorityService.updatePriority(currentPriority);
		return new ResponseEntity<Priority>(currentPriority, HttpStatus.OK);
	}

	// ------------------- Delete a
	// Priority-----------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/priority/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePriority(@PathVariable("id") int id,
			@ModelAttribute("username") String username){
		logger.info("Fetching & Deleting Priority with id {}", id);

		Priority priority = priorityService.findById(id);
		if (priority == null) {
			logger.error("Unable to delete. Priority with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Priority with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		priority.setActiveInd('N');
	    priority.setUpdatedBy(username);
		priorityService.updatePriority(priority);
		return new ResponseEntity<Priority>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Prioritys-----------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/priority/", method = RequestMethod.DELETE)
	public ResponseEntity<Priority> deleteAllPrioritys() {
		logger.info("Deleting All Prioritys");

		priorityService.deleteAllPrioritys();
		return new ResponseEntity<Priority>(HttpStatus.NO_CONTENT);
	}

}