package com.pfchoice.springboot.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import com.pfchoice.springboot.model.Claim;
import com.pfchoice.springboot.model.ClaimDTO;
import com.pfchoice.springboot.service.ClaimService;
import com.pfchoice.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
@SessionAttributes({ "username", "roleId", "userId", "roleName" })
public class ClaimController {

	public static final Logger logger = LoggerFactory.getLogger(ClaimController.class);

	@Autowired
	ClaimService claimService; // Service which will do all data
											// retrieval/manipulation work

	// -------------------Retrieve All
	// Claims---------------------------------------------

	@Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_EVENT_COORDINATOR", "ROLE_CARE_COORDINATOR", "ROLE_MANAGER" })
	@RequestMapping(value = "/claim/", method = RequestMethod.GET)
	public ResponseEntity<?> listAllClaims(@PageableDefault(page = 0, size = 100) Pageable pageRequest,
			@RequestParam(value = "teamAssignments", required = true) Integer teamAssignments,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "allocationDate", required = false) String allocationDate,
			@RequestParam(value = "practices", required = false) String practices,
			@RequestParam(value = "remarks", required = false) String remarks,
			@RequestParam(value = "srvcDtFrom", required = false) String srvcDtFrom,
			@RequestParam(value = "srvcDtTo", required = false) String srvcDtTo,
			@RequestParam(value = "patientName", required = false) String patientName,
			@RequestParam(value = "birthDate", required = false) String birthDate,
			@RequestParam(value = "insurances", required = false) String insurances,
			@RequestParam(value = "insuranceTypes", required = false) String insuranceTypes,
			@RequestParam(value = "chargesMin", required = false) Double chargesMin,
			@RequestParam(value = "chargesMax", required = false) Double chargesMax,
			@RequestParam(value = "claimStatus", required = false) String claimStatus,
			@RequestParam(value = "priorities", required = false) String priorities,
			@ModelAttribute("username") String username, @ModelAttribute("userId") Integer userId,
			@ModelAttribute("roleId") Integer roleId) {


		/* String sortCol = "";
		 String sortDir = "";
		 for (Sort.Order order : pageRequest.getSort()) {
		         sortCol =  order.getProperty();
		         sortDir = order.getDirection().toString() ;
		    }*/
		 
		List<ClaimDTO> claims = claimService.getClaims(pageRequest.getOffset(), pageRequest.getPageSize(),teamAssignments, search, allocationDate, 
				"" , "",  practices, remarks, srvcDtFrom, srvcDtTo, patientName, birthDate,	insurances, 
				  insuranceTypes, chargesMin, chargesMax, claimStatus, priorities,	
				  username,   userId,	  roleId);
	
		if (claims.size() == 0) {
			System.out.println("no claims");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<ClaimDTO>>(claims, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Claim------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/claim/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getClaim(@PathVariable("id") int id) {
		logger.info("Fetching Claim with id {}", id);
		Claim claim = claimService.findById(id);
		if (claim == null) {
			logger.error("Claim with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Claim with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Claim>(claim, HttpStatus.OK);
	}

	// -------------------Create a
	// Claim-------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/claim/", method = RequestMethod.POST)
	public ResponseEntity<?> createClaim(@RequestBody Claim claim, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Claim : {}", claim);

		if (claimService.isClaimExist(claim)) {
			logger.error("Unable to create. A Claim with name {} already exist", claim.getId());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A Claim with name " + claim.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		claim.setCreatedBy("sarath");
		claim.setUpdatedBy("sarath");
		claimService.saveClaim(claim);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/claim/{id}").buildAndExpand(claim.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Claim
	// ------------------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/claim/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateClaim(@PathVariable("id") int id, @RequestBody Claim claim,
			@ModelAttribute("username") String username){
		logger.info("Updating Claim with id {}", id);

		Claim currentClaim = claimService.findById(id);

		if (currentClaim == null) {
			logger.error("Unable to update. Claim with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Claim with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		
		currentClaim.setId(claim.getId());
		currentClaim.setLookup(claim.getLookup());
		currentClaim.setServiceDate(claim.getServiceDate());
		currentClaim.setPatientName(claim.getPatientName());
		currentClaim.setDob(claim.getDob());
		currentClaim.setInsId(claim.getInsId());
		currentClaim.setInsuranceType(claim.getInsuranceType());
		currentClaim.setCharges(claim.getCharges());
		currentClaim.setClaimStatusDetailId(claim.getClaimStatusDetailId());
		currentClaim.setClaimStatus(claim.getClaimStatus());
		currentClaim.setPriorityId(claim.getPriorityId());
		//currentClaim.setLookup(claim.getLookup());
		claimService.updateClaim(currentClaim);
		return new ResponseEntity<Claim>(currentClaim, HttpStatus.OK);
	}

	// ------------------- Delete a
	// Claim-----------------------------------------
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
	@RequestMapping(value = "/claim/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteClaim(@PathVariable("id") int id,
			@ModelAttribute("username") String username){
		logger.info("Fetching & Deleting Claim with id {}", id);

		Claim claim = claimService.findById(id);
		if (claim == null) {
			logger.error("Unable to delete. Claim with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Claim with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		claim.setActiveInd('N');
		claim.setUpdatedBy(username);
		claimService.updateClaim(claim);
		return new ResponseEntity<Claim>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Claims-----------------------------
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/claim/", method = RequestMethod.DELETE)
	public ResponseEntity<Claim> deleteAllClaims() {
		logger.info("Deleting All Claims");

		claimService.deleteAllClaims();
		return new ResponseEntity<Claim>(HttpStatus.NO_CONTENT);
	}

}