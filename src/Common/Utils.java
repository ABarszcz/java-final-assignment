package Common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.GregorianCalendar;

/**
 * This is a class with static utility methods for generic use
 * @author Anthony
 */
public class Utils {
    
    //<editor-fold desc="ID Number Generation">
    //create default ID numbers
    private static int empID = 0;
    private static int mfactID = 0;
    private static int productID = 0;
    private static int customerID = 0;
    private static int saleID = 0;
    
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
    
    /*
     * Generate customer ID number
     */
    public static String generateCustomerID() {
	customerID++;
	return String.valueOf(customerID);
    }
    
    /*
     * Generate sale ID number
     */
    public static String generateSaleID() {
	saleID++;
	return String.valueOf(saleID);
    }
    
    //</editor-fold>
    
    //attempt to parse an integer
    public static boolean tryParse(String str) {  
     try {  
         Integer.parseInt(str);  
         return true;
      } catch (NumberFormatException ex) {  
         return false;  
      }
    }
    
    //log an error
    public static void logError(Exception ex) throws IOException {
	//create a StringWriter to hold the stack trace
	StringWriter sw = new StringWriter();
	//print the stack trace to the string writer
	ex.printStackTrace(new PrintWriter(sw));
	//get the exception from the string writer
	String error = sw.toString();
	sw.close();
	
	//create BufferedWriter for writing to text files
	BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Errors/Errors.txt"));
	//create a GregorianCalendar to get the current time
	GregorianCalendar now = new GregorianCalendar();
	
	bw.append("============================================"); //top separator
	bw.append("\n" + now.toString()); //current date/time
	bw.append("\n" + error); //error
	bw.append("\n" + "============================================"); //bottom separator
	bw.newLine(); //new line
	
	bw.close();
    }
}
