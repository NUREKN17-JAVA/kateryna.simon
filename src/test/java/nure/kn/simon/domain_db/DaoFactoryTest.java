package nure.kn.simon.domain_db;

import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetUserDao() {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory instance is null", daoFactory);
			Dao<?> userDao = daoFactory.getUserDao();
			assertNotNull("Dao instance is null", userDao);
		} catch (RuntimeException e) {
			e.printStackTrace();
	
		}
		
	}

}
