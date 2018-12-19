package com.gaia.service;

import java.math.BigDecimal;
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

import com.gaia.domain.SalesQuoteItems;
import com.gaia.repository.SalesQuoteItemsRepo;

@Service
public class SalesQuoteItemsService {
	@Autowired
	private SalesQuoteItemsRepo repo;

	public SalesQuoteItems addSalesQuoteItem(SalesQuoteItems request) {
		return repo.save(request);
	}

	public SalesQuoteItems getSalesQuoteItem(Long id) {
		return repo.findById(id).orElse(null);
	}

	public Page<SalesQuoteItems> getSalesQuoteItem(Map<String, String> request, Map<String, BigDecimal> bigDecMap,
			Long id, Long quoteId, Long productId, Pageable pageable) {
		Specification<SalesQuoteItems> spec = new Specification<SalesQuoteItems>() {
			@Override
			public Predicate toPredicate(Root<SalesQuoteItems> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();

				request.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});

				bigDecMap.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});

				if (id != null)
					predicate.add(criteriaBuilder.equal(root.get("id"), id));

				if (quoteId != null)
					predicate.add(criteriaBuilder.equal(root.get("quoteId"), quoteId));

				if (productId != null)
					predicate.add(criteriaBuilder.equal(root.get("productId"), productId));

				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return repo.findAll(spec, pageable);

	}

	public void deleteSalesQuoteItem(Long id) {
		repo.deleteById(id);
	}


}
