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

import com.pfchoice.springboot.model.ProviderInsuranceDetails;
import com.pfchoice.springboot.repositories.specifications.ProviderInsuranceDetailsSpecifications;
import com.pfchoice.springboot.service.ProviderInsuranceDetailsService;
import com.pfchoice.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProviderInsuranceDetailsController {

	public static final Logger logger = LoggerFactory.getLogger(ProviderInsuranceDetailsController.class);

	@Autowired
	ProviderInsuranceDetailsService providerInsuranceDetailsService; // Service which will do all data
											// retrieval/manipulation work

	// -------------------Retrieve All
	// ProviderInsuranceDetailss---------------------------------------------

	@Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_EVENT_COORDINATOR", "ROLE_CARE_COORDINATOR", "ROLE_MANAGER" })
	@RequestMapping(value = "/providerInsuranceDetails/", method = RequestMethod.GET)
	public ResponseEntity<?> listAllProviderInsuranceDetailss(@PageableDefault(page = 0, size = 100) Pageable pageRequest,
			@RequestParam(value = "search", required = false) String search) {

		Specification<ProviderInsuranceDetails> spec = new ProviderInsuranceDetailsSpecifications(search);
		Page<ProviderInsuranceDetails> providerInsuranceDetailss = providerInsuranceDetailsService.findAllProviderInsuranceDetailssByPage(spec, pageRequest);

		if (providerInsuranceDetailss.getTotalElements() == 0) {
			System.out.println("no providerInsuranceDetailss");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Page<ProviderInsuranceDetails>>(providerInsuranceDetailss, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// ProviderInsuranceDetails------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/providerInsuranceDetails/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProviderInsuranceDetails(@PathVariable("id") int id) {
		logger.info("Fetching ProviderInsuranceDetails with id {}", id);
		ProviderInsuranceDetails providerInsuranceDetails = providerInsuranceDetailsService.findById(id);
		if (providerInsuranceDetails == null) {
			logger.error("ProviderInsuranceDetails with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("ProviderInsuranceDetails with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProviderInsuranceDetails>(providerInsuranceDetails, HttpStatus.OK);
	}

	// -------------------Create a
	// ProviderInsuranceDetails-------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/providerInsuranceDetails/", method = RequestMethod.POST)
	public ResponseEntity<?> createProviderInsuranceDetails(@RequestBody ProviderInsuranceDetails providerInsuranceDetails, UriComponentsBuilder ucBuilder) {
		logger.info("Creating ProviderInsuranceDetails : {}", providerInsuranceDetails);

		if (providerInsuranceDetailsService.isProviderInsuranceDetailsExist(providerInsuranceDetails)) {
			logger.error("Unable to create. A ProviderInsuranceDetails with name {} already exist", providerInsuranceDetails.getId());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A ProviderInsuranceDetails with name " + providerInsuranceDetails.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		providerInsuranceDetails.setCreatedBy("sarath");
		providerInsuranceDetails.setUpdatedBy("sarath");
		providerInsuranceDetailsService.saveProviderInsuranceDetails(providerInsuranceDetails);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/providerInsuranceDetails/{id}").buildAndExpand(providerInsuranceDetails.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a ProviderInsuranceDetails
	// ------------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/providerInsuranceDetails/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProviderInsuranceDetails(@PathVariable("id") int id, @RequestBody ProviderInsuranceDetails providerInsuranceDetails,
			@ModelAttribute("username") String username){
		logger.info("Updating ProviderInsuranceDetails with id {}", id);

		ProviderInsuranceDetails currentProviderInsuranceDetails = providerInsuranceDetailsService.findById(id);

		if (currentProviderInsuranceDetails == null) {
			logger.error("Unable to update. ProviderInsuranceDetails with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. ProviderInsuranceDetails with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currentProviderInsuranceDetails.setId(providerInsuranceDetails.getId());
		currentProviderInsuranceDetails.setPrvdr(providerInsuranceDetails.getPrvdr());
		currentProviderInsuranceDetails.setIns(providerInsuranceDetails.getIns());
		currentProviderInsuranceDetails.setPrac(providerInsuranceDetails.getPrac());
		currentProviderInsuranceDetails.setRole(providerInsuranceDetails.getRole());
		currentProviderInsuranceDetails.setUrl(providerInsuranceDetails.getUrl());
		currentProviderInsuranceDetails.setUrlUserName(providerInsuranceDetails.getUrlUserName());
		currentProviderInsuranceDetails.setUrlPassword(providerInsuranceDetails.getUrlPassword());
		currentProviderInsuranceDetails.setUrlPasswordActive(providerInsuranceDetails.getUrlPasswordActive());
		currentProviderInsuranceDetails.setIsClearingHouse(providerInsuranceDetails.getIsClearingHouse());
		
		providerInsuranceDetailsService.updateProviderInsuranceDetails(currentProviderInsuranceDetails);
		return new ResponseEntity<ProviderInsuranceDetails>(currentProviderInsuranceDetails, HttpStatus.OK);
	}

	// ------------------- Delete a
	// ProviderInsuranceDetails-----------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/providerInsuranceDetails/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProviderInsuranceDetails(@PathVariable("id") int id,
			@ModelAttribute("username") String username){
		logger.info("Fetching & Deleting ProviderInsuranceDetails with id {}", id);

		ProviderInsuranceDetails providerInsuranceDetails = providerInsuranceDetailsService.findById(id);
		if (providerInsuranceDetails == null) {
			logger.error("Unable to delete. ProviderInsuranceDetails with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. ProviderInsuranceDetails with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		providerInsuranceDetails.setActiveInd('N');
		providerInsuranceDetails.setUpdatedBy(username);
		providerInsuranceDetailsService.updateProviderInsuranceDetails(providerInsuranceDetails);
		return new ResponseEntity<ProviderInsuranceDetails>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All ProviderInsuranceDetailss-----------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/providerInsuranceDetails/", method = RequestMethod.DELETE)
	public ResponseEntity<ProviderInsuranceDetails> deleteAllProviderInsuranceDetailss() {
		logger.info("Deleting All ProviderInsuranceDetailss");

		providerInsuranceDetailsService.deleteAllProviderInsuranceDetailss();
		return new ResponseEntity<ProviderInsuranceDetails>(HttpStatus.NO_CONTENT);
	}

}