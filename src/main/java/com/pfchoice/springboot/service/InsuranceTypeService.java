package com.pfchoice.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.InsuranceType;

public interface InsuranceTypeService {

	InsuranceType findById(Integer id);

	InsuranceType findByDescription(String description);
	
	void saveInsuranceType(InsuranceType insuranceType);

	void updateInsuranceType(InsuranceType insuranceType);

	void deleteInsuranceTypeById(Integer id);

	void deleteAllInsuranceTypes();

	Page<InsuranceType> findAllInsuranceTypesByPage(Specification<InsuranceType> spec, Pageable pageable);

	boolean isInsuranceTypeExist(InsuranceType insuranceType);

}