package com.api.board.exception.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ResponseError {

	private int code;
	private String message;

	public ResponseError() {
	}

	public ResponseError(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}