package Common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Anthony
 */
public class Validation {
    //<editor-fold desc="Specific Validation Methods">
    
    /*
     * These methods check if specific entries are valid, such as ensuring
     * a name stored in a String does not contain numbers.
     */
    
    /*
     * Validates that a string is a name.
     * Accepts a boolean value that determines if spaces are allowed (such as in
     * full-name entries or manufacturer names).
     * Note: The functionality of this validation is limited. I do not know enough
     * about RegEx to make a proper name validation, and it will not accept names such as:
     *	Peter Müller
     *	François Hollande
     *	Patrick O'Brian
     *	Silvana Koch-Mehrin
     */
    public static boolean isValidName(String str, boolean hasSpaces) {
	//first validate the string itself
	if(isValid(str))
	    if(hasSpaces)
		//if the string is made of legal characters
		if(str.matches("[a-zA-Z ]+")) //space allowed
		    return true;
		else return false;
	    else
		//if the string is made of legal characters
		if(str.matches("[a-zA-Z]+")) //space not allowed
		    return true;
		else return false;
	else return false;
    }
    
    /*
     * Validates that a string is a sex.
     */
    public static boolean isValidSex(String str) {
	//first validate the string itself
	if(isValid(str))
	    //validate that input is "Male" or "Female"
	    if(str.toLowerCase().equals("male") || str.toLowerCase().equals("female"))
		return true;
	    else return false;
	else return false;
    }
    
    /*
     * Validates that a string is a phone number.
     * regex pattern acquired from:
     * http://stackoverflow.com/questions/25763935/how-to-check-phone-number-format-is-valid-or-not-from-telephony-manager
     */
    public static boolean isValidPhoneNum(String str) {
	//first validate the string itself
	if(isValid(str))
	    //validate that it is a valid phone number
	    if(str.matches("^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$"))
		return true;
	    else return false;
	else return false;
    }
    
    //</editor-fold>
    
    //<editor-fold desc="Generic Validation Methods">
    
    /*
     * These methods simply validate data before storing them in variables.
     * All isValid methods will perfrom generic data validation, such as checking
     * for null, empty, and completely invalid inputs (ie an integer set to a special
     * character).
     * All isValid methods have an isValid boolean, which is set to true by default
     * and false if any validation tests are failed. This value is then returned
     * at the end of the method.
     * isValid returns true if it is a valid value, false if it is not.
     */
    
    /*
     * Validates a String.
     */
    public static boolean isValid(String str) {
	//initially assume it is true
	boolean isValid = true;
	
	//remove leading and trailing whitespace
	str = str.trim();
	
	//null & empty validation
	if("".equals(str)) isValid = false; 
	
	//return the value
	return isValid;
    }
    
    /*
     * Validates an integer.
     */
    public static boolean isValid(int i) {
	boolean isValid = true;
	
	//nothing to do here
	
	return isValid;
    }
    
    /*
     * Validates a double.
     */
    public static boolean isValid(double dbl) {
	boolean isValid = true;
	
	//nothing to do here
	
	return isValid;
    }
    
    /*
     * Validates a BigDecimal.
     */
    public static boolean isValid(BigDecimal dec) {
	//initially assume it is true
	boolean isValid = true;
	
	//null validation
	if(dec == null) isValid = false;
	
	//return the value
	return isValid;
    }
    
    /*
     * Validates a BigDecimal when there is a maximum allowed value.
     */
    public static boolean isValid(BigDecimal dec, BigDecimal maxValue) {
	//initially assume it is true
	boolean isValid = true;
	
	//null validation
	if(dec != null) {
	    //if it's greater than the max value
	    if(dec.compareTo(maxValue) < 0) isValid = false;
	} else isValid = false;
	
	//return the value
	return isValid;
    }
    
    /*
     * Validates a GregorianCalendar.
     * Accepts booleans asking if the date can be before/after today's date
     */
    public static boolean isValid(GregorianCalendar gc, boolean beforeNow,
	    boolean afterNow) {
	//initially assume it is true
	boolean isValid = true;
	
	//create a calendar for today's date
	GregorianCalendar now = new GregorianCalendar();
	
	//perform tests which will set it to false if it fails any
	if(gc == null) isValid = false;
	
	//null validation
	if(gc != null) {
	    //if the date is not allowed to be before today
	    if(!beforeNow) {
		//validate that iit is not at an earlier date
		if(gc.get(gc.YEAR) < now.get(now.YEAR)) isValid = false;

		//only check the month if it is also the current year
		if(gc.get(gc.YEAR) == now.get(now.YEAR) &&
			gc.get(gc.MONTH) < now.get(now.MONTH))
		    isValid = false;

		//only check the day if it is also the current year and month
		if(gc.get(gc.YEAR) == now.get(now.YEAR) &&
			gc.get(gc.MONTH) == now.get(now.MONTH) &&
			gc.get(gc.DAY_OF_MONTH) < now.get(now.DAY_OF_MONTH))
		    isValid = false;
	    }

	    //if the date is not allowed to be after today
	    if(!afterNow) {
		//validate that it is not at a later date
		if(gc.get(gc.YEAR) > now.get(now.YEAR)) isValid = false;

		//only check the month if it is also the current year
		if(gc.get(gc.YEAR) == now.get(now.YEAR) &&
			gc.get(gc.MONTH) > now.get(now.MONTH))
		    isValid = false;

		//only check the day if it is also the current year and month
		if(gc.get(gc.YEAR) == now.get(now.YEAR) &&
			gc.get(gc.MONTH) == now.get(now.MONTH) &&
			gc.get(gc.DAY_OF_MONTH) > now.get(now.DAY_OF_MONTH))
		    isValid = false;
	    }
	} else isValid = false;
	
	//return the value
	return isValid;
    }
    
    /*
     * Validates an ArrayList.
     */
    public static boolean isValid(ArrayList al) {
	//initially assume it is true
	boolean isValid = true;
	
	//perform tests which will set it to false if it fails any
	if(al != null) {
	    //validate that it is not empty
	    if(al.isEmpty()) isValid = false;
	    //validate that it has a size greater than 0
	    if(al.size() < 1) isValid = false;
	} else isValid = false;
	
	//return the value
	return isValid;
    }
    
    //</editor-fold>
}
