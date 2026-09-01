package br.com.gui336.liferpgtracker.domain;

public class User {

	private int id;
	private String email;
	private String name;
	private String nickname;
	
	public User() {
		
	}
	
	public User(int id, String email,String name, String nickname) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.nickname = nickname;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
