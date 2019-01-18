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
import com.gaia.domain.CustomersEntity;
import com.gaia.domain.GlobalShipping;
import com.gaia.domain.GlobalTax;
import com.gaia.domain.SalesOrder;
import com.gaia.domain.SalesOrderAddress;
import com.gaia.domain.SalesOrderItems;
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
	@Autowired
	private SmsAlertService smsService;
	@Autowired
	private MailAlertService mailService;
	@Autowired
	private CustomersService customerService;

	public SalesQuote getQuoteOrder(Long quoteId) {
		SalesQuote salesQuote = salesQuoteService.getSalesQuote(quoteId);
		return salesQuote;
	}

	public SalesQuote addQuoteOrder(final CartReqVm cart) {
		SalesQuote salesQuote = getSalesQuote(cart);
		ProductVm product = productService.getSingleProduct(cart.getProductId());

		if (salesQuote == null) {
			salesQuote = new SalesQuote();
			// New item
			List<SalesQuoteItems> quoteItems = new ArrayList<SalesQuoteItems>();
			quoteItems.add(buildQuoteItem(new SalesQuoteItems(), salesQuote, cart, product, false));
			salesQuote.setQuoteOrderItems(quoteItems);
			// New cart
			salesQuote = updateQuote(salesQuote, cart, product);
			salesQuote.setQuoteOrderItems(null);
			salesQuoteService.addSalesQuote(salesQuote);
			// Add item
			quoteItems.get(0).setQuoteId(salesQuote.getId());
			salesQuote.setQuoteOrderItems(quoteItems);
			// Add Address
			salesQuote.setQuoteAddress(buildQuoteAddress(salesQuote, cart));
			// Update
			salesQuoteService.addSalesQuote(salesQuote);
		} else {
			if (cart.getProductId() != null) {
				List<SalesQuoteItems> items = salesQuote.getQuoteOrderItems();
				Optional<SalesQuoteItems> itemBean = items.stream()
						.filter(item -> item.getProductId().compareTo(cart.getProductId()) == 0).findFirst();
				if (itemBean.isPresent()) {
					buildQuoteItem(itemBean.get(), salesQuote, cart, product, true);
				} else {
					salesQuote.getQuoteOrderItems()
							.add(buildQuoteItem(new SalesQuoteItems(), salesQuote, cart, product, false));
				}
				updateQuote(salesQuote, null, product);
			}
			// Add Address
			salesQuote.setQuoteAddress(buildQuoteAddress(salesQuote, cart));

			salesQuoteService.addSalesQuote(salesQuote);
		}
		return salesQuote;
	}

	public SalesQuote updateQuoteOrder(final CartReqVm cart) {
		SalesQuote salesQuote = getSalesQuote(cart);

		if (salesQuote != null) {
			if (cart.getProductId() != null) {
				ProductVm product = productService.getSingleProduct(cart.getProductId());

				List<SalesQuoteItems> items = salesQuote.getQuoteOrderItems();
				Optional<SalesQuoteItems> itemBean = items.stream()
						.filter(item -> item.getProductId().compareTo(cart.getProductId()) == 0).findFirst();
				if (itemBean.isPresent()) {
					buildQuoteItem(itemBean.get(), salesQuote, cart, product, false);
					updateQuote(salesQuote, null, product);
				}
			}
			// Add Address
			salesQuote.setQuoteAddress(buildQuoteAddress(salesQuote, cart));

			salesQuoteService.addSalesQuote(salesQuote);
		}
		return salesQuote;
	}

	public SalesQuote deleteQuoteOrder(final CartReqVm cart) {
		SalesQuote salesQuote = getSalesQuote(cart);

		if (salesQuote != null && cart.getProductId() != null) {
			ProductVm product = productService.getSingleProduct(cart.getProductId());

			// Delete item
			salesQuote.getQuoteOrderItems().removeIf(item -> item.getProductId().compareTo(cart.getProductId()) == 0);
			// Add Address
			salesQuote.setQuoteAddress(buildQuoteAddress(salesQuote, cart));

			updateQuote(salesQuote, null, product);

			salesQuoteService.addSalesQuote(salesQuote);
		}
		return salesQuote;
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
			salesOrderService.addSalesOrder(saleOrder);

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
//			Set<SalesQuotePayment> payments = quoteOrder.getQuotePayments();
//			SalesQuotePayment payment = payments != null
//					? payments.stream().filter(item -> item.getStatus().compareTo("SUCCESS") == 0).findFirst().get()
//					: null;
//			if (payment != null) {
//				// SalesQuotePayment payment = itemBean.get();
//				SalesOrderPayment saleOrderPayment = dozerBeanMapper.map(payment, SalesOrderPayment.class);
//				saleOrderPayment.setId(null);
//				saleOrderPayment.setOrderId(saleOrder.getId());
//				saleOrder.setSalePayment(saleOrderPayment);
//			}

			salesOrderService.addSalesOrder(saleOrder);
			quoteOrder.setActive(false);
			salesQuoteService.addSalesQuote(quoteOrder);
			CustomersEntity customer = customerService.getCustomers(quoteOrder.getCustomerId());
			if(customer != null) {
				mailService.sendEmail(customer.getEmail(), "Order details",
						"Your have ordered " + saleOrder.getTotalItems() + " product's");
				smsService.sendSms(customer.getMobile(),
						"You have ordered " + saleOrder.getTotalItems() + " product's");
			}
		} else {
			return "Cart not found";
		}

		return "Success";
	}

	private SalesQuote getSalesQuote(CartReqVm cart) {
		SalesQuote salesQuote = null;
		if (cart.getQuoteId() != null)
			salesQuote = salesQuoteService.getSalesQuote(cart.getQuoteId());
		else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", cart.getCustomerId());
			map.put("active", true);
			salesQuote = salesQuoteService.getSalesQuote(map);
		}
		return salesQuote;
	}

	private SalesQuoteItems buildQuoteItem(SalesQuoteItems item, SalesQuote salesQuote, CartReqVm cart,
			ProductVm product, boolean addQuantity) {
		item.setQuoteId(salesQuote.getId());
		item.setProductId(cart.getProductId());
		item.setProductName(product.getName());
		item.setImage(product.getImagePath());
		item.setMeasurement(product.getMeasurement());
		item.setProductType("");
		item.setQuantity(addQuantity ? item.getQuantity() + cart.getQuantity() : cart.getQuantity());
		BigDecimal price = product.getSpecialPrice() != null ? product.getSpecialPrice() : product.getPrice();
		item.setPrice(price);
		item.setCost(new BigDecimal(item.getQuantity()).multiply(price));
		item.setSku(product.getSku());
		return item;
	}

	private SalesQuoteAddress buildQuoteAddress(SalesQuote salesQuote, CartReqVm cart) {
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

	private SalesQuote updateQuote(SalesQuote salesQuote, final CartReqVm cart, ProductVm product) {
		Long totalItems = Long.valueOf(0);
		Long totalItemsQty = Long.valueOf(0);
		BigDecimal subTotal = BigDecimal.ZERO;
		if (salesQuote.getCustomerId() == null || salesQuote.getWebsiteId() == null) {
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
