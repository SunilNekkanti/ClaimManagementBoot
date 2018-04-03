package com.pfchoice.springboot.repositories.specifications;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.MappingInsurance;

public class MappingInsuranceSpecifications implements Specification<MappingInsurance> {

	private String searchTerm;
	
	private String currentScreen;

	public MappingInsuranceSpecifications(String searchTerm, String currentScreen) {
		super();
		this.searchTerm = searchTerm;
		this.currentScreen = (currentScreen != null) ? currentScreen :"Active";
		
	}

	public Predicate toPredicate(Root<MappingInsurance> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

		String containsLikePattern = getContainsLikePattern(searchTerm);
		cq.distinct(true);

		Predicate p = cb.conjunction();

		if (searchTerm != null && !"".equals(searchTerm)) {
			p.getExpressions().add(cb.or(cb.like(cb.lower(root.get("name")), containsLikePattern)));
		}

		p.getExpressions().add(cb.and(cb.equal(root.get("activeInd"), 'Y')));
		// p.getExpressions().add(cb.and(cb.isNull(root.join("contract").join("referenceContract").get("prvdr"))));
		
		
		return p;

	}

	private static String getContainsLikePattern(String searchTerm) {
		if (searchTerm == null || searchTerm.isEmpty()) {
			return "%";
		} else {
			return "%" + searchTerm.toLowerCase() + "%";
		}
	}
}