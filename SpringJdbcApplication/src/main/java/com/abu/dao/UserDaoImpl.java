package com.abu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import abu.dao.model.User;

@Repository
public class UserDaoImpl implements Userdao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<User> listAllUsers() {
		String sql = "SELECT id, firstname, lastname, address FROM users";

		List<User> list = namedParameterJdbcTemplate.query(sql, getSqlParameterByModel(null), new UserMapper());

		return list;
	}

	private SqlParameterSource getSqlParameterByModel(User user) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		if (user != null) {
			parameterSource.addValue("id", user.getId());
			parameterSource.addValue("firstname", user.getFirstname());
			parameterSource.addValue("lastname", user.getLastname());
			parameterSource.addValue("address", user.getAddress());
		}
		return parameterSource;
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			user.setAddress(rs.getString("address"));

			return user;
		}

	}

	
	  public void deleteUser(int id) { String sql =
	  "DELETE FROM users WHERE id = :id";
	  
	  namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(new User(id)));
	  }
	  
	  @Override public void addUser(User user) { String sql =
	  "INSERT INTO users(firstname, lastname, address) VALUES(:firstname, :lastname, :address)"
	  ;
	  
	  namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user)); }
	  
	  @Override public void updateUser(User user) { String sql =
	  "UPDATE users SET firstname = :firstname, lastname = :lastname , address = :address WHERE id = :id"
	  ;
	  
	  namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user)); }
	  
	  @Override public User findUserById(int id) { String sql =
	  "SELECT * FROM users WHERE id = :id";
	  
	  return (User) namedParameterJdbcTemplate.queryForObject(sql,
	  getSqlParameterByModel(new User(id)), new UserMapper()); }
	 

}