package com.sbs.springbootsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sbs.springbootsecurity.exception.JwtExceptionHandler;
import com.sbs.springbootsecurity.filters.JwtFilter;
import com.sbs.springbootsecurity.service.impl.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class StudentConfiguration {

//	@Autowired
//	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private JwtExceptionHandler jwtExceptionHandler;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		 httpSecurity.csrf(c->c.disable());
		httpSecurity.authorizeHttpRequests(a -> a
				.requestMatchers("/student-home","/student-register","/login").permitAll()
				.requestMatchers("/get-session-id","/get-csrf","/get-all-student").hasAuthority("ADMIN")
//				.requestMatchers("/get-student/**","/get-all-student").hasAuthority("USER")
				.anyRequest().authenticated());
		httpSecurity.exceptionHandling(ex->ex.authenticationEntryPoint(jwtExceptionHandler));
		//httpSecurity.formLogin(Customizer.withDefaults());
		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
//		UserDetails u1 = User.withDefaultPasswordEncoder()
//				.username("vikram145")
//				.password("123")
//				.roles("intern")
//				.build(); 
//		
//		UserDetails u2 = User.withDefaultPasswordEncoder()
//				.username("raju121")
//				.password("321")
//				.roles("admin")
//				.build();
//		return new InMemoryUserDetailsManager(u1,u2);
		return new MyUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
		daoAuth.setPasswordEncoder( bCryptPasswordEncoder());
		daoAuth.setUserDetailsService(userDetailsService());
		return daoAuth;
	}
	
	@Bean
	public AuthenticationManager aunthenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
}
