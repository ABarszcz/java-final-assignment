package Customer;

import Common.Utils;
import java.util.GregorianCalendar;

/**
 *
 * @author Anthony
 */
public class Customer {
    //<editor-fold desc="Class Variables">
    private String firstName, lastName, sex, province, city, address,
	    phoneNum, customerID;
    private GregorianCalendar dateOfBirth, customerSince;
    //</editor-fold>
    
    public Customer(String firstName, String lastName, String sex,
	    String province, String city, String address, String phoneNum,
	    int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        this.firstName = firstName;
	this.lastName = lastName;
	this.sex = sex;
	this.province = province;
	this.city = city;
	this.address = address;
	this.phoneNum = phoneNum;
	this.customerID = Utils.generateCustomerID();
	this.dateOfBirth = new GregorianCalendar();
	this.dateOfBirth.set(yearOfBirth, monthOfBirth-1, dayOfBirth);
	this.customerSince = new GregorianCalendar(); //initializes to today
    } //end of customer constructor

    //<editor-fold desc="Accessor Methods">
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName;  }
    public String getSex() { return sex; }
    public String getProvince() { return province; }
    public String getCity() { return city; }
    public String getAddress() { return address; }
    public String getPhoneNum() { return phoneNum; }
    public String getCustomerID() { return customerID; }
    public GregorianCalendar getDateOfBirth() { return dateOfBirth; }
    public GregorianCalendar getCustomerSince() { return customerSince; }
    @Override public String toString() {
	return "Customer First Name: " + getFirstName() + "\n"
	    + "Customer Last Name: " + getLastName() + "\n"
	    + "Customer Sex: " + getSex() + "\n"
	    + "Customer Address: " + getAddress() + "\n"
	    + "Customer Phone Number: " + getPhoneNum() + "\n"
	    + "Customer EmployeeID: " + getCustomerID() + "\n"
	    + "Customer Date Of Birth: " + getDateOfBirth().getTime();
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

    public void setCustomerID(String customerID) {
	this.customerID = customerID;
    }

    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }
    //</editor-fold>
}
