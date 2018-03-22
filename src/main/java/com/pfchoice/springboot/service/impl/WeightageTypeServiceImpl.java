package com.pfchoice.springboot.service.impl;

import java.util.List;

import com.pfchoice.springboot.model.WeightageType;
import com.pfchoice.springboot.repositories.WeightageTypeRepository;
import com.pfchoice.springboot.service.WeightageTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("weightageTypeService")
@Transactional
public class WeightageTypeServiceImpl implements WeightageTypeService {

	@Autowired
	private WeightageTypeRepository weightageTypeRepository;

	public WeightageType findById(Integer id) {
		return weightageTypeRepository.findOne(id);
	}
    
	
	public WeightageType findByDescription(String description) {
		return weightageTypeRepository.findByDescription(description);
	}

	public void saveWeightageType(WeightageType weightageType) {
		weightageTypeRepository.save(weightageType);
	}

	public void updateWeightageType(WeightageType weightageType) {
		saveWeightageType(weightageType);
	}

	public void deleteWeightageTypeById(Integer id) {
		weightageTypeRepository.delete(id);
	}

	public void deleteAllWeightageTypes() {
		weightageTypeRepository.deleteAll();
	}

	public List<WeightageType> findAllWeightageTypes() {
		return (List<WeightageType>) weightageTypeRepository.findAll();
	}

	public Page<WeightageType> findAllWeightageTypesByPage(Specification<WeightageType> spec, Pageable pageable) {
		return weightageTypeRepository.findAll(spec, pageable);
	}

	public boolean isWeightageTypeExist(WeightageType weightageType) {
		return findByDescription(weightageType.getDescription()) != null;
	}

}


 