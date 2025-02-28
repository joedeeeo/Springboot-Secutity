package com.sbs.springbootsecurity.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtExceptionHandler implements AuthenticationEntryPoint{
	
	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver exceptionResolverOne;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("hello");
		System.err.println("message:-"+authException.getMessage());
		
		
		
//		Map<String, Object> errMap=new HashMap<>();
//		errMap.put("ErrorPath",request.getServletPath());
//		errMap.put("ErrorMessage", authException.getMessage());
//		errMap.put("ErrorClass", authException.getClass());
//		errMap.put("Status", HttpServletResponse.SC_UNAUTHORIZED);
//		response.setContentType("application/json");
//		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		ObjectMapper mapper = new ObjectMapper();
//		
//		mapper.writeValue(response.getOutputStream(),errMap);
//		ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver(); 
		exceptionResolverOne.resolveException(request, response, null, authException);
		
		
	}

}
