package ua.nure.kn.simon.agent;

import ua.nure.kn.simon.db.DatabaseException;

public class SearchException extends Exception {
	private String str;
	
	public SearchException(Exception e) {
		this.str = e.toString();
	}


}
