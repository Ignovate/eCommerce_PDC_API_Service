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
import com.gaia.domain.CountriesManyEntity;
import com.gaia.repository.CountriesManyRepo;

@Service
public class CountriesManyService {
	
	@Autowired
	private CountriesManyRepo countriesManyRepo;
	
	public CountriesManyEntity getCountriesMany(Long id) {
		return countriesManyRepo.findById(id).orElse(null);
	}
	
	public Page<CountriesManyEntity> getCountriesMany(Long id, String name, Pageable pageable) {
		Specification<CountriesManyEntity> spec = new Specification<CountriesManyEntity>() {

			@Override
			public Predicate toPredicate(Root<CountriesManyEntity> root, CriteriaQuery<?> query,
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
		return countriesManyRepo.findAll(spec, pageable);
	}
	
}
