package Common;

import Employees.Employee;
import GUI.MainGUI;
import Products.Product;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Driver class for the Lab
 * @author Anthony
 */
public class Main {
    public static void main(String[] args) throws IOException {
	//initialize arraylists of employees
	ArrayList<Employee> empList = new ArrayList<>();
	ArrayList<Product> productList = new ArrayList<>();
	
	MainGUI gui = new MainGUI();
	
	//create a buffered reader to get user inputs
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	boolean shouldLoop = true;
	
	while(shouldLoop) {
	    //greet the user and provide options
	    Utils.welcomeMsg();

	    //request selection
	    String selection = br.readLine();

	    switch(selection) {
		case "1": //selected create new employee
		    System.out.println("You selected \"Create New Employee\"!");
		    Utils.employeeSelectMsg();
		    
		    //request selection
		    selection = br.readLine();
		    
		    switch(selection) {
			case "1": //selected salary employee
			    empList.add(Utils.createSalaryEmployee());
			    break;
			case "2": //selected hourly employee
			    empList.add(Utils.createHourlyEmployee());
			    break;
			case "3": //selected commission employee
			    empList.add(Utils.createCommissionSalesEmployee());
			    break;
			case "4": //selected base plus commission employee
			    empList.add(Utils.createBasePlusCommissionEmployee());
			    break;
		    }
		    break; 
		case "2": //selected create new product
		    System.out.println("You selected \"Create New Product\"!");
		    productList.add(Utils.createProduct());
		    break;
		case "3": //selected search for employee
		    System.out.println("You selected \"Search for Employee\"!");
		    String lastName = Utils.requestStringParam("Last Name", "Employee you wish to search for");
		    
		    //create a boolean that saves whether or not a match was found
		    boolean employeeMatchFound = false;
		    
		    //for each employee in the list of employees
		    for(Iterator<Employee> i = empList.iterator(); i.hasNext(); ) {
			//get employee
			Employee emp = i.next();
			
			//if employee's last name matches last name searched for
			if(lastName.equalsIgnoreCase(emp.getLastName())) {
			    System.out.println("Employee found!\n"
				+ emp.toString());
			    employeeMatchFound = true;
			}
		    }
		    
		    if(!employeeMatchFound)
			System.out.println("No match found for an Employee with a last name of " + lastName);
		    break;
		case "4": //selected search for product
		    System.out.println("You selected \"Search for Product\"!");
		    String productName = Utils.requestStringParam("Product Name", "Product you wish to search for");
		    
		    //create a boolean that saves whether or not a match was found
		    boolean productMatchFound = false;
		    
		    //for each product in the list of products
		    for(Iterator<Product> i = productList.iterator(); i.hasNext(); ) {
			//get product
			Product product = i.next();
			
			//if product's name matches product name searched for
			if(productName.equalsIgnoreCase(product.getName())) {
			    System.out.println("Product found!\n"
				+ product.toString());
			    productMatchFound = true;
			}
		    }
		    
		    if(!productMatchFound)
			System.out.println("No match found for a product named " + productName);
		    break;
		default: //no correct selection
		    System.out.println("You did not select a valid option.");
		    break;
	    }
	} //end of while
    } //end of main
} //end of class