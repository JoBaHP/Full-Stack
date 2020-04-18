/**
 * 
 */
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.UsersDTO;

/**
 * @author jovan https://github.com/JoBaHP
 *                 https://gitlab.com/JoBaHP
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UsersDTO, Long>{
	
	UsersDTO findByName(String name);

	/**
	 * @param id
	 * @return
	 */
	UsersDTO findById(Long id);
	

}
