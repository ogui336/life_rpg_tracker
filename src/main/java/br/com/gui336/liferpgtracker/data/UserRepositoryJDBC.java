package br.com.gui336.liferpgtracker.data;

import java.util.Optional;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.gui336.liferpgtracker.domain.User;

public class UserRepositoryJDBC implements UserRepository{

	@Override
	public User create(String email, String name, String nickname) throws SQLException {
		String sql = "INSERT INTO users (email, name, nickname) VALUES(?,?,?)";
		
		User user = new User();
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			
			pstmt.setString(1, email);
			pstmt.setString(2, name);
			pstmt.setString(3, nickname);
			pstmt.executeUpdate();
			
			try(ResultSet generatedKeys = pstmt.getGeneratedKeys()){
				if(generatedKeys.next()) {
					user.setId(generatedKeys.getInt(1));
					user.setEmail(email);
					user.setName(name);
					user.setNickname(nickname);
				}
			}
		}
		return user;
	}

	@Override
	public Optional<User> findById(int id) throws SQLException {
		String sql = "SELECT id, email, name, nickname FROM users WHERE id = ?";
		
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, id);
			
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return Optional.of(new User(
							rs.getInt("id"),
							rs.getString("email"),
							rs.getString("name"),
							rs.getString("nickname")));
				}else {
					return Optional.empty();
				}
			}
		}
	}

	@Override
	public Optional<User> findByEmail(String email) throws SQLException {
		String sql = "SELECT id, email, name, nickname FROM users WHERE email = ?";
		
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, email);
			
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return Optional.of(new User(
							rs.getInt("id"),
							rs.getString("email"),
							rs.getString("name"),
							rs.getString("nickname")));
				}else {
					return Optional.empty();
				}
			}
		}
	}

	@Override
	public void updateNickname(int id, String newNickname) throws SQLException {
		String sql = "UPDATE users SET nickname = ? WHERE id = ?";
		
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1,newNickname);
			pstmt.setInt(2, id);
			
			pstmt.executeUpdate();
		}
	}

	
	
}
