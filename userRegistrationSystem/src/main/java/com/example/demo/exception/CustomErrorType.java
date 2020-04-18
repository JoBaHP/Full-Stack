/**
 * 
 */
package com.example.demo.exception;

import com.example.demo.dto.UsersDTO;

/**
 * @author jovan https://github.com/JoBaHP https://gitlab.com/JoBaHP
 */
public class CustomErrorType extends UsersDTO {

	private String errorMessage;

	public CustomErrorType(final String errorMessage) {
		this.errorMessage = errorMessage;

	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
