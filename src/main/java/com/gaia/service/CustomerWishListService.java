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

import com.gaia.domain.CustomerWishListEntity;
import com.gaia.repository.CustomerWishListRepo;

@Service
public class CustomerWishListService {

	@Autowired
	private CustomerWishListRepo repo;

	public CustomerWishListEntity addCustWishList(CustomerWishListEntity request) {
		return repo.save(request);
	}

	public CustomerWishListEntity getCustWishList(Long id) {
		return repo.findById(id).orElse(null);
	}

	public Page<CustomerWishListEntity> getCustWishList(Map<String, Long> map, Pageable pageable) {
		Specification<CustomerWishListEntity> spec = new Specification<CustomerWishListEntity>() {
			@Override
			public Predicate toPredicate(Root<CustomerWishListEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				map.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});
				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return repo.findAll(spec, pageable);

	}

	public void deleteCustWishList(Long id) {
		repo.deleteById(id);
	}

}
