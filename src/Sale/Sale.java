package Sale;

import Common.Utils;
import Customer.Customer;
import Employees.CommissionSalesEmployee;
import Employees.Employee;
import Products.Product;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 *
 * @author Anthony
 */
public class Sale {
    //<editor-fold desc="Class Variables">
    private String salesID;
    private Product product;
    private Customer customer;
    private Employee employee;
    private BigDecimal comm;
    private GregorianCalendar dateOfSale;
    //</editor-fold>
    
    public Sale(Product product, Customer customer, Employee employee, BigDecimal comm) {
	this.salesID = Utils.generateSaleID();
	this.product = product;
	this.customer = customer;
	this.employee = employee;
        this.comm = comm;
	this.dateOfSale = new GregorianCalendar(); //initializes to today
	/*
	if(employee.getClass().toString().equals("Employees.CommissionSalesEmployee")) {
	    employee.addSales(product.getPrice());
	}
	else if(employee.getClass().toString().equals("Employees.BasePlusCommissionEmployee")) {
	    employee.addSales(product.getPrice());
	}
	*/ 
    } //end of sale constructor
    
    //<editor-fold desc="Accessor Methods">
    public String getSalesID() { return this.salesID; }
    public Product getProduct() { return product; }
    public Customer getCustomer() { return customer; }
    public Employee getEmployee() { return employee; }
    public BigDecimal getComm() { return comm; }
    public GregorianCalendar getDateOfSale() { return dateOfSale; }
    @Override public String toString() {
	return "Product Sold: " + getProduct().toString() + "\n"
	    + "Customer: " + getCustomer().toString() + "\n"
	    + "Employee: " + getEmployee().toString() + "\n"
	    + "Date of Sale: " + getDateOfSale().getTime();
    }
    //</editor-fold>
    
    //<editor-fold desc="Mutator Methods">
    public void setSaleID(String salesID) {
	this.salesID = salesID;
    }

    public void setProduct(Product product) {
	this.product = product;
    }

    public void setCustomer(Customer customer) {
	this.customer = customer;
    }

    public void setEmployee(Employee employee) {
	this.employee = employee;
    }

    public void setComm(BigDecimal comm) {
	this.comm = comm;
    }
    //</editor-fold>
}
