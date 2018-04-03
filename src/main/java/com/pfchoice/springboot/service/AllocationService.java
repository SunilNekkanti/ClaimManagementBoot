package com.pfchoice.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.Allocation;

public interface AllocationService {

	Allocation findById(Integer id);
	
	Allocation findByPercentage(Integer percentage);

	void saveAllocation(Allocation allocation);

	void updateAllocation(Allocation allocation);

	void deleteAllocationById(Integer id);

	void deleteAllAllocations();

	List<Allocation> findAllAllocations();

	Page<Allocation> findAllAllocationsByPage(Specification<Allocation> spec, Pageable pageable);

	boolean isAllocationExist(Allocation allocation);

	List<Allocation> findDistinctAllocations();

}