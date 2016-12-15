package Common;

import Employees.CommissionSalesEmployee;
import Employees.Employee;
import GUI.*;
import Products.Product;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Driver class for the Lab
 * @author Anthony
 */
public class Main {
    public static void main(String[] args) throws IOException {
	//initialize arraylists
	ArrayList<Employee> empList = new ArrayList<>();
	ArrayList<Product> productList = new ArrayList<>();
	
	//new Login();
       new LoginK();
//	MainGUI gui = new MainGUI();
    //   gui.setVisible(true);
	
        //create emp object for testing purposes
	CommissionSalesEmployee emp = new CommissionSalesEmployee(
	"name", "name", "male", "ontario", "city", "address", "phoneNum",
	"department", "position", "ssn", 2000, 11, 25, new BigDecimal("50"),
		new BigDecimal("10"));
    } //end of main
} //end of class