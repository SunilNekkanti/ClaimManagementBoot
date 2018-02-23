package com.pfchoice.springboot.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfchoice.springboot.model.ClaimStatus;

@Repository
public interface ClaimStatusRepository extends PagingAndSortingRepository<ClaimStatus, Integer>, JpaSpecificationExecutor<ClaimStatus> {

	ClaimStatus findById(Integer id);
	
	ClaimStatus findByDescription(String name);

}
