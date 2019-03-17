package com.abu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abu.dao.Userdao;

import abu.dao.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	Userdao userdao;

	
	  public void setUserdao(Userdao userdao) { this.userdao = userdao; }
	 

	@Override
	public List<User> listAllUsers() {
 		return userdao.listAllUsers();
	}

	
	  @Override public void addUser(User user) { userdao.addUser(user);
	  
	  }
	  
	  @Override public void updateUser(User user) { userdao.updateUser(user); }
	  
	  @Override public void deleteUser(int id) { userdao.deleteUser(id); }
	  
	  @Override public User findUserById(int id) { return userdao.findUserById(id);
	  }
	 

}
