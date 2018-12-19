package com.gaia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gaia.domain.RecentlyViewedProdRowMapper;
import com.gaia.web.rest.vm.RecentlyViewedProdResponse;

@Service
public class RecentViewProdService {

	private String query = "select a.product_id, a.name, b.price, b.special_price, CONCAT('http://167.99.153.79:8080/gaia_ecom_admin/GAIA_VIEW_IMAGE_FILE?form=category&type=1&id=', a.product_id) imageurl from products_attributes a JOIN products_price b on a.product_id = b.product_id where a.product_id IN (?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<RecentlyViewedProdResponse> getRecentView(String productId) {
		List<RecentlyViewedProdResponse> response = jdbcTemplate.query(query, new Object[] { productId },
				new RecentlyViewedProdRowMapper());
		return response;
	}
}
