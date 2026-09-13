package br.com.gui336.liferpgtracker.domain;

public class User {

	private int id;
	private String email;
	private String name;
	private String nickname;
	
	public User() {
		
	}
	
	public User(int id, String email,String name, String nickname) {
		setId(id);
		setEmail(email);
		setName(name);
		setNickname(nickname);
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
		email = email.trim();
		
		if (!email.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
			throw new IllegalArgumentException("EMAIL INVALIDO!");
		}
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = name.trim();
		if (name.length() < 3) {
			throw new IllegalArgumentException("O NOME DEVE CONTER NO MINIMO 3 CARCTERS!!");
		}
		if (name.length() > 20) {
			throw new IllegalArgumentException("O NOME DEVE CONTER NO MAXIMO 20 CARCTERS!!");
		}
		if (!name.matches("[a-zA-ZÀ-ÿ ]+")) {
		    throw new IllegalArgumentException("O NOME DEVE CONTER APENAS LETRAS!!");
		}
		this.name = name;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		
		nickname = nickname.trim();
		
		if (nickname.length() < 4) {
			throw new IllegalArgumentException("O NICKNAME DEVE CONTER NO MINIMO 4 CARCTERS!!");
		}
		if (nickname.length() > 14) {
			throw new IllegalArgumentException("O NICKNAME DEVE CONTER NO MAXIMO 14 CARCTERS!!");
		}
		if (nickname.contains(" ")) {	
			throw new IllegalArgumentException("O NICKNAME NÃO DEVE CONTER EXPAÇOS EM BRANCO");	
			}
		this.nickname = nickname;
		
	}
	
	
}
