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

import com.gaia.domain.CategoriesProductEntity;
import com.gaia.repository.CategoriesProductRepo;

@Service
public class CategoriesProductService {
	
	@Autowired
	private CategoriesProductRepo categoriesPrdRepo;
	
	public CategoriesProductEntity getCategoriesProduct(Long id) {
		return categoriesPrdRepo.findById(id).orElse(null);
	}
	
	public Page<CategoriesProductEntity> getCategoriesProduct(Map<String,Long> map, Pageable pageable) {
		Specification<CategoriesProductEntity> spec = new Specification<CategoriesProductEntity>() {

			@Override
			public Predicate toPredicate(Root<CategoriesProductEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				
				map.forEach((k,v)->{
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});
				
				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}
			
		};
		return categoriesPrdRepo.findAll(spec, pageable);
	}
	
}
