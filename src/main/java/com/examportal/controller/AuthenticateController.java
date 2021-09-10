package com.examportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.config.JwtUtils;
import com.examportal.model.JwtRequest;
import com.examportal.model.JwtResponse;
import com.examportal.service.UserDetailsServiceImpl;

@RestController
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtils jwtutil;
	
	//generate token
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try {
			 authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not found");
		}
		UserDetails username = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String generateToken = this.jwtutil.generateToken(username);
		return ResponseEntity.ok(new JwtResponse(generateToken));
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch(DisabledException e) {
			throw new Exception("USER DISABLED "+e.getMessage());
		}catch(BadCredentialsException e) {
			throw new Exception("Invalid Credentials "+e.getMessage());
		}
		
	}

}
