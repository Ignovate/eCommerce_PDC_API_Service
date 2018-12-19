package com.gaia.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PlaceOrderRowMapper  implements RowMapper<PlaceOrder>{

	@Override
	public PlaceOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
		PlaceOrder resp = new PlaceOrder();
		resp.setId(rs.getLong("id"));
		resp.setCustomerId(rs.getLong("customerId"));
		resp.setFirstName(rs.getString("firstName"));
		resp.setLastName(rs.getString("lastName"));
		resp.setStreetName(rs.getString("streetName"));
		resp.setCountryName(rs.getString("countryName"));
		resp.setRegionName(rs.getString("regionName"));
		resp.setAreaName(rs.getString("areaName"));
		return resp;
	}

}
