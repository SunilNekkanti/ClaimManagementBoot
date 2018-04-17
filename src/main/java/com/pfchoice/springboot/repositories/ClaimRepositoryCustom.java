package com.pfchoice.springboot.repositories;

import java.util.List;

import com.pfchoice.springboot.model.ClaimDTO;

public interface ClaimRepositoryCustom {
	List<ClaimDTO> getClaims(int pageNo,  int pageSize,  int teamAssigments,  String sSearch,  String allocationDate,  String sort,  String sortdir, 
			 String practices,  String remarks,  String srvcDtFrom,  String srvcDtTo,  String patientName,  String birthDate,	 String insurances, 
			 String insuranceTypes,  Double chargesMin,  Double chargesMax,  String claimStatus,  String priorities,	
			 String tableName,  Integer userId,	 Integer roleId);
}


    