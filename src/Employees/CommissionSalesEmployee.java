package Employees;

import static Common.Validation.*;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Creates an instance of a CommissionSalesEmployee
 * @author Anthony
 */
public class CommissionSalesEmployee extends Employee {
    //<editor-fold desc="Class Variables">
    private String firstName, lastName, address, sex, department, position,
	    employeeID, socialSecurityNum;
    private BigDecimal commissionRates, sales;
    private GregorianCalendar dateOfBirth;
    //</editor-fold>
    
    public CommissionSalesEmployee(String firstName, String lastName,
	    String sex, String address, String phoneNum, String department,
	    String position, String socialSecurityNum,
	    int yearOfBirth, int monthOfBirth, int dayOfBirth,
	    BigDecimal commissionRates) {
	super(firstName, lastName, sex, address, phoneNum, department, position,
		socialSecurityNum, yearOfBirth, monthOfBirth, dayOfBirth);
	this.commissionRates = commissionRates;
    } //end of CommissionSalesEmployee constructor
    
    //<editor-fold desc="Accessor Methods">
    public BigDecimal getCommissionRates() { return commissionRates; }
    public BigDecimal getSales() { return sales; }
    @Override public String toString() {
	return super.toString() + "\n"
		+ "Employee Commission Rates: " + getCommissionRates() + "\n"
		+ "Employee Sales: " + getSales() + "\n";
    }
    //</editor-fold>
    
    //<editor-fold desc="Mutator Methods">
    public void setCommissionRates(BigDecimal commissionRates) {
	if(isValid(commissionRates))
	    this.commissionRates = commissionRates;
    }
    
    public void setSales(BigDecimal sales) {
	if(isValid(sales))
	    this.sales = sales;
    }
    
    public void addSales(BigDecimal sales) {
	if(isValid(sales))
	    this.sales.add(sales);
    }
    
    //</editor-fold>

    @Override public BigDecimal getPay() {
	//employee is paid a percentage of their sales
	return sales.multiply(commissionRates);
    }
} //end of class
