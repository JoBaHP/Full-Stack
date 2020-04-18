/**
 * 
 */
package com.example.demo.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UsersDTO;
import com.example.demo.exception.CustomErrorType;
import com.example.demo.repository.UserJpaRepository;

/**
 * @author jovan https://github.com/JoBaHP https://gitlab.com/JoBaHP
 */
@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {

	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);

	private UserJpaRepository userJpaRepository;

	// Annotation to autowire UserJpaRepository
	@Autowired
	public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	/*
	 * The listAllUsers method returns ResponseEntity containing the HTTP response.
	 * This method reads all of the users using UserJpaRepository
	 */
	@GetMapping("/")
	public ResponseEntity<List<UsersDTO>> listAllUsers() {
		List<UsersDTO> users = userJpaRepository.findAll();

		// verification handler
		if (users.isEmpty()) {
			return new ResponseEntity<List<UsersDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UsersDTO>>(users, HttpStatus.OK);
	}

	/*
	 * shortcut for @RequestMapping(value="/", method=RequestMethod.POST) creates a
	 * new user using the enclosed body of the request creating new user
	 */
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsersDTO> createUser(@Valid @RequestBody final UsersDTO user) {
        /* Valid annotation will instruct Spring to perform request data validation after
         * binding incoming POST parameters with an object
         * A resulting MethodArgumentNotValidException is handled in the
         * DefaultHandlerExceptionResolver and results in a 400 response code
         */
		if (userJpaRepository.findByName(user.getName()) != null) {
			return new ResponseEntity<UsersDTO>(
					new CustomErrorType(
							"Unable to create new user. A User with name " + user.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		userJpaRepository.save(user); // delegated the UserDTO persistence to userJpaRepository’s save method

		return new ResponseEntity<UsersDTO>(user, HttpStatus.CREATED); // created a new ResponseEntity

	}

	// shortcut for @RequestMapping(method =RequestMethod.PUT)
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsersDTO> updateUser(@PathVariable("id") final Long id, @RequestBody UsersDTO user) {

		// fetch user based on id and set it to currentUser object of type UserDTO
		UsersDTO currentUser = userJpaRepository.findById(id);
		if (currentUser == null) {
			return new ResponseEntity<UsersDTO>(
					new CustomErrorType("Unable to upate. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		// update currentUser object data with user object data
		currentUser.setName(user.getName());
		currentUser.setAddress(user.getAddress());
		currentUser.setEmail(user.getEmail());

		// save currentUser obejct
		userJpaRepository.saveAndFlush(currentUser);

		// return ResponseEntity object
		return new ResponseEntity<UsersDTO>(currentUser, HttpStatus.OK);
	}

	// Shortcut for @RequestMapping(method = RequestMethod.DELETE)
	// Requests that the application deletes resources identified by Request-URI
	// @PreAuthorize annotation only allows an authorized user to invoke the deleteUser method
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<UsersDTO> deleteUser(@PathVariable("id") final Long id) {
		UsersDTO user = userJpaRepository.findById(id); // UserJpaRepository’s delete method by passing the argument
		if (user == null) {                             // id from pathvariable
			return new ResponseEntity<UsersDTO>(
					new CustomErrorType("Unable to delete. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UsersDTO>(HttpStatus.NO_CONTENT);
	}

	// method to get user by Id
	@GetMapping("/{id}")
	public ResponseEntity<UsersDTO> getUserById(@PathVariable("id") final Long id) {
		UsersDTO user = userJpaRepository.findById(id);
		if (user == null) {
			return new ResponseEntity<UsersDTO>(new CustomErrorType("User with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UsersDTO>(user, HttpStatus.OK);
	}

}
