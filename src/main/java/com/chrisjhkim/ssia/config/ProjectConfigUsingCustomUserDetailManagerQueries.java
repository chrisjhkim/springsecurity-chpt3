package com.chrisjhkim.ssia.config;

import com.chrisjhkim.ssia.encoder.PlainTextPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

//@Configuration
public class ProjectConfigUsingCustomUserDetailManagerQueries {
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource){
		String usersByUsernameQuery = "SELECT username, password, enabled FROM users WHERE username = ? ";
		String authoritiesByUsernameQuery = "SELECT username, authority FROM authorities WHERE username = ? ";

		var userDetailManager = new JdbcUserDetailsManager(dataSource);
		userDetailManager.setUsersByUsernameQuery(usersByUsernameQuery);
		userDetailManager.setAuthoritiesByUsernameQuery(authoritiesByUsernameQuery);
		return userDetailManager;
	}


	@SuppressWarnings("deprecation") // 학습 목적
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new PlainTextPasswordEncoder();
	}
}
