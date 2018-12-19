package com.gaia.web.rest;

import java.time.LocalDateTime;
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
import org.springframework.jdbc.core.JdbcTemplate;
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
import com.gaia.service.CustomersAddrService;
import com.gaia.web.rest.vm.CustomerAddrResponse;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomersAddrController {

	@Autowired
	private CustomersAddrService custServ;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@PostMapping("customersaddress")
	public ResponseEntity<CustomersAddrEntity> add(@RequestBody CustomersAddrEntity custDetails) throws GaiaException {
		return new ResponseEntity<CustomersAddrEntity>(custServ.addCustomersAddr(custDetails), HttpStatus.OK);
	}

	@GetMapping("customersaddress/{id}")
	public ResponseEntity<CustomersAddrEntity> getCustomersAddr(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<CustomersAddrEntity>(custServ.getCustomersAddr(id), HttpStatus.OK);
	}

	@GetMapping("customersaddress")
	public ResponseEntity<PagedResources<Resource<CustomersAddrEntity>>> getCustomersAddr(
			PagedResourcesAssembler<CustomersAddrEntity> assembler, @RequestParam Map<String, String> map,
			@RequestParam(name = "firstname", required = false) String firstname,
			@RequestParam(name = "lastname", required = false) String lastname,
			@RequestParam(name = "streetname", required = false) String streetname, Pageable pageable)
			throws GaiaException {
		if (map.containsKey("firstname"))
			map.remove("firstname");
		if (map.containsKey("lastname"))
			map.remove("lastname");
		if (map.containsKey("streetname"))
			map.remove("streetname");

		CustomersAddrEntity custReq = dozerBeanMapper.map(map, CustomersAddrEntity.class);
		Map<String, Long> request = dozerBeanMapper.map(custReq, Map.class);
		Page<CustomersAddrEntity> response = custServ.getCustomersAddr(request, pageable, firstname, lastname,
				streetname);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

	@GetMapping("fetchcustomersaddress")
	public ResponseEntity<List<CustomerAddrResponse>> getCustomersAddr(
			@RequestParam(name = "custId", required = false) String custId, Pageable pageable) throws GaiaException {

		List<CustomerAddrResponse> response = custServ.getCustomersAddr(custId);
		if (response == null) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<List<CustomerAddrResponse>>(response, HttpStatus.OK);
	}

	@DeleteMapping("customersaddress/{id}")
	public ResponseEntity<ResponseVm> deleteCustomersAddr(@PathVariable long id) {
		custServ.deleteCustomersAddr(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("customersaddress/{id}")
	public ResponseEntity<ResponseVm> updateCustomersAddr(@RequestBody CustomersAddrEntity custDetails,
			@PathVariable long id) throws GaiaException {
		CustomersAddrEntity oldCustDetails = custServ.getCustomersAddr(id);
		if (oldCustDetails == null)
			return ResponseEntity.notFound().build();
		custServ.addCustomersAddr(custDetails);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

}
