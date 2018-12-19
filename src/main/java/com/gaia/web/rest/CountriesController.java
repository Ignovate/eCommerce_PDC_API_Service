package com.gaia.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.ErrorCodes;
import com.gaia.common.GaiaException;
import com.gaia.domain.CountriesEntity;
import com.gaia.service.CountriesService;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CountriesController {

	@Autowired
	private CountriesService countriesServ;

	@GetMapping("countries/{id}")
	public ResponseEntity<CountriesEntity> getCountries(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<CountriesEntity>(countriesServ.getCountries(id), HttpStatus.OK);
	}

	@GetMapping("countries")
	public ResponseEntity<PagedResources<Resource<CountriesEntity>>> getCountries(
			PagedResourcesAssembler<CountriesEntity> assembler,@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "name", required = false) String name,
			Pageable pageable) throws GaiaException {

		Page<CountriesEntity> response = countriesServ.getCountries(id, name, pageable);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}

		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

}
