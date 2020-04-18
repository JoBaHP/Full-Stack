/**
 * 
 */
package com.example.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author jovan https://github.com/JoBaHP 
 * https://gitlab.com/JoBaHP
 */
@Entity
@Table(name = "Users")
public class UsersDTO {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;
	@Column(name = "NAME")
	@Length(max = 50, message ="error.name.length")
	@NotEmpty(message ="error.name.empty")  //ensures that the input string is not null
	private String name;
	@Column(name="EMAIL")
	@Email(message = "error.email.email")     //validate the input string with a valid e-mail address
	@NotEmpty(message = "error.email.empty")
	private String email;
	@Column(name = "ADDRESS")
	@NotEmpty(message = "error.address.empty") // ensures that the input string is not null
	@Length(max = 150, message = "error.address.length")
	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
