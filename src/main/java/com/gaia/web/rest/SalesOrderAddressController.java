package com.gaia.web.rest;

import java.time.LocalDateTime;
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
import com.gaia.domain.SalesOrderAddress;
import com.gaia.service.SalesOrderAddressService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SalesOrderAddressController {

	@Autowired
	private SalesOrderAddressService salesAddrServ;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@PostMapping("salesorderaddress")
	public ResponseEntity<SalesOrderAddress> add(@RequestBody SalesOrderAddress salesAddrEntity)
			throws GaiaException {
		return new ResponseEntity<SalesOrderAddress>(salesAddrServ.addSalesOrderAddr(salesAddrEntity),
				HttpStatus.OK);
	}

	@GetMapping("salesorderaddress/{id}")
	public ResponseEntity<SalesOrderAddress> getSalesOrderAddr(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<SalesOrderAddress>(salesAddrServ.getSalesOrderAddr(id), HttpStatus.OK);
	}

	@GetMapping("salesorderaddress")
	public ResponseEntity<PagedResources<Resource<SalesOrderAddress>>> getSalesOrderAddr(
			PagedResourcesAssembler<SalesOrderAddress> assembler,
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "orderId", required = false) Long orderId, @RequestParam Map<String, String> map,
			Pageable pageable) throws GaiaException {
		if (map.containsKey("id"))
			map.remove("id");
		if (map.containsKey("orderId"))
			map.remove("orderId");

		SalesOrderAddress salesAddrReq = dozerBeanMapper.map(map, SalesOrderAddress.class);
		Map<String, String> request = dozerBeanMapper.map(salesAddrReq, Map.class);

		Page<SalesOrderAddress> response = salesAddrServ.getSalesOrderAddr(request, id, orderId, pageable);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

	@DeleteMapping("salesorderaddress/{id}")
	public ResponseEntity<ResponseVm> deleteSalesOrderAddr(@PathVariable long id) {
		salesAddrServ.deleteSalesOrderAddr(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("salesorderaddress/{id}")
	public ResponseEntity<ResponseVm> updateSalesOrder(@RequestBody SalesOrderAddress saleAddrEntity,
			@PathVariable long id) throws GaiaException {
		SalesOrderAddress oldSalesAddrDetails = salesAddrServ.getSalesOrderAddr(id);
		if (oldSalesAddrDetails == null)
			return ResponseEntity.notFound().build();
		dozerBeanMapper.map(saleAddrEntity, oldSalesAddrDetails);
		oldSalesAddrDetails.setId(id);
		oldSalesAddrDetails.setUpdatedDate(LocalDateTime.now());
		salesAddrServ.addSalesOrderAddr(oldSalesAddrDetails);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

}
