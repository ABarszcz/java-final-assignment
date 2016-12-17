package Employees;

import java.math.BigDecimal;

/**
 * Creates an instance of a salaried employee
 * @author Anthony
 */
public class SalaryEmployee extends Employee {
    //<editor-fold desc="Class Variables">
    private String salaryType; //salaryType is weekly/monthly/yearly
    private BigDecimal salaryAmount;
    //</editor-fold>
    
    public SalaryEmployee(String firstName, String lastName, String sex,
	    String province, String city, String address, String phoneNum,
	    String department, String position, String socialSecurityNum,
	    int yearOfBirth, int monthOfBirth, int dayOfBirth,
	    BigDecimal salaryAmount) {
        super(firstName, lastName, sex, province, city, address, phoneNum,
		department, position, socialSecurityNum, yearOfBirth, monthOfBirth,
		dayOfBirth);
	this.salaryType = salaryType;
	this.salaryAmount = salaryAmount;
    } //end of Employee constructor
    
    //<editor-fold desc="Accessor Methods">
    public String getSalaryType() { return this.salaryType; }
    public BigDecimal getSalaryAmount() { return this.salaryAmount; }
    @Override public String toString() {
	return super.toString() + "\n"
		+ "Employee Salary Type: " + getSalaryType() + "\n"
		+ "Employee Salary Amount: " + getSalaryAmount() + "\n";
    }
    //</editor-fold>
    
    //<editor-fold desc="Mutator Methods">
    public void setSalaryType(String salaryType) {
	this.salaryType = salaryType;
    }
    public void setSalaryAmount(BigDecimal salaryAmount) {
	this.salaryAmount = salaryAmount;
    }
    //</editor-fold>

    @Override public BigDecimal getPay() {
	//pay is salary - would also want to know type (weekly/monthly/yearly)
	return getSalaryAmount();
    }
} //end of class
