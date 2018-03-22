package com.pfchoice.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.Practice;

public interface PracticeService {

	Practice findById(Integer id);

	Practice findByName(String name);

	Practice findByShortName(String shortName);


	
	void savePractice(Practice practice);

	void updatePractice(Practice practice);

	void deletePracticeById(Integer id);

	void deleteAllPractices();

	List<Practice> findAllPractices();

	Page<Practice> findAllPracticesByPage(Specification<Practice> spec, Pageable pageable);

	boolean isPracticeExist(Practice practice);
}