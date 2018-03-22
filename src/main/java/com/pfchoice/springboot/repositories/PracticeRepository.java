package com.pfchoice.springboot.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfchoice.springboot.model.Practice;

@Repository
public interface PracticeRepository extends PagingAndSortingRepository<Practice, Integer>, JpaSpecificationExecutor<Practice> {

	Practice findById(Integer id);

	Practice findByName(String name);
	
	Practice findByShortName(String shortName);

	}
