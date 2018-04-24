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

import com.pfchoice.springboot.model.InsuranceType;
import com.pfchoice.springboot.repositories.specifications.InsuranceTypeSpecifications;
import com.pfchoice.springboot.service.InsuranceTypeService;
import com.pfchoice.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class InsuranceTypeController {

	public static final Logger logger = LoggerFactory.getLogger(InsuranceTypeController.class);

	@Autowired
	InsuranceTypeService insuranceTypeService; // Service which will do all data
											// retrieval/manipulation work

	// -------------------Retrieve All
	// InsuranceTypes---------------------------------------------

	@Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_EVENT_COORDINATOR", "ROLE_CARE_COORDINATOR", "ROLE_MANAGER" })
	@RequestMapping(value = "/insuranceType/", method = RequestMethod.GET)
	public ResponseEntity<?> listAllInsuranceTypes(@PageableDefault(page = 0, size = 100) Pageable pageRequest,
			@RequestParam(value = "search", required = false) String search) {

		Specification<InsuranceType> spec = new InsuranceTypeSpecifications(search);
		Page<InsuranceType> insuranceTypes = insuranceTypeService.findAllInsuranceTypesByPage(spec, pageRequest);

		if (insuranceTypes.getTotalElements() == 0) {
			System.out.println("no insuranceTypes");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Page<InsuranceType>>(insuranceTypes, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// InsuranceType------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/insuranceType/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getInsuranceType(@PathVariable("id") int id) {
		logger.info("Fetching InsuranceType with id {}", id);
		InsuranceType insuranceType = insuranceTypeService.findById(id);
		if (insuranceType == null) {
			logger.error("InsuranceType with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("InsuranceType with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<InsuranceType>(insuranceType, HttpStatus.OK);
	}

	// -------------------Create a
	// InsuranceType-------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/insuranceType/", method = RequestMethod.POST)
	public ResponseEntity<?> createInsuranceType(@RequestBody InsuranceType insuranceType, UriComponentsBuilder ucBuilder) {
		logger.info("Creating InsuranceType : {}", insuranceType);

		if (insuranceTypeService.isInsuranceTypeExist(insuranceType)) {
			logger.error("Unable to create. A InsuranceType with name {} already exist", insuranceType.getId());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A InsuranceType with name " + insuranceType.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		insuranceType.setCreatedBy("sarath");
		insuranceType.setUpdatedBy("sarath");
		insuranceTypeService.saveInsuranceType(insuranceType);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/insuranceType/{id}").buildAndExpand(insuranceType.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a InsuranceType
	// ------------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/insuranceType/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateInsuranceType(@PathVariable("id") int id, @RequestBody InsuranceType insuranceType,
			@ModelAttribute("username") String username){
		logger.info("Updating InsuranceType with id {}", id);

		InsuranceType currentInsuranceType = insuranceTypeService.findById(id);

		if (currentInsuranceType == null) {
			logger.error("Unable to update. InsuranceType with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. InsuranceType with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		
		currentInsuranceType.setDescription(insuranceType.getDescription());
		insuranceTypeService.updateInsuranceType(currentInsuranceType);
		return new ResponseEntity<InsuranceType>(currentInsuranceType, HttpStatus.OK);
	}

	// ------------------- Delete a
	// InsuranceType-----------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/insuranceType/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteInsuranceType(@PathVariable("id") int id,
			@ModelAttribute("username") String username){
		logger.info("Fetching & Deleting InsuranceType with id {}", id);

		InsuranceType insuranceType = insuranceTypeService.findById(id);
		if (insuranceType == null) {
			logger.error("Unable to delete. InsuranceType with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. InsuranceType with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		insuranceType.setActiveInd('N');
		insuranceType.setUpdatedBy(username);
		insuranceTypeService.updateInsuranceType(insuranceType);
		return new ResponseEntity<InsuranceType>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All InsuranceTypes-----------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/insuranceType/", method = RequestMethod.DELETE)
	public ResponseEntity<InsuranceType> deleteAllInsuranceTypes() {
		logger.info("Deleting All InsuranceTypes");

		insuranceTypeService.deleteAllInsuranceTypes();
		return new ResponseEntity<InsuranceType>(HttpStatus.NO_CONTENT);
	}

}