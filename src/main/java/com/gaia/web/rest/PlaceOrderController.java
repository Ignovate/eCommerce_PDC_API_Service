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

import com.gaia.common.ErrorCodes;
import com.gaia.common.GaiaException;
import com.gaia.domain.PlaceOrder;
import com.gaia.service.PlaceOrderService;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PlaceOrderController {

	@Autowired
	private PlaceOrderService serv;

	@GetMapping("placeorder")
	public ResponseEntity<List<PlaceOrder>> getPlaceOrder(
			@RequestParam(name = "customerId", required = true) Long customerId,
			@RequestParam(name = "countryId", required = true) Long countryId,
			@RequestParam(name = "areaId", required = true) Long areaId) throws GaiaException {
		List<PlaceOrder> response = serv.getPlaceOrder(customerId, countryId, areaId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("placeorderprocedure")
	public ResponseEntity<String> getCustomersAddr(@RequestParam(name = "quoteId", required = true) Long quoteId)
			throws Exception {
		String resp = serv.PlaceOrder(quoteId);
		if (resp == null) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}
}
