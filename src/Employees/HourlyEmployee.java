package Employees;

import static Common.Validation.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Creates an instance of an hourly employee
 * @author Anthony
 */
public class HourlyEmployee extends Employee {
    //<editor-fold desc="Class Variables">
    private String firstName, lastName, sex, address, phoneNum, department,
	    position, employeeID, socialSecurityNum;
    private double hoursWorked = 0;
    private BigDecimal wage;
    private GregorianCalendar dateOfBirth;
    //</editor-fold>
    
    public HourlyEmployee(String firstName, String lastName, String sex,
	    String address, String phoneNum, String department, String position,
	    String socialSecurityNum, int yearOfBirth,
	    int monthOfBirth, int dayOfBirth, BigDecimal wage) {
        super(firstName, lastName, sex, address, phoneNum, department, position,
		socialSecurityNum, yearOfBirth, monthOfBirth, dayOfBirth);
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
	if(isValid(hours))
	    this.hoursWorked += hours;
    }

    public void setWage(BigDecimal wage) {
	if(isValid(wage))
	    this.wage = wage;
    }
    //</editor-fold>

    @Override public BigDecimal getPay() {
	//pay is hourly wage multiplied by hours worked
	return wage.multiply(new BigDecimal(hoursWorked));
    }
} //end of class
