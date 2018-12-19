package com.gaia.web.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.GaiaException;
import com.gaia.service.GlobalService;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalController {

	@Autowired
	private GlobalService globalService;

	@GetMapping("global/filter")
	public <T> ResponseEntity<Map<String, List<T>>> filterGlobal(
			@RequestParam(name = "name", required = true) String name) throws GaiaException {
		Map<String, List<T>> map = globalService.filterGlobal(name);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
