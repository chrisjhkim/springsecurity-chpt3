package com.chrisjhkim.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class ProjectConfig {
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource){
		return new JdbcUserDetailsManager(dataSource);
	}


	@SuppressWarnings("deprecation") // 학습 목적
	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}
