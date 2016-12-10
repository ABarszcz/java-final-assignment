package Customer;

import Common.Utils;
import java.util.GregorianCalendar;

/**
 *
 * @author Anthony
 */
public class Customer {
    //<editor-fold desc="Class Variables">
    private String firstName, lastName, sex, address, phoneNum, customerID;
    private GregorianCalendar dateOfBirth;
    //</editor-fold>
    
    public Customer(String firstName, String lastName, String sex, String address,
	    String phoneNum, int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        this.firstName = firstName;
	this.lastName = lastName;
	this.sex = sex;
	this.address = address;
	this.phoneNum = phoneNum;
	this.customerID = Utils.generateCustomerID();
	this.dateOfBirth = new GregorianCalendar();
	this.dateOfBirth.set(yearOfBirth, monthOfBirth-1, dayOfBirth);
    } //end of customer constructor

    //<editor-fold desc="Accessor Methods">
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName;  }
    public String getSex() { return sex; }
    public String getAddress() { return address; }
    public String getPhoneNum() { return phoneNum; }
    public String getCustomerID() { return customerID; }
    public GregorianCalendar getDateOfBirth() { return dateOfBirth; }
    @Override public String toString() {
	return "Customer First Name: " + getFirstName() + "\n"
	    + "Customer Last Name: " + getLastName() + "\n"
	    + "Customer Sex: " + getSex() + "\n"
	    + "Customer Address: " + getAddress() + "\n"
	    + "Customer Phone Number: " + getPhoneNum() + "\n"
	    + "Customer EmployeeID: " + getCustomerID() + "\n"
	    + "Customer Date Of Birth: " + getDateOfBirth().toString();
    }
    //</editor-fold>

    //<editor-fold desc="Mutator Methods">
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public void setPhoneNum(String phoneNum) {
	this.phoneNum = phoneNum;
    }

    public void setCustomerID(String customerID) {
	this.customerID = customerID;
    }

    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }
    //</editor-fold>
}
