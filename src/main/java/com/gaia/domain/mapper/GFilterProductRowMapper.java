package com.gaia.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.gaia.web.rest.vm.ProductVm;

public class GFilterProductRowMapper implements RowMapper<ProductVm> {

	@Override
	public ProductVm mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductVm resp = new ProductVm();
		resp.setCategoryId(rs.getLong("category_id"));
		resp.setProductId(rs.getLong("id"));
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
		return resp;
	}

}
