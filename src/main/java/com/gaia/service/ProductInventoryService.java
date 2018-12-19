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

import com.gaia.domain.ProductInventory;
import com.gaia.repository.ProductInventoryRepo;

@Service
public class ProductInventoryService {
	
	@Autowired
	private ProductInventoryRepo prdInvRepo;
	
	public ProductInventory getProductInventory(Long id) {
		return prdInvRepo.findById(id).orElse(null);
	}
	
	public Page<ProductInventory> getProductInventory(Map<String, String> map, Long id, Long productId, Pageable pageable) {
		Specification<ProductInventory> spec = new Specification<ProductInventory>() {

			@Override
			public Predicate toPredicate(Root<ProductInventory> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();

				map.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});

				if (id != null)
					predicate.add(criteriaBuilder.equal(root.get("id"), id));
				if (productId != null)
					predicate.add(criteriaBuilder.equal(root.get("productId"), productId));

				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return prdInvRepo.findAll(spec, pageable);
	}



}
