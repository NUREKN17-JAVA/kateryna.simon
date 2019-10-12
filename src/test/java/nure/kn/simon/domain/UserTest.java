package nure.kn.simon.domain;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	private static final int YEAR_OF_BIRTH = 1999;
	private static final int CURRENT_YEAR = 2019;
	/** Test Case #1 the bd in the next months of the year */
	private static final int MONTH_OF_BIRTH1 = Calendar.DECEMBER;
	private static final int DAY_OF_BIRTH1 = 13;
	private static final int ETALONE_AGE1 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
	 /** Test Case #2 today is a bd */
	private static final int MONTH_OF_BIRTH2 = Calendar.OCTOBER;
	private static final int DAY_OF_BIRTH2 = 10;
	private static final int ETALONE_AGE2 = CURRENT_YEAR - YEAR_OF_BIRTH;
	 /** Test Case #3 yesterday is a bd */
	private static final int MONTH_OF_BIRTH3 = Calendar.OCTOBER;
	private static final int DAY_OF_BIRTH3 = 9;
	private static final int ETALONE_AGE3 = CURRENT_YEAR - YEAR_OF_BIRTH;
	 /** Test Case #4 tomorrow is a bd */
	private static final int MONTH_OF_BIRTH4 = Calendar.OCTOBER;
	private static final int DAY_OF_BIRTH4 = 11;
	private static final int ETALONE_AGE4 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
	 /** Test Case #5 bd is in past months */
	private static final int MONTH_OF_BIRTH5 = Calendar.SEPTEMBER;
	private static final int DAY_OF_BIRTH5 = 7;
	private static final int ETALONE_AGE5 = CURRENT_YEAR - YEAR_OF_BIRTH;
	
	private User user;
	private Date dateOfBirth;
	
	public void testGetFullName(){
		user.setFirstName("Chris");
		user.setLastName("Simon");
		assertEquals("Chris Simon", user.getFullName());
	}
	public void testGetAge1(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH1, DAY_OF_BIRTH1);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE1, user.getAge());
	}
	public void testGetAge2(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH2, DAY_OF_BIRTH2);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE2, user.getAge());
	}
	
	 public void testGetAge3(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH3, DAY_OF_BIRTH3);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE3, user.getAge());
	}
	public void testGetAge4(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH4, DAY_OF_BIRTH4);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE4, user.getAge());
	}
	public void testGetAge5(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH5, DAY_OF_BIRTH5);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE5, user.getAge());
	}

	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
