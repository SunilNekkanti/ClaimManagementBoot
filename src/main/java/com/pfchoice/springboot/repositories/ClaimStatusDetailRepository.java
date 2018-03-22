package com.pfchoice.springboot.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfchoice.springboot.model.ClaimStatusDetail;

@Repository
public interface ClaimStatusDetailRepository extends PagingAndSortingRepository<ClaimStatusDetail, Integer>, JpaSpecificationExecutor<ClaimStatusDetail> {

	ClaimStatusDetail findById(Integer id);
	
	ClaimStatusDetail findByDescription(String name);

}
