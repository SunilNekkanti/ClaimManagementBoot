package com.pfchoice.springboot.service.impl;

import java.util.List;
import java.util.TreeSet;

import com.pfchoice.springboot.model.Weightage;
import com.pfchoice.springboot.repositories.WeightageRepository;
import com.pfchoice.springboot.service.WeightageService;

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

@Service("weightageService")
@Transactional
public class WeightageServiceImpl implements WeightageService {

	@Autowired
	private WeightageRepository weightageRepository;

	public Weightage findById(Integer id) {
		return weightageRepository.findOne(id);
	}

	public Weightage findByConditionExp(Integer conditionExp) {
		return weightageRepository. findByConditionExp(conditionExp);
	}
	
	public Weightage findByPercentage(Integer percentage) {
		return weightageRepository.findByPercentage(percentage);
	}

	public void saveWeightage(Weightage weightage) {
		weightageRepository.save(weightage);
	}

	public void updateWeightage(Weightage weightage) {
		saveWeightage(weightage);
	}

	public void deleteWeightageById(Integer id) {
		weightageRepository.delete(id);
	}

	public void deleteAllWeightages() {
		weightageRepository.deleteAll();
	}

	public Page<Weightage> findAllWeightagesByPage(Specification<Weightage> spec, Pageable pageable) {
		return weightageRepository.findAll(spec, pageable);
	}

	public List<Weightage> findAllWeightages() {
		return (List<Weightage>) weightageRepository.findAll();
	}


	@SuppressWarnings("unchecked")
	public List<Weightage> findDistinctWeightages() {
		List<Weightage> weightages = findAllWeightages();

		List<Weightage> uniqueWeightages = weightages.stream().collect(
				collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(Weightage::getId))), ArrayList::new));
		return uniqueWeightages;
	}

	@Override
	public boolean isWeightageExist(Weightage weightage) {
		// TODO Auto-generated method stub
		return false;
	}

}
