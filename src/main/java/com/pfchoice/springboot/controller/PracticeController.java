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

import com.pfchoice.springboot.model.Practice;
import com.pfchoice.springboot.repositories.specifications.PracticeSpecifications;
import com.pfchoice.springboot.service.PracticeService;
import com.pfchoice.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
@SessionAttributes({ "username", "roleId", "userId", "roleName" })
public class PracticeController {

	public static final Logger logger = LoggerFactory.getLogger(PracticeController.class);

	@Autowired
	PracticeService practiceService; // Service which will do all data
											// retrieval/manipulation work

	// -------------------Retrieve All
	// Practices---------------------------------------------

	@Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_EVENT_COORDINATOR", "ROLE_CARE_COORDINATOR", "ROLE_MANAGER" })
	@RequestMapping(value = "/practice/", method = RequestMethod.GET)
	public ResponseEntity<?> listAllPractices(@PageableDefault(page = 0, size = 100) Pageable pageRequest,
			@RequestParam(value = "search", required = false) String search) {

		Specification<Practice> spec = new PracticeSpecifications(search);
		Page<Practice> practices = practiceService.findAllPracticesByPage(spec, pageRequest);

		if (practices.getTotalElements() == 0) {
			System.out.println("no practices");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Page<Practice>>(practices, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Practice------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/practice/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPractice(@PathVariable("id") int id) {
		logger.info("Fetching Practice with id {}", id);
		Practice practice = practiceService.findById(id);
		if (practice == null) {
			logger.error("Practice with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Practice with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Practice>(practice, HttpStatus.OK);
	}

	// -------------------Create a
	// Practice-------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/practice/", method = RequestMethod.POST)
	public ResponseEntity<?> createPractice(@RequestBody Practice practice, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Practice : {}", practice);

		if (practiceService.isPracticeExist(practice)) {
			logger.error("Unable to create. A Practice with name {} already exist", practice.getId());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A Practice with name " + practice.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		practice.setCreatedBy("sarath");
		practice.setUpdatedBy("sarath");
		practiceService.savePractice(practice);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/practice/{id}").buildAndExpand(practice.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Practice
	// ------------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/practice/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePractice(@PathVariable("id") int id, @RequestBody Practice practice,
			@ModelAttribute("username") String username){
		logger.info("Updating Practice with id {}", id);

		Practice currentPractice = practiceService.findById(id);

		if (currentPractice == null) {
			logger.error("Unable to update. Practice with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Practice with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currentPractice.setId(practice.getId());
		currentPractice.setShortName(practice.getShortName());
		currentPractice.setName(practice.getName());
		currentPractice.setNpi(practice.getNpi());
		currentPractice.setClearingHouseURL(practice.getClearingHouseURL());
		currentPractice.setUserName(practice.getUserName());
		currentPractice.setPassword(practice.getPassword());

		practiceService.updatePractice(currentPractice);
		return new ResponseEntity<Practice>(currentPractice, HttpStatus.OK);
	}

	// ------------------- Delete a
	// Practice-----------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/practice/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePractice(@PathVariable("id") int id,
			@ModelAttribute("username") String username){
		logger.info("Fetching & Deleting Practice with id {} ", id);
		

		Practice practice = practiceService.findById(id);
		if (practice == null) {
			logger.error("Unable to delete. Practice with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Practice with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		practice.setActiveInd(new Character('N'));
		practice.setUpdatedBy(username);
		practiceService.updatePractice(practice);
		return new ResponseEntity<Practice>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Practices-----------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/practice/", method = RequestMethod.DELETE)
	public ResponseEntity<Practice> deleteAllPractices() {
		logger.info("Deleting All Practices");

		practiceService.deleteAllPractices();
		return new ResponseEntity<Practice>(HttpStatus.NO_CONTENT);
	}

}