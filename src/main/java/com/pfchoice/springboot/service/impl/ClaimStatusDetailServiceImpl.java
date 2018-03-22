package com.pfchoice.springboot.service.impl;

import java.util.List;
import java.util.TreeSet;

import com.pfchoice.springboot.model.ClaimStatusDetail;
import com.pfchoice.springboot.repositories.ClaimStatusDetailRepository;
import com.pfchoice.springboot.service.ClaimStatusDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;

@Service("claimStatusDetailService")
@Transactional
public class ClaimStatusDetailServiceImpl implements ClaimStatusDetailService {

	@Autowired
	private ClaimStatusDetailRepository claimStatusDetailRepository;

	public ClaimStatusDetail findById(Integer id) {
		return claimStatusDetailRepository.findOne(id);
	}

	public ClaimStatusDetail findByDescription(String name) {
		return claimStatusDetailRepository.findByDescription(name);
	}

	public void saveClaimStatusDetail(ClaimStatusDetail claimStatusDetail) {
		claimStatusDetailRepository.save(claimStatusDetail);
	}

	public void updateClaimStatusDetail(ClaimStatusDetail claimStatusDetail) {
		saveClaimStatusDetail(claimStatusDetail);
	}

	public void deleteClaimStatusDetailById(Integer id) {
		claimStatusDetailRepository.delete(id);
	}

	public void deleteAllClaimStatusDetails() {
		claimStatusDetailRepository.deleteAll();
	}

	public Page<ClaimStatusDetail> findAllClaimStatusDetailsByPage(Specification<ClaimStatusDetail> spec, Pageable pageable) {
		return claimStatusDetailRepository.findAll(spec, pageable);
	}

	public List<ClaimStatusDetail> findAllClaimStatusDetails() {
		return (List<ClaimStatusDetail>) claimStatusDetailRepository.findAll();
	}

	public boolean isClaimStatusDetailExist(ClaimStatusDetail claimStatusDetail) {
		return findByDescription(claimStatusDetail.getDescription()) != null;
	}

	@SuppressWarnings("unchecked")
	public List<ClaimStatusDetail> findDistinctClaimStatusDetails() {
		List<ClaimStatusDetail> claimStatusDetails = findAllClaimStatusDetails();

		List<ClaimStatusDetail> uniqueClaimStatusDetails = claimStatusDetails.stream().collect(
				collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(ClaimStatusDetail::getId))), ArrayList::new));
		return uniqueClaimStatusDetails;
	}

}
