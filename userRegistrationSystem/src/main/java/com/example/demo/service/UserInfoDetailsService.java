/**
 * 
 */
package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserInfo;
import com.example.demo.repository.UserInfoJpaRepository;

/**
 * @author jovan https://github.com/JoBaHP
 *                 https://gitlab.com/JoBaHP
 *                 
 *   is used to return user information from the back end (database)
 *   that gets compared with a submitted user’s credentials during the authentication process
 */
@Service
public class UserInfoDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	@Autowired
    private UserInfoJpaRepository userInfoJpaRepository;
	
/*
 * This method first checks if user retrieved from the database is null and, if so, throws 
 * a UsernameNotFoundException, otherwise creates an instance 
 * of org.springframework.security.core.userdetails.User and populates it
 * with the user data returned from the database
 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserInfo user = userInfoJpaRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Opps! user not found with user-name: " + username);
			
		}
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), getAuthorities(user));
	}
	private Collection<GrantedAuthority> getAuthorities(UserInfo user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities = AuthorityUtils.createAuthorityList(user.getRole());
        return authorities;
        
	}

}
