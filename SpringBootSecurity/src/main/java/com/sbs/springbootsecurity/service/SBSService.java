package com.sbs.springbootsecurity.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sbs.springbootsecurity.proxy.AdminProxy;
import com.sbs.springbootsecurity.proxy.LoginRequest;
import com.sbs.springbootsecurity.proxy.LoginResponse;
import com.sbs.springbootsecurity.proxy.StudentProxy;

public interface SBSService {

	//public Student saveStudent(Student student);
	//public Student getStudentByUsername(String username);
	//public List<Student> getAllStudent();
	
	public StudentProxy registerStudent(StudentProxy studentProxy,MultipartFile file);
	public AdminProxy registerAdmin();
	
	public List<StudentProxy>getAllStudent();
	public List<AdminProxy>getAllAdmin();
	
	public StudentProxy getStudentByUsername(String username);
	public AdminProxy getAdminByUsername(String username);
	
	public LoginResponse stdLogin(LoginRequest logreq);
	
	public String uploadProfileImage(MultipartFile file);
	
}
