package com.pfchoice.springboot.service.impl;

import java.util.List;

import com.pfchoice.springboot.model.MappingInsurance;
import com.pfchoice.springboot.repositories.MappingInsuranceRepository;
import com.pfchoice.springboot.service.MappingInsuranceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mappingInsuranceService")
@Transactional
public class MappingInsuranceServiceImpl implements MappingInsuranceService {

	@Autowired
	private MappingInsuranceRepository mappingInsuranceRepository;

	public MappingInsurance findById(Integer id) {
		return mappingInsuranceRepository.findOne(id);
	}

	public MappingInsurance findByName(String name) {
		return mappingInsuranceRepository.findByName(name);
	}

	public void saveMappingInsurance(MappingInsurance mappingInsurance) {
		mappingInsuranceRepository.save(mappingInsurance);
	}

	public void updateMappingInsurance(MappingInsurance mappingInsurance) {
		saveMappingInsurance(mappingInsurance);
	}

	public void deleteMappingInsuranceById(Integer id) {
		mappingInsuranceRepository.delete(id);
	}

	public void deleteAllMappingInsurances() {
		mappingInsuranceRepository.deleteAll();
	}

	public List<MappingInsurance> findAllMappingInsurances() {
		return (List<MappingInsurance>) mappingInsuranceRepository.findAll();
	}

	public Page<MappingInsurance> findAllMappingInsurancesByPage(Specification<MappingInsurance> spec, Pageable pageable) {
		return mappingInsuranceRepository.findAll(spec, pageable);
	}

	public boolean isMappingInsuranceExist(MappingInsurance mappingInsurance) {
		return findByName(mappingInsurance.getName()) != null;
	}

}
