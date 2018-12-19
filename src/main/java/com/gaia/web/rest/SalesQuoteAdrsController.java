package com.gaia.web.rest;

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
import com.gaia.domain.CustomerWishListEntity;
import com.gaia.domain.SalesOrderAddress;
import com.gaia.domain.SalesQuoteAddress;
import com.gaia.service.SalesQuoteAdrsService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SalesQuoteAdrsController {
	
	@Autowired
	private SalesQuoteAdrsService service;
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@PostMapping("salesquoteaddress")
	public ResponseEntity<SalesQuoteAddress> add(@RequestBody SalesQuoteAddress request)
			throws GaiaException {
		return new ResponseEntity<SalesQuoteAddress>(service.addSalesQuoteAdrs(request), HttpStatus.OK);
	}

	@GetMapping("salesquoteaddress/{id}")
	public ResponseEntity<SalesQuoteAddress> getsalesquoteaddress(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<SalesQuoteAddress>(service.getSalesQuoteAdrs(id), HttpStatus.OK);
	}

	@GetMapping("salesquoteaddress")
	public ResponseEntity<PagedResources<Resource<SalesQuoteAddress>>> getSalesQuotesAddr(
			PagedResourcesAssembler<SalesQuoteAddress> assembler,
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "quoteId", required = false) Long quoteId, @RequestParam Map<String, String> map,
			Pageable pageable) throws GaiaException {
		if (map.containsKey("id"))
			map.remove("id");
		if (map.containsKey("quoteId"))
			map.remove("quoteId");

		SalesQuoteAddress salesAddrReq = dozerBeanMapper.map(map, SalesQuoteAddress.class);
		Map<String, String> request = dozerBeanMapper.map(salesAddrReq, Map.class);

		Page<SalesQuoteAddress> response = service.getSalesQuoteAddr(request, id, quoteId, pageable);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

	@DeleteMapping("salesquoteaddress/{id}")
	public ResponseEntity<ResponseVm> deleteCustWishList(@PathVariable long id) {
		service.deleteSalesQuoteAdrs(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("salesquoteaddress/{id}")
	public ResponseEntity<ResponseVm> updateCustWishList(@RequestBody SalesQuoteAddress request,
			@PathVariable long id) throws GaiaException {
		SalesQuoteAddress oldCustDetails = service.getSalesQuoteAdrs(id);
		if (oldCustDetails == null)
			return ResponseEntity.notFound().build();
		service.addSalesQuoteAdrs(request);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

}
