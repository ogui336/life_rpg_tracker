package br.com.gui336.liferpgtracker.data;

import java.util.Optional;

import br.com.gui336.liferpgtracker.domain.User;

import java.sql.SQLException;

public interface UserRepository {

	public User create(User user) throws SQLException;
	
	public Optional<User> findById(int id) throws SQLException;
	
	public Optional<User> findByEmail(String email) throws SQLException;
	
	public void updateNickname(int id, String newNickname) throws SQLException;

	public void updateName(int id, String newName) throws SQLException;
}
