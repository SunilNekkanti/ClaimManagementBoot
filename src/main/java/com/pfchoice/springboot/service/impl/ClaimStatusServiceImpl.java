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

@Service("claimstatusService")
@Transactional
public class ClaimStatusServiceImpl implements ClaimStatusService {

	@Autowired
	private ClaimStatusRepository claimstatusRepository;

	public ClaimStatus findById(Integer id) {
		return claimstatusRepository.findOne(id);
	}

	public ClaimStatus findByDescription(String name) {
		return claimstatusRepository.findByDescription(name);
	}

	public void saveClaimStatus(ClaimStatus claimstatus) {
		claimstatusRepository.save(claimstatus);
	}

	public void updateClaimStatus(ClaimStatus claimstatus) {
		saveClaimStatus(claimstatus);
	}

	public void deleteClaimStatusById(Integer id) {
		claimstatusRepository.delete(id);
	}

	public void deleteAllClaimStatuss() {
		claimstatusRepository.deleteAll();
	}

	public Page<ClaimStatus> findAllClaimStatussByPage(Specification<ClaimStatus> spec, Pageable pageable) {
		return claimstatusRepository.findAll(spec, pageable);
	}

	public List<ClaimStatus> findAllClaimStatuss() {
		return (List<ClaimStatus>) claimstatusRepository.findAll();
	}

	public boolean isClaimStatusExist(ClaimStatus claimstatus) {
		return findByDescription(claimstatus.getDescription()) != null;
	}

	@SuppressWarnings("unchecked")
	public List<ClaimStatus> findDistinctClaimStatuss() {
		List<ClaimStatus> claimstatuss = findAllClaimStatuss();

		List<ClaimStatus> uniqueClaimStatuss = claimstatuss.stream().collect(
				collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(ClaimStatus::getId))), ArrayList::new));
		return uniqueClaimStatuss;
	}

}
