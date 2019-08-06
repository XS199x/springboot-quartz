package com.example.demo.util.http;

import lombok.Getter;
import lombok.Setter;

/**
 * @author
 */
public class HttpResult {
	
	@Getter @Setter private Integer code;
	@Getter @Setter private String body;
	
	public HttpResult() {
		super();
	}
	
	public HttpResult(Integer code, String body) {
		this();
		this.code = code;
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "HttpResult{" +
			"code=" + code +
			'}';
	}
}
