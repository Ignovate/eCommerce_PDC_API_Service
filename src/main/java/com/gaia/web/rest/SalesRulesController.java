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
import com.gaia.domain.SalesRulesEntity;
import com.gaia.service.SalesRulesService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SalesRulesController {
	
	@Autowired
	private SalesRulesService serv;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@PostMapping("salesrules")
	public ResponseEntity<SalesRulesEntity> add(@RequestBody SalesRulesEntity salesPayEntity)
			throws GaiaException {
		return new ResponseEntity<SalesRulesEntity>(serv.addSalesRules(salesPayEntity),
				HttpStatus.OK);
	}

	@GetMapping("salesrules/{id}")
	public ResponseEntity<SalesRulesEntity> getSalesRules(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<SalesRulesEntity>(serv.getSalesRules(id), HttpStatus.OK);
	}

	@GetMapping("salesrules")
	public ResponseEntity<PagedResources<Resource<SalesRulesEntity>>> getSalesRules(
			PagedResourcesAssembler<SalesRulesEntity> assembler,
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "isActive", required = false) Long isActive,
			@RequestParam(name = "discountPercentage", required = false) Long discountPercentage,
			@RequestParam Map<String, String> map,
			Pageable pageable) throws GaiaException {
		
		if (map.containsKey("id"))
			map.remove("id");
		if (map.containsKey("isActive"))
			map.remove("isActive");
		if (map.containsKey("discountPercentage"))
			map.remove("discountPercentage");

		SalesRulesEntity salesPayReq = dozerBeanMapper.map(map, SalesRulesEntity.class);
		Map<String, Long> request = dozerBeanMapper.map(salesPayReq, Map.class);
		Page<SalesRulesEntity> response = serv.getSalesRules(request,id, isActive, discountPercentage, pageable);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

	@DeleteMapping("salesrules/{id}")
	public ResponseEntity<ResponseVm> deleteSalesRules(@PathVariable long id) {
		serv.deleteSalesRules(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("salesrules/{id}")
	public ResponseEntity<ResponseVm> updateSalesRules(@RequestBody SalesRulesEntity salePayEntity,
			@PathVariable long id) throws GaiaException {
		SalesRulesEntity oldSalesPayDetails = serv.getSalesRules(id);
		if (oldSalesPayDetails == null)
			return ResponseEntity.notFound().build();
		dozerBeanMapper.map(salePayEntity, oldSalesPayDetails);
		oldSalesPayDetails.setId(id);
		serv.addSalesRules(oldSalesPayDetails);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

}
