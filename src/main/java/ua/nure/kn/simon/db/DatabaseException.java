package ua.nure.kn.simon.db;

public class DatabaseException extends Exception {

	public DatabaseException(Exception e) {
		super(e);
	}
	public DatabaseException(String string) {
		super(string);
		}
}
