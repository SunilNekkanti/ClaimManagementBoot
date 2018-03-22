package com.pfchoice.springboot.service.impl;

import java.util.List;
import java.util.TreeSet;

import com.pfchoice.springboot.model.AllocationLevel;
import com.pfchoice.springboot.repositories.AllocationLevelRepository;
import com.pfchoice.springboot.service.AllocationLevelService;

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

@Service("allocationLevelService")
@Transactional
public class AllocationLevelServiceImpl implements AllocationLevelService {

	@Autowired
	private AllocationLevelRepository allocationLevelRepository;

	public AllocationLevel findById(Integer id) {
		return allocationLevelRepository.findOne(id);
	}

	public AllocationLevel findByDescription(String name) {
		return allocationLevelRepository.findByDescription(name);
	}

	public void saveAllocationLevel(AllocationLevel allocationLevel) {
		allocationLevelRepository.save(allocationLevel);
	}

	public void updateAllocationLevel(AllocationLevel allocationLevel) {
		saveAllocationLevel(allocationLevel);
	}

	public void deleteAllocationLevelById(Integer id) {
		allocationLevelRepository.delete(id);
	}

	public void deleteAllAllocationLevels() {
		allocationLevelRepository.deleteAll();
	}

	public Page<AllocationLevel> findAllAllocationLevelsByPage(Specification<AllocationLevel> spec, Pageable pageable) {
		return allocationLevelRepository.findAll(spec, pageable);
	}

	public List<AllocationLevel> findAllAllocationLevels() {
		return (List<AllocationLevel>) allocationLevelRepository.findAll();
	}

	public boolean isAllocationLevelExist(AllocationLevel allocationLevel) {
		return findByDescription(allocationLevel.getDescription()) != null;
	}

	@SuppressWarnings("unchecked")
	public List<AllocationLevel> findDistinctAllocationLevels() {
		List<AllocationLevel> allocationLevels = findAllAllocationLevels();

		List<AllocationLevel> uniqueAllocationLevels = allocationLevels.stream().collect(
				collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(AllocationLevel::getId))), ArrayList::new));
		return uniqueAllocationLevels;
	}

}
