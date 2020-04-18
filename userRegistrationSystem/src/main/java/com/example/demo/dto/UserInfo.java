/**
 * 
 */
package com.example.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author jovan https://github.com/JoBaHP
 *                 https://gitlab.com/JoBaHP
 *                 
 * will be stored in database so need JpaRepository to perform CRUD operations
 */
@Entity
@Table(name="users")
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userid")
	private Long id;
	@NotEmpty
	@Column(name="username")
	private String username;
	@NotEmpty
	@Column(name="password")
	private String password;
	@Column(name="enabled")
	private boolean isEnabled;
	@Column(name="role")
	private String role;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
	
	

}
