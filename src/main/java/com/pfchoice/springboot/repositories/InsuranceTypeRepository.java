package com.pfchoice.springboot.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfchoice.springboot.model.InsuranceType;

@Repository
public interface InsuranceTypeRepository
		extends PagingAndSortingRepository<InsuranceType, Integer>, JpaSpecificationExecutor<InsuranceType> {

	public InsuranceType findById(Integer id);

	public InsuranceType findByDescription(String description);
}
