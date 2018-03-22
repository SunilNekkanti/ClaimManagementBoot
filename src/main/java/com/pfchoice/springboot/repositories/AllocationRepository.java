package com.pfchoice.springboot.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfchoice.springboot.model.Allocation;

@Repository
public interface AllocationRepository extends PagingAndSortingRepository<Allocation, Integer>, JpaSpecificationExecutor<Allocation> {

	Allocation findById(Integer id);
	
	

}
