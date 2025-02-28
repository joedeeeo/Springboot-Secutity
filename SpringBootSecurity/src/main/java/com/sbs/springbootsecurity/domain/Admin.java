package com.sbs.springbootsecurity.domain;

import com.sbs.springbootsecurity.enums.GenderEnum;
import com.sbs.springbootsecurity.enums.RoleEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "administrator")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private RoleEnum role;
	
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	
	private String address;
	private Boolean isActive;
}
