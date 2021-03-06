package com.examportal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.examportal.model.Role;
import com.examportal.model.User;
import com.examportal.model.UserRole;
import com.examportal.service.UserService;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
 
//		 System.out.println("Code started...");
//		 User user = new User();
//		 user.setFirstname("Ajay");
//		 user.setLastname("Kumar");
//		 user.setUsername("admin");
//		 user.setPassword(this.bCryptPasswordEncoder.encode("admin123"));
//		 user.setPhone("9998887771");
//		 user.setEmail("ajay@gmail.com");
//		 user.setProfile("default.png");
//		 
//		 Role role = new Role();
//		 role.setRoleId(100L);
//		 role.setRoleName("ADMIN");
//		 
//		 Set<UserRole> userRoleSet = new HashSet<>();
//		 UserRole userRole = new UserRole();
//		 userRole.setRole(role);
//		 userRole.setUser(user);
//		 
//		 userRoleSet.add(userRole);
//		 
//		 User createUser = this.userService.createUser(user, userRoleSet);
//		 System.out.println(createUser.getUsername());
//		 
	 
	}

}
