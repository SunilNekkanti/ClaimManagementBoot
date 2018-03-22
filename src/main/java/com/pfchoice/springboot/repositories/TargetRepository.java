package com.pfchoice.springboot.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfchoice.springboot.model.Target;

@Repository
public interface TargetRepository
		extends PagingAndSortingRepository<Target, Integer>, JpaSpecificationExecutor<Target> {

	public Target findById(Integer id);
	
	

	public Target findByDescription(String description);

}
