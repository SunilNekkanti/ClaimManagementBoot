package com.pfchoice.springboot.service.impl;

import com.pfchoice.springboot.model.InsuranceType;
import com.pfchoice.springboot.repositories.InsuranceTypeRepository;
import com.pfchoice.springboot.service.InsuranceTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("insurnaceTypeService")
@Transactional
public class InsuranceTypeServiceImpl implements InsuranceTypeService {

	@Autowired
	private InsuranceTypeRepository insurnaceTypeRepository;

	public InsuranceType findById(Integer id) {
		return insurnaceTypeRepository.findOne(id);
	}

	public InsuranceType findByDescription(String description) {
		return insurnaceTypeRepository.findByDescription(description);
	}
	

	public void saveInsuranceType(InsuranceType insurnaceType) {
		insurnaceTypeRepository.save(insurnaceType);
	}

	public void updateInsuranceType(InsuranceType insurnaceType) {
		saveInsuranceType(insurnaceType);
	}

	public void deleteInsuranceTypeById(Integer id) {
		insurnaceTypeRepository.delete(id);
	}

	public void deleteAllInsuranceTypes() {
		insurnaceTypeRepository.deleteAll();
	}

	public Page<InsuranceType> findAllInsuranceTypesByPage(Specification<InsuranceType> spec, Pageable pageable) {
		return insurnaceTypeRepository.findAll(spec, pageable);
	}

	public boolean isInsuranceTypeExist(InsuranceType insurnaceType) {
		return findByDescription(insurnaceType.getDescription()) != null;
	}

}
