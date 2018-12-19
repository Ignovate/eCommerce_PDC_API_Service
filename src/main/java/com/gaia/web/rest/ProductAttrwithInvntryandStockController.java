package com.gaia.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.GaiaException;
import com.gaia.domain.ProductAttributesWithInventry;
import com.gaia.service.ProductAttrwithInvntryandStockService;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductAttrwithInvntryandStockController {
	
	@Autowired
	private ProductAttrwithInvntryandStockService service;

	@GetMapping("productattrwithinvntryandstock")
	public ResponseEntity<List<ProductAttributesWithInventry>> getProdStock(
			@RequestParam(name = "id", required = true) Long id) throws GaiaException {
		List<ProductAttributesWithInventry> response = service.getProdStock(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
