/**
 * 
 */
package com.example.demo.exception;

import java.awt.TrayIcon.MessageType;

/**
 * @author jovan https://github.com/JoBaHP 
 *               https://gitlab.com/JoBaHP
 */
public class FieldValidationError {

	private String field;
	private String message;
	// MessageType is an enumeration containing the possible message types
	private MessageType type;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

}
