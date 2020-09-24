package com.revature.repositories;

import java.util.List;

import com.revature.models.User;

//@Repository
public interface IUserDAO { 
	
	public User findByUsername(String username);
	
	public User insert(User u);

	public User selectByUserId(int userId);

	public User updateUser(User u);

	public List<User> findAllUsers();

}
