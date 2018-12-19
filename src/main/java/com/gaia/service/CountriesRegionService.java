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
import com.gaia.domain.CountriesRegionEntity;
import com.gaia.repository.CountriesRegionRepo;

@Service
public class CountriesRegionService {
	
	@Autowired
	private CountriesRegionRepo countriesRegRepo;
	
	public CountriesRegionEntity getCountriesRegion(Long id) {
		return countriesRegRepo.findById(id).orElse(null);
	}
	
	public Page<CountriesRegionEntity> getCountriesRegion(Long id, Long countryID, String name, Pageable pageable) {
		Specification<CountriesRegionEntity> spec = new Specification<CountriesRegionEntity>() {

			@Override
			public Predicate toPredicate(Root<CountriesRegionEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				
				if(id !=null) {
					predicate.add(criteriaBuilder.equal(root.get("countryID"), countryID));
				}
				
				if(countryID !=null) {
					predicate.add(criteriaBuilder.equal(root.get("countryID"), countryID));
				}
				
				if(StringUtils.isNullOREmpty(name)) {
					predicate.add(criteriaBuilder.equal(root.get("name"), name));
				}
				
				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}
		};
		return countriesRegRepo.findAll(spec, pageable);
	}
	
}
