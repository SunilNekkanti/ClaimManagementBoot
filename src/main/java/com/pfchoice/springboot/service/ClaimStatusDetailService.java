package com.pfchoice.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.ClaimStatusDetail;

public interface ClaimStatusDetailService {

	ClaimStatusDetail findById(Integer id);

	ClaimStatusDetail findByDescription(String name);

	void saveClaimStatusDetail(ClaimStatusDetail claimStatusDetail);

	void updateClaimStatusDetail(ClaimStatusDetail claimStatusDetail);

	void deleteClaimStatusDetailById(Integer id);

	void deleteAllClaimStatusDetails();

	List<ClaimStatusDetail> findAllClaimStatusDetails();

	Page<ClaimStatusDetail> findAllClaimStatusDetailsByPage(Specification<ClaimStatusDetail> spec, Pageable pageable);

	boolean isClaimStatusDetailExist(ClaimStatusDetail claimStatusDetail);

	List<ClaimStatusDetail> findDistinctClaimStatusDetails();

}