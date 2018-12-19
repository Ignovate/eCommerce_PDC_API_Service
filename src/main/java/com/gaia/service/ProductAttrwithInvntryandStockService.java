package com.gaia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gaia.domain.ProductAttributesWithInventry;
import com.gaia.domain.ProductAttributesWithInventryRowMapper;

@Service
public class ProductAttrwithInvntryandStockService {

	private String query = "SELECT a.`id`,a.`product_id` as productId,`name`,`gender`,`brand`,`measurement`,`usage`,`composition`,`description`,`sku`,`type_id` as typeId,`type`,COALESCE(`stock`,0) stockCount,COALESCE(`stock_status`,'in Stock')stockStatus FROM `products_attributes` a JOIN `products` b ON a.`product_id` = b.`id` JOIN `products_type` c ON c.`id` = b.`type_id`  LEFT JOIN `products_inventory` d ON d.`product_id` = b.`id` WHERE a.`id` = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<ProductAttributesWithInventry> getProdStock(Long id) {
		List<ProductAttributesWithInventry> response = jdbcTemplate.query(query, new Object[] { id },
				new ProductAttributesWithInventryRowMapper());
		return response;
	}

}
