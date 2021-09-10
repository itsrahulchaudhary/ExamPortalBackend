package com.examportal.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
 
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.examportal.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtjwtAuthenticationFilter extends OncePerRequestFilter{
	
    @Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
    
    @Autowired
    private JwtUtils jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			username = this.jwtUtil.extractUsername(jwtToken);
			try {
				
			}catch(ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("Jwt token has expired");
			}catch(Exception e) {
				e.printStackTrace();
				System.err.println("Error");
			}
		} else {
			System.out.println("Invalid Token , not start with bearer string");
		}
		
		// validate
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);

			if (this.jwtUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordToken);
			}

		}else {
			System.out.println("Token is not valid");
		}

		filterChain.doFilter(request, response);
	}

}
