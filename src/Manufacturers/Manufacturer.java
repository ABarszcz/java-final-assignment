package Manufacturers;

import Common.Utils;
import static Common.Validation.*;

/**
 * Creates an instance of a Manufacturer
 * @author Anthony Barszcz
 */
public class Manufacturer {
    //<editor-fold desc="Class Variables">
    String name, address, phoneNum, mfactID;
    //</editor-fold>
    
    public Manufacturer (String name, String address, String phoneNum) {
	this.name = name;
	this.address = address;
	this.phoneNum = phoneNum;
	this.mfactID = Utils.generateMfactID();
    } //end of Manufacturer constructor
    
    //<editor-fold desc="Accessor Methods">
    public String getName() { return this.name; };
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
	if(isValid(name))
	    this.name = name;
    }
    
    public void setAddress(String address) {
	if(isValid(address))
	    this.address = address;
    }
    
    public void setPhoneNum(String phoneNum) {
	if(isValid(phoneNum))
	    this.phoneNum = phoneNum;
    }
    //</editor-fold>
    
} //end of class
