package com.pfchoice.springboot.service.impl;

import java.util.List;
import java.util.TreeSet;

import com.pfchoice.springboot.model.ClaimStatus;
import com.pfchoice.springboot.repositories.ClaimStatusRepository;
import com.pfchoice.springboot.service.ClaimStatusService;

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

@Service("claimStatusService")
@Transactional
public class ClaimStatusServiceImpl implements ClaimStatusService {

	@Autowired
	private ClaimStatusRepository claimStatusRepository;

	public ClaimStatus findById(Integer id) {
		return claimStatusRepository.findOne(id);
	}

	public ClaimStatus findByDescription(String name) {
		return claimStatusRepository.findByDescription(name);
	}

	public void saveClaimStatus(ClaimStatus claimStatus) {
		claimStatusRepository.save(claimStatus);
	}

	public void updateClaimStatus(ClaimStatus claimStatus) {
		saveClaimStatus(claimStatus);
	}

	public void deleteClaimStatusById(Integer id) {
		claimStatusRepository.delete(id);
	}

	public void deleteAllClaimStatuss() {
		claimStatusRepository.deleteAll();
	}

	public Page<ClaimStatus> findAllClaimStatussByPage(Specification<ClaimStatus> spec, Pageable pageable) {
		return claimStatusRepository.findAll(spec, pageable);
	}

	public List<ClaimStatus> findAllClaimStatuss() {
		return (List<ClaimStatus>) claimStatusRepository.findAll();
	}

	public boolean isClaimStatusExist(ClaimStatus claimStatus) {
		return findByDescription(claimStatus.getDescription()) != null;
	}

	@SuppressWarnings("unchecked")
	public List<ClaimStatus> findDistinctClaimStatuss() {
		List<ClaimStatus> claimStatuss = findAllClaimStatuss();

		List<ClaimStatus> uniqueClaimStatuss = claimStatuss.stream().collect(
				collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(ClaimStatus::getId))), ArrayList::new));
		return uniqueClaimStatuss;
	}

}
