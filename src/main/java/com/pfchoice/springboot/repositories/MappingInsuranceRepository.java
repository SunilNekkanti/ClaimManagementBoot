package com.pfchoice.springboot.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfchoice.springboot.model.MappingInsurance;

@Repository
public interface MappingInsuranceRepository
		extends PagingAndSortingRepository<MappingInsurance, Integer>, JpaSpecificationExecutor<MappingInsurance> {

	public MappingInsurance findById(Integer id);

	public MappingInsurance findByName(String name);
}
