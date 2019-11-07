package nure.kn.simon.domain_db;

import java.io.IOException;
import java.util.Properties;

import org.hsqldb.User;

public class DaoFactory {

	private static final String USER_DAO = "dao.knure.ctde.usermanagement_db.UserDao";
	private final Properties properties;
	
    private final static DaoFactory INSTANCE = new DaoFactory();
    
    public static DaoFactory getInstance() {
        return INSTANCE;
    }
	
	public DaoFactory(){
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}
	
	private ConnectionFactory getConnectionFactory() {
	String user = properties.getProperty("connection.user");
	String password = properties.getProperty("connection.password");
	String url = properties.getProperty("connection.url");
	String driver = properties.getProperty("connection.driver");
	return new ConnectionFactoryImpl(driver, url, user, password);
			}
	
	public Dao<User> getUserDao() {
		Dao<User> result = null;
		try {
			Class<User> clazz = (Class<User>) Class.forName(properties.getProperty(USER_DAO));
			result = (Dao<User>) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
            throw new RuntimeException(e);
		}
		return result;
	}
	}