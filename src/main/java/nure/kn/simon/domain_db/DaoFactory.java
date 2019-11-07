package nure.kn.simon.domain_db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {

	private final Properties properties;
	
	public DaoFactory(){
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
}
