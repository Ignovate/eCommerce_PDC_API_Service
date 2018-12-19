package com.gaia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.gaia.domain.QuoteEntity;
import com.gaia.domain.SalesQuote;
import com.gaia.repository.QuoteRepo;
import com.gaia.repository.SalesQuoteRepo;

@Service
public class SalesQuoteService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SalesQuoteService.class);
	@Autowired
	private SalesQuoteRepo salesQuoteRepo;
	@Autowired
	private QuoteRepo quoteRepo;

	public SalesQuote addSalesQuote(SalesQuote request) {
		return salesQuoteRepo.save(request);
	}

	public SalesQuote getSalesQuote(Long id) {
		return salesQuoteRepo.findById(id).orElse(null);
	}
	
	public List<SalesQuote> getSalesQuotes(Long id) {
		Specification<SalesQuote> specification = new Specification<SalesQuote>() {
			@Override
			public Predicate toPredicate(Root<SalesQuote> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.get("id"), id),
						cb.equal(root.get("active"), 1));
			}
		};
		LOGGER.info("ID = " + id);
		return salesQuoteRepo.findAll(specification);
	}

	public Page<SalesQuote> getSalesQuote(Map<String, String> map, Pageable pageable, Map<String, String> stringMap,
			Map<String, BigDecimal> decimalMap) {
		Specification<SalesQuote> spec = new Specification<SalesQuote>() {
			@Override
			public Predicate toPredicate(Root<SalesQuote> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				decimalMap.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});
				stringMap.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});
				map.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});
				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return salesQuoteRepo.findAll(spec, pageable);

	}

	public SalesQuote getSalesQuote(Map<String, Object> map) {
		Specification<SalesQuote> spec = new Specification<SalesQuote>() {
			@Override
			public Predicate toPredicate(Root<SalesQuote> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				map.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});
				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}
		};
		return salesQuoteRepo.findOne(spec).orElse(null);
	}

	public void deleteSalesQuote(Long id) {
		salesQuoteRepo.deleteById(id);
	}

	public QuoteEntity getQuote(long id) {
		return quoteRepo.findById(id).orElse(null);
	}

	public List<QuoteEntity> getQuote() {
		return quoteRepo.findAll();
	}

	public QuoteEntity addSalesQuote(QuoteEntity request) {
		return quoteRepo.save(request);
	}

}
