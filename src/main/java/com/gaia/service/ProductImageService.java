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

import com.gaia.domain.ProductImage;
import com.gaia.repository.ProductImageRepo;

@Service
public class ProductImageService {

	@Autowired
	private ProductImageRepo repo;

	public ProductImage addProductImage(ProductImage request) {
		return repo.save(request);
	}

	public ProductImage getProductImage(Long id) {
		return repo.findById(id).orElse(null);
	}

	public Page<ProductImage> getProductImage(Map<String, Long> map, Pageable pageable, Long id, Long productId, Long position) {
		Specification<ProductImage> spec = new Specification<ProductImage>() {
			@Override
			public Predicate toPredicate(Root<ProductImage> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				map.remove("active");

				if (id != null)
					predicate.add(criteriaBuilder.equal(root.get("id"), id));
				if (productId != null)
					predicate.add(criteriaBuilder.equal(root.get("productId"), productId));
				if (position != null)
					predicate.add(criteriaBuilder.equal(root.get("position"), position));
				map.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});
				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return repo.findAll(spec, pageable);

	}

	public void deleteProductImage(Long id) {
		repo.deleteById(id);
	}

}
