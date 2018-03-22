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

import com.pfchoice.springboot.model.Weightage;
import com.pfchoice.springboot.repositories.specifications.WeightageSpecifications;
import com.pfchoice.springboot.service.WeightageService;
import com.pfchoice.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WeightageController {

	public static final Logger logger = LoggerFactory.getLogger(WeightageController.class);

	@Autowired
	WeightageService weightageService; // Service which will do all data
											// retrieval/manipulation work

	// -------------------Retrieve All
	// Weightages---------------------------------------------

	@Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_EVENT_COORDINATOR", "ROLE_CARE_COORDINATOR", "ROLE_MANAGER" })
	@RequestMapping(value = "/weightage/", method = RequestMethod.GET)
	public ResponseEntity<?> listAllWeightages(@PageableDefault(page = 0, size = 100) Pageable pageRequest,
			@RequestParam(value = "search", required = false) String search) {

		Specification<Weightage> spec = new WeightageSpecifications(search);
		Page<Weightage> weightages = weightageService.findAllWeightagesByPage(spec, pageRequest);

		if (weightages.getTotalElements() == 0) {
			System.out.println("no weightages");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Page<Weightage>>(weightages, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Weightage------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/weightage/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getWeightage(@PathVariable("id") int id) {
		logger.info("Fetching Weightage with id {}", id);
		Weightage weightage = weightageService.findById(id);
		if (weightage == null) {
			logger.error("Weightage with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Weightage with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Weightage>(weightage, HttpStatus.OK);
	}

	// -------------------Create a
	// Weightage-------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/weightage/", method = RequestMethod.POST)
	public ResponseEntity<?> createWeightage(@RequestBody Weightage weightage, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Weightage : {}", weightage);

		if (weightageService.isWeightageExist(weightage)) {
			logger.error("Unable to create. A Weightage with name {} already exist", weightage.getId());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A Weightage with name " + weightage.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		weightage.setCreatedBy("sarath");
		weightage.setUpdatedBy("sarath");
		weightageService.saveWeightage(weightage);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/weightage/{id}").buildAndExpand(weightage.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Weightage
	// ------------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/weightage/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateWeightage(@PathVariable("id") int id, @RequestBody Weightage weightage,
			@ModelAttribute("username") String username){
		logger.info("Updating Weightage with id {}", id);

		Weightage currentWeightage = weightageService.findById(id);

		if (currentWeightage == null) {
			logger.error("Unable to update. Weightage with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Weightage with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currentWeightage.setConditionExp(weightage.getConditionExp());
		currentWeightage.setPercentage(weightage.getPercentage());
		currentWeightage.setWtageType(weightage.getWtageType());

		weightageService.updateWeightage(currentWeightage);
		return new ResponseEntity<Weightage>(currentWeightage, HttpStatus.OK);
	}

	// ------------------- Delete a
	// Weightage-----------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/weightage/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteWeightage(@PathVariable("id") int id,
			@ModelAttribute("username") String username){
		logger.info("Fetching & Deleting Weightage with id {}", id);

		Weightage weightage = weightageService.findById(id);
		if (weightage == null) {
			logger.error("Unable to delete. Weightage with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Weightage with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		weightage.setActiveInd('N');
		weightage.setUpdatedBy(username);
		weightageService.updateWeightage(weightage);
		return new ResponseEntity<Weightage>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Weightages-----------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/weightage/", method = RequestMethod.DELETE)
	public ResponseEntity<Weightage> deleteAllWeightages() {
		logger.info("Deleting All Weightages");

		weightageService.deleteAllWeightages();
		return new ResponseEntity<Weightage>(HttpStatus.NO_CONTENT);
	}

}