package com.pfchoice.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.ClaimStatus;

public interface ClaimStatusService {

	ClaimStatus findById(Integer id);

	ClaimStatus findByDescription(String name);

	void saveClaimStatus(ClaimStatus claimStatus);

	void updateClaimStatus(ClaimStatus claimStatus);

	void deleteClaimStatusById(Integer id);

	void deleteAllClaimStatuss();

	List<ClaimStatus> findAllClaimStatuss();

	Page<ClaimStatus> findAllClaimStatussByPage(Specification<ClaimStatus> spec, Pageable pageable);

	boolean isClaimStatusExist(ClaimStatus claimStatus);

	List<ClaimStatus> findDistinctClaimStatuss();

}