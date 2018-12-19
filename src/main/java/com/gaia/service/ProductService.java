package com.gaia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gaia.domain.Product;
import com.gaia.domain.SingleProductEntity;
import com.gaia.domain.SortProductRowMapper;
import com.gaia.domain.mapper.ProductRowMapper;
import com.gaia.repository.ProductRepo;
import com.gaia.repository.SingleProductRepo;
import com.gaia.web.rest.vm.ProductVm;
import com.gaia.web.rest.vm.SortProductVm;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String singleProductQuery = "SELECT p.id, p.sku, pa.name, pp.price, pp.special_price, pp.special_price_start_date, pp.special_price_end_date, pi.image, pi.image_label, pin.stock, pa.measurement, pin.stock_status FROM products p LEFT JOIN products_attributes pa ON pa.product_id = p.id LEFT JOIN products_price pp ON pp.product_id = p.id LEFT JOIN products_images pi ON pi.product_id = p.id LEFT JOIN products_inventory pin ON pin.product_id = p.id LEFT JOIN products_status ps ON ps.product_id = p.id WHERE p.id = ? AND ps.status = 1";
	private String filterProductQry = "SELECT cp.category_id, p.id, p.sku, pa.name, pp.price, pp.special_price, pp.special_price_start_date, pp.special_price_end_date, pi.image, pi.image_label, pin.stock, pa.measurement, pin.stock_status FROM categories_products cp JOIN products p ON p.id = cp.product_id JOIN products_attributes pa ON pa.product_id = p.id JOIN products_price pp ON pp.product_id = p.id JOIN products_images pi ON pi.product_id = p.id JOIN products_inventory pin ON pin.product_id = p.id JOIN products_status ps ON ps.product_id = p.id JOIN brand b ON b.id = pa.brand WHERE (cp.category_id = ? AND (((pp.price > ? && pp.price < ?) OR (pp.special_price > ? && pp.special_price < ?)) OR pa.rating = ? OR pa.gender = ? OR b.id = ? )) AND ps.status = 1";

	// private String query = "select a.product_id,\r\n" +
	// " b.sku,\r\n" +
	// " a.name,\r\n" +
	// " a.measurement,\r\n" +
	// " c.stock,\r\n" +
	// " c.stock_status,\r\n" +
	// " d.price,\r\n" +
	// " d.special_price, \r\n" +
	// " e.image\r\n" +
	// "from\r\n" +
	// "products_attributes a , \r\n" +
	// "products b , \r\n" +
	// "products_inventory c , \r\n" +
	// "products_price d , \r\n" +
	// "products_images e , \r\n" +
	// "products_status f , \r\n" +
	// "products_deals g , \r\n" +
	// "products_type h\r\n" +
	// "where \r\n" +
	// "f.status=\"1\" and (g.start_date between ? and ?) and \r\n" +
	// "(g.end_date between ? and ?) and h.type = ? and a.brand = ? and
	// a.gender=? \r\n" +
	// "and (d.price between ? and ?);";
	//
	// @Autowired
	// private JdbcTemplate jdbcTemplate;

	@Autowired
	private SingleProductRepo singleProductRepo;

	public ProductVm getSingleProduct(Long productId) {
		List<ProductVm> response = jdbcTemplate.query(singleProductQuery, new Object[] { productId },
				new ProductRowMapper());
		return (response != null) ? response.get(0) : null;
	}

	public Product getProduct(Long id) {
		return productRepo.findById(id).orElse(null);
	}

	public Page<Product> getProduct(Map<String, Long> map, String sku, Pageable pageable) {
		Specification<Product> spec = new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();

				map.forEach((k, v) -> {
					predicate.add(criteriaBuilder.equal(root.get(k), v));
				});

				if (sku != null)
					predicate.add(criteriaBuilder.equal(root.get("sku"), sku));

				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return productRepo.findAll(spec, pageable);
	}

	public SingleProductEntity getProductView(long id) {

		return singleProductRepo.findById(id).orElse(null);
	}

	public List<SingleProductEntity> getProductView() {
		return singleProductRepo.findAll();
	}

	public List<SortProductVm> getSortProductView(String fromDT, String endDT, String type, String brand, String gender,
			String min, String max) {

		List<SortProductVm> response = jdbcTemplate.query(singleProductQuery,
				new Object[] { fromDT, endDT, fromDT, endDT, type, brand, gender, min, max },
				new SortProductRowMapper());
		return response;
	}

	public List<ProductVm> filterProduct(String gender, Long categoryId, Long brandId, Long minPrice, Long maxPrice,
			Long rating) {

		Object[] params = new Object[8];
		params[0] = categoryId;
		params[1] = minPrice != null ? minPrice : null;
		params[2] = maxPrice != null ? maxPrice : null;
		params[3] = minPrice != null ? minPrice : null;
		params[4] = maxPrice != null ? maxPrice : null;
		params[5] = rating != null ? rating : null;
		params[6] = gender != null ? gender : null;
		params[7] = brandId != null ? brandId : null;

		List<ProductVm> response = jdbcTemplate.query(filterProductQry, params, new ProductRowMapper());
		return response;
	}

}
