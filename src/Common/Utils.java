package Common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 * This is a class with static utility methods for generic use
 * @author Anthony
 */
public class Utils {
    
//add start takaaki
    /** action: edit */
    public static final String ACTION_EDIT = "edit";
    /** action: delete */
    public static final String ACTION_DELETE = "delete";
//add e n d takaaki
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
      } catch (Exception ex) {
	  logError(ex);
	  return false;
      }
    }
    
    //log an error
    public static void logError(Exception ex) {
	try {
	    StringWriter sw = new StringWriter();
	    ex.printStackTrace(new PrintWriter(sw)); //get the stack trace
	    String error = sw.toString(); //save the stack trace in a string
	    sw.close();

	    //create the directory
	    File errorDir = new File("C:/Errors/");
	    File errorFile = new File(errorDir, "errors.txt");

	    //if the file doesn't exist
	    if(!errorFile.exists()) {
		errorDir.mkdirs(); //make the directories
		errorFile.createNewFile();//create the file in the directory
	    }

	    //create BufferedWriter for writing to the text file
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(errorFile, true)));

	    //append the info
	    out.append(new GregorianCalendar().getTime() + "\n"); //current date/time
	    out.append(error + "\n"); //error
	    out.append("=====================================================================================\n\n"); //bottom separator

	    out.close();
	} catch (IOException ioEx) {
	    System.out.println("Error logging exception\n" + ioEx.toString());
	} catch (Exception genEx) {
	    System.out.println("Error logging exception\n" + genEx.toString());
	}
    }
    
    //show a confirmation dialog
    public static boolean showConfirmDialog(String action) {
	int reply = JOptionPane.showConfirmDialog(null, "Are you sure you wish to " + action + "?", "Confirmation",
		JOptionPane.YES_NO_OPTION);
	
	return reply == JOptionPane.YES_OPTION;
    }
    
    /**
     * It checks the string value is empty or not.
     * @param string
     * @return true if it is null or "", false otherwise
     */
    public static boolean isEmpty(String string) {
        if (string == null) {
            return true;
        }
        
        if ("".equals(string)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * It checks the string value is empty or not.
     * @param value
     * @return true if it is null or "", false otherwise
     */
    public static boolean isZero(BigDecimal value) {
        if (value == null) {
            return true;
        }
        System.out.println("value:" + value);
        System.out.println("result of compareTo:" + BigDecimal.ZERO.compareTo(value));
        if (BigDecimal.ZERO.compareTo(value) == 0) {
            return true;
        }
        return false;
    }
}
