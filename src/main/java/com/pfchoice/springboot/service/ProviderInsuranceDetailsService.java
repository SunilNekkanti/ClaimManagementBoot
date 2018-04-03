package com.pfchoice.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.ProviderInsuranceDetails;

public interface ProviderInsuranceDetailsService {

	ProviderInsuranceDetails findById(Integer id);
	

	void saveProviderInsuranceDetails(ProviderInsuranceDetails providerInsuranceDetails);

	void updateProviderInsuranceDetails(ProviderInsuranceDetails providerInsuranceDetails);

	void deleteProviderInsuranceDetailsById(Integer id);

	void deleteAllProviderInsuranceDetailss();

	List<ProviderInsuranceDetails> findAllProviderInsuranceDetailss();

	Page<ProviderInsuranceDetails> findAllProviderInsuranceDetailssByPage(Specification<ProviderInsuranceDetails> spec, Pageable pageable);

	boolean isProviderInsuranceDetailsExist(ProviderInsuranceDetails providerInsuranceDetails);
}