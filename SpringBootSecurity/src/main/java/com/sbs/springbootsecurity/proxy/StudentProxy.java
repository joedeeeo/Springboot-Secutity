package com.sbs.springbootsecurity.proxy;

import com.sbs.springbootsecurity.enums.GenderEnum;
import com.sbs.springbootsecurity.enums.RoleEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProxy {

	private Long id;
	
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private RoleEnum role;
	
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	
	private String address;
	private Boolean isActive;
	
	private byte[] profileimage;
}
