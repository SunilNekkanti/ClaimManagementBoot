package com.pfchoice.springboot.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfchoice.springboot.model.Priority;

@Repository
public interface PriorityRepository extends PagingAndSortingRepository<Priority, Integer>, JpaSpecificationExecutor<Priority> {

	Priority findById(Integer id);

	Priority findByCode(String code);

	Priority findByDescription(String description);

}
