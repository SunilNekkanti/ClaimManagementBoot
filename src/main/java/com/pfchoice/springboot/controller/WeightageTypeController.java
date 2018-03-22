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

import com.pfchoice.springboot.model.WeightageType;
import com.pfchoice.springboot.repositories.specifications.WeightageTypeSpecifications;
import com.pfchoice.springboot.service.WeightageTypeService;
import com.pfchoice.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WeightageTypeController {

	public static final Logger logger = LoggerFactory.getLogger(WeightageTypeController.class);

	@Autowired
	WeightageTypeService weightageTypeService; // Service which will do all data
											// retrieval/manipulation work

	// -------------------Retrieve All
	// WeightageTypes---------------------------------------------

	@Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_EVENT_COORDINATOR", "ROLE_CARE_COORDINATOR", "ROLE_MANAGER" })
	@RequestMapping(value = "/weightageType/", method = RequestMethod.GET)
	public ResponseEntity<?> listAllWeightageTypes(@PageableDefault(page = 0, size = 100) Pageable pageRequest,
			@RequestParam(value = "search", required = false) String search) {

		Specification<WeightageType> spec = new WeightageTypeSpecifications(search);
		Page<WeightageType> weightageTypes = weightageTypeService.findAllWeightageTypesByPage(spec, pageRequest);

		if (weightageTypes.getTotalElements() == 0) {
			System.out.println("no weightageTypes");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Page<WeightageType>>(weightageTypes, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// WeightageType------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/weightageType/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getWeightageType(@PathVariable("id") int id) {
		logger.info("Fetching WeightageType with id {}", id);
		WeightageType weightageType = weightageTypeService.findById(id);
		if (weightageType == null) {
			logger.error("WeightageType with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("WeightageType with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<WeightageType>(weightageType, HttpStatus.OK);
	}

	// -------------------Create a
	// WeightageType-------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/weightageType/", method = RequestMethod.POST)
	public ResponseEntity<?> createWeightageType(@RequestBody WeightageType weightageType, UriComponentsBuilder ucBuilder) {
		logger.info("Creating WeightageType : {}", weightageType);

		if (weightageTypeService.isWeightageTypeExist(weightageType)) {
			logger.error("Unable to create. A WeightageType with name {} already exist", weightageType.getId());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A WeightageType with name " + weightageType.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		weightageType.setCreatedBy("sarath");
		weightageType.setUpdatedBy("sarath");
		weightageTypeService.saveWeightageType(weightageType);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/weightageType/{id}").buildAndExpand(weightageType.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a WeightageType
	// ------------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/weightageType/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateWeightageType(@PathVariable("id") int id, @RequestBody WeightageType weightageType,
			@ModelAttribute("username") String username){
		logger.info("Updating WeightageType with id {}", id);

		WeightageType currentWeightageType = weightageTypeService.findById(id);

		if (currentWeightageType == null) {
			logger.error("Unable to update. WeightageType with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. WeightageType with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentWeightageType.setWeightagePercent(weightageType.getWeightagePercent());
		currentWeightageType.setDescription(weightageType.getDescription());

		weightageTypeService.updateWeightageType(currentWeightageType);
		return new ResponseEntity<WeightageType>(currentWeightageType, HttpStatus.OK);
	}

	// ------------------- Delete a
	// WeightageType-----------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/weightageType/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteWeightageType(@PathVariable("id") int id,
			@ModelAttribute("username") String username){
		logger.info("Fetching & Deleting WeightageType with id {}", id);

		WeightageType weightageType = weightageTypeService.findById(id);
		if (weightageType == null) {
			logger.error("Unable to delete. WeightageType with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. WeightageType with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		weightageType.setActiveInd('N');
		weightageType.setUpdatedBy(username);
		weightageTypeService.updateWeightageType(weightageType);
		return new ResponseEntity<WeightageType>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All WeightageTypes-----------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/weightageType/", method = RequestMethod.DELETE)
	public ResponseEntity<WeightageType> deleteAllWeightageTypes() {
		logger.info("Deleting All WeightageTypes");

		weightageTypeService.deleteAllWeightageTypes();
		return new ResponseEntity<WeightageType>(HttpStatus.NO_CONTENT);
	}

}