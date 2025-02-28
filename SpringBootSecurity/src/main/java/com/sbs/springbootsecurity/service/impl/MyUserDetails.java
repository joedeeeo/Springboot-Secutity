package com.sbs.springbootsecurity.service.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sbs.springbootsecurity.domain.Student;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class MyUserDetails implements UserDetails{

//	private String userName;
//	private String password;
//	private String roles;
//	private boolean isActive;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Student std;

//	public CustomUserDetails(String userName, String password, String roles, Boolean isActive) {
//		super();
//		this.userName = userName;
//		this.password = password;
//		this.roles = roles; 
//		this.isActive = isActive;
//	}
	
	public MyUserDetails(Student std) {
		super();
		this.std = std;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority(std.getRole().toString()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return std.getPassword();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return Boolean.TRUE;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return Boolean.TRUE;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return Boolean.TRUE;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return std.getIsActive();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return std.getUsername();
	}

	

	

}
