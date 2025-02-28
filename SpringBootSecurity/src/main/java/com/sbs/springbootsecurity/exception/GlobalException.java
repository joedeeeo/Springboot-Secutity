package com.sbs.springbootsecurity.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(value = ExpiredJwtException.class)
	public Map<String, Object>exception(HttpServletRequest req ,HttpServletResponse resp,ExpiredJwtException e){
		Map<String, Object> errMap=new HashMap<>();
		errMap.put("ErrorPath",req.getServletPath());
		errMap.put("ErrorMessage", e.getMessage());
		errMap.put("ErrorClass", e.getClass());
		errMap.put("Status", HttpServletResponse.SC_NOT_ACCEPTABLE);
//		response.setContentType("application/json");
//		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		ObjectMapper mapper = new ObjectMapper();
//		
//		mapper.writeValue(response.getOutputStream(),errMap);
		return errMap;
	}
	
	@ExceptionHandler(value = Exception.class)
	public Map<String, Object>exception(HttpServletRequest req ,HttpServletResponse resp,Exception e){
		Map<String, Object> errMap=new HashMap<>();
		errMap.put("ErrorPath",req.getServletPath());
		errMap.put("ErrorMessage", e.getMessage());
		errMap.put("ErrorClass", e.getClass());
		errMap.put("Status", HttpServletResponse.SC_UNAUTHORIZED);
//		response.setContentType("application/json");
//		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		ObjectMapper mapper = new ObjectMapper();
//		
//		mapper.writeValue(response.getOutputStream(),errMap);
		return errMap;
	}
	

}
