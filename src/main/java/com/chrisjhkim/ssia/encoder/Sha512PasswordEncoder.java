package com.chrisjhkim.ssia.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha512PasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return hashWithSHA512(rawPassword.toString());
	}


	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String hashedPassword = encode(rawPassword);
		return encodedPassword.equals(hashedPassword);
	}

	private String hashWithSHA512(String plainText) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] digested = md.digest(plainText.getBytes());
			for (int i = 0; i < digested.length; i++) {
				sb.append(Integer.toHexString(0xFF & digested[i]));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Bad algorithm");
		}



	}
}
