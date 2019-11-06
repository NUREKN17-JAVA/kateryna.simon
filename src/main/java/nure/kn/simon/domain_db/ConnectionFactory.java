package nure.kn.simon.domain_db;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection getConnection() throws DatabaseException; 
}
