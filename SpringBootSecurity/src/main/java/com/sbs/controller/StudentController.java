package com.sbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.domain.StudentDetails;
import com.sbs.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class StudentController {

//	List<Student> student = new ArrayList<>(List.of(new Student(10l, "vikram", "IND"),
//			new Student(11l, "nishit", "ahd"), new Student(12l, "aryan", "USA"), new Student(13l, "panshul", "raj")));
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/get-all-student")
	public List<StudentDetails>getAllStudents(){
		return studentService.getAllStudent();
	}
	
	@PostMapping("/add-student")
	public StudentDetails addStudent(@RequestBody StudentDetails std) {
		return studentService.saveStudent(std);
	}
	
	@GetMapping("/get-student/{username}")
	public StudentDetails getStudent(@PathVariable("username") String username) {
		return studentService.getStudentByUsername(username);
	}
	
	@GetMapping("/")
	public String msg() {
		return "Hello from SpringBoot";
	}
	
	@GetMapping("/get-session-id")
	public String getSessionId(HttpServletRequest hs) {
		return hs.getSession().getId();
	}
	
	@GetMapping("/get-csrf")
	public CsrfToken getCsrfToken(HttpServletRequest hs) {
		return (CsrfToken) hs.getAttribute("_csrf");
	}
	
	
	
}
