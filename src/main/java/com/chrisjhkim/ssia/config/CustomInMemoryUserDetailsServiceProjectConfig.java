package com.chrisjhkim.ssia.config;

import com.chrisjhkim.ssia.model.User;
import com.chrisjhkim.ssia.service.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

//@Configuration
public class CustomInMemoryUserDetailsServiceProjectConfig {

	@Bean
	public UserDetailsService userDetailsService(){
		UserDetails user = new User("john", "12345", "read");
		List<UserDetails> users = List.of(user);
		return new InMemoryUserDetailsService(users);
	}


	@SuppressWarnings("deprecation") // 학습 목적
	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}
