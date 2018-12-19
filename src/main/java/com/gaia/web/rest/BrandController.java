package com.gaia.web.rest;

import java.util.ArrayList;
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
import com.gaia.domain.Brand;
import com.gaia.service.BrandService;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BrandController {

	@Autowired
	private BrandService brandService;

	@GetMapping("brand/filter")
	public ResponseEntity<List<Brand>> filterBrand(@RequestParam(name = "name", required = false) String name)
			throws GaiaException {
		List<Brand> list = new ArrayList<Brand>();
		if (name != null) {
			list = brandService.filterBrand(name);
		}
		return new ResponseEntity<List<Brand>>(list, HttpStatus.OK);
	}
}
