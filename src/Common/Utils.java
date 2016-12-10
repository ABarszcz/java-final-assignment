package Common;

/**
 * This is a class with static utility methods for generic use
 * @author Anthony
 */
public class Utils {
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
