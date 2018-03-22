package com.pfchoice.springboot.service.impl;

import java.util.List;
import java.util.TreeSet;

import com.pfchoice.springboot.model.Priority;
import com.pfchoice.springboot.repositories.PriorityRepository;
import com.pfchoice.springboot.service.PriorityService;

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

@Service("priorityService")
@Transactional
public class PriorityServiceImpl implements PriorityService {

	@Autowired
	private PriorityRepository priorityRepository;

	public Priority findById(Integer id) {
		return priorityRepository.findOne(id);
	}

	public Priority findByDescription(String description) {
		return priorityRepository.findByDescription(description);
	}
	
	public Priority findByCode(String code) {
		return priorityRepository.findByCode(code);
	}

	public void savePriority(Priority priority) {
		priorityRepository.save(priority);
	}

	public void updatePriority(Priority priority) {
		savePriority(priority);
	}

	public void deletePriorityById(Integer id) {
		priorityRepository.delete(id);
	}

	public void deleteAllPrioritys() {
		priorityRepository.deleteAll();
	}

	public Page<Priority> findAllPrioritysByPage(Specification<Priority> spec, Pageable pageable) {
		return priorityRepository.findAll(spec, pageable);
	}

	public List<Priority> findAllPrioritys() {
		return (List<Priority>) priorityRepository.findAll();
	}

	public boolean isPriorityExist(Priority priority) {
		return findByDescription(priority.getDescription()) != null;
	}

	@SuppressWarnings("unchecked")
	public List<Priority> findDistinctPrioritys() {
		List<Priority> prioritys = findAllPrioritys();

		List<Priority> uniquePrioritys = prioritys.stream().collect(
				collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(Priority::getId))), ArrayList::new));
		return uniquePrioritys;
	}

}
