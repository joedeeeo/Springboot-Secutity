package com.sbs.springbootsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sbs.springbootsecurity.domain.Student;
import com.sbs.springbootsecurity.repository.StudentRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private StudentRepo studentRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Student std = studentRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Enter valid username"));
		return new MyUserDetails(std);
	}

}
