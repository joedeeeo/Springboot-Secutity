package com.sbs.springbootsecurity.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sbs.springbootsecurity.domain.Student;
import com.sbs.springbootsecurity.proxy.LoginRequest;
import com.sbs.springbootsecurity.proxy.LoginResponse;
import com.sbs.springbootsecurity.proxy.StudentProxy;
import com.sbs.springbootsecurity.repository.StudentRepo;
import com.sbs.springbootsecurity.utils.JwtUtils;
import com.sbs.springbootsecurity.utils.MapperUtils;

@Service
public class StudentServiceImpl extends AdminServiceImpl {

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	public StudentProxy registerStudent(StudentProxy studentProxy,MultipartFile file) {
		// TODO Auto-generated method stub
		Student sid = new Student();
		studentProxy.setPassword(bCryptPasswordEncoder.encode(studentProxy.getPassword()));
		Student std = MapperUtils.convertValue(studentProxy, Student.class);
		Student savedata = studentRepo.save(std);
		studentProxy.setId(savedata.getId());
		
		try {
		 sid.setProfileimage(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentProxy;
	}

	@Override
	public List<StudentProxy> getAllStudent() {
		// TODO Auto-generated method stub
		List<Student> stdList = studentRepo.findAll();
		List<StudentProxy> proxyStdList = MapperUtils.convertListValue(stdList, StudentProxy.class);
		return proxyStdList;
	}

	@Override
	public StudentProxy getStudentByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginResponse stdLogin(LoginRequest logReq) {
		// TODO Auto-generated method stub
//		Optional<Student> std = studentRepo.findByUsername(logreq.getUsername());
		
		Authentication  auth = new UsernamePasswordAuthenticationToken(logReq.getUsername(), logReq.getPassword());
		Authentication verfAuth = authManager.authenticate(auth);
		
		System.err.print("Generated Token=>"+jwtUtils.generateKey());
		
		if(verfAuth.isAuthenticated()) {
			return new LoginResponse(logReq.getUsername(),jwtUtils.generateToken(logReq.getUsername()));
		}
		
		return new LoginResponse(logReq.getUsername(),"Request Failed");
	}

	@Override
	public String uploadProfileImage(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
//	@Override
//	public Student saveStudent(Student student) {
//		// TODO Auto-generated method stub
//		student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
//		return studentRepo.save(student);
//	}

//	@Override
//	public Student getStudentByUsername(String username) {
//		// TODO Auto-generated method stub
//		return studentRepo.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("Enter valid username"));
//	}
//
//	@Override
//	public List<Student> getAllStudent() {
//		// TODO Auto-generated method stub
//		return studentRepo.findAll();
//	}

}
