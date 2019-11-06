package nure.kn.simon.domain_db;

public class DatabaseException extends Exception {

	public DatabaseException(Exception e) {
		super(e);
	}

	public DatabaseException(String string) {
		super(string);
	}

}
