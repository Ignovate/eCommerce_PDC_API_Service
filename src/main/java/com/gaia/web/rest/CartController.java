package com.gaia.web.rest;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.GaiaException;
import com.gaia.domain.SalesQuote;
import com.gaia.service.CartService;
import com.gaia.web.rest.vm.CartReqVm;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@GetMapping("cart/read")
	public ResponseEntity<SalesQuote> getQuoteOrder(@RequestParam(name = "quoteId", required = true) Long quoteId)
			throws GaiaException {
		SalesQuote salesQuote = cartService.getQuoteOrder(quoteId);
		return new ResponseEntity<SalesQuote>(salesQuote, HttpStatus.OK);
	}

	@PostMapping("cart/new")
	public ResponseEntity<SalesQuote> addQuoteOrder(@RequestBody CartReqVm cart) throws GaiaException {
		return new ResponseEntity<SalesQuote>(cartService.addQuoteOrder(cart), HttpStatus.OK);
	}

	@PostMapping("cart/update")
	public ResponseEntity<SalesQuote> updateQuoteOrder(@RequestBody CartReqVm cart) throws GaiaException {
		return new ResponseEntity<SalesQuote>(cartService.updateQuoteOrder(cart), HttpStatus.OK);
	}

	@PostMapping("cart/delete")
	public ResponseEntity<SalesQuote> deleteQuoteOrder(@RequestBody CartReqVm cart) throws GaiaException {
		return new ResponseEntity<SalesQuote>(cartService.deleteQuoteOrder(cart), HttpStatus.OK);
	}

}
