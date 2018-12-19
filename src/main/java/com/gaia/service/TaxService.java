package com.gaia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gaia.domain.GlobalTax;
import com.gaia.repository.GlobalTaxRepo;

@Service
public class TaxService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	GlobalTaxRepo globalTaxRepo;

	public GlobalTax getGlobalTax(Long id) {
		return globalTaxRepo.findById(id).orElse(null);
	}

	public GlobalTax getGlobalTaxByOrigin(Long taxOrigin) {
		Specification<GlobalTax> spec = new Specification<GlobalTax>() {

			@Override
			public Predicate toPredicate(Root<GlobalTax> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();

				predicate.add(criteriaBuilder.equal(root.get("taxOrigin"), taxOrigin));

				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return globalTaxRepo.findOne(spec).orElse(null);
	}
}
