package com.pfchoice.springboot.service.impl;

import java.util.List;
import java.util.TreeSet;

import com.pfchoice.springboot.model.Allocation;
import com.pfchoice.springboot.repositories.AllocationRepository;
import com.pfchoice.springboot.service.AllocationService;

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

@Service("allocationService")
@Transactional
public class AllocationServiceImpl implements AllocationService {

	@Autowired
	private AllocationRepository allocationRepository;

	public Allocation findById(Integer id) {
		return allocationRepository.findOne(id);
	}

	

	public void saveAllocation(Allocation allocation) {
		allocationRepository.save(allocation);
	}

	public void updateAllocation(Allocation allocation) {
		saveAllocation(allocation);
	}

	public void deleteAllocationById(Integer id) {
		allocationRepository.delete(id);
	}

	public void deleteAllAllocations() {
		allocationRepository.deleteAll();
	}

	public Page<Allocation> findAllAllocationsByPage(Specification<Allocation> spec, Pageable pageable) {
		return allocationRepository.findAll(spec, pageable);
	}

	public List<Allocation> findAllAllocations() {
		return (List<Allocation>) allocationRepository.findAll();
	}

	public boolean isAllocationExist(Allocation allocation) {
		return findById(allocation.getId()) != null;
	}

	@SuppressWarnings("unchecked")
	public List<Allocation> findDistinctAllocations() {
		List<Allocation> allocations = findAllAllocations();

		List<Allocation> uniqueAllocations = allocations.stream().collect(
				collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(Allocation::getId))), ArrayList::new));
		return uniqueAllocations;
	}

}
