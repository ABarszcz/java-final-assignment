package Employees;

import Common.Utils;
import java.math.BigDecimal;
import java.util.*;

/**
 * Creates an instance of an employee
 * @author Anthony
 */
public abstract class Employee {
    //<editor-fold desc="Class Variables">
    private String firstName, lastName, sex, province, city, address,
	    phoneNum, department, position, employeeID, socialSecurityNum;
    private GregorianCalendar dateOfBirth;
    private int year;
    //</editor-fold>
    
    public Employee(String firstName){
        this.firstName = firstName;
    }
    
    public Employee(String firstName, String lastName, String sex,
	    String address, String city, String province, String phoneNum,
	    String department, String position, String socialSecurityNum, int yearOfBirth,
	    int monthOfBirth, int dayOfBirth) {
        this.firstName = firstName;
	this.lastName = lastName;
	this.sex = sex;
	this.province = province;
	this.city = city;
	this.address = address;
	this.phoneNum = phoneNum;
	this.department = department;
	this.position = position;
	this.employeeID = Utils.generateEmpID();
	this.socialSecurityNum = socialSecurityNum;
	this.dateOfBirth = new GregorianCalendar();
	this.dateOfBirth.set(yearOfBirth, monthOfBirth-1, dayOfBirth);
        this.year = yearOfBirth;
    } //end of Employee constructor
    
    //<editor-fold desc="Accessor Methods">
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getSex() { return sex; }
    public String getProvince() { return province; }
    public String getCity() { return city; }
    public String getAddress() { return address; }
    public String getPhoneNum() { return phoneNum; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
    public String getEmployeeID() { return employeeID; }
    public String getSocialSecurityNum() { return socialSecurityNum; }
    public int getYear(){ return year; }
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
	    + "Employee Date Of Birth: " + (getDateOfBirth() == null? "": getDateOfBirth().getTime()) + "\n";
    }
    //</editor-fold>
    
    //<editor-fold desc="Mutator Methods">
    public void setEmployeeID(String employeeID) {
	this.employeeID = employeeID;
    }
    
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
	this.lastName = lastName;
    }
    
    public void setSex(String sex) {
	this.sex = sex;
    }

    public void setProvince(String province) {
	this.province = province;
    }

    public void setCity(String city) {
	this.city = city;
    }
    
    public void setAddress(String address) {
	this.address = address;
    }
    
    public void setPhoneNum(String phoneNum) {
	this.phoneNum = phoneNum;
    }
    
    public void setDepartment(String department) {
	this.department = department;
    }
    
    public void setPosition(String position) {
	this.position = position;
    }
    
    public void setSocialSecurityNum(String socialSecurityNum) {
	this.socialSecurityNum = socialSecurityNum;
    }
    
    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }
    //</editor-fold>
    
    public abstract BigDecimal getPay();
} //end of class
