package com.gaia.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductAttributesWithInventryRowMapper implements RowMapper<ProductAttributesWithInventry>{
	
	@Override
	public ProductAttributesWithInventry mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductAttributesWithInventry prodtStock = new ProductAttributesWithInventry();
		
		prodtStock.setId(rs.getLong("id"));
		prodtStock.setProductId(rs.getLong("productId"));
		prodtStock.setName(rs.getString("name"));
		prodtStock.setGender(rs.getString("gender"));
		prodtStock.setBrand(rs.getString("brand"));
		prodtStock.setMeasurement(rs.getString("measurement"));
		prodtStock.setUsage(rs.getString("usage"));
		prodtStock.setComposition(rs.getString("composition"));
		prodtStock.setDescription(rs.getString("description"));
		prodtStock.setSku(rs.getString("sku"));
		prodtStock.setTypeId(rs.getLong("typeId"));
		prodtStock.setType(rs.getString("type"));
		prodtStock.setStockCount(rs.getLong("stockCount"));
		prodtStock.setStockStatus(rs.getString("stockStatus"));
		
		return prodtStock;
		
	}

}
