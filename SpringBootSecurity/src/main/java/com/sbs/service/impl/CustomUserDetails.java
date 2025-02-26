package com.sbs.service.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sbs.domain.StudentDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class CustomUserDetails implements UserDetails{

//	private String userName;
//	private String password;
//	private String roles;
//	private boolean isActive;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudentDetails std;

//	public CustomUserDetails(String userName, String password, String roles, Boolean isActive) {
//		super();
//		this.userName = userName;
//		this.password = password;
//		this.roles = roles; 
//		this.isActive = isActive;
//	}
	
	public CustomUserDetails(StudentDetails std) {
		super();
		this.std = std;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority(std.getRoles()));
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
		return std.getIsEnable();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return std.getUserName();
	}

	

	

}
