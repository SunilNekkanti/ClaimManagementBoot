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
		extends PagingAndSortingRepository<Claim, Integer>, JpaSpecificationExecutor<Claim> , ClaimRepositoryCustom{

	Claim findById(Integer id);
	
	Claim findByLookup(String lookup);
	
	Claim findByClaimNo(Long claimNo);
	
	Claim findByServiceDate(Date serviceDate);
	
	Claim findByPatientName(String patientName);
	
	Claim findByInsId(Integer insId);
	
	Claim findByInsuranceType(Integer insuranceType);
	
	Claim findByCharges(Double charges);
	
	Claim findByClaimStatus(String claimStatus);
	
	Claim findByPriorityId(Integer priorityId);
	
	@Procedure("Claim.claimStatuses")
	List<ClaimDTO> getClaims(int pageNo,  int pageSize,  int teamAssigments,  String sSearch,  String allocationDate,  String sort,  String sortdir, 
			 String practices,  String remarks,  String srvcDtFrom,  String srvcDtTo,  String patientName,  String birthDate,	 String insurances, 
			 String insuranceTypes,  Double chargesMin,  Double chargesMax,  String claimStatus,  String priorities,	
			 String tableName,  int userId,	 int roleId);

}
