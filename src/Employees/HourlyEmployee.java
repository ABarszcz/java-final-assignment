/*
 * Assignment 2 - Part 2
 */
package Employees;

import java.math.BigDecimal;

/**
 * Creates an instance of an hourly employee
 * @author Anthony
 */
public class HourlyEmployee extends Employee {
    //<editor-fold desc="Class Variables">
    private double hoursWorked = 0;
    private BigDecimal wage;
    //</editor-fold>
    
    public HourlyEmployee(String firstName, String lastName, String sex,
	    String province, String city, String address, String phoneNum, String department,
	    String position, String socialSecurityNum, int yearOfBirth, int monthOfBirth,
	    int dayOfBirth, BigDecimal wage) {
        super(firstName, lastName, sex, province, city, address, phoneNum,
		department, position, socialSecurityNum, yearOfBirth, monthOfBirth,
		dayOfBirth);
	this.wage = wage;
    } //end of Employee constructor
    
    //<editor-fold desc="Accessor Methods">
    public double getHoursWorked() { return this.hoursWorked; }
    public BigDecimal getWage() { return this.wage; }
    @Override public String toString() {
	return super.toString() + "\n"
		+ "Employee Hours Worked: " + getHoursWorked() + "\n"
		+ "Employee Wage: " + getWage() + "\n";
    }
    //</editor-fold>
    
    //<editor-fold desc="Mutator Methods">
    public void setHoursWorked(double hoursWorked) {
	//if(isValid(hoursWorked))
	    this.hoursWorked = hoursWorked;
    }
    
    //add additional hours worked instead of overwriting old value
    public void addHoursWorked(double hours) {
	this.hoursWorked += hours;
    }

    public void setWage(BigDecimal wage) {
	this.wage = wage;
    }
    //</editor-fold>

    @Override public BigDecimal getPay() {
	//pay is hourly wage multiplied by hours worked
	return wage.multiply(new BigDecimal(hoursWorked));
    }
} //end of class
