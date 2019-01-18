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
import com.gaia.domain.CustomersAddrEntity;
import com.gaia.domain.CustomersEntity;
import com.gaia.service.CustomersService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomersController {

	@Autowired
	private CustomersService custServ;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@PostMapping("customers")
	public ResponseEntity<CustomersEntity> add(@RequestBody CustomersEntity custDetails) throws GaiaException {
		return new ResponseEntity<CustomersEntity>(custServ.addCustomers(custDetails), HttpStatus.OK);
	}

	@GetMapping("customers/{id}")
	public ResponseEntity<CustomersEntity> getCustomers(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<CustomersEntity>(custServ.getCustomers(id), HttpStatus.OK);
	}

	@GetMapping("customers")
	public ResponseEntity<PagedResources<Resource<CustomersEntity>>> getCustomers(
			PagedResourcesAssembler<CustomersEntity> assembler, @RequestParam Map<String, String> map,
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "active", required = false) boolean isActive, Pageable pageable) throws GaiaException {
		boolean isPresent = false;
		if (map.containsKey("id"))
			map.remove("id");
		if (map.containsKey("active")) {
			isPresent = true;
		}

		CustomersEntity custReq = dozerBeanMapper.map(map, CustomersEntity.class);
		Map<String, String> request = dozerBeanMapper.map(custReq, Map.class);
		Page<CustomersEntity> response = custServ.getCustomers(request, pageable, id, isActive, isPresent);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

	@DeleteMapping("customers/{id}")
	public ResponseEntity<ResponseVm> deleteCustomers(@PathVariable long id) {
		custServ.deleteCustomers(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("customers/{id}")
	public ResponseEntity<ResponseVm> updateCustomers(@RequestBody CustomersEntity custDetails, @PathVariable long id)
			throws GaiaException {
		ResponseVm response = custServ.updateCustomer(id, custDetails);
		if (response == null)
			return ResponseEntity.notFound().build();

		return new ResponseEntity<ResponseVm>(response, HttpStatus.OK);
	}

}
