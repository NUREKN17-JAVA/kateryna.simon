package ua.nure.kn.simon.db;

import ua.nure.kn.simon.domain.User;

public class DaoFactoryImpl extends DaoFactory {
	
	@SuppressWarnings("unchecked")
	public DAO<User> getUserDao(){
		DAO<User> result = null;
		try {
			Class<?> clas = Class.forName(properties.getProperty(USER_DAO));
			result = (DAO<User>) clas.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
