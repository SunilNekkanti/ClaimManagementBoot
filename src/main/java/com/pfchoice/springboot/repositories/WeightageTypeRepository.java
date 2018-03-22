package com.pfchoice.springboot.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfchoice.springboot.model.WeightageType;

@Repository
public interface WeightageTypeRepository
		extends PagingAndSortingRepository<WeightageType, Integer>, JpaSpecificationExecutor<WeightageType> {

	public WeightageType findById(Integer id);
	
	

	public WeightageType findByDescription(String description);

}
