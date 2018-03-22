package com.pfchoice.springboot.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfchoice.springboot.model.Weightage;

@Repository
public interface WeightageRepository extends PagingAndSortingRepository<Weightage, Integer>, JpaSpecificationExecutor<Weightage> {

	Weightage findById(Integer id);

	Weightage findByConditionExp(Integer conditionExp);

	Weightage findByPercentage(Integer percentage);

}
