package com.pfchoice.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.Priority;

public interface PriorityService {

	Priority findById(Integer id);

	Priority findByCode(String code);

	Priority findByDescription(String description);

	void savePriority(Priority priority);

	void updatePriority(Priority priority);

	void deletePriorityById(Integer id);

	void deleteAllPrioritys();

	List<Priority> findAllPrioritys();

	Page<Priority> findAllPrioritysByPage(Specification<Priority> spec, Pageable pageable);

	boolean isPriorityExist(Priority priority);
}