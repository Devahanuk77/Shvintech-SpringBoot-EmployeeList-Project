package com.demo.model;

import org.springframework.http.HttpStatus;

public class HttpResponse<T> {
    private T response;
    private HttpStatus status;

    public HttpResponse(T response, HttpStatus status) {
        this.response = response;
        this.status = status;
    }

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public HttpResponse() {
		
	}

	

    
}
