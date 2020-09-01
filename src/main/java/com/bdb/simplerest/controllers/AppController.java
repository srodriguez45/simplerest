package com.bdb.simplerest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bdb.simplerest.models.SimpleResponse;


public class AppController {

	@Autowired
	protected SimpleResponse responseModel;
	
	protected Exception exception;
	
	protected HttpStatus status;
	
	public void before() {
		this.exception = null;
		this.status = null;
	}
	
	public ResponseEntity<SimpleResponse> setResponse(Object data) {
		
		HttpStatus code = HttpStatus.NOT_FOUND;
		String message = null;
		
		if(data != null && this.exception == null) {
			
			code = HttpStatus.OK;
			message = code.getReasonPhrase();
			
		} else if(this.exception != null) {
			
			code = HttpStatus.BAD_REQUEST;
			message = this.exception.getCause() + " Message exception: " + this.exception.getMessage();
			
		} 
		
		if(status != null) {
			code = status;
			message = code.getReasonPhrase();
		}
		
		responseModel.setMessage(message);
		responseModel.setCode(code);
		responseModel.setData(data);
		
		return new ResponseEntity<>(responseModel, responseModel.getCode());
		
	}
	
	
}
