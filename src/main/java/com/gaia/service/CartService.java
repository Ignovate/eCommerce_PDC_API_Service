package com.gaia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gaia.domain.CustomersAddrEntity;
import com.gaia.domain.GlobalShipping;
import com.gaia.domain.GlobalTax;
import com.gaia.domain.SalesQuote;
import com.gaia.domain.SalesQuoteAddress;
import com.gaia.domain.SalesQuoteItems;
import com.gaia.web.rest.vm.CartReqVm;
import com.gaia.web.rest.vm.ProductVm;

@Service
public class CartService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SalesQuoteService salesQuoteService;
	@Autowired
	private SalesOrderService salesOrderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomersService customersService;
	@Autowired
	private TaxService taxService;
	@Autowired
	private ShippingService shippingService;
	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	public SalesQuote getQuoteOrder(Long quoteId) {
		SalesQuote salesQuote = salesQuoteService.getSalesQuote(quoteId);
		return salesQuote;
	}

	public SalesQuote addQuoteOrder(CartReqVm cart) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerId", cart.getCustomerId());
		map.put("active", true);
		SalesQuote salesQuote = salesQuoteService.getSalesQuote(map);

		if (salesQuote == null) {
			ProductVm product = productService.getSingleProduct(cart.getProductId());
			salesQuote = new SalesQuote();
			// New item
			List<SalesQuoteItems> quoteItems = new ArrayList<SalesQuoteItems>();
			quoteItems.add(buildQuoteItem(new SalesQuoteItems(), salesQuote, cart, product));
			salesQuote.setQuoteOrderItems(quoteItems);

			// New cart
			salesQuote = updateQuote(salesQuote, cart, product);
			salesQuote.setQuoteOrderItems(null);
			salesQuoteService.addSalesQuote(salesQuote);

			// Add item
			quoteItems.get(0).setQuoteId(salesQuote.getId());
			salesQuote.setQuoteOrderItems(quoteItems);
			// Add Address
			salesQuote.setQuoteAddress(buildQuoteAddress(salesQuote, cart, product));

			// Update
			salesQuoteService.addSalesQuote(salesQuote);
		} else {
			salesQuote = updateQuoteOrder(cart);
		}
		return salesQuote;
	}

	public SalesQuote updateQuoteOrder(CartReqVm cart) {
		SalesQuote salesQuote = null;
		if (cart.getQuoteId() != null)
			salesQuote = salesQuoteService.getSalesQuote(cart.getQuoteId());
		else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", cart.getCustomerId());
			map.put("active", true);
			salesQuote = salesQuoteService.getSalesQuote(map);
		}

		if (salesQuote != null) {
			ProductVm product = productService.getSingleProduct(cart.getProductId());

			List<SalesQuoteItems> items = salesQuote.getQuoteOrderItems();
			Optional<SalesQuoteItems> itemBean = items.stream()
					.filter(item -> item.getProductId() == cart.getProductId()).findFirst();
			if (itemBean.isPresent()) {
				if (cart.getQuantity() == null || cart.getQuantity() == 0) {
					// Delete existing item
					salesQuote = deleteQuoteOrder(cart);
				} else { // Update existing item
					buildQuoteItem(itemBean.get(), salesQuote, cart, product);
				}
			} else {
				// Add item
				salesQuote.getQuoteOrderItems().add(buildQuoteItem(new SalesQuoteItems(), salesQuote, cart, product));
			}
			// Add Address
			salesQuote.setQuoteAddress(buildQuoteAddress(salesQuote, cart, product));

			updateQuote(salesQuote, null, product);

			salesQuoteService.addSalesQuote(salesQuote);
		}
		return salesQuote;
	}

	public SalesQuote deleteQuoteOrder(CartReqVm cart) {
		SalesQuote salesQuote = null;
		if (cart.getQuoteId() != null)
			salesQuote = salesQuoteService.getSalesQuote(cart.getQuoteId());
		else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", cart.getCustomerId());
			map.put("active", true);
			salesQuote = salesQuoteService.getSalesQuote(map);
		}

		if (salesQuote != null && (cart.getQuantity() == null || cart.getQuantity() == 0)) {
			ProductVm product = productService.getSingleProduct(cart.getProductId());

			// Delete item
			salesQuote.getQuoteOrderItems().removeIf(item -> item.getProductId() == cart.getProductId());
			// Add Address
			salesQuote.setQuoteAddress(buildQuoteAddress(salesQuote, cart, product));

			updateQuote(salesQuote, null, product);

			salesQuoteService.addSalesQuote(salesQuote);
		}
		return salesQuote;
	}

	private SalesQuoteItems buildQuoteItem(SalesQuoteItems item, SalesQuote salesQuote, CartReqVm cart,
			ProductVm product) {
		item.setQuoteId(salesQuote.getId());
		item.setProductId(cart.getProductId());
		item.setProductName(product.getName());
		item.setImageUrl(product.getImageUrl());
		item.setMeasurement(product.getMeasurement());
		item.setProductType("");
		BigDecimal price = product.getSpecialPrice() != null ? product.getSpecialPrice() : product.getPrice();
		item.setPrice(price);
		item.setCost(new BigDecimal(cart.getQuantity()).multiply(price));
		item.setQuantity(cart.getQuantity());
		item.setSku(product.getSku());
		return item;
	}

	private SalesQuoteAddress buildQuoteAddress(SalesQuote salesQuote, CartReqVm cart, ProductVm product) {
		SalesQuoteAddress quoteAddress = null;
		if (cart.getAddressId() != null) {
			CustomersAddrEntity customerAddress = customersService.getCustomerAddress(cart.getAddressId());
			if (customerAddress != null) {
				quoteAddress = new SalesQuoteAddress();
				quoteAddress.setQuoteId(salesQuote.getId());
				quoteAddress.setFirstName(customerAddress.getFirstname());
				quoteAddress.setLastName(customerAddress.getLastname());
				quoteAddress.setStreet(customerAddress.getStreetname());
				quoteAddress.setArea(customerAddress.getAreaId() + "");
				quoteAddress.setCountry(customerAddress.getCountryId() + "");
				quoteAddress.setPostCode(customerAddress.getPostcode() + "");
				quoteAddress.setRegion(customerAddress.getRegionId() + "");
			}
		}
		return quoteAddress;
	}

	private SalesQuote updateQuote(SalesQuote salesQuote, CartReqVm cart, ProductVm product) {
		Long totalItems = Long.valueOf(0);
		Long totalItemsQty = Long.valueOf(0);
		BigDecimal subTotal = BigDecimal.ZERO;
		if (cart != null) {
			salesQuote.setCustomerId(cart.getCustomerId());
			salesQuote.setWebsiteId(cart.getWebsiteId());
			salesQuote.setActive(true);
		}
		GlobalTax globalTax = taxService.getGlobalTaxByOrigin(salesQuote.getWebsiteId());
		GlobalShipping globalShipping = shippingService.getGlobalShippingByOrigin(salesQuote.getWebsiteId());
		Long tax = globalTax != null ? globalTax.getTaxPercentage() : 0;

		List<SalesQuoteItems> list = salesQuote.getQuoteOrderItems();
		if (list != null) {
			for (SalesQuoteItems item : list) {
				totalItems += 1;
				totalItemsQty += item.getQuantity();
				subTotal = subTotal.add(item.getCost());
			}
		}
		salesQuote.setTotalItems(totalItems);
		salesQuote.setTotalItemsQty(totalItemsQty);
		salesQuote.setSubTotal(subTotal);
		salesQuote.setTaxAmount(salesQuote.getSubTotal().multiply(new BigDecimal(tax)).divide(new BigDecimal(100)));
		salesQuote.setShippingAmount(globalShipping != null ? globalShipping.getShipAmount() : BigDecimal.ZERO);
		salesQuote.setGrandTotal(
				salesQuote.getSubTotal().add(salesQuote.getTaxAmount()).add(salesQuote.getShippingAmount()));

		return salesQuote;
	}

}
