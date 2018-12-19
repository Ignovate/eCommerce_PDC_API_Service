package com.gaia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gaia.common.StringUtils;
import com.gaia.domain.CountriesEntity;
import com.gaia.repository.CountriesRepo;

@Service
public class CountriesService {
	
	@Autowired
	private CountriesRepo countriesRepo;
	
	public CountriesEntity getCountries(Long id) {
		return countriesRepo.findById(id).orElse(null);
	}
	
	public Page<CountriesEntity> getCountries(Long id, String name, Pageable pageable) {
		Specification<CountriesEntity> spec = new Specification<CountriesEntity>() {

			@Override
			public Predicate toPredicate(Root<CountriesEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				
				if(id !=null) {
					predicate.add(criteriaBuilder.equal(root.get("id"), id));
				}
				
				if(StringUtils.isNullOREmpty(name)) {
					predicate.add(criteriaBuilder.equal(root.get("name"), name));
				}
				
				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}
		};
		return countriesRepo.findAll(spec, pageable);
	}
	
}
