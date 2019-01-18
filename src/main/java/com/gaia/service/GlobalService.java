package com.gaia.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaia.domain.Brand;
import com.gaia.domain.CategoriesDetailsEntity;
import com.gaia.web.rest.vm.ProductVm;

@Service
public class GlobalService {

	@Autowired
	private CategoriesService categoriesService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductAttrService productAttrService;
	@Autowired
	ProductService productService;

	public synchronized <T> Map<String, List<T>> filterGlobal(String name) {
		Map<String, List<T>> map = new LinkedHashMap<String, List<T>>();
		List<CategoriesDetailsEntity> categoriesList = categoriesService.filterCategories(name);
		List<Brand> brandList = brandService.filterBrand(name);
		// List<ProductAttributes> productList =
		// productAttrService.filterProduct(name);
		List<ProductVm> productList = productService.globalFilterByProduct(name);

		map.put("category", (List<T>) categoriesList);
		map.put("brand", (List<T>) brandList);
		map.put("product", (List<T>) productList);
		return map;
	}

}
