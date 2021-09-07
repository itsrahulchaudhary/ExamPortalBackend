package com.examportal.service;

import java.util.Set;
 
import com.examportal.model.User;
import com.examportal.model.UserRole;


public interface UserService {
	
	public User createUser(User user, Set<UserRole> userRole) throws Exception;
	
	//Get user by username
	public User getUser(String username);
	
	//Delete user by Id
	public void deleteUser(Long UserId);

}
