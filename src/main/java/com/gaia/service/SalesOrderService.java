package com.gaia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gaia.domain.SalesEntity;
import com.gaia.domain.SalesOrder;
import com.gaia.domain.SalesOrderAddress;
import com.gaia.domain.SalesOrderItems;
import com.gaia.domain.SalesOrderPayment;
import com.gaia.domain.SalesQuote;
import com.gaia.domain.SalesQuoteItems;
import com.gaia.repository.SalesOrderRepo;
import com.gaia.repository.SalesRepo;

@Service
public class SalesOrderService {

	@Autowired
	private SalesOrderRepo salesOrderRepo;
	@Autowired
	private SalesQuoteService salesQuoteService;
	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@Autowired
	private SalesRepo salesRepo;

	public SalesOrder addSalesOrder(SalesOrder request) {
		return salesOrderRepo.save(request);
	}

	public SalesOrder getSalesOrder(Long id) {
		return salesOrderRepo.findById(id).orElse(null);
	}

	public List<SalesOrder> getSalesOrders(Long customerId) {
		Specification<SalesOrder> spec = new Specification<SalesOrder>() {

			@Override
			public Predicate toPredicate(Root<SalesOrder> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();

				predicate.add(criteriaBuilder.equal(root.get("customerId"), customerId));

				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return salesOrderRepo.findAll(spec);
	}

	public Page<SalesOrder> getSalesOrder(Map<String, String> map, Pageable pageable, Map<String, Long> longMap,
			Map<String, BigDecimal> decimalMap) {
		Specification<SalesOrder> spec = new Specification<SalesOrder>() {
			@Override
			public Predicate toPredicate(Root<SalesOrder> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				decimalMap.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});
				longMap.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});
				map.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});
				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return salesOrderRepo.findAll(spec, pageable);

	}

	public void deleteSalesOrder(Long id) {
		salesOrderRepo.deleteById(id);
	}

	public SalesEntity getSales(long id) {
		return salesRepo.findById(id).orElse(null);
	}

	public List<SalesEntity> getSales() {
		return salesRepo.findAll();
	}

	public SalesEntity addSalesOrder(SalesEntity salesEntity) {
		return salesRepo.save(salesEntity);
	}

	public String saleOrder(Long quoteId) {
		SalesQuote quoteOrder = salesQuoteService.getSalesQuote(quoteId);
		if (quoteOrder != null) {
			SalesOrder saleOrder = dozerBeanMapper.map(quoteOrder, SalesOrder.class);
			saleOrder.setId(null);
			saleOrder.setSaleOrderItems(null);
			saleOrder.setSaleAddress(null);
			saleOrder.setSalePayment(null);
			saleOrder.setDisplayId("1");
			addSalesOrder(saleOrder);

			// Add items
			List<SalesOrderItems> list = new ArrayList<SalesOrderItems>();
			for (SalesQuoteItems item : quoteOrder.getQuoteOrderItems()) {
				SalesOrderItems saleOrderItem = dozerBeanMapper.map(item, SalesOrderItems.class);
				saleOrderItem.setId(null);
				saleOrderItem.setOrderId(saleOrder.getId());
				list.add(saleOrderItem);
			}
			saleOrder.setSaleOrderItems(list);
			// Add address
			if (quoteOrder.getQuoteAddress() != null) {
				SalesOrderAddress saleOrderAddress = dozerBeanMapper.map(quoteOrder.getQuoteAddress(),
						SalesOrderAddress.class);
				saleOrderAddress.setId(null);
				saleOrderAddress.setOrderId(saleOrder.getId());
				saleOrder.setSaleAddress(saleOrderAddress);
			}
			// Add payment
			if (quoteOrder.getQuotePayment() != null) {
				SalesOrderPayment saleOrderPayment = dozerBeanMapper.map(quoteOrder.getQuotePayment(),
						SalesOrderPayment.class);
				saleOrderPayment.setId(null);
				saleOrderPayment.setOrderId(saleOrder.getId());
				saleOrder.setSalePayment(saleOrderPayment);
			}

			addSalesOrder(saleOrder);
			quoteOrder.setActive(false);
			salesQuoteService.addSalesQuote(quoteOrder);
		} else {
			return "Cart not found";
		}

		return "Success";
	}
}
