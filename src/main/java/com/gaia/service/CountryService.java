package com.gaia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gaia.domain.CountriesEntity;
import com.gaia.domain.CountriesRegionAreaEntity;
import com.gaia.domain.CountriesRegionEntity;
import com.gaia.repository.CountriesRegionAreaRepo;
import com.gaia.repository.CountriesRegionRepo;
import com.gaia.repository.CountriesRepo;

@Service
public class CountryService {

	@Autowired
	private CountriesRepo countriesRepo;
	@Autowired
	private CountriesRegionRepo regionRepo;
	@Autowired
	private CountriesRegionAreaRepo areaRepo;

	public CountriesEntity getCountry(Long id) {
		return countriesRepo.findById(id).orElse(null);
	}

	public List<CountriesEntity> getAllCountries() {
		return countriesRepo.findAll();
	}

	public CountriesEntity getCountryByName(String name) {
		return countriesRepo.findByName(name);
	}

	public List<CountriesEntity> getCountries(Map<String, String> params) {
		Specification<CountriesEntity> specification = new Specification<CountriesEntity>() {
			@Override
			public Predicate toPredicate(Root<CountriesEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				params.forEach((k, v) -> predicates.add(cb.equal(root.get(k), v)));
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
		};
		return countriesRepo.findAll(specification);
	}

	public CountriesEntity addCountry(CountriesEntity country) {
		return countriesRepo.save(country);
	}

	public void deleteCountry(Long id) {
		countriesRepo.deleteById(id);
	}

	public CountriesRegionEntity getRegion(Long id) {
		return regionRepo.findById(id).orElse(null);
	}

	public List<CountriesRegionEntity> getAllRegions() {
		return regionRepo.findAll();
	}

	public List<CountriesRegionEntity> getRegionByCountryId(Long countryId) {
		return regionRepo.findByCountryId(countryId);
	}

	public List<CountriesRegionEntity> getRegions(Long countryId) {
		Specification<CountriesRegionEntity> specification = new Specification<CountriesRegionEntity>() {
			@Override
			public Predicate toPredicate(Root<CountriesRegionEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("countryId"), countryId);
			}
		};
		return regionRepo.findAll(specification);
	}

	public CountriesRegionEntity addRegion(CountriesRegionEntity region) {
		return regionRepo.save(region);
	}

	public void deleteRegion(Long id) {
		regionRepo.deleteById(id);
	}

	public void deleteRegionByCountryId(Long countryId) {
		regionRepo.deleteByCountryId(countryId);
	}

	public CountriesRegionAreaEntity getArea(Long id) {
		return areaRepo.findById(id).orElse(null);
	}

	public List<CountriesRegionAreaEntity> getAllAreas() {
		return areaRepo.findAll();
	}

	public List<CountriesRegionAreaEntity> getAreas(Long countryId, Long regionId) {
		Specification<CountriesRegionAreaEntity> specification = new Specification<CountriesRegionAreaEntity>() {
			@Override
			public Predicate toPredicate(Root<CountriesRegionAreaEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.get("countryId"), countryId));
				predicates.add(cb.equal(root.get("regionId"), regionId));
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
		};
		return areaRepo.findAll(specification);
	}

	public CountriesRegionAreaEntity addArea(CountriesRegionAreaEntity area) {
		return areaRepo.save(area);
	}

	public void deleteArea(Long id) {
		areaRepo.deleteById(id);
	}
}
