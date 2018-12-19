package com.gaia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gaia.domain.SalesRulesEntity;
import com.gaia.repository.SalesRulesRepo;

@Service
public class SalesRulesService {
	
	@Autowired
	private SalesRulesRepo repo;
	
	public SalesRulesEntity addSalesRules(SalesRulesEntity request) {
		return repo.save(request);
	}

	public SalesRulesEntity getSalesRules(Long id) {
		return repo.findById(id).orElse(null);
	}


	public void deleteSalesRules(Long id) {
		repo.deleteById(id);
	}

	public Page<SalesRulesEntity> getSalesRules(Map<String, Long> request, Long id, Long isActive,
			Long discountPercentage, Pageable pageable) {
		Specification<SalesRulesEntity> spec = new Specification<SalesRulesEntity>() {
			@Override
			public Predicate toPredicate(Root<SalesRulesEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();

				request.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});

				if (id != null)
					predicate.add(criteriaBuilder.equal(root.get("id"), id));

				if (isActive != null)
					predicate.add(criteriaBuilder.equal(root.get("isActive"), isActive));
				
				if (discountPercentage != null)
					predicate.add(criteriaBuilder.equal(root.get("discountPercentage"), discountPercentage));

				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return repo.findAll(spec, pageable);

	}
}
