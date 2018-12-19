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
import com.gaia.domain.ProductImage;
import com.gaia.service.ProductImageService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductImageController {

	@Autowired
	private ProductImageService prodImgServ;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@PostMapping("productimage")
	public ResponseEntity<ProductImage> add(@RequestBody ProductImage prodImageEntity) throws GaiaException {
		return new ResponseEntity<ProductImage>(prodImgServ.addProductImage(prodImageEntity), HttpStatus.OK);
	}

	@GetMapping("productimage/{id}")
	public ResponseEntity<ProductImage> getProductImage(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<ProductImage>(prodImgServ.getProductImage(id), HttpStatus.OK);
	}

	@GetMapping("productimage")
	public ResponseEntity<PagedResources<Resource<ProductImage>>> getProductImage(
			PagedResourcesAssembler<ProductImage> assembler, @RequestParam Map<String, String> map,
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "productId", required = false) Long productId,
			@RequestParam(name = "position", required = false) Long position,
			Pageable pageable) throws GaiaException {
		if (map.containsKey("id"))
			map.remove("id");
		if (map.containsKey("productId")) 
			map.remove("productId");
		if (map.containsKey("position")) 
			map.remove("position");
		
		ProductImage custReq = dozerBeanMapper.map(map, ProductImage.class);
		Map<String, Long> request = dozerBeanMapper.map(custReq, Map.class);
		Page<ProductImage> response = prodImgServ.getProductImage(request, pageable, id, productId, position);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

	@DeleteMapping("productimage/{id}")
	public ResponseEntity<ResponseVm> deleteProductImage(@PathVariable long id) {
		prodImgServ.deleteProductImage(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("productimage/{id}")
	public ResponseEntity<ResponseVm> updateProductImage(@RequestBody ProductImage prodImgEntity,
			@PathVariable long id) throws GaiaException {
		ProductImage oldPrdImgDetails = prodImgServ.getProductImage(id);
		if (oldPrdImgDetails == null)
			return ResponseEntity.notFound().build();
		prodImgServ.addProductImage(prodImgEntity);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

}
