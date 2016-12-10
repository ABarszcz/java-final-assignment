package Employees;

import java.math.BigDecimal;

/**
 * Creates an instance of a BasePlusCommissionEmployee
 * @author Anthony
 */
public class BasePlusCommissionEmployee extends CommissionSalesEmployee {
    //<editor-fold desc="Class Variables">
    private BigDecimal baseSalary;
    //</editor-fold>
    
    public BasePlusCommissionEmployee(String firstName, String lastName, String sex, String country,
	    String province, String city, String address, String phoneNum, String department,
	    String position, String socialSecurityNum, int yearOfBirth, int monthOfBirth,
	    int dayOfBirth, BigDecimal wage, BigDecimal commissionRates, BigDecimal baseSalary) {
	super(firstName, lastName, sex, country, province, city, address, phoneNum,
		department, position, socialSecurityNum, yearOfBirth, monthOfBirth,
		dayOfBirth, wage, commissionRates);
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
	this.baseSalary = baseSalary;
    }
    //</editor-fold>
    
    @Override public BigDecimal getPay() {
	//employee is paid a base salary plus a percentage of their sales
	return baseSalary.add(super.getPay());
    }
} //end of class
