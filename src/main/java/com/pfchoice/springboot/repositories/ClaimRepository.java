package com.pfchoice.springboot.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfchoice.springboot.model.Claim;
import com.pfchoice.springboot.model.ClaimDTO;

@Repository
public interface ClaimRepository
		extends PagingAndSortingRepository<Claim, Integer>, JpaSpecificationExecutor<Claim> {

	public Claim findById(Integer id);
	
	public Claim findByLookup(String lookup);
	
	public Claim findByClaimNo(Long claimNo);
	
	public Claim findByServiceDate(Date serviceDate);
	
	public Claim findByPatientName(String patientName);
	
	public Claim findByInsId(Integer insId);
	
	public Claim findByInsuranceType(Integer insuranceType);
	
	public Claim findByCharges(Double charges);
	
	public Claim findByClaimStatus(String claimStatus);
	
	public Claim findByPriorityId(Integer priorityId);
	
	@Procedure("Claim.claimStatuses")
	List<ClaimDTO> getClaims(int pageNo,  int pageSize,  int teamAssigments,  String sSearch,  String allocationDate,  String sort,  String sortdir, 
			 String practices,  String remarks,  String srvcDtFrom,  String srvcDtTo,  String patientName,  String birthDate,	 String insurances, 
			 String insuranceTypes,  Double chargesMin,  Double chargesMax,  String claimStatus,  String priorities,	
			 String tableName,  Integer userId,	 Integer roleId);

}
