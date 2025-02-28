package com.sbs.springbootsecurity.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sbs.springbootsecurity.service.impl.MyUserDetailsService;
import com.sbs.springbootsecurity.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getHeader("Authorization") != null) {
			String tokenString = request.getHeader("Authorization");
			String token = tokenString.substring(7);
			String userName = jwtUtils.extractUserName(token);
			UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);
 
			if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				if (jwtUtils.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
 
				}
			}
 
		}
		filterChain.doFilter(request, response);
	}
 

}
