package nure.kn.simon.domain_db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import nure.kn.simon.domain.User;

public class HsqldbUserDao implements Dao<User> {
	
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
	private ConnectionFactory connectionFactory;
	public HsqldbUserDao(ConnectionFactory connectionFactory){
		this.connectionFactory = connectionFactory;
	}

	public User create(User entity) throws DatabaseException {
		try {
			Connection connection = connectionFactory.getConnection();
			PreparedStatement statement = 
				connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.setDate(3, new Date(entity.getDateOfBirth().getTime()));
			int numberOfRows =statement.executeUpdate();
			if (numberOfRows != 1) {
				throw new DatabaseException("Number of inserted rows: " + numberOfRows);
			}
			CallableStatement callableStatement =
					connection.prepareCall("call IDENTITY()");
			ResultSet key = callableStatement.executeQuery();
			if (key.next()) {
				entity.setId(new Long(key.getLong(1)));
			}
			key.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return entity; // <null
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
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
