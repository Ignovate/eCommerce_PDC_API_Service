package com.gaia.web.rest;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.GaiaException;
import com.gaia.domain.SalesOrder;
import com.gaia.service.SalesOrderService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SalesOrderController {

	@Autowired
	private SalesOrderService salesOrderService;
	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@GetMapping("salesorder/{id}")
	public ResponseEntity<SalesOrder> getSalesOrder(@PathVariable Long id) throws GaiaException {
		return new ResponseEntity<SalesOrder>(salesOrderService.getSalesOrder(id), HttpStatus.OK);
	}

	@GetMapping("salesorders/{customerId}")
	public ResponseEntity<List<SalesOrder>> getSalesOrders(@PathVariable Long customerId) throws GaiaException {
		return new ResponseEntity<List<SalesOrder>>(salesOrderService.getSalesOrders(customerId), HttpStatus.OK);
	}

	@GetMapping("salesorder/add")
	public ResponseEntity<ResponseVm> saleOrder(@RequestParam(name = "quoteId", required = true) Long quoteId)
			throws GaiaException {
		String message = salesOrderService.saleOrder(quoteId);
		ResponseVm res = ResponseVm.getSuccessVm();
		if (!"Success".equals(message))
			res.setMessage(message);

		return new ResponseEntity<ResponseVm>(res, HttpStatus.OK);
	}

	@PostMapping("salesorder")
	public ResponseEntity<SalesOrder> add(@RequestBody SalesOrder salesEntity) throws GaiaException {
		return new ResponseEntity<SalesOrder>(salesOrderService.addSalesOrder(salesEntity), HttpStatus.OK);
	}

//	@GetMapping("salesordermapped/{id}")
//	public ResponseEntity<SalesEntity> getSales(@PathVariable long id) throws GaiaException {
//		return new ResponseEntity<SalesEntity>(salesOrderService.getSales(id), HttpStatus.OK);
//	}
//
//	@GetMapping("salesordermapped")
//	public ResponseEntity<List<SalesEntity>> getSales() throws GaiaException {
//		return new ResponseEntity<List<SalesEntity>>(salesOrderService.getSales(), HttpStatus.OK);
//	}
//
//	@PostMapping("salesordermapped")
//	public ResponseEntity<SalesEntity> add(@RequestBody SalesEntity salesEntity) throws GaiaException {
//		return new ResponseEntity<SalesEntity>(salesOrderService.addSalesOrder(salesEntity), HttpStatus.OK);
//	}
//
//	@GetMapping("salesorder")
//	public ResponseEntity<PagedResources<Resource<SalesOrder>>> getSalesOrder(
//			PagedResourcesAssembler<SalesOrder> assembler, @RequestParam Map<String, String> map,
//			@RequestParam(name = "id", required = false) Long id,
//			@RequestParam(name = "customerId", required = false) Long customerId,
//			@RequestParam(name = "websiteId", required = false) Long websiteId,
//			@RequestParam(name = "totalItems", required = false) Long totalItems,
//			@RequestParam(name = "totalItemsQty", required = false) Long totalItemsQty,
//
//			@RequestParam(name = "grandTotal", required = false) BigDecimal grandTotal,
//			@RequestParam(name = "shippingAmount", required = false) BigDecimal shippingAmount,
//			@RequestParam(name = "taxAmount", required = false) BigDecimal taxAmount,
//			@RequestParam(name = "codCharges", required = false) BigDecimal codCharges, Pageable pageable)
//			throws GaiaException {
//		Map<String, Long> longMap = new HashMap<String, Long>();
//		Map<String, BigDecimal> decimalMap = new HashMap<String, BigDecimal>();
//
//		if (map.containsKey("id")) {
//			longMap.put("id", id);
//			map.remove("id");
//		}
//
//		if (map.containsKey("customerId")) {
//			longMap.put("customerId", customerId);
//			map.remove("customerId");
//		}
//
//		if (map.containsKey("websiteId")) {
//			longMap.put("websiteId", websiteId);
//			map.remove("websiteId");
//		}
//
//		if (map.containsKey("totalItems")) {
//			longMap.put("totalItems", totalItems);
//			map.remove("totalItems");
//		}
//		if (map.containsKey("totalItemsQty")) {
//			longMap.put("totalItemsQty", totalItemsQty);
//			map.remove("totalItemsQty");
//		}
//		if (map.containsKey("grandTotal")) {
//			decimalMap.put("grandTotal", grandTotal);
//			map.remove("grandTotal");
//		}
//
//		if (map.containsKey("shippingAmount")) {
//			decimalMap.put("shippingAmount", shippingAmount);
//			map.remove("shippingAmount");
//		}
//
//		if (map.containsKey("taxAmount")) {
//			decimalMap.put("taxAmount", taxAmount);
//			map.remove("taxAmount");
//		}
//
//		if (map.containsKey("codCharges")) {
//			decimalMap.put("codCharges", codCharges);
//			map.remove("codCharges");
//		}
//
//		SalesOrder salesReq = dozerBeanMapper.map(map, SalesOrder.class);
//		Map<String, String> request = dozerBeanMapper.map(salesReq, Map.class);
//		Page<SalesOrder> response = salesOrderService.getSalesOrder(request, pageable, longMap, decimalMap);
//		if (response.getContent().isEmpty()) {
//			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
//		}
//		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
//	}
//
//	@DeleteMapping("salesorder/{id}")
//	public ResponseEntity<ResponseVm> deleteSalesOrder(@PathVariable long id) {
//		salesOrderService.deleteSalesOrder(id);
//		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
//	}
//
//	@PutMapping("salesorder/{id}")
//	public ResponseEntity<ResponseVm> updateSalesOrder(@RequestBody SalesOrder salesEntity, @PathVariable long id)
//			throws GaiaException {
//		SalesOrder oldSalesDetails = salesOrderService.getSalesOrder(id);
//		if (oldSalesDetails == null)
//			return ResponseEntity.notFound().build();
//		salesEntity.setCreatedDate(oldSalesDetails.getCreatedDate());
//		salesEntity.setUpdatedDate(LocalDateTime.now());
//		salesOrderService.addSalesOrder(salesEntity);
//		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
//	}

}
