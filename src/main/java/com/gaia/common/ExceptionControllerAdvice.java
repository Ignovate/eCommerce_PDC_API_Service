package com.gaia.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gaia.web.rest.vm.ResponseVm;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(GaiaException.class)
	public ResponseEntity<ResponseVm> notFoundException(final GaiaException e) {
		return new ResponseEntity<ResponseVm>(ResponseVm.getErrorVm(e), HttpStatus.OK);
	}

}
