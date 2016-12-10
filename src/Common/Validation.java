package Common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Anthony
 */
public class Validation {
    //<editor-fold desc="Generic Validation Methods">
    
    /*
     * These methods are used to validate data before storing it.
     * All methods throw an IllegalArgumentException if the value is not valid.
     */
    
    /*
     * Validates a String.
     */
    public static void isValid(String str) throws IllegalArgumentException {
	//remove leading and trailing whitespace
	str = str.trim();
	
	//null & empty validation
	if("".equals(str)) throw new IllegalArgumentException("Invalid String");
    }
    
    /*
     * Validates an integer.
     */
    public static void isValid(int i) {
	//nothing to do here
    }
    
    /*
     * Validates a double.
     */
    public static void isValid(double dbl) {
	//nothing to do here
    }
    
    /*
     * Validates a BigDecimal.
     */
    public static void isValid(BigDecimal dec) throws IllegalArgumentException {
	//null validation
	if(dec == null) throw new IllegalArgumentException();
    }
    
    /*
     * Validates a BigDecimal when there is a maximum allowed value.
     */
    public static void isValid(BigDecimal dec, BigDecimal maxValue) 
	    throws IllegalArgumentException {
	//null validation
	if(dec != null) {
	    //if it's greater than the max value
	    if(dec.compareTo(maxValue) < 0) throw new IllegalArgumentException();
	} else throw new IllegalArgumentException();
    }
    
    /*
     * Validates a GregorianCalendar.
     * Accepts booleans asking if the date can be before/after today's date
     */
    public static void isValid(GregorianCalendar gc, boolean beforeNow,
	    boolean afterNow) {
	
	//create a calendar for today's date
	GregorianCalendar now = new GregorianCalendar();
	
	//null validation
	if(gc != null) {
	    //if the date is not allowed to be before today
	    if(!beforeNow) {
		//validate that it is not before today
		if(gc.get(gc.YEAR) < now.get(now.YEAR))
		    throw new IllegalArgumentException();

		//only check the month if it is also the current year
		if(gc.get(gc.YEAR) == now.get(now.YEAR) &&
			gc.get(gc.MONTH) < now.get(now.MONTH))
		    throw new IllegalArgumentException();

		//only check the day if it is also the current year and month
		if(gc.get(gc.YEAR) == now.get(now.YEAR) &&
			gc.get(gc.MONTH) == now.get(now.MONTH) &&
			gc.get(gc.DAY_OF_MONTH) < now.get(now.DAY_OF_MONTH))
		    throw new IllegalArgumentException();
	    }

	    //if the date is not allowed to be after today
	    if(!afterNow) {
		//validate that it is not after today
		if(gc.get(gc.YEAR) > now.get(now.YEAR))
		    throw new IllegalArgumentException();

		//only check the month if it is also the current year
		if(gc.get(gc.YEAR) == now.get(now.YEAR) &&
			gc.get(gc.MONTH) > now.get(now.MONTH))
		    throw new IllegalArgumentException();

		//only check the day if it is also the current year and month
		if(gc.get(gc.YEAR) == now.get(now.YEAR) &&
			gc.get(gc.MONTH) == now.get(now.MONTH) &&
			gc.get(gc.DAY_OF_MONTH) > now.get(now.DAY_OF_MONTH))
		    throw new IllegalArgumentException();
	    }
	} else throw new IllegalArgumentException();
    }
    
    /*
     * Validates an ArrayList.
     */
    public static void isValid(ArrayList al) throws IllegalArgumentException {
	
	//null check
	if(al != null) {
	    //validate that it is not empty
	    if(al.isEmpty()) throw new IllegalArgumentException();
	    //validate that it has a size greater than 0
	    if(al.size() < 1) throw new IllegalArgumentException();
	} else throw new IllegalArgumentException();
    }
    
    //</editor-fold>
    
    //<editor-fold desc="Specific Validation Methods">
    
    /*
     * These methods validate data in more complicated and specific ways.
     */
    
    /*
     * Validates that a string is a proper name.
     * Accepts a boolean value that determines if spaces are allowed (such as in
     * full-name entries or manufacturer names).
     * Note: The functionality of this validation is limited.
     * It will not accept names such as:
     *	Peter Müller
     *	François Hollande
     *	Patrick O'Brian
     *	Silvana Koch-Mehrin
     */
    public static void isValidName(String str, boolean hasSpaces) 
	    throws IllegalArgumentException {
	isValid(str);
	
	if(hasSpaces) {
	    //validate that the string is made of legal characters
	    if(!str.matches("[a-zA-Z ]+")) //space allowed
		throw new IllegalArgumentException();
	}
	else {
	    //validate that the string is made of legal characters
	    if(!str.matches("[a-zA-Z]+")) //space not allowed
		throw new IllegalArgumentException();
	}
    }
    
    /*
     * Validates that a string is a sex.
     */
    public static void isValidSex(String str) 
	    throws IllegalArgumentException {
	isValid(str);
	//validate that the input is "male" or "female"
	if(!str.toLowerCase().equals("male") && !str.toLowerCase().equals("female"))
	    throw new IllegalArgumentException();
    }
    
    /*
     * Validates that a string is a phone number.
     * regex pattern acquired from:
     * http://stackoverflow.com/questions/25763935/how-to-check-phone-number-format-is-valid-or-not-from-telephony-manager
     */
    public static void isValidPhoneNum(String str) {
	isValid(str);
	//validate that it is a valid phone number
	if(!str.matches("^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$"))
	    throw new IllegalArgumentException();
    }
    
    //</editor-fold>

}
