package nure.kn.simon.domain_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;

import nure.kn.simon.domain.User;

public class HsqldbUserDao implements Dao<User> {
	
	private ConnectionFactory connectionFactory;
	public HsqldbUserDao(ConnectionFactory connectionFactory){
		this.connectionFactory = connectionFactory;
	}

	public User create(User entity) throws DatabaseException {

		return null;
	}

	public void update(User entity) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	public void delete(User entity) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	public User find(Long id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<User> findAll() throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

}