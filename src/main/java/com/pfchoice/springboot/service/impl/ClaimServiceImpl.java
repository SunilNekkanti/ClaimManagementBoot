package com.pfchoice.springboot.service.impl;

import java.util.Date;
import java.util.List;

import com.pfchoice.springboot.model.Claim;
import com.pfchoice.springboot.model.ClaimDTO;
import com.pfchoice.springboot.repositories.ClaimRepository;
import com.pfchoice.springboot.service.ClaimService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("claimService")
@Transactional
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	private ClaimRepository claimRepository;

	public Claim findById(Integer id) {
		return claimRepository.findOne(id);
	}
    
	

	public void saveClaim(Claim claim) {
		claimRepository.save(claim);
	}

	public void updateClaim(Claim claim) {
		saveClaim(claim);
	}

	public void deleteClaimById(Integer id) {
		claimRepository.delete(id);
	}

	public void deleteAllClaims() {
		claimRepository.deleteAll();
	}

	public List<Claim> findAllClaims() {
		return (List<Claim>) claimRepository.findAll();
	}

	public Page<Claim> findAllClaimsByPage(Specification<Claim> spec, Pageable pageable) {
		return claimRepository.findAll(spec, pageable);
	}

	public boolean isClaimExist(Claim claim) {
		return findByClaimNo(claim.getClaimNo()) != null;
	}



	@Override
	public Claim findByClaimNo(Long claimNo) {
		// TODO Auto-generated method stub
		return claimRepository.findByClaimNo(claimNo);
	}



	@Override
	public Claim findByLookup(String lookup) {
		// TODO Auto-generated method stub
		return claimRepository.findByLookup(lookup);
	}



	@Override
	public Claim findByServiceDate(Date serviceDate) {
		// TODO Auto-generated method stub
		return claimRepository.findByServiceDate(serviceDate);
	}



	@Override
	public Claim findByPatientName(String patientName) {
		// TODO Auto-generated method stub
		return claimRepository.findByPatientName(patientName);
	}



	@Override
	public Claim findByInsId(Integer insId) {
		// TODO Auto-generated method stub
		return claimRepository.findByInsId(insId);
	}



	@Override
	public Claim findByInsuranceType(Integer insuranceType) {
		// TODO Auto-generated method stub
		return claimRepository.findByInsuranceType(insuranceType);
	}



	@Override
	public Claim findByCharges(Double charges) {
		// TODO Auto-generated method stub
		return claimRepository.findByCharges(charges);
	}



	@Override
	public Claim findByClaimStatus(String claimStatus) {
		// TODO Auto-generated method stub
		return claimRepository.findByClaimStatus(claimStatus);
	}

	@Override
	public Claim findByPriorityId(Integer priorityId) {
		// TODO Auto-generated method stub
		return claimRepository.findByPriorityId(priorityId);
	}
	
	@Override
	//@Cacheable("claims")
	public Page<ClaimDTO> getClaims(final int pageNo, final int pageSize, final int teamAssigments, final String sSearch,final String allocationDate,final String sort, final String sortdir,
			final String practices, final String remarks, final String srvcDtFrom,final  String srvcDtTo,final String patientName, final String birthDate,final	String insurances, 
			final String insuranceTypes,final Double chargesMin,final Double chargesMax,final String claimStatus,final String priorities,final	String userName, final Integer userId, final Integer roleId){
		
		return claimRepository.getClaims( pageNo, pageSize, teamAssigments, sSearch, allocationDate, sort,  sortdir,
				 practices, remarks, srvcDtFrom, srvcDtTo, patientName, birthDate,	insurances, 
				 insuranceTypes, chargesMin, chargesMax, claimStatus, priorities, userName, userId, roleId);
	}


}


 