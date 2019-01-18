package com.gaia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gaia.domain.CategoriesDetailsEntity;
import com.gaia.domain.CategoriesEntity;
import com.gaia.repository.CategoriesDetailsRepo;
import com.gaia.repository.CategoriesRepo;

@Service
public class CategoriesService {

	@Autowired
	private CategoriesRepo categoriesRepo;
	@Autowired
	private CategoriesDetailsRepo categoriesDetailRepo;

	public CategoriesEntity addCategories(CategoriesEntity categories) {
		return categoriesRepo.save(categories);
	}

	public CategoriesEntity getCategories(Long id) {
		return categoriesRepo.findById(id).orElse(null);
	}

	public List<CategoriesEntity> getCategories() {
		return categoriesRepo.findAll();
	}

	public void deleteCategories(Long id) {
		categoriesRepo.deleteById(id);
	}

	public List<CategoriesDetailsEntity> filterCategories(String name) {
		Specification<CategoriesDetailsEntity> spec = new Specification<CategoriesDetailsEntity>() {

			@Override
			public Predicate toPredicate(Root<CategoriesDetailsEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				return criteriaBuilder.like(root.get("name"), "%" + name + "%");
			}

		};
		return categoriesDetailRepo.findAll(spec);
	}

}
