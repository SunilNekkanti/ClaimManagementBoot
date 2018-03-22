package com.pfchoice.springboot.service.impl;

import java.util.List;
import java.util.TreeSet;

import com.pfchoice.springboot.model.Practice;
import com.pfchoice.springboot.repositories.PracticeRepository;
import com.pfchoice.springboot.service.PracticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;

@Service("practiceService")
@Transactional
public class PracticeServiceImpl implements PracticeService {

	@Autowired
	private PracticeRepository practiceRepository;

	public Practice findById(Integer id) {
		return practiceRepository.findOne(id);
	}

	public Practice findByName(String name) {
		return practiceRepository.findByName(name);
	}
	
	public Practice findByShortName(String shortName) {
		return practiceRepository.findByShortName(shortName);
	}
	 
	public void savePractice(Practice practice) {
		practiceRepository.save(practice);
	}

	public void updatePractice(Practice practice) {
		savePractice(practice);
	}

	public void deletePracticeById(Integer id) {
		practiceRepository.delete(id);
	}

	public void deleteAllPractices() {
		practiceRepository.deleteAll();
	}

	public Page<Practice> findAllPracticesByPage(Specification<Practice> spec, Pageable pageable) {
		return practiceRepository.findAll(spec, pageable);
	}

	public List<Practice> findAllPractices() {
		return (List<Practice>) practiceRepository.findAll();
	}

	public boolean isPracticeExist(Practice practice) {
		return findByName(practice.getName()) != null;
	}

	@SuppressWarnings("unchecked")
	public List<Practice> findDistinctPractices() {
		List<Practice> practices = findAllPractices();

		List<Practice> uniquePractices = practices.stream().collect(
				collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(Practice::getId))), ArrayList::new));
		return uniquePractices;
	}

	
	

}
