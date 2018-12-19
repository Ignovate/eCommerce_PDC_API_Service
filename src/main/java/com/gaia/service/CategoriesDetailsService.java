package com.gaia.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gaia.domain.CategoriesDetailsEntity;
import com.gaia.repository.CategoriesDetailsRepo;
import com.gaia.web.rest.vm.CategoriesDetailsVm;

@Service
public class CategoriesDetailsService {

	@Autowired
	private CategoriesDetailsRepo repo;

	@Autowired
	private DozerBeanMapper beanMapper;

	public CategoriesDetailsEntity addCatDetails(CategoriesDetailsVm request) {
		CategoriesDetailsEntity catDetails = beanMapper.map(request, CategoriesDetailsEntity.class);
		catDetails.setImg(Base64.getDecoder().decode(request.getImage()));
		catDetails.setThumnail(Base64.getDecoder().decode(request.getThumbnail()));
		return repo.save(catDetails);
	}

	public CategoriesDetailsVm getCatDetails(Long id) {
		CategoriesDetailsEntity catDetails = repo.findById(id).orElse(null);
		return createResponse(catDetails);
	}

	public Page<CategoriesDetailsVm> getCatDetails(Map<String, Long> map, Pageable pageable) {
		Specification<CategoriesDetailsEntity> spec = new Specification<CategoriesDetailsEntity>() {
			@Override
			public Predicate toPredicate(Root<CategoriesDetailsEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				map.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});
				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		Page<CategoriesDetailsEntity> catDetList = repo.findAll(spec, pageable);

		List<CategoriesDetailsVm> response = new ArrayList<CategoriesDetailsVm>();
		for (CategoriesDetailsEntity cat : catDetList) {
			response.add(createResponse(cat));
		}
		Page<CategoriesDetailsVm> resp = new PageImpl<CategoriesDetailsVm>(response, pageable, response.size());
		return resp;
	}

	public void deleteCatDetails(Long id) {
		repo.deleteById(id);
	}

	private CategoriesDetailsVm createResponse(CategoriesDetailsEntity catDetails) {
		CategoriesDetailsVm response = null;
		if (catDetails != null) {
			response = beanMapper.map(catDetails, CategoriesDetailsVm.class);
			if (catDetails.getImg() != null)
				response.setImage(new String(Base64.getEncoder().encode(catDetails.getImg())));
			if (catDetails.getThumnail() != null)
				response.setThumbnail(new String(Base64.getEncoder().encode(catDetails.getThumnail())));
		}
		return response;
	}

	public void updateRecords(CategoriesDetailsVm oldDetails, CategoriesDetailsVm newDetails, long id) {
		beanMapper.map(newDetails, oldDetails);
		oldDetails.setId(id);
		addCatDetails(oldDetails);

	}

}
