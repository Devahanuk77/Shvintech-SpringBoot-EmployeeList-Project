package com.demo.model;

public class Response<T> {

    int recordCount;
    T response;
    
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	
	public Response(int recordCount, T response) {
		this.recordCount = recordCount;
		this.response = response;
	}
	
	public Response() {
	}
	
	@Override
	public String toString() {
		return "APIResponse [recordCount=" + recordCount + ", response=" + response + "]";
	}
	
	
	
	
	
    
    

}
