package com.flamingo.models;
import java.util.List;
import java.util.ArrayList;

public class DTCliente {
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
	public void setEmall(String email) {
		this.email = email;
	}
	public DTCliente(String nickname, String email) {
		super();
		this.nickname = nickname;
		this.email = email;
	}
	
	public String toString() {
		return this.getNickname() + " - " + this.getEmail();
	}
	
	
}
