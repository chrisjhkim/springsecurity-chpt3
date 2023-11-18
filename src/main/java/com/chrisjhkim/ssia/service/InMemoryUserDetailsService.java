package com.chrisjhkim.ssia.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@AllArgsConstructor
public class InMemoryUserDetailsService implements UserDetailsService {
	private final List<UserDetails> users;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.users.stream()
				.filter(userDetails -> userDetails.getUsername().equals(username))
				.filter(
						u -> u.getUsername().equals(username))
				.findFirst()
				.orElseThrow(
						()->new UsernameNotFoundException("User not found"));

	}
}
