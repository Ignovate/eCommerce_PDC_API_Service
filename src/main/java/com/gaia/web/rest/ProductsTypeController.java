package com.gaia.web.rest;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.ErrorCodes;
import com.gaia.common.GaiaException;
import com.gaia.domain.ProductsType;
import com.gaia.service.ProductsTypeService;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsTypeController {

	@Autowired
	private ProductsTypeService prodTypeServ;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@GetMapping("productstype/{id}")
	public ResponseEntity<ProductsType> getProdType(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<ProductsType>(prodTypeServ.getProdTypes(id), HttpStatus.OK);
	}

	@GetMapping("productstype")
	public ResponseEntity<PagedResources<Resource<ProductsType>>> getProdType(
			PagedResourcesAssembler<ProductsType> assembler, @RequestParam Map<String, String> map,
			Pageable pageable) throws GaiaException {

		ProductsType prodTypeEntity = dozerBeanMapper.map(map, ProductsType.class);
		Map<String, Long> request = dozerBeanMapper.map(prodTypeEntity, Map.class);
		Page<ProductsType> response = prodTypeServ.getProdTypes(request, pageable);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

}
