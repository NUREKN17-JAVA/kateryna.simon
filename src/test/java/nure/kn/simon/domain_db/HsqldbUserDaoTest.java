package nure.kn.simon.domain_db;

import java.util.Calendar;
import java.util.Collection;

import nure.kn.simon.domain.User;
import nure.kn.simon.domain_db.HsqldbUserDao;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

public class HsqldbUserDaoTest extends DatabaseTestCase {

	private ConnectionFactory connectionFactory;
	private HsqldbUserDao dao;
	private static final String LAST_NAME = "Simon";
	private static final String FIRST_NAME = "Kate";
	private static final int DAY_OF_BIRTH = 13;
	private static final int MONTH = 12;
	private static final int YEAR = 1999;

	public void testCreate() throws DatabaseException{
	  try {
		User user = new User();
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(YEAR, MONTH, DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		assertNull(user.getId());
		User userToCheck = dao.create(user);
		assertNotNull(userToCheck);
		assertNotNull(userToCheck.getId());
	  } catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testFindAll(){
		try {
			Collection<User> collection = dao.findAll();
			assertNotNull("Collection is null", collection);
			assertNotNull("Collection size", collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl( "org.hsqldb.jdbcDriver",  "jdbc:hsqldb:file:db/usermanagement", "sa", "");
		return new DatabaseConnection(connectionFactory.getConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader()
				.getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}
