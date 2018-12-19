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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.ErrorCodes;
import com.gaia.common.GaiaException;
import com.gaia.domain.Product;
import com.gaia.domain.ProductMapEntity;
import com.gaia.service.ProductMapService;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductMapController {
	
	@Autowired
	private ProductMapService productMapService;
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	
	@GetMapping("prdt/{id}")
	public ResponseEntity<ProductMapEntity> getProduct(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<ProductMapEntity>(productMapService.getProduct(id), HttpStatus.OK);
	}
	
	@GetMapping("prdtlist")
	public ResponseEntity<List<ProductMapEntity>> getProduct() throws GaiaException {
		return new ResponseEntity<List<ProductMapEntity>>(productMapService.getProducts(), HttpStatus.OK);
	}

	
	@GetMapping("prdt")
	public ResponseEntity<PagedResources<Resource<ProductMapEntity>>> getProduct(
			PagedResourcesAssembler<ProductMapEntity> assembler,@RequestParam(name = "sku", required = false) String sku,
			 @RequestParam Map<String, String> map,
			Pageable pageable) throws GaiaException {
		if(map.containsKey("sku"))
			map.remove("sku");
		Product prodEntity = dozerBeanMapper.map(map, Product.class);
		Map<String, Long> request = dozerBeanMapper.map(prodEntity, Map.class);
		Page<ProductMapEntity> response = productMapService.getProduct(request,sku, pageable);
		
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}

		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}
	
	

}
