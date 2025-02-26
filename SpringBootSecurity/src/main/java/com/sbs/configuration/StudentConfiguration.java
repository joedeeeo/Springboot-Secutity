package com.sbs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sbs.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class StudentConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		 httpSecurity.csrf(c->c.disable());
		httpSecurity.authorizeHttpRequests(a -> a
				.requestMatchers("/","/add-student").permitAll()
				.requestMatchers("/get-session-id","/get-csrf").hasAuthority("ADMIN")
				.requestMatchers("/get-student/**","/get-all-student").hasAuthority("USER")
				.anyRequest().authenticated());
		httpSecurity.formLogin(Customizer.withDefaults());
		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
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
		return new CustomUserDetailsService();
	}

//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
		daoAuth.setPasswordEncoder(new BCryptPasswordEncoder());
		daoAuth.setUserDetailsService(userDetailsService());
		return daoAuth;
	}
}
