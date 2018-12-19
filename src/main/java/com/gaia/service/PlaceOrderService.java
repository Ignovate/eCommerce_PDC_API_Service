package com.gaia.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gaia.domain.PlaceOrder;
import com.gaia.domain.PlaceOrderRowMapper;

@Service
public class PlaceOrderService {

	private String query = "  SELECT a.`id`,`customer_id` as customerId,`firstname` firstName,`lastname` lastName,`streetname` streetName ,b.`name` countryName,c.`name` regionName,d.`name` areaName FROM `customers_address` a JOIN `countries` b ON a.`customer_id` = b.`id`  JOIN `countries_regions` c ON a.`region_id` = c.`id` AND b.`id` = c.`country_id`  JOIN `countries_regions_areas` d ON a.`area_id` = d.`id` AND a.`country_id` = d.`country_id` AND a.`area_id` = d.`region_id` WHERE a.`customer_id` = 1 AND a.`country_id` = ? AND a.`region_id` = ? AND a.`area_id` = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<PlaceOrder> getPlaceOrder(Long customerId, Long countryId, Long areaId) {
		List<PlaceOrder> response = jdbcTemplate.query(query, new Object[] { customerId, countryId, areaId },
				new PlaceOrderRowMapper());
		return response;
	}

	public String PlaceOrder(Long quoteId) throws Exception {
		Connection conn;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();

			CallableStatement cs = conn.prepareCall("{call P_GAIA_PLACE_ORDER(?)}");
			cs.setLong("P_QUOTE_ID", quoteId);
			cs.executeUpdate();

			return "SUCCESS";
		} catch (SQLException e) {
			throw new Exception(e);
		}
	}

}
