package com.gaia.web.rest;

import java.util.Map;

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
import com.gaia.domain.SalesQuote;
import com.gaia.service.AddToCartService;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AddToCartController {

	@Autowired
	private AddToCartService addToCart;

	@GetMapping("addtocart")
	public ResponseEntity<Long> addToCart(@RequestParam(name = "quoteId", required = true) Long quoteId,
			@RequestParam(name = "custId", required = true) Long custId,
			@RequestParam(name = "prodId", required = true) Long prodId,
			@RequestParam(name = "qty", required = true) Long qty,
			@RequestParam(name = "websiteId", required = true) Long websiteId,
			@RequestParam(name = "addrId", required = true) Long addrId,
			@RequestParam(name = "action", required = true) String action, @RequestParam Map<String, Long> map)
			throws Exception {
		Long resp = addToCart.addToCart(quoteId, custId, prodId, qty, websiteId, addrId, action);
		if (resp == null) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}

		return new ResponseEntity<Long>(resp, HttpStatus.OK);
	}
}
