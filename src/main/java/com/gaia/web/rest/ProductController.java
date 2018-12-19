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
import com.gaia.domain.SingleProductEntity;
import com.gaia.service.ProductService;
import com.gaia.web.rest.vm.ProductVm;
import com.gaia.web.rest.vm.SortProductVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@GetMapping("product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<Product>(productService.getProduct(id), HttpStatus.OK);
	}

	@GetMapping("product")
	public ResponseEntity<PagedResources<Resource<Product>>> getProduct(PagedResourcesAssembler<Product> assembler,
			@RequestParam(name = "sku", required = false) String sku, @RequestParam Map<String, String> map,
			Pageable pageable) throws GaiaException {
		if (map.containsKey("sku"))
			map.remove("sku");
		Product prodEntity = dozerBeanMapper.map(map, Product.class);
		Map<String, Long> request = dozerBeanMapper.map(prodEntity, Map.class);
		Page<Product> response = productService.getProduct(request, sku, pageable);

		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}

		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

	@GetMapping("productviewmapped/{id}")
	public ResponseEntity<SingleProductEntity> getProductView(@PathVariable int id) throws GaiaException {
		return new ResponseEntity<SingleProductEntity>(productService.getProductView(id), HttpStatus.OK);
	}

	@GetMapping("productviewmapped")
	public ResponseEntity<List<SingleProductEntity>> getProductView() throws GaiaException {
		return new ResponseEntity<List<SingleProductEntity>>(productService.getProductView(), HttpStatus.OK);
	}

	@GetMapping("sortproductview")
	public ResponseEntity<List<SortProductVm>> getSortProductView(
			@RequestParam(name = "fromDT", required = true) String fromDT,
			@RequestParam(name = "endDT", required = true) String endDT,
			@RequestParam(name = "type", required = true) String type,
			@RequestParam(name = "brand", required = true) String brand,
			@RequestParam(name = "gender", required = true) String gender,
			@RequestParam(name = "min", required = true) String min,
			@RequestParam(name = "max", required = true) String max) throws GaiaException {
		return new ResponseEntity<List<SortProductVm>>(
				productService.getSortProductView(fromDT, endDT, type, brand, gender, min, max), HttpStatus.OK);
	}

	@GetMapping("singleproduct")
	public ResponseEntity<ProductVm> getSingleProduct1(
			@RequestParam(name = "productId", required = true) Long productId) throws GaiaException {
		ProductVm response = productService.getSingleProduct(productId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("product/single")
	public ResponseEntity<Product> getSingleProduct(@RequestParam(name = "productId", required = true) Long productId)
			throws GaiaException {
		Product response = productService.getProduct(productId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("product/filter")
	public ResponseEntity<List<ProductVm>> filterProduct(@RequestParam(name = "gender", required = false) String gender,
			@RequestParam(name = "categoryId", required = false) Long categoryId,
			@RequestParam(name = "brandId", required = false) Long brandId,
			@RequestParam(name = "minPrice", required = false) Long minPrice,
			@RequestParam(name = "maxPrice", required = false) Long maxPrice,
			@RequestParam(name = "rating", required = false) Long rating) throws GaiaException {
		List<ProductVm> list = productService.filterProduct(gender, categoryId, brandId, minPrice, maxPrice, rating);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
