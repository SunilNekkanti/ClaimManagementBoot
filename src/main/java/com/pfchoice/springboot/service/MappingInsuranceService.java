package com.pfchoice.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.MappingInsurance;

public interface MappingInsuranceService {

	MappingInsurance findById(Integer id);

	MappingInsurance findByName(String name);

	void saveMappingInsurance(MappingInsurance mappingInsurance);

	void updateMappingInsurance(MappingInsurance mappingInsurance);

	void deleteMappingInsuranceById(Integer id);

	void deleteAllMappingInsurances();

	List<MappingInsurance> findAllMappingInsurances();

	Page<MappingInsurance> findAllMappingInsurancesByPage(Specification<MappingInsurance> spec, Pageable pageable);

	boolean isMappingInsuranceExist(MappingInsurance mappingInsurance);
}