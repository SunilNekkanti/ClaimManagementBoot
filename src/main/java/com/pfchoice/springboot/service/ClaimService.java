package com.pfchoice.springboot.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.Claim;
import com.pfchoice.springboot.model.ClaimDTO;

public interface ClaimService {

	Claim findById(Integer id);
	
	Claim findByClaimNo(Long claimNo);
	
	Claim findByLookup(String lookup);
	
	Claim findByServiceDate(Date serviceDate);
	
	Claim findByPatientName(String patientName);
	
	Claim findByInsId(Integer insId);
	
	Claim findByInsuranceType(Integer insuranceType);
	
	Claim findByCharges(Double charges);
	
	Claim findByClaimStatus(String claimStatus);
	
	Claim findByPriorityId(Integer priorityId);

	void saveClaim(Claim claim);

	void updateClaim(Claim claim);

	void deleteClaimById(Integer id);

	void deleteAllClaims();

	List<Claim> findAllClaims();

	Page<Claim> findAllClaimsByPage(Specification<Claim> spec, Pageable pageable);

	boolean isClaimExist(Claim claim);
	
	Page<ClaimDTO> getClaims(int pageNo, int pageSize, int teamAssigments, String sSearch, String allocationDate, String sort, String sortdir,
			String practices, String remarks, String srvcDtFrom, String srvcDtTo, String patientName, String birthDate,	String insurances, 
			String insuranceTypes, Double chargesMin, Double chargesMax, String claimStatus, String priorities,	String userName, Integer userId, Integer roleId);

}