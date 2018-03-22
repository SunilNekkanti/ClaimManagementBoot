package com.pfchoice.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.AllocationLevel;

public interface AllocationLevelService {

	AllocationLevel findById(Integer id);

	AllocationLevel findByDescription(String name);

	void saveAllocationLevel(AllocationLevel allocationLevel);

	void updateAllocationLevel(AllocationLevel allocationLevel);

	void deleteAllocationLevelById(Integer id);

	void deleteAllAllocationLevels();

	List<AllocationLevel> findAllAllocationLevels();

	Page<AllocationLevel> findAllAllocationLevelsByPage(Specification<AllocationLevel> spec, Pageable pageable);

	boolean isAllocationLevelExist(AllocationLevel allocationLevel);

	List<AllocationLevel> findDistinctAllocationLevels();

}