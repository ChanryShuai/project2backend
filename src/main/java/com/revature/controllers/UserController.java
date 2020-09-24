package com.revature.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.services.UserService;
import com.revature.models.User;

@CrossOrigin
@Controller
@RequestMapping(value = "/user")
@ResponseBody
public class UserController {

	private UserService uSer;

	@Autowired
	public UserController(UserService uSer) {
		super();
		this.uSer = uSer;
	}

	// register new user
	@PostMapping("/register")
	public ResponseEntity<User> addUser(@RequestBody User u) {
		User u1 = uSer.insertUser(u);
		if (u == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
		}
		return ResponseEntity.status(HttpStatus.OK).body(u1);
	}

	// retrieving user info based on input
	@GetMapping("/{input}")
	public ResponseEntity<User> getUserByUsename(@PathVariable("input") String input) {
		// if input = user_Id
		if (isNumeric(input)) {
			User u = uSer.selectByUserId(Integer.parseInt(input));
			if (u == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // .build() builds an empty response body
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(u);
			// if input = username
		} else {
			
			User u = uSer.findByUsername(input);
			if (u == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // .build() builds an empty response body
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(u);

		}
	} 

	// getting a list of all users
	@GetMapping
	public ResponseEntity<List<User>> findAllUsers() {
		List<User> uList = uSer.findAllUsers();

		if (uList == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uList);
	}

	// update one user
	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User u) {
		User u1 = uSer.updateUser(u);
		if (u1 == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // .build() builds an empty response body
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(u1);
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
