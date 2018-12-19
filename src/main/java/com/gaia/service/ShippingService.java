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

import com.gaia.domain.GlobalShipping;
import com.gaia.repository.GlobalShippingRepo;

@Service
public class ShippingService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private GlobalShippingRepo globalShippingRepo;

	public GlobalShipping getGlobalShipping(Long id) {
		return globalShippingRepo.findById(id).orElse(null);
	}

	public GlobalShipping getGlobalShippingByOrigin(Long shipOrigin) {
		Specification<GlobalShipping> spec = new Specification<GlobalShipping>() {

			@Override
			public Predicate toPredicate(Root<GlobalShipping> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();

				predicate.add(criteriaBuilder.equal(root.get("shipOrigin"), shipOrigin));

				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return globalShippingRepo.findOne(spec).orElse(null);
	}
}
