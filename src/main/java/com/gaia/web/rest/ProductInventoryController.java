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
import com.gaia.domain.ProductAttributes;
import com.gaia.domain.ProductInventory;
import com.gaia.service.ProductInventoryService;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductInventoryController {
	

	@Autowired
	private ProductInventoryService productInventoryService;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@GetMapping("productinventory/{id}")
	public ResponseEntity<ProductInventory> getProductInvetry(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<ProductInventory>(productInventoryService.getProductInventory(id), HttpStatus.OK);
	}

	@GetMapping("productinventory")
	public ResponseEntity<PagedResources<Resource<ProductInventory>>> getProductInvetry(
			PagedResourcesAssembler<ProductInventory> assembler, @RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "productId", required = false) Long productId, @RequestParam Map<String, String> map,
			Pageable pageable) throws GaiaException {
		if (map.containsKey("id"))
			map.remove("id");
		if (map.containsKey("productId"))
			map.remove("productId");
		ProductAttributes prodEntity = dozerBeanMapper.map(map, ProductAttributes.class);
		Map<String, String> request = dozerBeanMapper.map(prodEntity, Map.class);
		Page<ProductInventory> response = productInventoryService.getProductInventory(request, id, productId, pageable);

		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}

		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}


}
