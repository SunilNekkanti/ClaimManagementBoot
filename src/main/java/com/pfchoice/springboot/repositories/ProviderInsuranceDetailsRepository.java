package com.pfchoice.springboot.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfchoice.springboot.model.ProviderInsuranceDetails;

@Repository
public interface ProviderInsuranceDetailsRepository
		extends PagingAndSortingRepository<ProviderInsuranceDetails, Integer>, JpaSpecificationExecutor<ProviderInsuranceDetails> {

	public ProviderInsuranceDetails findById(Integer id);
	

}
