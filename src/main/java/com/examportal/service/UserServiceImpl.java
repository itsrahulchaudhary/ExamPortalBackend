package com.examportal.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.model.User;
import com.examportal.model.UserRole;
import com.examportal.repository.RoleRepository;
import com.examportal.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	// Creating User
	@Override
	public User createUser(User user, Set<UserRole> userRole) throws Exception {

		User userName = this.userRepository.findByUsername(user.getUsername());

		if (userName != null) {
			System.out.println("User is aready present !!!");
			throw new Exception("User is aready present ");
		} else {
			// create user
			for (UserRole ur : userRole) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRole);
			userName = this.userRepository.save(user);

		}

		return userName;
	}

	// Get user by Username
	@Override
	public User getUser(String username) {

		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long UserId) {
		userRepository.deleteById(UserId);
		
	}

}
