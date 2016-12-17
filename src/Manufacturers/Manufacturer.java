package Manufacturers;

import Common.Utils;

/**
 * Creates an instance of a Manufacturer
 * @author Anthony Barszcz
 */
public class Manufacturer {
    //<editor-fold desc="Class Variables">
    String name, province, city, address, phoneNum, mfactID;
    //</editor-fold>
    
    public Manufacturer(String name){
        this.name = name;
    }
    public Manufacturer(String name, String province,
	    String city, String address, String phoneNum) {
	this.name = name;
	this.province = province;
	this.city = city;
	this.address = address;
	this.phoneNum = phoneNum;
	this.mfactID = Utils.generateMfactID();
    } //end of Manufacturer constructor
    
    //<editor-fold desc="Accessor Methods">
    public String getName() { return this.name; }
    public String getProvince() { return province; }
    public String getCity() { return city; }
    public String getAddress() { return this.address; };
    public String getPhoneNum() { return this.phoneNum; };
    public String getMfactID() { return this.mfactID; };
    @Override public String toString() {
	return "Manufacturer Name: " + getName() + "\n"
		+ "Manufacturer Address: " + getAddress() + "\n"
		+ "Manufacturer Phone Number: " + getPhoneNum() + "\n"
		+ "Manufacturer ID: " + getMfactID() + "\n";
    }
    //</editor-fold>
    
    //<editor-fold desc="Mutator Methods">
    public void setName(String name) {
	this.name = name;
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
    //</editor-fold>
    
} //end of class
