package com.gaia.web.rest;

import java.math.BigDecimal;
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
import com.gaia.domain.SalesQuoteItems;
import com.gaia.service.SalesQuoteItemsService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SalesQuoteItemsController {
	
	@Autowired
	private SalesQuoteItemsService serv;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@PostMapping("salesquoteitems")
	public ResponseEntity<SalesQuoteItems> add(@RequestBody SalesQuoteItems request)
			throws GaiaException {
		return new ResponseEntity<SalesQuoteItems>(serv.addSalesQuoteItem(request),
				HttpStatus.OK);
	}

	@GetMapping("salesquoteitems/{id}")
	public ResponseEntity<SalesQuoteItems> getSalesquoteItem(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<SalesQuoteItems>(serv.getSalesQuoteItem(id), HttpStatus.OK);
	}

	@GetMapping("salesquoteitems")
	public ResponseEntity<PagedResources<Resource<SalesQuoteItems>>> getSalesquoteItem(
			PagedResourcesAssembler<SalesQuoteItems> assembler,
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "quoteId", required = false) Long orderId,
			@RequestParam(name = "productId", required = false) Long productId,
			@RequestParam(name = "price", required = false) BigDecimal price,
			@RequestParam(name = "cost", required = false) BigDecimal cost, @RequestParam Map<String, String> map,
			Pageable pageable) throws GaiaException {

		if (map.containsKey("id"))
			map.remove("id");
		if (map.containsKey("quoteId"))
			map.remove("quoteId");
		if (map.containsKey("productId"))
			map.remove("productId");

		Map<String, BigDecimal> decimalMap = new HashMap<String, BigDecimal>();

		if (map.containsKey("price")) {
			decimalMap.put("price", price);
			map.remove("price");
		}

		if (map.containsKey("cost")) {
			decimalMap.put("cost", cost);
			map.remove("cost");
		}

		SalesQuoteItems salesItemReq = dozerBeanMapper.map(map, SalesQuoteItems.class);
		Map<String, String> request = dozerBeanMapper.map(salesItemReq, Map.class);

		Page<SalesQuoteItems> response = serv.getSalesQuoteItem(request, decimalMap, id, orderId,
				productId, pageable);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

	@DeleteMapping("salesquoteitems/{id}")
	public ResponseEntity<ResponseVm> deletesalesquoteitems(@PathVariable long id) {
		serv.deleteSalesQuoteItem(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("salesquoteitems/{id}")
	public ResponseEntity<ResponseVm> updateSalesOrderItem(@RequestBody SalesQuoteItems saleItemEntity,
			@PathVariable long id) throws GaiaException {
		SalesQuoteItems oldSalesAddrDetails = serv.getSalesQuoteItem(id);
		if (oldSalesAddrDetails == null)
			return ResponseEntity.notFound().build();
		dozerBeanMapper.map(saleItemEntity, oldSalesAddrDetails);
		oldSalesAddrDetails.setId(id);
		oldSalesAddrDetails.setUpdatedDate(LocalDateTime.now());
		serv.addSalesQuoteItem(oldSalesAddrDetails);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}


}
