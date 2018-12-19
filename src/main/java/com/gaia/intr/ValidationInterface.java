package com.gaia.intr;

import com.gaia.web.rest.vm.ValidateResponse;

public interface ValidationInterface {
	public ValidateResponse validateToken(String token, String authmanUrl);

}
