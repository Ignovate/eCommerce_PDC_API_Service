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

import com.gaia.domain.ProductMapEntity;
import com.gaia.repository.ProductMapRepo;

@Service
public class ProductMapService {
	
	@Autowired
	private ProductMapRepo productMapRepo;
	

	public ProductMapEntity getProduct(Long id) {
		return productMapRepo.findById(id).orElse(null);
	}

	
	public Page<ProductMapEntity> getProduct(Map<String, Long> map, String sku, Pageable pageable) {
		Specification<ProductMapEntity> spec = new Specification<ProductMapEntity>() {

			@Override
			public Predicate toPredicate(Root<ProductMapEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();

				map.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});

				if (sku != null)
					predicate.add(criteriaBuilder.equal(root.get("sku"), sku));

				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return productMapRepo.findAll(spec, pageable);
	}


	public List<ProductMapEntity> getProducts() {
		return productMapRepo.findAll();
	}
}
