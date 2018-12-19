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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.ErrorCodes;
import com.gaia.common.GaiaException;
import com.gaia.domain.WebsitesEntity;
import com.gaia.service.WebsitesService;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class WebsitesController {

	@Autowired
	private WebsitesService webServ;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@GetMapping("websites/{id}")
	public ResponseEntity<WebsitesEntity> getWebLang(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<WebsitesEntity>(webServ.getWebsites(id), HttpStatus.OK);
	}

	@GetMapping("websites")
	public ResponseEntity<PagedResources<Resource<WebsitesEntity>>> getWebLang(
			PagedResourcesAssembler<WebsitesEntity> assembler, @RequestParam Map<String, String> map, Pageable pageable)
			throws GaiaException {

		WebsitesEntity webLangEntity = dozerBeanMapper.map(map, WebsitesEntity.class);
		Map<String, Long> request = dozerBeanMapper.map(webLangEntity, Map.class);
		Page<WebsitesEntity> response = webServ.getWebsites(request, pageable);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}
		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

}
