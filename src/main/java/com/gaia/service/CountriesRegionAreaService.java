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
import com.gaia.domain.CountriesRegionAreaEntity;
import com.gaia.repository.CountriesRegionAreaRepo;

@Service
public class CountriesRegionAreaService {
	
	@Autowired
	private CountriesRegionAreaRepo countriesRegAreaRepo;
	
	public CountriesRegionAreaEntity getCountriesAreaRegion(Long id) {
		return countriesRegAreaRepo.findById(id).orElse(null);
	}
	
	public Page<CountriesRegionAreaEntity> getCountriesAreaRegion(Long id, Long countryID, Long regionID, String name, Pageable pageable) {
		Specification<CountriesRegionAreaEntity> spec = new Specification<CountriesRegionAreaEntity>() {

			@Override
			public Predicate toPredicate(Root<CountriesRegionAreaEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				
				if(id !=null) {
					predicate.add(criteriaBuilder.equal(root.get("countryID"), countryID));
				}
				
				if(countryID !=null) {
					predicate.add(criteriaBuilder.equal(root.get("countryID"), countryID));
				}
				
				if(regionID !=null) {
					predicate.add(criteriaBuilder.equal(root.get("regionID"), regionID));
				}
				
				if(StringUtils.isNullOREmpty(name)) {
					predicate.add(criteriaBuilder.equal(root.get("name"), name));
				}
				
				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}
		};
		return countriesRegAreaRepo.findAll(spec, pageable);
	}
	
}
