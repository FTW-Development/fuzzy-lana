package com.ftwdev.stuffinc.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	private String username;
	private byte[] password;
	
	public User(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	public void setPassword(byte[] password) {
		this.password = password;
	}
	
	public byte[] getPassword() {
		return password;
	}

	public byte[] hashPassword(String password) {
		try {
			return MessageDigest.getInstance("MD5").digest(password.getBytes());
		} catch (NoSuchAlgorithmException e) {
			//should not fail
			return null;
		}
	}
}
