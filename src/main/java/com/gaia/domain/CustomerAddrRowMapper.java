package com.gaia.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gaia.web.rest.vm.CustomerAddrResponse;

public class CustomerAddrRowMapper implements RowMapper<CustomerAddrResponse> {

	@Override
	public CustomerAddrResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerAddrResponse custAddrResp = new CustomerAddrResponse();
		custAddrResp.setAddressId(rs.getLong("addressId"));
		custAddrResp.setCustId(rs.getLong("custId"));
		custAddrResp.setFirstname(rs.getString("firstname"));
		custAddrResp.setLastname(rs.getString("lastname"));
		custAddrResp.setStreetname(rs.getString("streetname"));
		custAddrResp.setCountryId(rs.getLong("countryId"));
		custAddrResp.setRegionId(rs.getLong("regionId"));
		custAddrResp.setAreaId(rs.getLong("areaId"));
		custAddrResp.setPostcode(rs.getString("postcode"));
		custAddrResp.setCountry(rs.getString("country"));
		custAddrResp.setRegion(rs.getString("region"));
		custAddrResp.setAreaname(rs.getString("areaname"));
		return custAddrResp;
	}

}
