package com.gaia.web.rest;

import java.time.LocalDateTime;
import java.util.HashMap;
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
import com.gaia.domain.SalesQuotePayment;
import com.gaia.service.SalesQuotePaymentService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SalesQuotePaymentController {
	
	@Autowired
	private SalesQuotePaymentService serv;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@PostMapping("salesquotepayment")
	public ResponseEntity<SalesQuotePayment> add(@RequestBody SalesQuotePayment salesPayEntity)
			throws GaiaException {
		return new ResponseEntity<SalesQuotePayment>(serv.addSalesQuotePay(salesPayEntity),
				HttpStatus.OK);
	}

	@GetMapping("salesquotepayment/{id}")
	public ResponseEntity<SalesQuotePayment> getSalesQuotePay(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<SalesQuotePayment>(serv.getSalesQuotePay(id), HttpStatus.OK);
	}

	@GetMapping("salesquotepayment")
	public ResponseEntity<PagedResources<Resource<SalesQuotePayment>>> getSalesQuotePay(
			PagedResourcesAssembler<SalesQuotePayment> assembler,
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "quoteId", required = false) Long quoteId,
			@RequestParam(name = "ccExpMonth", required = false) Long ccExpMonth,
			@RequestParam(name = "ccExpYear", required = false) Long ccExpYear, @RequestParam Map<String, String> map,
			Pageable pageable) throws GaiaException {

		Map<String, Long> longMap = new HashMap<String, Long>();

		if (map.containsKey("id")) {
			longMap.put("id", id);
			map.remove("id");
		}

		if (map.containsKey("quoteId")) {
			longMap.put("quoteId", quoteId);
			map.remove("quoteId");
		}

		if (map.containsKey("ccExpMonth")) {
			longMap.put("ccExpMonth", ccExpMonth);
			map.remove("ccExpMonth");
		}

		if (map.containsKey("ccExpYear")) {
			longMap.put("ccExpYear", ccExpYear);
			map.remove("ccExpYear");
		}

		SalesQuotePayment salesPayReq = dozerBeanMapper.map(map, SalesQuotePayment.class);
		Map<String, String> request = dozerBeanMapper.map(salesPayReq, Map.class);

		Page<SalesQuotePayment> response = serv.getSalesQuotePay(request, longMap, pageable);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

	@DeleteMapping("salesquotepayment/{id}")
	public ResponseEntity<ResponseVm> deleteSalesQuotePay(@PathVariable long id) {
		serv.deleteSalesQuoteItem(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("salesquotepayment/{id}")
	public ResponseEntity<ResponseVm> updateSalesQuotePay(@RequestBody SalesQuotePayment salePayEntity,
			@PathVariable long id) throws GaiaException {
		SalesQuotePayment oldSalesPayDetails = serv.getSalesQuotePay(id);
		if (oldSalesPayDetails == null)
			return ResponseEntity.notFound().build();
		dozerBeanMapper.map(salePayEntity, oldSalesPayDetails);
		oldSalesPayDetails.setId(id);
		oldSalesPayDetails.setUpdatedDate(LocalDateTime.now());
		serv.addSalesQuotePay(oldSalesPayDetails);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

}
