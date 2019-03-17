package com.abu.dao;

import java.util.List;

import abu.dao.model.User;

public interface Userdao {

	public List<User> listAllUsers();

	
	  public void addUser(User user);
	  
	  public void updateUser(User user);
	  
	  public void deleteUser(int id);
	  
	  public User findUserById(int id);
	 

}
