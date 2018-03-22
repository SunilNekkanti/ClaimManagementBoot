package com.pfchoice.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.Target;

public interface TargetService {

	Target findById(Integer id);
	
	

	Target findByDescription(String description);

	void saveTarget(Target target);

	void updateTarget(Target target);

	void deleteTargetById(Integer id);

	void deleteAllTargets();

	List<Target> findAllTargets();

	Page<Target> findAllTargetsByPage(Specification<Target> spec, Pageable pageable);

	boolean isTargetExist(Target target);
}