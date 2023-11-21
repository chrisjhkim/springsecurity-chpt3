package com.chrisjhkim.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource){
		String usersByUsernameQuery = "SELECT username, password, enabled FROM users WHERE username = ? ";
		String authoritiesByUsernameQuery = "SELECT username, authority FROM authorities WHERE username = ? ";

		var userDetailManager = new JdbcUserDetailsManager(dataSource);
		userDetailManager.setUsersByUsernameQuery(usersByUsernameQuery);
		userDetailManager.setAuthoritiesByUsernameQuery(authoritiesByUsernameQuery);
		return userDetailManager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("noop", NoOpPasswordEncoder.getInstance());

		// BCryptPasswordEncoder 옵션 가능
		encoders.put("bcrypt", new BCryptPasswordEncoder());
//		encoders.put("bcrypt", new BCryptPasswordEncoder(4));
//		SecureRandom s = SecureRandom.getInstanceStrong();
//		encoders.put("bcrypt", new BCryptPasswordEncoder(4, s));


		// SCryptPasswordEncoder 옵션 가능
		encoders.put("scrypt", new SCryptPasswordEncoder());
//		encoders.put("scrypt", new SCryptPasswordEncoder(16384,8,1,32,64));

		return new DelegatingPasswordEncoder("bcrypt", encoders);
	}
}
