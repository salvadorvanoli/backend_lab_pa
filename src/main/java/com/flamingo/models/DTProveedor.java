package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;

public class DTProveedor {
	private String nickname;
	private String email;
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public DTProveedor(String nickname, String email) {
		super();
		this.nickname = nickname;
		this.email = email;
	}
	
	public String toString() {
		return this.nickname;
	}
	
}
