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

import com.gaia.domain.SalesOrderPayment;
import com.gaia.repository.SalesOrderPaymentRepo;

@Service
public class SalesOrderPaymentService {

	@Autowired
	private SalesOrderPaymentRepo repo;

	public SalesOrderPayment addSalesOrderPay(SalesOrderPayment request) {
		return repo.save(request);
	}

	public SalesOrderPayment getSalesOrderPay(Long id) {
		return repo.findById(id).orElse(null);
	}

	public Page<SalesOrderPayment> getSalesOrderPay(Map<String, String> request, Map<String, Long> longMap,
			Pageable pageable) {
		Specification<SalesOrderPayment> spec = new Specification<SalesOrderPayment>() {
			@Override
			public Predicate toPredicate(Root<SalesOrderPayment> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();

				request.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});

				longMap.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});

				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return repo.findAll(spec, pageable);

	}

	public void deleteSalesOrderItem(Long id) {
		repo.deleteById(id);
	}

}
