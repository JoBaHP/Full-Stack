/**
 * 
 */
package com.example.demo.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.UsersDTO;

/**
 * @author jovan https://github.com/JoBaHP
 *                 https://gitlab.com/JoBaHP
 */
//creating a RestTemplate instance at the class level as RestTemplate
public class UserRegistrationClient {
	
	private static RestTemplate restTemplate = new RestTemplate();
	private static final String USER_REGISTRATION_BASE_URL = 
			                                  "http://localhost:8080/api/user/";
	//created a getUserById method to call RestTemplate’s 
	//getForObject method to consume the REST API
	public UsersDTO getUserById(final Long userId) {
		return restTemplate.getForObject(USER_REGISTRATION_BASE_URL + "/{id}",
				                        UsersDTO.class, userId);
	}
	//RestTemplate will convert the HTTP response content from the server to the
	//UserDTO instance using HttpMessageConverter
	public UsersDTO createUser(final UsersDTO user) {
		return restTemplate.postForObject(USER_REGISTRATION_BASE_URL,user,UsersDTO.class);
	}
	public void updateUser(final Long userId, final UsersDTO user) {
        restTemplate.put( USER_REGISTRATION_BASE_URL + "/{id}",user, userId);
        
	}
	public void deleteUser(final Long userId) {
        restTemplate.delete( USER_REGISTRATION_BASE_URL + "/{id}",userId);
        
	}
	public ResponseEntity<UsersDTO> getUserByIdUsingExchangeAPI(final Long userId) {
		  HttpEntity<UsersDTO> httpEntity = new HttpEntity<UsersDTO>(new UsersDTO());
		        return restTemplate.exchange(USER_REGISTRATION_BASE_URL + "/{id}",
		                        HttpMethod.GET, httpEntity, UsersDTO.class,        userId);
		}

}
