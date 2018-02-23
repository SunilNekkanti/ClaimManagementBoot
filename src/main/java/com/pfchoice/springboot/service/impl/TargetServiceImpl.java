package com.pfchoice.springboot.service.impl;

import java.util.List;

import com.pfchoice.springboot.model.Target;
import com.pfchoice.springboot.repositories.TargetRepository;
import com.pfchoice.springboot.service.TargetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("targetService")
@Transactional
public class TargetServiceImpl implements TargetService {

	@Autowired
	private TargetRepository targetRepository;

	public Target findById(Integer id) {
		return targetRepository.findOne(id);
	}


	public Target findByDescription(String description) {
		return targetRepository.findByDescription(description);
	}

	public void saveTarget(Target target) {
		targetRepository.save(target);
	}

	public void updateTarget(Target target) {
		saveTarget(target);
	}

	public void deleteTargetById(Integer id) {
		targetRepository.delete(id);
	}

	public void deleteAllTargets() {
		targetRepository.deleteAll();
	}

	public List<Target> findAllTargets() {
		return (List<Target>) targetRepository.findAll();
	}

	public Page<Target> findAllTargetsByPage(Specification<Target> spec, Pageable pageable) {
		return targetRepository.findAll(spec, pageable);
	}

	public boolean isTargetExist(Target target) {
		return findById(target.getId()) != null;
	}

}
