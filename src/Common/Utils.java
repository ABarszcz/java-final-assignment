package Common;

import static Common.Validation.*;
import Employees.BasePlusCommissionEmployee;
import Employees.CommissionSalesEmployee;
import Employees.HourlyEmployee;
import Employees.SalaryEmployee;
import Manufacturers.Manufacturer;
import Products.Product;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This is a class with static utility methods for generic use
 * @author Anthony
 */
public class Utils {
    //create a generic calendar to hold current date
    private static GregorianCalendar curDate = new GregorianCalendar();
    //create default ID numbers
    private static int empID = 0;
    private static int mfactID = 0;
    private static int productID = 0;
    
    /*
     * Generate Employee ID number
     */
    public static String generateEmpID() {
	empID++;
	return String.valueOf(empID);
    }
    
    /*
     * Generate Manufacturer ID number
     */
    public static String generateMfactID() {
	mfactID++;
	return String.valueOf(mfactID);
    }
    
    /*
     * Generate Product ID number
     */
    public static String generateProductID() {
	productID++;
	return String.valueOf(productID);
    }
    
    //<editor-fold desc="Simple Message Methods">
    
    /*
     * Simple message methods only perform multi-line print statements
     * for the purpose of de-cluttering the main method.
     */
    
    //prints a welcome message
    public static void welcomeMsg() {
	System.out.println("Welcome! What would you like to do?\n"
	    + "Enter \"1\" to create a new Employee\n"
	    + "Enter \"2\" to create a new Product\n"
	    + "Enter \"3\" to search for an existing Employee\n"
	    + "Enter \"4\" to search for an existing Product");
    }
    
    //prints a message that asks the user which employee they want to create
    public static void employeeSelectMsg() {
	System.out.println("Which type of Employee do you want to create?\n"
	    + "Enter \"1\" for Salary Employee\n"
	    + "Enter \"2\" for Hourly Employee\n"
	    + "Enter \"3\" for Commission Employee\n"
	    + "Enter \"4\" for Base Plus Commission Employee");
    }
    
    //</editor-fold>
    
    //<editor-fold desc="Complex Message Methods">
    
    /*
     * Complex message methods are designed to acquire required parameters from
     * the user. They accept the name of the parameter (ie First Name) and the
     * name of the object that is being created (ie Employee).
     * These parameters are used to create an appropriate request message.
     * The method will use methods from the Validation class to ensure the user
     * inputs a valid response before returning the data.
     */
    
    /*
     * Asks the user for a generic string parameter.
     */
    public static String requestStringParam(String param, String objName) throws IOException {
	String str = "";
	boolean shouldLoop = true;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	while(shouldLoop) {
	    //ask for the input
	    System.out.println("Please enter the " + param + " of the " + objName);
	    str = br.readLine();

	    //validate
	    if(isValid(str))
		//exit the loop if valid
		shouldLoop = false;
	    else
		//inform the user it was invalid
		System.out.println("Invalid " + param + ", please try again");
	}
	
	return str;
    }
    
    /*
     * Asks the user for a name parameter of String type.
     */
    public static String requestNameParam(String param, String objName, boolean hasSpaces) throws IOException {
	String str = "";
	boolean shouldLoop = true;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	while(shouldLoop) {
	    //ask for the input
	    System.out.println("Please enter the " + param + " of the " + objName);
	    str = br.readLine();
	    
	    //validate
	    if(isValidName(str, hasSpaces))
		//exit the loop if valid
		shouldLoop = false;
	    else
		//inform the user it was invalid
		System.out.println("Invalid " + param + ", please try again");
		
	}
	
	return str;
    }
    
    /*
     * Asks the user a sex parameter of String type (Male/Female).
     */
    public static String requestSexParam(String param, String objName) throws IOException {
	String str = "";
	boolean shouldLoop = true;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	while(shouldLoop) {
	    //ask for the input
	    System.out.println("Please enter the " + param + " of the " + objName);
	    str = br.readLine();

	    //validate string
	    if(isValidSex(str))
		//exit the loop if valid
		shouldLoop = false;
	    else
		//inform the user it was invalid
		System.out.println("Invalid " + param + ", please try again");
	}
	
	return str;
    }
    
    /*
     * Asks the user for a phone number parameter of String type.
     */
    public static String requestPhoneNumParam(String param, String objName) throws IOException {
	String str = "";
	boolean shouldLoop = true;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	while(shouldLoop) {
	    //ask for the input
	    System.out.println("Please enter the " + param + " of the " + objName);
	    str = br.readLine();

	    //validate string
	    if(isValidPhoneNum(str))
		//exit the loop if valid
		shouldLoop = false;
	    else
		//inform the user it was invalid
		System.out.println("Invalid " + param + ", please try again");
	}
	
	return str;
    }
    
    /*
     * Asks the user for a social security number parameter of String type.
     */
    public static String requestSocialSecurityNumParam(String param, String objName) throws IOException {
	String str = "";
	boolean shouldLoop = true;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	while(shouldLoop) {
	    //ask for the input
	    System.out.println("Please enter the " + param + " of the " + objName);
	    str = br.readLine();

	    //validate
	    if(isValid(str)) {
		//remove all spaces
		str = str.replace(" ", "");
		//validate that it's numerical
		if(str.matches("^[0-9]*$"))
		    //exit the loop if valid
		    shouldLoop = false;
		else
		    //inform the user it was invalid
		    System.out.println("Invalid " + param + ", please try again");
	    } else
		//inform the user it was invalid
		System.out.println("Invalid " + param + ", please try again");
	}
	
	return str;
    }
    
    /*
     * Asks the user for a generic integer parameter.
     */
    public static int requestIntParam(String param, String objName) throws IOException {
	String res = "";
	int i = 0;
	boolean shouldLoop = true;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	while(shouldLoop) {
	    //ask for the input
	    System.out.println("Please enter the " + param + " of the " + objName);
	    res = br.readLine();
	    
	    //try to parse the string to an integer
	    if(tryParse(res)) {
		i = Integer.parseInt(res);
		//validate the integer
		if(isValid(i)) {
		    shouldLoop = false;
		} else
		    //inform the user it was invalid
		    System.out.println("Invalid " + param + ", please try again");
	    } else
		//inform the user it was invalid
		System.out.println("Invalid " + param + ", please try again");
	}
	return i;
    }
    
    /*
     * Asks the user for a generic integer parameter, but with a min and max value.
     */
    public static int requestIntParam(String param, String objName, int minValue, int maxValue)
	    throws IOException {
	String res = "";
	int i = 0;
	boolean shouldLoop = true;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	while(shouldLoop) {
	    //ask for the input
	    System.out.println("Please enter the " + param + " of the " + objName);
	    res = br.readLine();
	    
	    //try to parse the string to an integer
	    if(tryParse(res)) {
		i = Integer.parseInt(res);
		//validate the integer
		if(isValid(i)) {

		    if(i >= minValue && i <= maxValue)
			//exit the loop if valid
			shouldLoop = false;
		    else
			//inform the user it was invalid
			System.out.println("Invalid " + param + ", please try again");
		} else
		    //inform the user it was invalid
		    System.out.println("Invalid " + param + ", please try again");
	    } else
		//inform the user it was invalid
		System.out.println("Invalid " + param + ", please try again");
	}
	return i;
    }
    
    /*
     * Asks the user for a generic BigDecimal parameter.
     */
    public static BigDecimal requestBigDecimalParam(String param, String objName) throws IOException {
	String res;
	BigDecimal deci = new BigDecimal("0.00");
	final BigDecimal DIVISOR = new BigDecimal("100.00");
	boolean shouldLoop = true;
	boolean isPerc = false;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	while(shouldLoop) {
	    //ask for the input
	    System.out.println("Please enter the " + param + " of the " + objName);
	    
	    //get the response
	    res = br.readLine();
	    
	    //validate the response
	    if(isValid(res)) {
	    
		//remove all $
		res = res.replace("$", "");

		//check to see if the string contains a %
		if(res.contains("%")) {
		    //if it does, save it as a bool so we can conver to decimal later
		    isPerc = true;
		    //remove them
		    res.replace("%", "");
		}
		//ensure the string is only numbers
		if(res.matches("^[0-9]*$")) {
		    deci = new BigDecimal(res);
		    
		    //validate the decimal
		    if(isValid(deci)) {
			//if it was supposed to be a %, divide by 100 to get a decimal
			//(ie 20% = .20 instead of 20)
			if(isPerc)
			    deci = deci.divide(DIVISOR);
			//exit the loop
			shouldLoop = false;
		    } else
			//inform the user it was invalid
			System.out.println("Invalid " + param + ", please try again");
		} else
		    //inform the user it was invalid
		    System.out.println("Invalid " + param + ", please try again");
	    } else
		//inform the user it was invalid
		System.out.println("Invalid " + param + ", please try again");
	}
	
	return deci;
    }
    
    //</editor-fold>
    
    //<editor-fold desc="Object Creators">
    
    /*
     * Creates a SalaryEmployee object by utilizing the Complex Message Methods to 
     * get information from the user.
     */
    public static SalaryEmployee createSalaryEmployee() throws IOException {
	//get generic Employee information
	String firstName = Utils.requestNameParam("First Name", "Employee", false);
	String lastName = Utils.requestNameParam("Last Name", "Employee", false);
	String sex = Utils.requestSexParam("Sex (Male/Female)", "Employee");
	String address = Utils.requestStringParam("Address", "Employee");
	String phoneNum = Utils.requestPhoneNumParam("Phone Number", "Employee");
	String department = Utils.requestStringParam("Department", "Employee");
	String position = Utils.requestStringParam("Position", "Employee");
	String employeeID = Utils.requestStringParam("Employee ID", "Employee");
	String socialSecurityNum = Utils.requestSocialSecurityNumParam("Social Security Number", "Employee");
	int yearOfBirth = Utils.requestIntParam("Year of Birth", "Employee", 1900, curDate.get(Calendar.YEAR));
	int monthOfBirth = Utils.requestIntParam("Month of Birth", "Employee", 1, 12);
	int dayOfBirth = 0;
	
	//logic to determine what are valid inputs for day of birth
	boolean isLeapYear;
	
	//if they were born in a month with 31 days, max day allowed is 31
	if(monthOfBirth == 4 || monthOfBirth == 6 || monthOfBirth == 9 || 
		monthOfBirth == 11)
	    dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 31);

	//if they were born in a month with 30 days, max day allowed is 30
	if(monthOfBirth == 1 || monthOfBirth == 3 || monthOfBirth == 5 || 
		monthOfBirth == 7 || monthOfBirth == 8 || monthOfBirth == 10 || 
		monthOfBirth == 12)
	    dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 30);

	//if they were born in february
	if(monthOfBirth == 2) {
	    //determine if their birthdate is on a leap year
	    Calendar cal = GregorianCalendar.getInstance();
	    cal.set(GregorianCalendar.YEAR, yearOfBirth);
	    isLeapYear = cal.getActualMaximum(GregorianCalendar.DAY_OF_YEAR) > 365;

	    if(isLeapYear) //if it is, max day allowed is 29
		dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 29);
	    else //if it isn't, max day allowed is 28
		dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 28);
	}
	
	//get SalaryEmployee specific data
	String salaryType = Utils.requestStringParam("Salary Type", "Employee");
	BigDecimal salaryAmount = Utils.requestBigDecimalParam("Salary Amount", "Employee");
	
	//create and return an anonymous SalaryEmployee object using the data
	return new SalaryEmployee(firstName, lastName, sex, address, phoneNum,
	department, position, socialSecurityNum, salaryType, yearOfBirth,
	monthOfBirth, dayOfBirth, salaryAmount);
    }
    
     /*
      * Creates a ourlyEmployee object by utilizing the Complex Message Methods to 
      * get information from the user.
      */
    public static HourlyEmployee createHourlyEmployee() throws IOException {
	//get generic Employee information
	String firstName = Utils.requestNameParam("First Name", "Employee", false);
	String lastName = Utils.requestNameParam("Last Name", "Employee", false);
	String sex = Utils.requestSexParam("Sex (Male/Female)", "Employee");
	String address = Utils.requestStringParam("Address", "Employee");
	String phoneNum = Utils.requestPhoneNumParam("Phone Number", "Employee");
	String department = Utils.requestStringParam("Department", "Employee");
	String position = Utils.requestStringParam("Position", "Employee");
	String employeeID = Utils.requestStringParam("Employee ID", "Employee");
	String socialSecurityNum = Utils.requestSocialSecurityNumParam("Social Security Number", "Employee");
	int yearOfBirth = Utils.requestIntParam("Year of Birth", "Employee", 1900, curDate.get(Calendar.YEAR));
	int monthOfBirth = Utils.requestIntParam("Month of Birth", "Employee", 1, 12);
	int dayOfBirth = 0;
	
	//logic to determine what are valid inputs for day of birth
	boolean isLeapYear;
	
	//if they were born in a month with 31 days, max day allowed is 31
	if(monthOfBirth == 4 || monthOfBirth == 6 || monthOfBirth == 9 || 
		monthOfBirth == 11)
	    dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 31);

	//if they were born in a month with 30 days, max day allowed is 30
	if(monthOfBirth == 1 || monthOfBirth == 3 || monthOfBirth == 5 || 
		monthOfBirth == 7 || monthOfBirth == 8 || monthOfBirth == 10 || 
		monthOfBirth == 12)
	    dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 30);

	//if they were born in february
	if(monthOfBirth == 2) {
	    //determine if their birthdate is on a leap year
	    Calendar cal = GregorianCalendar.getInstance();
	    cal.set(GregorianCalendar.YEAR, yearOfBirth);
	    isLeapYear = cal.getActualMaximum(GregorianCalendar.DAY_OF_YEAR) > 365;

	    if(isLeapYear) //if it is, max day allowed is 29
		dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 29);
	    else //if it isn't, max day allowed is 28
		dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 28);
	}
	
	//get HourlyEmployee specific data
	BigDecimal wage = Utils.requestBigDecimalParam("Wage", "Employee");
	
	//create and return an anonymous HourlyEmployee object using the data
	return new HourlyEmployee(firstName, lastName, sex, address, phoneNum,
	department, position, socialSecurityNum, yearOfBirth,
	monthOfBirth, dayOfBirth, wage);
    }
    
     /*
      * Creates a BasePlusCommissionEmployee object by utilizing the Complex Message Methods to 
      * get information from the user.
      */
    public static BasePlusCommissionEmployee createBasePlusCommissionEmployee() throws IOException {
	//get generic Employee information
	String firstName = Utils.requestNameParam("First Name", "Employee", false);
	String lastName = Utils.requestNameParam("Last Name", "Employee", false);
	String sex = Utils.requestSexParam("Sex (Male/Female)", "Employee");
	String address = Utils.requestStringParam("Address", "Employee");
	String phoneNum = Utils.requestPhoneNumParam("Phone Number", "Employee");
	String department = Utils.requestStringParam("Department", "Employee");
	String position = Utils.requestStringParam("Position", "Employee");
	String employeeID = Utils.requestStringParam("Employee ID", "Employee");
	String socialSecurityNum = Utils.requestSocialSecurityNumParam("Social Security Number", "Employee");
	int yearOfBirth = Utils.requestIntParam("Year of Birth", "Employee", 1900, curDate.get(Calendar.YEAR));
	int monthOfBirth = Utils.requestIntParam("Month of Birth", "Employee", 1, 12);
	int dayOfBirth = 0;
	
	//logic to determine what are valid inputs for day of birth
	boolean isLeapYear;
	
	//if they were born in a month with 31 days, max day allowed is 31
	if(monthOfBirth == 4 || monthOfBirth == 6 || monthOfBirth == 9 || 
		monthOfBirth == 11)
	    dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 31);

	//if they were born in a month with 30 days, max day allowed is 30
	if(monthOfBirth == 1 || monthOfBirth == 3 || monthOfBirth == 5 || 
		monthOfBirth == 7 || monthOfBirth == 8 || monthOfBirth == 10 || 
		monthOfBirth == 12)
	    dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 30);

	//if they were born in february
	if(monthOfBirth == 2) {
	    //determine if their birthdate is on a leap year
	    Calendar cal = GregorianCalendar.getInstance();
	    cal.set(GregorianCalendar.YEAR, yearOfBirth);
	    isLeapYear = cal.getActualMaximum(GregorianCalendar.DAY_OF_YEAR) > 365;

	    if(isLeapYear) //if it is, max day allowed is 29
		dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 29);
	    else //if it isn't, max day allowed is 28
		dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 28);
	}
	
	//get BasePlusCommissionEmployee specific data
	BigDecimal commissionRates = Utils.requestBigDecimalParam("Commission Rates", "Employee");
	BigDecimal baseSalary = Utils.requestBigDecimalParam("Base Salary", "Employee");
	
	//create and return an anonymous BasePlusCommissionEmployee object using the data
	return new BasePlusCommissionEmployee(firstName, lastName, sex, address, phoneNum,
	department, position, socialSecurityNum, yearOfBirth,
	monthOfBirth, dayOfBirth, commissionRates, baseSalary);
    }
    
     /*
      * Creates a CommissionSalesEmployee object by utilizing the Complex Message Methods to 
      * get information from the user.
      */
    public static CommissionSalesEmployee createCommissionSalesEmployee() throws IOException {
	//get generic Employee information
	String firstName = Utils.requestNameParam("First Name", "Employee", false);
	String lastName = Utils.requestNameParam("Last Name", "Employee", false);
	String sex = Utils.requestSexParam("Sex (Male/Female)", "Employee");
	String address = Utils.requestStringParam("Address", "Employee");
	String phoneNum = Utils.requestPhoneNumParam("Phone Number", "Employee");
	String department = Utils.requestStringParam("Department", "Employee");
	String position = Utils.requestStringParam("Position", "Employee");
	String employeeID = Utils.requestStringParam("Employee ID", "Employee");
	String socialSecurityNum = Utils.requestSocialSecurityNumParam("Social Security Number", "Employee");
	int yearOfBirth = Utils.requestIntParam("Year of Birth", "Employee", 1900, curDate.get(Calendar.YEAR));
	int monthOfBirth = Utils.requestIntParam("Month of Birth", "Employee", 1, 12);
	int dayOfBirth = 0;
	
	//logic to determine what are valid inputs for day of birth
	boolean isLeapYear;
	
	//if they were born in a month with 31 days, max day allowed is 31
	if(monthOfBirth == 4 || monthOfBirth == 6 || monthOfBirth == 9 || 
		monthOfBirth == 11)
	    dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 31);

	//if they were born in a month with 30 days, max day allowed is 30
	if(monthOfBirth == 1 || monthOfBirth == 3 || monthOfBirth == 5 || 
		monthOfBirth == 7 || monthOfBirth == 8 || monthOfBirth == 10 || 
		monthOfBirth == 12)
	    dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 30);

	//if they were born in february
	if(monthOfBirth == 2) {
	    //determine if their birthdate is on a leap year
	    Calendar cal = GregorianCalendar.getInstance();
	    cal.set(GregorianCalendar.YEAR, yearOfBirth);
	    isLeapYear = cal.getActualMaximum(GregorianCalendar.DAY_OF_YEAR) > 365;

	    if(isLeapYear) //if it is, max day allowed is 29
		dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 29);
	    else //if it isn't, max day allowed is 28
		dayOfBirth = Utils.requestIntParam("Day of Birth", "Employee", 1, 28);
	}
	
	//get CommissionSalesEmployee specific data
	BigDecimal commissionRates = Utils.requestBigDecimalParam("Commission Rates", "Employee");
	
	//create and return an anonymous CommissionSalesEmployee object using the data
	return new CommissionSalesEmployee(firstName, lastName, sex, address, phoneNum,
	department, position, socialSecurityNum, yearOfBirth,
	monthOfBirth, dayOfBirth, commissionRates);
    }
    
    /*
     * Creates a Product object by utilizing the Complex Message methods to get information
     * from the user.
     */
    public static Product createProduct() throws IOException {
	//get parameter information from user
	String name = Utils.requestNameParam("Name", "Product", true);
	String productID = Utils.requestStringParam("ID", "Product");
	BigDecimal price = Utils.requestBigDecimalParam("Price", "Product");
	BigDecimal discount = Utils.requestBigDecimalParam("Discount", "Product");
	Manufacturer mfact = createMfact();
	
	//return anonymous Product object
	return new Product(name, price, discount, mfact);
    }
    
    /*
     * Creates a Product object by utilizing the Complex Message methods to get information
     * from the user.
     */
    public static Manufacturer createMfact() throws IOException {
	//get parameter information from user
	String name = Utils.requestNameParam("Name", "Manufacturer", true);
	String address = Utils.requestStringParam("Address", "Manufacturer");
	String phoneNum = Utils.requestPhoneNumParam("Phone Number", "Manufacturer");
	String mfactID = Utils.requestStringParam("ID", "Manufacturer");
	
	//return anonymous Manufacturer object
	return new Manufacturer(name, address, phoneNum);
    }
    
    //</editor-fold>
    
    //<editor-fold desc="Other">
    
    //attempt to parse an integer
    public static boolean tryParse(String str) {  
     try {  
         Integer.parseInt(str);  
         return true;
      } catch (NumberFormatException ex) {  
         return false;  
      }  
    }
    
    //</editor-fold>
}
