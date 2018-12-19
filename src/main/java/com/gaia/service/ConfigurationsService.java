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

import com.gaia.domain.ConfigurationsEntity;
import com.gaia.repository.ConfigurationsRepo;

@Service
public class ConfigurationsService {

	@Autowired
	private ConfigurationsRepo configRepo;

	public ConfigurationsEntity getConfigurations(Long id) {
		return configRepo.findById(id).orElse(null);
	}

	public Page<ConfigurationsEntity> getConfigurations(Long id, Long websiteId, String path, String value,
			Pageable pageable) {
		Specification<ConfigurationsEntity> spec = new Specification<ConfigurationsEntity>() {

			@Override
			public Predicate toPredicate(Root<ConfigurationsEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();

				if (id != null)
					predicate.add(criteriaBuilder.equal(root.get("id"), id));
				if (websiteId != null)
					predicate.add(criteriaBuilder.equal(root.get("websiteId"), websiteId));

				if (path != null)
					predicate.add(criteriaBuilder.equal(root.get("path"), path));

				if (value != null)
					predicate.add(criteriaBuilder.equal(root.get("value"), value));

				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return configRepo.findAll(spec, pageable);
	}

}
