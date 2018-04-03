package com.pfchoice.springboot.service.impl;

import java.util.List;

import com.pfchoice.springboot.model.ProviderInsuranceDetails;
import com.pfchoice.springboot.repositories.ProviderInsuranceDetailsRepository;
import com.pfchoice.springboot.service.ProviderInsuranceDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("providerInsuranceDetailsService")
@Transactional
public class ProviderInsuranceDetailsServiceImpl implements ProviderInsuranceDetailsService {

	@Autowired
	private ProviderInsuranceDetailsRepository providerInsuranceDetailsRepository;

	public ProviderInsuranceDetails findById(Integer id) {
		return providerInsuranceDetailsRepository.findOne(id);
	}
    

	public void saveProviderInsuranceDetails(ProviderInsuranceDetails providerInsuranceDetails) {
		providerInsuranceDetailsRepository.save(providerInsuranceDetails);
	}

	public void updateProviderInsuranceDetails(ProviderInsuranceDetails providerInsuranceDetails) {
		saveProviderInsuranceDetails(providerInsuranceDetails);
	}

	public void deleteProviderInsuranceDetailsById(Integer id) {
		providerInsuranceDetailsRepository.delete(id);
	}

	public void deleteAllProviderInsuranceDetailss() {
		providerInsuranceDetailsRepository.deleteAll();
	}

	public List<ProviderInsuranceDetails> findAllProviderInsuranceDetailss() {
		return (List<ProviderInsuranceDetails>) providerInsuranceDetailsRepository.findAll();
	}

	public Page<ProviderInsuranceDetails> findAllProviderInsuranceDetailssByPage(Specification<ProviderInsuranceDetails> spec, Pageable pageable) {
		return providerInsuranceDetailsRepository.findAll(spec, pageable);
	}

	public boolean isProviderInsuranceDetailsExist(ProviderInsuranceDetails providerInsuranceDetails) {
		return findById(providerInsuranceDetails.getId()) != null;
	}

}


 