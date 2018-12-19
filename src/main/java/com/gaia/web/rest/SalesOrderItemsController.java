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
import com.gaia.domain.SalesOrderItems;
import com.gaia.service.SalesOrderItemsService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SalesOrderItemsController {

	@Autowired
	private SalesOrderItemsService salesItemsServ;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@PostMapping("salesorderitems")
	public ResponseEntity<SalesOrderItems> add(@RequestBody SalesOrderItems salesItemEntity)
			throws GaiaException {
		return new ResponseEntity<SalesOrderItems>(salesItemsServ.addSalesOrderItem(salesItemEntity),
				HttpStatus.OK);
	}

	@GetMapping("salesorderitems/{id}")
	public ResponseEntity<SalesOrderItems> getSalesOrderItem(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<SalesOrderItems>(salesItemsServ.getSalesOrderItem(id), HttpStatus.OK);
	}

	@GetMapping("salesorderitems")
	public ResponseEntity<PagedResources<Resource<SalesOrderItems>>> getSalesOrderItem(
			PagedResourcesAssembler<SalesOrderItems> assembler,
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "orderId", required = false) Long orderId,
			@RequestParam(name = "productId", required = false) Long productId,
			@RequestParam(name = "price", required = false) BigDecimal price,
			@RequestParam(name = "cost", required = false) BigDecimal cost, @RequestParam Map<String, String> map,
			Pageable pageable) throws GaiaException {

		if (map.containsKey("id"))
			map.remove("id");
		if (map.containsKey("orderId"))
			map.remove("orderId");
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

		SalesOrderItems salesItemReq = dozerBeanMapper.map(map, SalesOrderItems.class);
		Map<String, String> request = dozerBeanMapper.map(salesItemReq, Map.class);

		Page<SalesOrderItems> response = salesItemsServ.getSalesOrderItem(request, decimalMap, id, orderId,
				productId, pageable);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

	@DeleteMapping("salesorderitems/{id}")
	public ResponseEntity<ResponseVm> deleteSalesOrderItem(@PathVariable long id) {
		salesItemsServ.deleteSalesOrderItem(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("salesorderitems/{id}")
	public ResponseEntity<ResponseVm> updateSalesOrderItem(@RequestBody SalesOrderItems saleItemEntity,
			@PathVariable long id) throws GaiaException {
		SalesOrderItems oldSalesAddrDetails = salesItemsServ.getSalesOrderItem(id);
		if (oldSalesAddrDetails == null)
			return ResponseEntity.notFound().build();
		dozerBeanMapper.map(saleItemEntity, oldSalesAddrDetails);
		oldSalesAddrDetails.setId(id);
		oldSalesAddrDetails.setUpdatedDate(LocalDateTime.now());
		salesItemsServ.addSalesOrderItem(oldSalesAddrDetails);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

}
