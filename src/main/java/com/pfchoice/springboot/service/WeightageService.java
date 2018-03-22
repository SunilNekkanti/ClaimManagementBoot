package com.pfchoice.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.Weightage;

public interface WeightageService {

	Weightage findById(Integer id);

	Weightage findByConditionExp(Integer conditionExp);

	Weightage findByPercentage(Integer percentage);

	void saveWeightage(Weightage weightage);

	void updateWeightage(Weightage weightage);

	void deleteWeightageById(Integer id);

	void deleteAllWeightages();

	List<Weightage> findAllWeightages();

	Page<Weightage> findAllWeightagesByPage(Specification<Weightage> spec, Pageable pageable);

	boolean isWeightageExist(Weightage weightage);
}