package com.gaia.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.GaiaException;
import com.gaia.domain.CategoriesDetailsEntity;
import com.gaia.domain.CategoriesEntity;
import com.gaia.service.CategoriesService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriesController {

	@Autowired
	CategoriesService categoriesService;

	@Autowired
	private DozerBeanMapper beanMapper;

	@PostMapping("categories")
	public ResponseEntity<CategoriesEntity> add(@RequestBody CategoriesEntity category) throws GaiaException {
		return new ResponseEntity<CategoriesEntity>(categoriesService.addCategories(category), HttpStatus.OK);
	}

	@GetMapping("categories/{id}")
	public ResponseEntity<CategoriesEntity> getCategories(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<CategoriesEntity>(categoriesService.getCategories(id), HttpStatus.OK);
	}

	@GetMapping("categories")
	public ResponseEntity<List<CategoriesEntity>> getCategories() throws GaiaException {
		return new ResponseEntity<List<CategoriesEntity>>(categoriesService.getCategories(), HttpStatus.OK);
	}

	@DeleteMapping("categories/{id}")
	public ResponseEntity<ResponseVm> deleteCategories(@PathVariable long id) {
		categoriesService.deleteCategories(id);

		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("categories/{id}")
	public ResponseEntity<ResponseVm> updateCategories(@RequestBody CategoriesEntity category, @PathVariable long id)
			throws GaiaException {
		CategoriesEntity categaryOld = categoriesService.getCategories(id);
		if (categaryOld == null)
			return ResponseEntity.notFound().build();
		beanMapper.map(category, categaryOld);
		categaryOld.setId(id);
		categoriesService.addCategories(categaryOld);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@GetMapping("categories/filter")
	public ResponseEntity<List<CategoriesDetailsEntity>> filterCategories(
			@RequestParam(name = "name", required = false) String name) throws GaiaException {
		List<CategoriesDetailsEntity> list = new ArrayList<CategoriesDetailsEntity>();
		if (name != null) {
			list = categoriesService.filterCategories(name);
		}
		return new ResponseEntity<List<CategoriesDetailsEntity>>(list, HttpStatus.OK);
	}

}
