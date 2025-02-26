package com.sbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sbs.domain.StudentDetails;
import com.sbs.repository.StudentRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private StudentRepo studentRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		StudentDetails student = studentRepo.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("Enter valid username"));
		return new CustomUserDetails(student);
	}

}
