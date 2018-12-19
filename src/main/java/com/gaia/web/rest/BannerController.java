package com.gaia.web.rest;

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
import com.gaia.domain.BannerEntity;
import com.gaia.domain.BannerImageEntity;
import com.gaia.service.BannerService;
import com.gaia.web.rest.vm.ResponseVm;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BannerController {

	@Autowired
	private BannerService bannerService;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@GetMapping("banner")
	public ResponseEntity<BannerEntity> getBanner(@RequestParam(name = "bannerId", required = false) Long bannerId,
			@RequestParam(name = "bannerName", required = false) String bannerName) throws GaiaException {
		BannerEntity banner;
		if (bannerId != null)
			banner = bannerService.getBanner(bannerId);
		else
			banner = bannerService.getBanner(bannerName);
		return new ResponseEntity<BannerEntity>(banner, HttpStatus.OK);
	}

	@GetMapping("banners")
	public ResponseEntity<List<BannerEntity>> getBanners() throws GaiaException {
		return new ResponseEntity<List<BannerEntity>>(bannerService.getBanners(), HttpStatus.OK);
	}

	@PostMapping("banner/add")
	public ResponseEntity<BannerEntity> addBanner(@RequestBody BannerEntity banner) throws GaiaException {
		return new ResponseEntity<BannerEntity>(bannerService.saveBanner(banner), HttpStatus.OK);
	}

	@DeleteMapping("banner/delete/{id}")
	public ResponseEntity<ResponseVm> deleteBanner(@PathVariable long id) {
		bannerService.deleteBanner(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("banner/update/{id}")
	public ResponseEntity<ResponseVm> updateBanner(@RequestBody BannerEntity banner, @PathVariable long id)
			throws GaiaException {
		BannerEntity oldBanner = bannerService.getBanner(id);
		if (oldBanner == null)
			return ResponseEntity.notFound().build();
		bannerService.saveBanner(banner);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@GetMapping("banner/image/{id}")
	public ResponseEntity<BannerImageEntity> getBannerImage(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<BannerImageEntity>(bannerService.getBannerImage(id), HttpStatus.OK);
	}

	@GetMapping("banner/images/{bannerId}")
	public ResponseEntity<List<BannerImageEntity>> getBannerImages(@PathVariable long bannerId) throws GaiaException {
		return new ResponseEntity<List<BannerImageEntity>>(bannerService.getBannerImages(bannerId), HttpStatus.OK);
	}

	@PostMapping("banner/image/add")
	public ResponseEntity<BannerImageEntity> addBannerImage(@RequestBody BannerImageEntity bannerImage)
			throws GaiaException {
		return new ResponseEntity<BannerImageEntity>(bannerService.saveBannerImage(bannerImage), HttpStatus.OK);
	}

	@DeleteMapping("banner/image/delete/{id}")
	public ResponseEntity<ResponseVm> deleteBannerImage(@PathVariable long id) {
		bannerService.deleteBannerImage(id);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}

	@PutMapping("banner/image/update/{id}")
	public ResponseEntity<ResponseVm> updateBannerImage(@RequestBody BannerImageEntity bannerImage,
			@PathVariable long id) throws GaiaException {
		BannerImageEntity oldBannerImage = bannerService.getBannerImage(id);
		if (oldBannerImage == null)
			return ResponseEntity.notFound().build();
		bannerService.saveBannerImage(bannerImage);
		return new ResponseEntity<ResponseVm>(ResponseVm.getSuccessVm(), HttpStatus.OK);
	}
}
