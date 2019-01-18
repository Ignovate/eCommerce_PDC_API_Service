package com.gaia.web.rest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.ErrorCodes;
import com.gaia.common.GaiaException;
import com.gaia.domain.QuoteEntity;
import com.gaia.domain.SalesOrder;
import com.gaia.domain.SalesQuote;
import com.gaia.service.SalesQuoteService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SalesQuoteController {

	@Autowired
	private SalesQuoteService salesServ;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@PostMapping("salesquote")
	public ResponseEntity<SalesQuote> add(@RequestBody SalesQuote salesEntity) throws GaiaException {
		return new ResponseEntity<SalesQuote>(salesServ.addSalesQuote(salesEntity), HttpStatus.OK);
	}

	@GetMapping("salesquote/{id}")
	public ResponseEntity<SalesQuote> getSalesOrder(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<SalesQuote>(salesServ.getSalesQuote(id), HttpStatus.OK);
	}

	@GetMapping("salesquote")
	public ResponseEntity<PagedResources<Resource<SalesQuote>>> getSalesOrder(
			PagedResourcesAssembler<SalesQuote> assembler, @RequestParam Map<String, String> map,
			@RequestParam(name = "currency", required = false) String currency,
			@RequestParam(name = "couponCode", required = false) String couponCode,
			@RequestParam(name = "paymentMethod", required = false) String paymentMethod,
			@RequestParam(name = "grandTotal", required = false) BigDecimal grandTotal,
			@RequestParam(name = "shippingAmount", required = false) BigDecimal shippingAmount,
			@RequestParam(name = "taxAmount", required = false) BigDecimal taxAmount,
			@RequestParam(name = "codCharges", required = false) BigDecimal codCharges, Pageable pageable)
			throws GaiaException {

		Map<String, String> stringMap = new HashMap<String, String>();
		Map<String, BigDecimal> decimalMap = new HashMap<String, BigDecimal>();

		if (map.containsKey("currency")) {
			stringMap.put("currency", currency);
			map.remove("currency");
		}

		if (map.containsKey("couponCode")) {
			stringMap.put("couponCode", couponCode);
			map.remove("couponCode");
		}

		if (map.containsKey("paymentMethod")) {
			stringMap.put("paymentMethod", paymentMethod);
			map.remove("paymentMethod");
		}

		if (map.containsKey("grandTotal")) {
			decimalMap.put("grandTotal", grandTotal);
			map.remove("grandTotal");
		}

		if (map.containsKey("shippingAmount")) {
			decimalMap.put("shippingAmount", shippingAmount);
			map.remove("shippingAmount");
		}

		if (map.containsKey("taxAmount")) {
			decimalMap.put("taxAmount", taxAmount);
			map.remove("taxAmount");
		}

		if (map.containsKey("codCharges")) {
			decimalMap.put("codCharges", codCharges);
			map.remove("codCharges");
		}

		SalesOrder salesReq = dozerBeanMapper.map(map, SalesOrder.class);
		Map<String, String> request = dozerBeanMapper.map(salesReq, Map.class);

		Page<SalesQuote> response = salesServ.getSalesQuote(request, pageable, stringMap, decimalMap);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

	@DeleteMapping("salesquote/{id}")
	public ResponseEntity<ResponseVm> deleteSalesOrder(@PathVariable long id) {
		salesServ.deleteSalesQuote(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("salesquote/{id}")
	public ResponseEntity<ResponseVm> updateSalesOrder(@RequestBody SalesQuote salesEntity, @PathVariable long id)
			throws GaiaException {
		SalesQuote oldSalesDetails = salesServ.getSalesQuote(id);
		if (oldSalesDetails == null)
			return ResponseEntity.notFound().build();
		dozerBeanMapper.map(salesEntity, oldSalesDetails);
		oldSalesDetails.setId(id);
		oldSalesDetails.setUpdatedAt(LocalDateTime.now());
		salesServ.addSalesQuote(oldSalesDetails);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}
	
	@GetMapping("salesquotemapped/{id}")
	public ResponseEntity<QuoteEntity> getQuote(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<QuoteEntity>(salesServ.getQuote(id), HttpStatus.OK);
	}
	
	@GetMapping("salesquotemapped")
	public ResponseEntity<List<QuoteEntity>> getQuote() throws GaiaException {
		return new ResponseEntity<List<QuoteEntity>>(salesServ.getQuote(), HttpStatus.OK);
	}
	@PostMapping("salesquotemapped")
	public ResponseEntity<QuoteEntity> add(@RequestBody QuoteEntity salesEntity) throws GaiaException {
		return new ResponseEntity<QuoteEntity>(salesServ.addSalesQuote(salesEntity), HttpStatus.OK);
	}
}
