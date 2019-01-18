package com.gaia.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.gaia.web.rest.vm.ProductVm;

public class ProductRowMapper implements RowMapper<ProductVm> {

	@Override
	public ProductVm mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductVm resp = new ProductVm();
		try {
			resp.setCategoryId(rs.getLong("category_id"));
		} catch (SQLException e) {
		}
		try {
			resp.setBrandId(rs.getLong("brand_id"));
		} catch (SQLException e) {
		}
		resp.setProductId(rs.getLong("id"));
		resp.setSku(rs.getString("sku"));
		resp.setName(rs.getString("name"));
		resp.setPrice(rs.getBigDecimal("price"));
		resp.setSpecialPrice(rs.getBigDecimal("special_price"));
		Timestamp timestamp = rs.getTimestamp("special_price_start_date");
		if (timestamp != null)
			resp.setSplPriceStartDate(timestamp.toLocalDateTime());

		timestamp = rs.getTimestamp("special_price_end_date");
		if (timestamp != null)
			resp.setSplPriceEndDate(timestamp.toLocalDateTime());

		resp.setImageUrl(rs.getString("image"));
		resp.setImageLabel(rs.getString("image_label"));
		resp.setStock(rs.getLong("stock"));
		resp.setMeasurement(rs.getString("measurement"));
		resp.setStockStatus(rs.getLong("stock_status"));
		return resp;
	}

}
