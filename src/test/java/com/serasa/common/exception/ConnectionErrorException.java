package com.serasa.common.exception;

import java.io.Serializable;

public class ConnectionErrorException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public ConnectionErrorException(String msg) {
		super(msg);
	}
	
}
