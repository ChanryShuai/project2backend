package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.UserService;


@CrossOrigin 
@RestController
@RequestMapping(value="/login")
public class LoginController {
 
	private UserService uSer;

	@Autowired
	public LoginController(UserService uSer) {
		super(); 
		this.uSer = uSer;
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}
		
		// Takes in a LoginDTO object: returns it if found and null if not found
		@PostMapping(value="/validate",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<User> getUserLogin(@RequestBody LoginDTO l) {
			try {
			User temp = uSer.findByUsername(l.getUsername());
			if (temp.getPassword().equals(l.getPassword())) {
				System.out.println(l);
				User loggedInUser = temp;
				return new ResponseEntity<User>(loggedInUser, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			} catch (NullPointerException e) {
				return null;
			}
		}

		public UserService getuSer() {
			return uSer;
		}

		public void setuSer(UserService uSer) {
			this.uSer = uSer;
		}
		
		
}
