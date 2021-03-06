package com.pfchoice.springboot.repositories.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.pfchoice.springboot.model.Role;

public class RoleSpecifications implements Specification<Role> {

	private String searchTerm;

	public RoleSpecifications(String searchTerm) {
		super();
		this.searchTerm = searchTerm;
	}

	public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

		String containsLikePattern = getContainsLikePattern(searchTerm);
		
		Predicate p = cb.conjunction();
		p.getExpressions().add(cb.or(cb.like(cb.lower(root.get("role")), containsLikePattern)));
		p.getExpressions().add(cb.and(cb.equal(root.get("activeInd"), 'Y')));
		cq.distinct(true);
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