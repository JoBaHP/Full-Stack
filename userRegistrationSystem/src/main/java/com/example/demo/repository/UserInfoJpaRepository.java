/**
 * 
 */
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.UserInfo;

/**
 * @author jovan https://github.com/JoBaHP
 *                 https://gitlab.com/JoBaHP
 */
@Repository
public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long> {
	//Spring Data JPA will provide a runtime implementation that
	//allows the findByUsername method to return a user
	

	UserInfo findByusername (String username);
	public UserInfo findByUsername(String username);

}
