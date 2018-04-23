package com.pfchoice.springboot.repositories;

import org.springframework.data.domain.Page;

import com.pfchoice.springboot.model.ClaimDTO;

public interface ClaimRepositoryCustom {
	Page<ClaimDTO> getClaims(int pageNo,  int pageSize,  int teamAssigments,  String sSearch,  String allocationDate,  String sort,  String sortdir, 
			 String practices,  String remarks,  String srvcDtFrom,  String srvcDtTo,  String patientName,  String birthDate,	 String insurances, 
			 String insuranceTypes,  Double chargesMin,  Double chargesMax,  String claimStatus,  String priorities,	
			 String tableName,  Integer userId,	 Integer roleId);
}


    