package com.gaia.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gaia.web.rest.vm.MenuCategoryResponse;

public class MenuCategoryRowMapper implements RowMapper<MenuCategoryResponse>{

	@Override
	public MenuCategoryResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		MenuCategoryResponse menu = new MenuCategoryResponse();
		menu.setCategoryId(rs.getLong("category_id"));
		menu.setCategoryName(rs.getString("name"));
		menu.setCategoryUrl(rs.getString("url_key"));
		menu.setLevel(rs.getLong("level"));
		menu.setProductCount(rs.getLong("product_count"));
		menu.setParentId(rs.getLong("is_parent"));
		menu.setImage(rs.getString("image"));
		menu.setThumbnail(rs.getString("thumbnail"));
		return menu;
	}

}
