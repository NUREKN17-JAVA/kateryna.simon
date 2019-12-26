package ua.nure.kn.simon.web;

import java.text.DateFormat;
import java.util.Date;

import ua.nure.kn.simon.domain.User;

public class AddServletTest extends MockServletTestCase {

	private static final String OK_BUTTON = "Ok";
	private static final String LAST_NAME_TEST = "Kim";
	private static final String FIRST_NAME_TEST = "Namjoon";
	public void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}
	public void testAdd(){
		Date date = new Date();
		User newUser = new User(FIRST_NAME_TEST,LAST_NAME_TEST,date);
		User user = new User(new Long(1000),FIRST_NAME_TEST,LAST_NAME_TEST,date);
		getMockUserDao().expectAndReturn("create",newUser,user);
		
		addRequestParameter("firstName",FIRST_NAME_TEST);
		addRequestParameter("lastName",LAST_NAME_TEST);
		addRequestParameter("date",DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton",OK_BUTTON);
		doPost();
	}

	public void testAddEmptyFirstName(){
		Date date = new Date();
		addRequestParameter("lastName",LAST_NAME_TEST);
		addRequestParameter("date",DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton",OK_BUTTON);
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope",errorMessage);
	}
	public void testAddEmptyLastName(){
		Date date = new Date();
		addRequestParameter("firstName",FIRST_NAME_TEST);
		addRequestParameter("date",DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton",OK_BUTTON);
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope",errorMessage);
	}
	public void testAddEmptyDateOfBirth(){
		Date date = new Date();
		addRequestParameter("firstName",FIRST_NAME_TEST);
		addRequestParameter("lastName",LAST_NAME_TEST);
		addRequestParameter("okButton",OK_BUTTON);
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope",errorMessage);
	}
	public void testAddEmptyDateIncorrect(){
		Date date = new Date();
		addRequestParameter("firstName",FIRST_NAME_TEST);
		addRequestParameter("lastName",LAST_NAME_TEST);
		addRequestParameter("date","oops=)");
		addRequestParameter("okButton",OK_BUTTON);
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope",errorMessage);
	}
}
