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

import com.pfchoice.springboot.model.MappingInsurance;
import com.pfchoice.springboot.repositories.specifications.MappingInsuranceSpecifications;
import com.pfchoice.springboot.service.MappingInsuranceService;
import com.pfchoice.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MappingInsuranceController {

	public static final Logger logger = LoggerFactory.getLogger(MappingInsuranceController.class);

	@Autowired
	MappingInsuranceService mappingInsuranceService; // Service which will do all data
										// retrieval/manipulation work

	// -------------------Retrieve All
	// MappingInsurances---------------------------------------------

	@Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_EVENT_COORDINATOR", "ROLE_CARE_COORDINATOR", "ROLE_MANAGER" })
	@RequestMapping(value = "/mappingInsurance/", method = RequestMethod.GET)
	public ResponseEntity<?> listAllMappingInsurances(@PageableDefault(page = 0, size = 100) Pageable pageRequest,
			@RequestParam(value = "search", required = false) String search) {

		Specification<MappingInsurance> spec = new MappingInsuranceSpecifications(search);
		Page<MappingInsurance> mappingInsurances = mappingInsuranceService.findAllMappingInsurancesByPage(spec, pageRequest);

		if (mappingInsurances.getTotalElements() == 0) {
			System.out.println("no mappingInsurances");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Page<MappingInsurance>>(mappingInsurances, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// MappingInsurance------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/mappingInsurance/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMappingInsurance(@PathVariable("id") int id) {
		logger.info("Fetching MappingInsurance with id {}", id);
		MappingInsurance mappingInsurance = mappingInsuranceService.findById(id);
		if (mappingInsurance == null) {
			logger.error("MappingInsurance with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("MappingInsurance with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MappingInsurance>(mappingInsurance, HttpStatus.OK);
	}

	// -------------------Create a
	// MappingInsurance-------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/mappingInsurance/", method = RequestMethod.POST)
	public ResponseEntity<?> createMappingInsurance(@RequestBody MappingInsurance mappingInsurance, UriComponentsBuilder ucBuilder) {
		logger.info("Creating MappingInsurance : {}", mappingInsurance);

		if (mappingInsuranceService.isMappingInsuranceExist(mappingInsurance)) {
			logger.error("Unable to create. A MappingInsurance with name {} already exist", mappingInsurance.getId());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A MappingInsurance with name " + mappingInsurance.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		mappingInsurance.setCreatedBy("sarath");
		mappingInsurance.setUpdatedBy("sarath");
		mappingInsuranceService.saveMappingInsurance(mappingInsurance);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/mappingInsurance/{id}").buildAndExpand(mappingInsurance.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a MappingInsurance
	// ------------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/mappingInsurance/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateMappingInsurance(@PathVariable("id") int id, @RequestBody MappingInsurance mappingInsurance,
			@ModelAttribute("username") String username){
		logger.info("Updating MappingInsurance with id {}", id);

		MappingInsurance currentMappingInsurance = mappingInsuranceService.findById(id);

		if (currentMappingInsurance == null) {
			logger.error("Unable to update. MappingInsurance with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. MappingInsurance with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currentMappingInsurance.setName(mappingInsurance.getName());
		currentMappingInsurance.setId(mappingInsurance.getId());
		mappingInsuranceService.updateMappingInsurance(currentMappingInsurance);
		return new ResponseEntity<MappingInsurance>(currentMappingInsurance, HttpStatus.OK);
	}

	// ------------------- Delete a
	// MappingInsurance-----------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/mappingInsurance/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMappingInsurance(@PathVariable("id") int id,
			@ModelAttribute("username") String username){
		logger.info("Fetching & Deleting MappingInsurance with id {}", id);

		MappingInsurance mappingInsurance = mappingInsuranceService.findById(id);
		if (mappingInsurance == null) {
			logger.error("Unable to delete. MappingInsurance with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. MappingInsurance with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		mappingInsurance.setActiveInd('N');
		mappingInsurance.setUpdatedBy(username);
		mappingInsuranceService.updateMappingInsurance(mappingInsurance);
		return new ResponseEntity<MappingInsurance>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All MappingInsurances-----------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/mappingInsurance/", method = RequestMethod.DELETE)
	public ResponseEntity<MappingInsurance> deleteAllMappingInsurances() {
		logger.info("Deleting All MappingInsurances");

		mappingInsuranceService.deleteAllMappingInsurances();
		return new ResponseEntity<MappingInsurance>(HttpStatus.NO_CONTENT);
	}

}