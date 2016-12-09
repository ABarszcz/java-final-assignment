package Employees;

import Common.Utils;
import static Common.Validation.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Creates an instance of an employee
 * @author Anthony
 */
public abstract class Employee {
    //<editor-fold desc="Class Variables">
    private String firstName, lastName, sex, address, phoneNum, department,
	    position, employeeID, socialSecurityNum;
    private GregorianCalendar dateOfBirth;
    //</editor-fold>
    
    public Employee(String firstName, String lastName, String sex,
	    String address, String phoneNum, String department, String position,
	    String socialSecurityNum, int yearOfBirth,
	    int monthOfBirth, int dayOfBirth) {
        this.firstName = firstName;
	this.lastName = lastName;
	this.sex = sex;
	this.address = address;
	this.phoneNum = phoneNum;
	this.department = department;
	this.position = position;
	this.employeeID = Utils.generateEmpID();
	this.socialSecurityNum = socialSecurityNum;
	this.dateOfBirth = new GregorianCalendar();
	this.dateOfBirth.set(yearOfBirth, monthOfBirth-1, dayOfBirth);
    } //end of Employee constructor
    
    //<editor-fold desc="Accessor Methods">
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getSex() { return sex; }
    public String getAddress() { return address; }
    public String getPhoneNum() { return phoneNum; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
    public String getEmployeeID() { return employeeID; }
    public String getSocialSecurityNum() { return socialSecurityNum; }
    public GregorianCalendar getDateOfBirth() { return dateOfBirth; }
    @Override public String toString() {
	return "Employee First Name: " + getFirstName() + "\n"
	    + "Employee Last Name: " + getLastName() + "\n"
	    + "Employee Sex: " + getSex() + "\n"
	    + "Employee Address: " + getAddress() + "\n"
	    + "Employee Phone Number: " + getPhoneNum() + "\n"
	    + "Employee Department: " + getDepartment() + "\n"
	    + "Employee Position: " + getPosition() + "\n"
	    + "Employee EmployeeID: " + getEmployeeID() + "\n"
	    + "Employee Social Security Number: " + getSocialSecurityNum() + "\n"
	    + "Employee Date Of Birth: " + getDateOfBirth() + "\n";
    }
    //</editor-fold>
    
    //<editor-fold desc="Mutator Methods">
    public void setFirstName(String firstName) {
	if(isValid(firstName))
	    this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
	if(isValid(lastName))
	    this.lastName = lastName;
    }
    
    public void setSex(String sex) {
	if(isValid(sex))
	    this.sex = sex;
    }
    
    public void setAddress(String address) {
	if(isValid(address))
	    this.address = address;
    }
    
    public void setPhoneNum(String phoneNum) {
	if(isValid(phoneNum))
	    this.phoneNum = phoneNum;
    }
    
    public void setDepartment(String department) {
	if(isValid(department))
	    this.department = department;
    }
    
    public void setPosition(String position) {
	if(isValid(position))
	    this.position = position;
    }
    
    public void setSocialSecurityNum(String socialSecurityNum) {
	if(isValid(employeeID))
	    this.socialSecurityNum = socialSecurityNum;
    }
    
    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
	if(isValid(dateOfBirth, true, false))
	    this.dateOfBirth = dateOfBirth;
    }
    //</editor-fold>
    
    public abstract BigDecimal getPay();
} //end of class
