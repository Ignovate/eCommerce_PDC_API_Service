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
import com.gaia.service.RelatedProductsService;
import com.gaia.web.rest.vm.ProductVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RelatedProductsController {

	@Autowired
	private RelatedProductsService service;

	@GetMapping("relatedproducts")
	public ResponseEntity<List<ProductVm>> getRelatedProducts(
			@RequestParam(name = "categoryId", required = true) Long categoryId) throws GaiaException {
		List<ProductVm> response = service.getRelatedProducts(categoryId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
