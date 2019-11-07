package nure.kn.simon.domain_db;

import java.io.IOException;
import java.util.Properties;

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
	
	public Dao<?> getUserDao() {
		Dao<?> result = null;
		try {
			Class<?> clazz = Class.forName(properties.getProperty(USER_DAO));
			result = (Dao<?>) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
            throw new RuntimeException(e);
		}
		return result;
	}
	}