package com.pfchoice.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.WeightageType;

public interface WeightageTypeService {

	WeightageType findById(Integer id);
	
	

	WeightageType findByDescription(String description);

	void saveWeightageType(WeightageType weightageType);

	void updateWeightageType(WeightageType weightageType);

	void deleteWeightageTypeById(Integer id);

	void deleteAllWeightageTypes();

	List<WeightageType> findAllWeightageTypes();

	Page<WeightageType> findAllWeightageTypesByPage(Specification<WeightageType> spec, Pageable pageable);

	boolean isWeightageTypeExist(WeightageType weightageType);
}