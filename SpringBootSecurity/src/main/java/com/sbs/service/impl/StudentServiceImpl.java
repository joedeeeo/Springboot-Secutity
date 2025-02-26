package com.sbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbs.domain.StudentDetails;
import com.sbs.repository.StudentRepo;
import com.sbs.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public StudentDetails saveStudent(StudentDetails student) {
		// TODO Auto-generated method stub
		student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
		return studentRepo.save(student);
	}

	@Override
	public StudentDetails getStudentByUsername(String username) {
		// TODO Auto-generated method stub
		return studentRepo.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("Enter valid username"));
	}

	@Override
	public List<StudentDetails> getAllStudent() {
		// TODO Auto-generated method stub
		return studentRepo.findAll();
	}

}
