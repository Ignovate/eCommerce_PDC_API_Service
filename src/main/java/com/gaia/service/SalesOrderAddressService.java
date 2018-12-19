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

import com.gaia.domain.SalesOrderAddress;
import com.gaia.repository.SalesOrderAddressRepo;

@Service
public class SalesOrderAddressService {

	@Autowired
	private SalesOrderAddressRepo repo;

	public SalesOrderAddress addSalesOrderAddr(SalesOrderAddress request) {
		return repo.save(request);
	}
	
	public SalesOrderAddress getSalesOrderAddr(Long id) {
		return repo.findById(id).orElse(null);
	}

	public Page<SalesOrderAddress> getSalesOrderAddr(Map<String, String> request, Long id, Long orderId,
			Pageable pageable) {
		Specification<SalesOrderAddress> spec = new Specification<SalesOrderAddress>() {
			@Override
			public Predicate toPredicate(Root<SalesOrderAddress> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();

				request.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});

				if (id != null)
					predicate.add(criteriaBuilder.equal(root.get("id"), id));

				if (orderId != null)
					predicate.add(criteriaBuilder.equal(root.get("orderId"), orderId));

				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return repo.findAll(spec, pageable);

	}

	public void deleteSalesOrderAddr(Long id) {
		repo.deleteById(id);
	}

}
