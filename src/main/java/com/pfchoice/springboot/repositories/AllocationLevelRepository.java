package com.pfchoice.springboot.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfchoice.springboot.model.AllocationLevel;

@Repository
public interface AllocationLevelRepository extends PagingAndSortingRepository<AllocationLevel, Integer>, JpaSpecificationExecutor<AllocationLevel> {

	AllocationLevel findById(Integer id);
	
	AllocationLevel findByDescription(String name);

}
