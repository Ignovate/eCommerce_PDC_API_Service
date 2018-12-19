package com.gaia.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AddToCartService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Long addToCart(Long quoteId, Long custId, Long prodId, Long qty, Long websiteId, Long addrId, String action)
			throws Exception {

		Connection conn;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();

			CallableStatement cs = conn.prepareCall("{call P_GAIA_ADDTO_CART(?,?,?,?,?,?,?,?)}");
			cs.setLong("P_QUOTE_ID", quoteId);
			cs.setLong("P_CUSTOMER_ID", custId);
			cs.setLong("P_PROD_ID", prodId);
			cs.setLong("P_QTY", qty);
			cs.setLong("P_WEBSITE_ID", websiteId);
			cs.setLong("P_ADDRESS_ID", addrId);
			cs.setString("P_ACTION", action);
			cs.registerOutParameter("O_QUOTE_ID", Types.INTEGER);

			cs.executeUpdate();
			return cs.getLong("O_QUOTE_ID");
		} catch (SQLException e) {
			throw new Exception(e);
		}
	}

}
