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
import com.gaia.domain.ConfigurationsEntity;
import com.gaia.service.ConfigurationsService;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ConfigurationsController {

	@Autowired
	private ConfigurationsService confSer;

	@GetMapping("config/{id}")
	public ResponseEntity<ConfigurationsEntity> getConfigurations(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<ConfigurationsEntity>(confSer.getConfigurations(id), HttpStatus.OK);
	}

	@GetMapping("config")
	public ResponseEntity<PagedResources<Resource<ConfigurationsEntity>>> getConfigurations(
			PagedResourcesAssembler<ConfigurationsEntity> assembler,
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "websiteId", required = false) Long websiteId,
			@RequestParam(name = "path", required = false) String path,
			@RequestParam(name = "value", required = false) String value, Pageable pageable) throws GaiaException {
	
		Page<ConfigurationsEntity> response = confSer.getConfigurations(id, websiteId, path, value, pageable);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}

		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

}
