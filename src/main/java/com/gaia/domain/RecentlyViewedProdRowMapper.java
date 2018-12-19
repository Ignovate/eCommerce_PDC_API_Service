package com.gaia.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gaia.web.rest.vm.RecentlyViewedProdResponse;

public class RecentlyViewedProdRowMapper implements RowMapper<RecentlyViewedProdResponse> {

	@Override
	public RecentlyViewedProdResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		RecentlyViewedProdResponse resp = new RecentlyViewedProdResponse();
		resp.setProductId(rs.getLong("product_id"));
		resp.setName(rs.getString("name"));
		resp.setPrice(rs.getBigDecimal("price"));
		resp.setSpecialPrice(rs.getBigDecimal("special_price"));
		resp.setImageUrl(rs.getString("imageurl"));
		return resp;
	}

}
