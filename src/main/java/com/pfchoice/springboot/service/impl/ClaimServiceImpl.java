package com.pfchoice.springboot.service.impl;

import java.util.List;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;

import com.pfchoice.springboot.model.Claim;
import com.pfchoice.springboot.model.ClaimDTO;
import com.pfchoice.springboot.repositories.ClaimRepository;
import com.pfchoice.springboot.service.ClaimService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("claimService")
@CacheDefaults(cacheName = "claims")
@Transactional
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	private ClaimRepository claimRepository;

	@CacheResult
	public Claim findById(Integer id) {
		return claimRepository.findOne(id);
	}
    
	@CachePut
	public void saveClaim(Claim claim) {
		claimRepository.save(claim);
	}

	@CachePut
	public void updateClaim(Claim claim) {
		saveClaim(claim);
	}

	@CacheRemove
	public void deleteClaimById(Integer id) {
		claimRepository.delete(id);
	}

	@CacheRemoveAll
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
		return claimRepository.findByClaimNo(claimNo);
	}

	@Override
	public Claim findByLookup(String lookup) {
		return claimRepository.findByLookup(lookup);
	}

	@Override
	@CacheResult(cacheName="claims")
	public Page<ClaimDTO> getClaims(final int pageNo, final int pageSize, final int teamAssigments, final String sSearch,final String allocationDate,final String sort, final String sortdir,
			final String practices, final String remarks, final String srvcDtFrom,final  String srvcDtTo,final String patientName, final String birthDate,final	String insurances, 
			final String insuranceTypes,final Double chargesMin,final Double chargesMax,final String claimStatus,final String priorities,final	String userName, final Integer userId, final Integer roleId){
		
		System.out.println("inside claims cache");
		return claimRepository.getClaims( pageNo, pageSize, teamAssigments, sSearch, allocationDate, sort,  sortdir,
				 practices, remarks, srvcDtFrom, srvcDtTo, patientName, birthDate,	insurances, 
				 insuranceTypes, chargesMin, chargesMax, claimStatus, priorities, userName, userId, roleId);
	}


}


 