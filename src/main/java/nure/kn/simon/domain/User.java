package nure.kn.simon.domain;

import java.util.Calendar;
import java.util.Date;
import java.io.Serializable;

public class User implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1942568892054644812L;
	
private Long id;
private String firstName;
private String lastName;
private Date dateOfBirth;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public Date getDateOfBirth() {
	return dateOfBirth;
}
public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}
public String getFullName() {
	return getFirstName() + " " + getLastName(); 
}
public long getAge() { /* to correct */

	    int age = 0;
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());  
	    int currentYear = calendar.get(Calendar.YEAR);
	    int currentMonth = calendar.get(Calendar.MONTH);
	    int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
	   
	    calendar.setTime(getDateOfBirth());
	    int birthYear = calendar.get(Calendar.YEAR);
	    int birthDay = calendar.get(Calendar.DAY_OF_MONTH);
	    int birthMonth = calendar.get(Calendar.MONTH);
	    if (birthMonth < currentMonth){ 
	      age = currentYear - birthYear;}
	    else 
	      if(birthMonth > currentMonth) {
	        age = currentYear - birthYear - 1;}
	      else if (birthMonth == currentMonth && birthDay > currentDay) {
	         age = currentYear - birthYear - 1;} 
	       else if (birthMonth == currentMonth && birthDay < currentDay) {
	          age = currentYear - birthYear;} //yesterday
	        else if(birthMonth == currentMonth && birthDay == currentDay) {
	           age = currentYear - birthYear; }//today
	              
	    return age;
 }
}
