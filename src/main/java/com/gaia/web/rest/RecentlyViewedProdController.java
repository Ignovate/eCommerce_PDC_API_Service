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
import com.gaia.service.RecentViewProdService;
import com.gaia.web.rest.vm.RecentlyViewedProdResponse;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RecentlyViewedProdController {

	@Autowired
	private RecentViewProdService service;

	@GetMapping("recentviewprod")
	public ResponseEntity<List<RecentlyViewedProdResponse>> getRecentView(
			@RequestParam(name = "productId", required = true) String productId) throws GaiaException {
		List<RecentlyViewedProdResponse> response = service.getRecentView(productId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
