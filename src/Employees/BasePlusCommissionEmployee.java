package Employees;

import static Common.Validation.*;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Creates an instance of a BasePlusCommissionEmployee
 * @author Anthony
 */
public class BasePlusCommissionEmployee extends CommissionSalesEmployee {
    //<editor-fold desc="Class Variables">
    private String firstName, lastName, address, sex, department, position,
	    employeeID, socialSecurityNum;
    private BigDecimal commissionRates, sales, baseSalary;
    private GregorianCalendar dateOfBirth;
    //</editor-fold>
    
    public BasePlusCommissionEmployee(String firstName, String lastName,
	    String sex, String address, String phoneNum, String department,
	    String position, String socialSecurityNum,
	    int yearOfBirth, int monthOfBirth, int dayOfBirth,
	    BigDecimal commissionRates, BigDecimal baseSalary) {
	super(firstName, lastName, sex, address, phoneNum, department, position,
		socialSecurityNum, yearOfBirth, monthOfBirth, dayOfBirth,
		commissionRates);
	this.baseSalary = baseSalary;
    } //end of BasePlusCommissionEmployee constructor
    
    //<editor-fold desc="Accessor Methods">
    public BigDecimal getBaseSalary() { return baseSalary; }
    @Override public String toString() {
	return super.toString() + "\n"
		+ "Employee Base Salary: " + getBaseSalary() + "\n";
    }
    //</editor-fold>
    
    //<editor-fold desc="Mutator Methods">
    public void setBaseSalary(BigDecimal baseSalary) {
	if(isValid(baseSalary))
	    this.baseSalary = baseSalary;
    }
    //</editor-fold>
    
    @Override public BigDecimal getPay() {
	//employee is paid a base salary plus a percentage of their sales
	return baseSalary.add(super.getPay());
    }
} //end of class
