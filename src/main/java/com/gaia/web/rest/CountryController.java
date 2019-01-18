package com.gaia.web.rest;

import java.util.List;

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
import com.gaia.domain.CountriesEntity;
import com.gaia.domain.CountriesRegionAreaEntity;
import com.gaia.domain.CountriesRegionEntity;
import com.gaia.service.CountryService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

	@Autowired
	private CountryService countryService;

	// Country
	@GetMapping("country/{id}")
	public ResponseEntity<CountriesEntity> getCountry(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<CountriesEntity>(countryService.getCountry(id), HttpStatus.OK);
	}

	@GetMapping("country/all")
	public ResponseEntity<List<CountriesEntity>> getAllCountries() throws GaiaException {
		return new ResponseEntity<List<CountriesEntity>>(countryService.getAllCountries(), HttpStatus.OK);
	}

	@PostMapping("country")
	public ResponseEntity<CountriesEntity> addCountry(@RequestBody CountriesEntity country) throws GaiaException {
		return new ResponseEntity<CountriesEntity>(countryService.addCountry(country), HttpStatus.OK);
	}

	@PutMapping("country/{id}")
	public ResponseEntity<CountriesEntity> updateCountry(@PathVariable long id, @RequestBody CountriesEntity country)
			throws GaiaException {
		return new ResponseEntity<CountriesEntity>(countryService.addCountry(country), HttpStatus.OK);
	}

	@DeleteMapping("country/{id}")
	public ResponseEntity<ResponseVm> deleteCountry(@PathVariable long id) throws GaiaException {
		countryService.deleteCountry(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	// Region
	@GetMapping("country/region/{id}")
	public ResponseEntity<CountriesRegionEntity> getRegion(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<CountriesRegionEntity>(countryService.getRegion(id), HttpStatus.OK);
	}

	@GetMapping("country/region")
	public ResponseEntity<List<CountriesRegionEntity>> getRegions(@RequestParam long countryId) throws GaiaException {
		return new ResponseEntity<List<CountriesRegionEntity>>(countryService.getRegionByCountryId(countryId),
				HttpStatus.OK);
	}

	@GetMapping("country/region/all")
	public ResponseEntity<List<CountriesRegionEntity>> getAllRegions() throws GaiaException {
		return new ResponseEntity<List<CountriesRegionEntity>>(countryService.getAllRegions(), HttpStatus.OK);
	}

	@PostMapping("country/region/")
	public ResponseEntity<CountriesRegionEntity> addRegion(@RequestBody CountriesRegionEntity region)
			throws GaiaException {
		return new ResponseEntity<CountriesRegionEntity>(countryService.addRegion(region), HttpStatus.OK);
	}

	@PutMapping("country/region/{id}")
	public ResponseEntity<CountriesRegionEntity> updateRegion(@PathVariable long id,
			@RequestBody CountriesRegionEntity region) throws GaiaException {
		return new ResponseEntity<CountriesRegionEntity>(countryService.addRegion(region), HttpStatus.OK);
	}

	@DeleteMapping("country/region/{id}")
	public ResponseEntity<ResponseVm> deleteRegion(@PathVariable long id) throws GaiaException {
		countryService.deleteRegion(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@DeleteMapping("country/region")
	public ResponseEntity<ResponseVm> deleteRegionByCountryId(@RequestParam long countryId) throws GaiaException {
		countryService.deleteRegionByCountryId(countryId);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	// Area
	@GetMapping("country/region/area/{id}")
	public ResponseEntity<CountriesRegionAreaEntity> getArea(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<CountriesRegionAreaEntity>(countryService.getArea(id), HttpStatus.OK);
	}

	@GetMapping("country/region/area")
	public ResponseEntity<List<CountriesRegionAreaEntity>> getAreas(@RequestParam long countryId,
			@RequestParam long regionId) throws GaiaException {
		return new ResponseEntity<List<CountriesRegionAreaEntity>>(countryService.getAreas(countryId, regionId),
				HttpStatus.OK);
	}

	@GetMapping("country/region/area/all")
	public ResponseEntity<List<CountriesRegionAreaEntity>> getAllAreas() throws GaiaException {
		return new ResponseEntity<List<CountriesRegionAreaEntity>>(countryService.getAllAreas(), HttpStatus.OK);
	}

	@PostMapping("country/region/area")
	public ResponseEntity<CountriesRegionAreaEntity> addArea(@RequestBody CountriesRegionAreaEntity area)
			throws GaiaException {
		return new ResponseEntity<CountriesRegionAreaEntity>(countryService.addArea(area), HttpStatus.OK);
	}

	@PutMapping("country/region/area/{id}")
	public ResponseEntity<CountriesRegionAreaEntity> updateArea(@PathVariable long id,
			@RequestBody CountriesRegionAreaEntity area) throws GaiaException {
		return new ResponseEntity<CountriesRegionAreaEntity>(countryService.addArea(area), HttpStatus.OK);
	}

	@DeleteMapping("country/region/area/{id}")
	public ResponseEntity<ResponseVm> deleteArea(@PathVariable long id) throws GaiaException {
		countryService.deleteArea(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

}
