package com.gaia.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gaia.web.rest.vm.SortProductVm;

public class SortProductRowMapper implements RowMapper<SortProductVm> {

	@Override
	public SortProductVm mapRow(ResultSet rs, int rowNum) throws SQLException {
		SortProductVm productVm = new SortProductVm();
		productVm.setPrdId(rs.getLong("product_id"));
		productVm.setSku(rs.getString("sku"));
		productVm.setName(rs.getString("name"));
		productVm.setMeasurement(rs.getString("measurement"));
		productVm.setStock(rs.getString("stock"));
		productVm.setStockStatus(rs.getString("stock_status"));
		productVm.setPrice(rs.getBigDecimal("price"));
		productVm.setSplPrice(rs.getBigDecimal("special_price"));
		productVm.setImage(rs.getString("image"));
		return productVm;
	}

}
