package com.sbs.springbootsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sbs.springbootsecurity.proxy.LoginRequest;
import com.sbs.springbootsecurity.proxy.LoginResponse;
import com.sbs.springbootsecurity.proxy.StudentProxy;
import com.sbs.springbootsecurity.service.SBSService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class StudentController {

//	List<Student> student = new ArrayList<>(List.of(new Student(10l, "vikram", "IND"),
//			new Student(11l, "nishit", "ahd"), new Student(12l, "aryan", "USA"), new Student(13l, "panshul", "raj")));
	
	@Autowired
	private SBSService studentService;
	
//	@GetMapping("/get-all-student")
//	public List<Student>getAllStudents(){
//		return studentService.getAllStudent();
//	}
	
//	@PostMapping("/add-student")
//	public Student addStudent(@RequestBody Student std) {
//		return studentService.saveStudent(std);
//	}
	
//	@GetMapping("/get-student/{username}")
//	public Student getStudent(@PathVariable("username") String username) {
//		return studentService.getStudentByUsername(username);
//	}
	
//	@GetMapping("/")
//	public String msg() {
//		return "Hello from SpringBoot";
//	}
	
	@GetMapping("/get-session-id")
	public String getSessionId(HttpServletRequest hs) {
		return hs.getSession().getId();
	}
	
	@GetMapping("/get-csrf")
	public CsrfToken getCsrfToken(HttpServletRequest hs) {
		return (CsrfToken) hs.getAttribute("_csrf");
	}
	
	@GetMapping("/student-home")
	public ResponseEntity<String>home(){
		return new ResponseEntity<String>("welcome to student home page",HttpStatus.OK);
	}
	
	@PostMapping("/student-register")
	public ResponseEntity<StudentProxy>registerStudent(@RequestBody StudentProxy studentProxy,MultipartFile file){
		return new ResponseEntity<StudentProxy>(studentService.registerStudent(studentProxy, file),HttpStatus.CREATED);
	}
	
	@GetMapping("/get-all-student")
	public ResponseEntity<List<StudentProxy>>getAllStudent(){
		return new ResponseEntity<List<StudentProxy>>(studentService.getAllStudent(),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest logreq){
		return new ResponseEntity<LoginResponse>(studentService.stdLogin(logreq),HttpStatus.ACCEPTED);
	}
}
