/**
 * 
 */
package com.example.demo.client;



import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * @author jovan https://github.com/JoBaHP
 *                 https://gitlab.com/JoBaHP
 */
//To interact with a secure API, 
//must to programmatically Base64-encode the user’s credentials and also
//construct an authorization request header by prefixing Basic to the encoded value
public class UserRegistrationClientBasicAuth {
	private static final String securityUserName = "admin";
    private static final String securityUserPassword = "password";
    
    private static final String USER_REGISTRATION_BASE_URL =
                               "http://localhost:8080/api/user/";
    private static RestTemplate restTemplate = new RestTemplate();
    
    //concatenate securityUserName and securityUserPassword, and then Base64
    //encodes this user credential and creates authenticationHeaders
    //by prefixing Basic to this encoded value
    
    /*Creating an instance of HttpEntity of type Void
     *  by passing authenticationHeaders to its constructor
     * and called RestTemplate’s exchange method 
     * to perform an HTTP DELETE operation with a responseType of Void.
    */
    
    public void deleteUserById(Long userId) {
    	 String userCredential = securityUserName + ":" + securityUserPassword;
    	 byte[] base64UserCredentialData = Base64.encodeBase64(userCredential.getBytes());
    	 HttpHeaders authenticationHeaders = new HttpHeaders();
    	 authenticationHeaders.set("Authorization",
    			 "Basic " + new String(base64UserCredentialData));
    	 HttpEntity<Void> httpEntity = new HttpEntity<Void>(authenticationHeaders);
    	 
    	 restTemplate.exchange(USER_REGISTRATION_BASE_URL + "/{id}",
    			 HttpMethod.DELETE, httpEntity, Void.class, userId);
    }

}
