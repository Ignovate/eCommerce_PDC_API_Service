package com.gaia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gaia.domain.mapper.ProductRowMapper;
import com.gaia.web.rest.vm.ProductVm;

@Service
public class RelatedProductsService {

	private String query = "SELECT cp.category_id, p.id, p.sku, pa.name, pp.price, pp.special_price, pp.special_price_start_date, pp.special_price_end_date, pi.image, pi.image_label, pin.stock, pa.measurement, pin.stock_status from categories_products cp JOIN products p ON p.id = cp.product_id JOIN products_attributes pa ON pa.product_id = p.id JOIN products_price pp ON pp.product_id = p.id JOIN products_images pi ON pi.product_id = p.id JOIN products_inventory pin ON pin.product_id = p.id JOIN products_status ps ON ps.product_id = p.id WHERE cp.category_id = ? AND ps.status = 1";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<ProductVm> getRelatedProducts(Long categoryId) {
		List<ProductVm> response = jdbcTemplate.query(query, new Object[] { categoryId }, new ProductRowMapper());
		return response;
	}

}
