package com.gaia.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gaia.domain.MenuCategoryRowMapper;
import com.gaia.web.rest.vm.BreadCrumb;
import com.gaia.web.rest.vm.MenuCategoryResponse;

@Service
public class MenuCategoryService {

	private static final Map<Long, List<BreadCrumb>> breadcrumb = new LinkedHashMap<Long, List<BreadCrumb>>();
	private String query = "select a.category_id, a.name, a.url_key, b.level, b.product_count, b.is_parent, a.image, a.thumbnail from categories_details a JOIN categories b on a.category_id=b.id order by b.level";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<MenuCategoryResponse> getMenu() {
		List<MenuCategoryResponse> response = jdbcTemplate.query(query, new MenuCategoryRowMapper());

		List<MenuCategoryResponse> main = new ArrayList<MenuCategoryResponse>();
		List<MenuCategoryResponse> sub = new ArrayList<MenuCategoryResponse>();
		List<MenuCategoryResponse> pre = new ArrayList<MenuCategoryResponse>();

		response.forEach(menu -> {
			if (menu.getLevel() == 1)
				sub.add(menu);
			else if (menu.getLevel() == 2)
				pre.add(menu);
			else
				main.add(menu);
		});

		sub.forEach(cat -> this.updateMenu(cat, main));
		pre.forEach(cat -> main.forEach(menu -> {
			this.updateMenu(cat, menu.getSubCategories());
		}));

		return main;
	}

	private void updateMenu(MenuCategoryResponse cat, List<MenuCategoryResponse> list) {
		for (MenuCategoryResponse menu : list) {
			
		/*	if (menu.getCategoryId() == cat.getParentId()) {
				menu.getSubCategories().add(cat);
			}*/
			if(menu.getCategoryId() != null && menu.getCategoryId().compareTo(cat.getParentId()) == 0){
				menu.getSubCategories().add(cat);
			}
				
		}
	}

	private void addBreadCrumb(MenuCategoryResponse cat, Long categoryId) {
		List<BreadCrumb> list = breadcrumb.get(categoryId);
		if (list == null) {
			breadcrumb.put(categoryId, new ArrayList<BreadCrumb>());
			breadcrumb.get(categoryId).add(0, new BreadCrumb(cat.getCategoryId(), cat.getCategoryName()));
		} else if (categoryId != cat.getCategoryId()) {
			list.add(0, new BreadCrumb(cat.getCategoryId(), cat.getCategoryName()));
		}
	}

	public List<BreadCrumb> getBreadcrumbDetails(Long categoryId) {
		if (breadcrumb.size() == 0) {
			List<MenuCategoryResponse> list = getMenu();
			for (MenuCategoryResponse cat : list) {
				addBreadCrumb(cat, cat.getCategoryId());
			}
			for (MenuCategoryResponse cat : list) {
				for (MenuCategoryResponse sub : cat.getSubCategories()) {
					addBreadCrumb(sub, sub.getCategoryId());
					addBreadCrumb(cat, sub.getCategoryId());
				}
			}
			for (MenuCategoryResponse cat : list) {
				for (MenuCategoryResponse sub : cat.getSubCategories()) {
					for (MenuCategoryResponse pre : sub.getSubCategories()) {
						addBreadCrumb(pre, pre.getCategoryId());
						addBreadCrumb(sub, pre.getCategoryId());
						addBreadCrumb(cat, pre.getCategoryId());
					}
				}
			}
		}
		System.out.println(breadcrumb);
		return breadcrumb.get(categoryId);
	}
}
