package com.revature.controllers;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.revature.models.User;
import com.revature.services.UserService;
@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/record")
@ResponseBody

public class RecordController {

	private UserService uSer;  
	
	@Autowired
	public RecordController() {
		super();
	}

	// getting all user records 
	@GetMapping
	public ResponseEntity<List<User>> getUserRecords() {
		List<User> uList = uSer.findAllUsers();

		if (uList == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		List<User> newList = new ArrayList<>();
		
		for (User u : uList) {
			User addToList = new User(u.getUserId(), u.getFirst(), u.getLast(), u.getUserRecord());
			newList.add(addToList);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(newList);
	}
}
