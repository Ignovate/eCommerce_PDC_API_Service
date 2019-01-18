package com.gaia.web.rest;

import java.util.List;
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
import com.gaia.domain.CustomerWishListEntity;
import com.gaia.service.CustomerWishListService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerWishListController {

	@Autowired
	private CustomerWishListService custServ;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@PostMapping("customerwishlist")
	public ResponseEntity<CustomerWishListEntity> add(@RequestBody CustomerWishListEntity custDetails)
			throws GaiaException {
		return new ResponseEntity<CustomerWishListEntity>(custServ.addCustWishList(custDetails), HttpStatus.OK);
	}

	@GetMapping("customerwishlist/{id}")
	public ResponseEntity<CustomerWishListEntity> getCustWishList(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<CustomerWishListEntity>(custServ.getCustWishList(id), HttpStatus.OK);
	}

	@GetMapping("customerwishlist")
	public ResponseEntity<PagedResources<Resource<CustomerWishListEntity>>> getCustWishList(
			PagedResourcesAssembler<CustomerWishListEntity> assembler, @RequestParam Map<String, String> map,
			Pageable pageable) throws GaiaException {
		CustomerWishListEntity custReq = dozerBeanMapper.map(map, CustomerWishListEntity.class);
		Map<String, Long> request = dozerBeanMapper.map(custReq, Map.class);
		Page<CustomerWishListEntity> response = custServ.getCustWishList(request, pageable);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

	@DeleteMapping("customerwishlist/{id}")
	public ResponseEntity<ResponseVm> deleteCustWishList(@PathVariable long id) {
		custServ.deleteCustWishList(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("customerwishlist/{id}")
	public ResponseEntity<ResponseVm> updateCustWishList(@RequestBody CustomerWishListEntity custtDetails,
			@PathVariable long id) throws GaiaException {
		CustomerWishListEntity oldCustDetails = custServ.getCustWishList(id);
		if (oldCustDetails == null)
			return ResponseEntity.notFound().build();
		custServ.addCustWishList(custtDetails);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@GetMapping("customerwishlists/{customerId}")
	public ResponseEntity<List<CustomerWishListEntity>> getCustWishLists(@PathVariable Long customerId)
			throws GaiaException {

		List<CustomerWishListEntity> wishLists = custServ.getCustWishLists(customerId);
		if (wishLists == null)
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);

		return new ResponseEntity<List<CustomerWishListEntity>>(wishLists, HttpStatus.OK);
	}

}
