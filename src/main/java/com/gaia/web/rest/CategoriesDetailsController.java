package com.gaia.web.rest;

import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
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

import com.gaia.common.ErrorCodes;
import com.gaia.common.GaiaException;
import com.gaia.domain.CategoriesDetailsEntity;
import com.gaia.service.CategoriesDetailsService;
import com.gaia.web.rest.vm.CategoriesDetailsVm;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriesDetailsController {

	@Autowired
	CategoriesDetailsService catDetServ;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@PostMapping("categoriesdetails")
	public ResponseEntity<CategoriesDetailsEntity> add(@RequestBody CategoriesDetailsVm catDetails)
			throws GaiaException {
		return new ResponseEntity<CategoriesDetailsEntity>(catDetServ.addCatDetails(catDetails), HttpStatus.OK);
	}

	@GetMapping("categoriesdetails/{id}")
	public ResponseEntity<CategoriesDetailsVm> getCatDetails(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<CategoriesDetailsVm>(catDetServ.getCatDetails(id), HttpStatus.OK);
	}

	@GetMapping("categoriesdetails")
	public ResponseEntity<PagedResources<Resource<CategoriesDetailsVm>>> getCatDetails(
			PagedResourcesAssembler<CategoriesDetailsVm> assembler, @RequestParam Map<String, String> map,
			Pageable pageable) throws GaiaException {
		CategoriesDetailsVm catDetailsReq = dozerBeanMapper.map(map, CategoriesDetailsVm.class);
		Map<String, Long> request = dozerBeanMapper.map(catDetailsReq, Map.class);
		Page<CategoriesDetailsVm> response = catDetServ.getCatDetails(request, pageable);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

	@DeleteMapping("categoriesdetails/{id}")
	public ResponseEntity<ResponseVm> deleteCategories(@PathVariable long id) {
		catDetServ.deleteCatDetails(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("categoriesdetails/{id}")
	public ResponseEntity<ResponseVm> updateCatDetails(@RequestBody CategoriesDetailsVm catDetails,
			@PathVariable long id) throws GaiaException {
		CategoriesDetailsVm oldCatDetails = catDetServ.getCatDetails(id);
		if (oldCatDetails == null)
			return ResponseEntity.notFound().build();
		catDetServ.updateRecords(oldCatDetails, catDetails, id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

}
