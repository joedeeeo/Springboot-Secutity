package com.sbs.service;

import java.util.List;

import com.sbs.domain.StudentDetails;

public interface StudentService {

	public StudentDetails saveStudent(StudentDetails student);
	public StudentDetails getStudentByUsername(String username);
	public List<StudentDetails> getAllStudent();
}
