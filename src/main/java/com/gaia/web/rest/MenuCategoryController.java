package com.gaia.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.GaiaException;
import com.gaia.service.MenuCategoryService;
import com.gaia.web.rest.vm.BreadCrumb;
import com.gaia.web.rest.vm.MenuCategoryResponse;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuCategoryController {

	@Autowired
	private MenuCategoryService service;

	@GetMapping("menu")
	public ResponseEntity<List<MenuCategoryResponse>> getMenu() throws GaiaException {
		List<MenuCategoryResponse> response = service.getMenu();
		return new ResponseEntity<List<MenuCategoryResponse>>(response, HttpStatus.OK);
	}

	@GetMapping("breadcrumb")
	public ResponseEntity<List<BreadCrumb>> getBreadcrumbDetails(
			@RequestParam(name = "categoryId", required = true) Long categoryId) throws GaiaException {
		return new ResponseEntity<List<BreadCrumb>>(service.getBreadcrumbDetails(categoryId), HttpStatus.OK);
	}
}
