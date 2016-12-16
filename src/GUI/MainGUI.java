package GUI;

import Common.Utils;
import Common.Validation;
import Customer.Customer;
import Customer.CustomerHelper;
import GUI.JTable.CustomerJTable;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Double.parseDouble;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Creates the Graphical User Interface for the application.
 * @author Noah Michael
 */
public class MainGUI extends JFrame {
    private final JPanel pnlFullScreen;
    private final JTabbedPane pnlTabbedPane;
    
    //<editor-fold desc="Greeting Message">
    private final JPanel pnlGreeting;
    private final JLabel lblGreeting;
    //</editor-fold>
    
    //<editor-fold desc="Exit Button">
    private final JPanel pnlExit;
    private final JButton btnExit;
    //</editor-fold> 
     
    //<editor-fold desc="Employee GUI Components">
    private final JTabbedPane pnlEmp; //holds all employee content
    
    //S = Salary, H = Hourly, CS = Commission-Sales, BPC = Base Plus Commission
    private final JPanel pnlEmpS, pnlEmpH, pnlEmpCS, pnlEmpBPC, pnlEmpSCenter,
	    pnlEmpHCenter, pnlEmpCSCenter, pnlEmpBPCCenter, pnlEmpSSouth, pnlEmpSouth, pnlEmpHSouth,
	    pnlEmpCSSouth, pnlEmpBPCSouth, pnlEmpSLabelGrid, pnlEmpHLabelGrid,
	    pnlEmpCSLabelGrid, pnlEmpBPCLabelGrid, pnlEmpSFieldGrid, pnlEmpHFieldGrid,
	    pnlEmpCSFieldGrid, pnlEmpBPCFieldGrid, pnlEmpSSex, pnlEmpHSex, pnlEmpCSSex,
	    pnlEmpBPCSex, pnlEmpSearch, pnlEmpSearchNorth, pnlEmpSearchCenter, pnlEmpSearchSouth;
    private final JLabel lblEmpSearchLastName, lblEmpSearchDepartment;
    private final JTextField txtEmpSearchLastName, txtEmpSearchDepartment;
    private final JTextArea txaEmpResults;
    private final JScrollPane spEmpResults;
    
    //text fields for Salary employee
    private final JTextField txtEmpSFirstName,
	    txtEmpSLastName, txtEmpSAddress, txtEmpSCity, txtEmpSProvince, txtEmpSPhoneNum, txtEmpSDepartment,
	    txtEmpSPosition, txtEmpSSocialSecurityNum, txtEmpSYearOfBirth,
	    txtEmpSMonthOfBirth, txtEmpSDayOfBirth, txtEmpSSalaryType, txtEmpSSalaryAmount,
	    txtEmpSWage, txtEmpSCommissionRates, txtEmpSBaseSalary;
    
    //radio buttons and the radio button group for Salary employee
    JRadioButton rdoEmpSSexMale, rdoEmpSSexFemale;
    ButtonGroup grpEmpSSex;
    
    //text fields for Hourly employee
    private final JTextField txtEmpHFirstName,
	    txtEmpHLastName, txtEmpHAddress, txtEmpHCity, txtEmpHProvince, txtEmpHPhoneNum, txtEmpHDepartment,
	    txtEmpHPosition, txtEmpHSocialSecurityNum, txtEmpHYearOfBirth,
	    txtEmpHMonthOfBirth, txtEmpHDayOfBirth, txtEmpHSalaryType, txtEmpHSalaryAmount,
	    txtEmpHWage, txtEmpHCommissionRates, txtEmpHBaseSalary;
    
    //create radio buttons and the radio button group for Hourly employee
    JRadioButton rdoEmpHSexMale, rdoEmpHSexFemale;
    ButtonGroup grpEmpHSex;
    
    //text fields for Commission-Sales employee
    private final JTextField txtEmpCSFirstName,
	    txtEmpCSLastName, txtEmpCSAddress, txtEmpCSCity, txtEmpCSProvince, txtEmpCSPhoneNum, txtEmpCSDepartment,
	    txtEmpCSPosition, txtEmpCSSocialSecurityNum, txtEmpCSYearOfBirth,
	    txtEmpCSMonthOfBirth, txtEmpCSDayOfBirth, txtEmpCSSalaryType, txtEmpCSSalaryAmount,
	    txtEmpCSWage, txtEmpCSCommissionRates, txtEmpCSBaseSalary;
    
    //create radio buttons and the radio button group for Commission-Sales employee
    JRadioButton rdoEmpCSSexMale, rdoEmpCSSexFemale;
    ButtonGroup grpEmpCSSex;
    
    //text fields for Base Plus Commission employee
    private final JTextField txtEmpBPCFirstName,
	    txtEmpBPCLastName, txtEmpBPCAddress, txtEmpBPCCity, txtEmpBPCProvince, txtEmpBPCPhoneNum, txtEmpBPCDepartment,
	    txtEmpBPCPosition, txtEmpBPCSocialSecurityNum, txtEmpBPCYearOfBirth,
	    txtEmpBPCMonthOfBirth, txtEmpBPCDayOfBirth, txtEmpBPCSalaryType, txtEmpBPCSalaryAmount,
	    txtEmpBPCWage, txtEmpBPCCommissionRates, txtEmpBPCBaseSalary;
    
    //create radio buttons and the radio button group for Base Plus Commission employee
    JRadioButton rdoEmpBPCSexMale, rdoEmpBPCSexFemale;
    ButtonGroup grpEmpBPCSex;
    
    //create "create new" buttons
    private final JButton btnEmpSNew, btnEmpHNew, btnEmpCSNew, btnEmpBPCNew,btnEmployeeEdit, btnEmployeeDelete;
    
    //</editor-fold>
    
    //<editor-fold desc="Manufacturer GUI Components">
    private final JTabbedPane pnlMfact;
    private final JPanel pnlMfactNew, pnlMfactNorth, pnlMfactCenter, pnlMfactSouth,
	    pnlMfactLabelGrid, pnlMfactFieldGrid, pnlMfactSearch, pnlMfactSearchNorth,
	    pnlMfactSearchCenter, pnlManufacturerSouth;
    private final JTextField txtMfactSearch, txtMfactName, txtMfactAddress, txtMfactCity, txtMfactProvince,
	    txtMfactPhoneNum;
    private final JButton btnMfactNew;
    private final JLabel lblMfactSearch;
    private final JTextArea txaMfactResults;
    private final JScrollPane spMfactResults;
    
    private final JButton btnManufacturerEdit, btnManufacturerDelete;
    
    //</editor-fold>
    
    //<editor-fold desc="Product GUI Components">
    // pnlProductSouthED; E = Edit, D = Delete
    private final JTabbedPane pnlProduct;
    private final JPanel pnlProductNew, pnlProductNorth, pnlProductCenter,
	    pnlProductSouth, pnlProductLabelGrid, pnlProductFieldGrid, pnlProductSearch,
	    pnlProductSearchNorth, pnlProductSearchCenter, pnlProductSouthED;
    private final JTextField txtProductSearch, txtProductName, txtProductPrice,
	    txtProductDiscount, txtProductManuSearch;
    private final JComboBox cboProductManufacturer;
    private final JButton btnProductNew;
    private final JLabel lblProductSearch, lblProductManuSearch;
    private final JTextArea txaProductResults;
    private final JScrollPane spProductResults;
    
    private final JButton btnProductEdit, btnProductDelete;
    
    //</editor-fold>
    
    //<editor-fold desc="Sales GUI Components">
    // pnlSalesSouthED; E = Edit, D = Delete
    private final JTabbedPane pnlSales;
    private final JPanel pnlSalesNew, pnlSalesNorth, pnlSalesCenter,
	    pnlSalesSouth, pnlSalesLabelGrid, pnlSalesFieldGrid, pnlSalesSearch,
	    pnlSalesSearchNorth, pnlSalesSearchCenter, pnlSalesSouthED;
    private final JTextField txtSalesSearchLastName, txtSalesProductName, txtSalesCustomer, txtSalesEmployee,
	    txtSalesCommission, txtSalesSearchProductName, txtSalesDate;
    private final JButton btnSalesNew;
    private final JLabel lblSalesSearchLastName, lblSalesSearchProductName;
    private final JTextArea txaSalesResults;
    private final JScrollPane spSalesResults;
    
    private final JButton btnSalesEdit, btnSalesDelete;
    
    //</editor-fold>
    
    //<editor-fold desc="Customer GUI components">
    // pnlCustomerSouthED; E = Edit, D = Delete
    private final JTabbedPane pnlCustomer;
    private final JPanel pnlCustomerNew, pnlCustomerNorth, pnlCustomerCenter,
            pnlCustomerSouth, pnlCustomerLabelGrid, pnlCustomerFieldGrid, pnlCustomerSearch,
            pnlCustomerSearchNorth, pnlCustomerSearchCenter, pnlCustomerSex, pnlCustomerSouthED;
    private final JTextField txtCustomerSearchLastName, txtCustomerSearchPhoneNumber, txtCustomerFirstName, 
            txtCustomerLastName, txtCustomerPhone, txtCustomerSex, txtCustomerProvince, txtCustomerCity, 
            txtCustomerAddress, txtCustomerYearOfBirth, txtCustomerMonthOfBirth, txtCustomerDayOfBirth;
    private final JButton btnCustomerNew;
    private final JLabel lblCustomerSearchLastName, lblCustomerSearchPhoneNumber;
//    private final JTextArea txaCustomerResults;
//    private final JScrollPane spCustomerResults;
    private final CustomerJTable customerJTable;
    
    //radio buttons and the radio button group for Salary employee
    JRadioButton rdoCustomerSexMale, rdoCustomerSexFemale;
    ButtonGroup grpCustomerSex;
    
    private final JButton btnCustomerSearch, btnCustomerEdit, btnCustomerDelete;
    
    //</editor-fold>
    
    public MainGUI() {
	super("Business Manager");
	//initialize full-screen components
	this.pnlFullScreen = new JPanel();
	this.pnlTabbedPane = new JTabbedPane();
	
	//initialize greeting components
	this.pnlGreeting = new JPanel();
	this.lblGreeting = new JLabel("Welcome!");
	
	//<editor-fold desc="Initialize Employee Components">

	//initialize tabbedpane
	this.pnlEmp = new JTabbedPane();
	
	//initialize JPanels
	this.pnlEmpS = new JPanel();
	this.pnlEmpH = new JPanel();
	this.pnlEmpCS = new JPanel();
	this.pnlEmpBPC = new JPanel();
	this.pnlEmpSCenter = new JPanel();
	this.pnlEmpHCenter = new JPanel();
	this.pnlEmpCSCenter = new JPanel();
	this.pnlEmpBPCCenter = new JPanel();
	this.pnlEmpSSouth = new JPanel();
        this.pnlEmpSouth = new JPanel();
	this.pnlEmpHSouth = new JPanel();
	this.pnlEmpCSSouth = new JPanel();
	this.pnlEmpBPCSouth = new JPanel();
	this.pnlEmpSLabelGrid = new JPanel();
	this.pnlEmpHLabelGrid = new JPanel();
	this.pnlEmpCSLabelGrid = new JPanel();
	this.pnlEmpBPCLabelGrid = new JPanel();
	this.pnlEmpSFieldGrid = new JPanel();
	this.pnlEmpHFieldGrid = new JPanel();
	this.pnlEmpCSFieldGrid = new JPanel();
	this.pnlEmpBPCFieldGrid = new JPanel();
	this.pnlEmpSearch = new JPanel();
	this.pnlEmpSearchNorth = new JPanel();
	this.pnlEmpSearchCenter = new JPanel();
        this.pnlEmpSearchSouth = new JPanel();
	
	//initialize salary JTextFields
	this.txtEmpSFirstName = new JTextField();
	this.txtEmpSLastName = new JTextField();
	this.txtEmpSAddress = new JTextField();
        this.txtEmpSCity = new JTextField();
        this.txtEmpSProvince = new JTextField();
	this.txtEmpSPhoneNum = new JTextField();
	this.txtEmpSDepartment = new JTextField();
	this.txtEmpSPosition = new JTextField();
	this.txtEmpSSocialSecurityNum = new JTextField();
	this.txtEmpSYearOfBirth = new JTextField();
	this.txtEmpSMonthOfBirth = new JTextField();
	this.txtEmpSDayOfBirth = new JTextField();
	this.txtEmpSSalaryType = new JTextField();
	this.txtEmpSSalaryAmount = new JTextField();
	this.txtEmpSWage = new JTextField();
	this.txtEmpSCommissionRates = new JTextField();
	this.txtEmpSBaseSalary = new JTextField();
	
	//create radio buttons and the radio button group for Salary employee
	this.rdoEmpSSexMale = new JRadioButton("Male");
	this.rdoEmpSSexFemale = new JRadioButton("Female");
	this.grpEmpSSex = new ButtonGroup();
	this.grpEmpSSex.add(rdoEmpSSexMale);
	this.grpEmpSSex.add(rdoEmpSSexFemale);
	
	//create panel for sex radio buttons
	this.pnlEmpSSex = new JPanel();
	this.pnlEmpSSex.add(rdoEmpSSexMale);
	this.pnlEmpSSex.add(rdoEmpSSexFemale);
	
	//initialize Hourly JTextFields
	this.txtEmpHFirstName = new JTextField();
	this.txtEmpHLastName = new JTextField();
	this.txtEmpHAddress = new JTextField();
        this.txtEmpHCity = new JTextField();
        this.txtEmpHProvince = new JTextField();
	this.txtEmpHPhoneNum = new JTextField();
	this.txtEmpHDepartment = new JTextField();
	this.txtEmpHPosition = new JTextField();
	this.txtEmpHSocialSecurityNum = new JTextField();
	this.txtEmpHYearOfBirth = new JTextField();
	this.txtEmpHMonthOfBirth = new JTextField();
	this.txtEmpHDayOfBirth = new JTextField();
	this.txtEmpHSalaryType = new JTextField();
	this.txtEmpHSalaryAmount = new JTextField();
	this.txtEmpHWage = new JTextField();
	this.txtEmpHCommissionRates = new JTextField();
	this.txtEmpHBaseSalary = new JTextField();
	
	//create radio buttons and the radio button group for Hourly employee
	this.rdoEmpHSexMale = new JRadioButton("Male");
	this.rdoEmpHSexFemale = new JRadioButton("Female");
	this.grpEmpHSex = new ButtonGroup();
	this.grpEmpHSex.add(rdoEmpHSexMale);
	this.grpEmpHSex.add(rdoEmpHSexFemale);
	
	//create panel for sex radio buttons
	this.pnlEmpHSex = new JPanel();
	this.pnlEmpHSex.add(rdoEmpHSexMale);
	this.pnlEmpHSex.add(rdoEmpHSexFemale);
	
	//initialize Commission-Sales JTextFields
	this.txtEmpCSFirstName = new JTextField();
	this.txtEmpCSLastName = new JTextField();
	this.txtEmpCSAddress = new JTextField();
        this.txtEmpCSCity = new JTextField();
        this.txtEmpCSProvince = new JTextField();
	this.txtEmpCSPhoneNum = new JTextField();
	this.txtEmpCSDepartment = new JTextField();
	this.txtEmpCSPosition = new JTextField();
	this.txtEmpCSSocialSecurityNum = new JTextField();
	this.txtEmpCSYearOfBirth = new JTextField();
	this.txtEmpCSMonthOfBirth = new JTextField();
	this.txtEmpCSDayOfBirth = new JTextField();
	this.txtEmpCSSalaryType = new JTextField();
	this.txtEmpCSSalaryAmount = new JTextField();
	this.txtEmpCSWage = new JTextField();
	this.txtEmpCSCommissionRates = new JTextField();
	this.txtEmpCSBaseSalary = new JTextField();
	
	//create radio buttons and the radio button group for Commission-Sales employee
	this.rdoEmpCSSexMale = new JRadioButton("Male");
	this.rdoEmpCSSexFemale = new JRadioButton("Female");
	this.grpEmpCSSex = new ButtonGroup();
	this.grpEmpCSSex.add(rdoEmpCSSexMale);
	this.grpEmpCSSex.add(rdoEmpCSSexFemale);
	
	//create panel for sex radio buttons
	this.pnlEmpCSSex = new JPanel();
	this.pnlEmpCSSex.add(rdoEmpCSSexMale);
	this.pnlEmpCSSex.add(rdoEmpCSSexFemale);
	
	//initialize Base Plus Commission JTextFields
	this.txtEmpBPCFirstName = new JTextField();
	this.txtEmpBPCLastName = new JTextField();
	this.txtEmpBPCAddress = new JTextField();
        this.txtEmpBPCCity = new JTextField();
        this.txtEmpBPCProvince = new JTextField();
	this.txtEmpBPCPhoneNum = new JTextField();
	this.txtEmpBPCDepartment = new JTextField();
	this.txtEmpBPCPosition = new JTextField();
	this.txtEmpBPCSocialSecurityNum = new JTextField();
	this.txtEmpBPCYearOfBirth = new JTextField();
	this.txtEmpBPCMonthOfBirth = new JTextField();
	this.txtEmpBPCDayOfBirth = new JTextField();
	this.txtEmpBPCSalaryType = new JTextField();
	this.txtEmpBPCSalaryAmount = new JTextField();
	this.txtEmpBPCWage = new JTextField();
	this.txtEmpBPCCommissionRates = new JTextField();
	this.txtEmpBPCBaseSalary = new JTextField();
	
	//create radio buttons and the radio button group for Base Plus Commission employee
	this.rdoEmpBPCSexMale = new JRadioButton("Male");
	this.rdoEmpBPCSexFemale = new JRadioButton("Female");
	this.grpEmpBPCSex = new ButtonGroup();
	this.grpEmpBPCSex.add(rdoEmpBPCSexMale);
	this.grpEmpBPCSex.add(rdoEmpBPCSexFemale);
	
	//create panel for sex radio buttons
	this.pnlEmpBPCSex = new JPanel();
	this.pnlEmpBPCSex.add(rdoEmpBPCSexMale);
	this.pnlEmpBPCSex.add(rdoEmpBPCSexFemale);
	
	//initialize search components
	this.lblEmpSearchLastName = new JLabel("Search by Employee's Last Name");
	this.txtEmpSearchLastName = new JTextField(15);
        this.lblEmpSearchDepartment = new JLabel("Search by Department");
	this.txtEmpSearchDepartment = new JTextField(15);
	this.txaEmpResults = new JTextArea(15, 30);
	this.spEmpResults = new JScrollPane(txaEmpResults);
	
	//initialize JButtons
	this.btnEmpSNew = new JButton("Create Salary Employee");
	this.btnEmpHNew = new JButton("Create Hourly Employee");
	this.btnEmpCSNew = new JButton("Create Commission-Sales Employee");
	this.btnEmpBPCNew = new JButton("Create Base Plus Commission Employee");
        this.btnEmployeeEdit = new JButton("Edit");
        this.btnEmployeeDelete = new JButton("Delete");
	//</editor-fold>
	
	//<editor-fold desc="Initialize Manufacturer Components">
	
	//initialize JPanels
	this.pnlMfact = new JTabbedPane();
	this.pnlMfactNew = new JPanel();
	this.pnlMfactNorth = new JPanel();
	this.pnlMfactCenter = new JPanel();
	this.pnlMfactSouth = new JPanel();
	this.pnlMfactLabelGrid = new JPanel();
	this.pnlMfactFieldGrid = new JPanel();
	this.pnlMfactSearch = new JPanel();
	this.pnlMfactSearchNorth = new JPanel();
	this.pnlMfactSearchCenter = new JPanel();
        this.pnlManufacturerSouth = new JPanel();
	
	//initialize JTextFields
	this.txtMfactAddress = new JTextField(15);
        this.txtMfactCity = new JTextField(15);
        this.txtMfactProvince = new JTextField(15);
	this.txtMfactName = new JTextField(15);
	this.txtMfactPhoneNum = new JTextField(15);
	
	//initialize JButton
	this.btnMfactNew = new JButton("Create Manufacturer");
        this.btnMfactNew.addActionListener(new CreateMfactButtonHandler());
	this.btnManufacturerEdit = new JButton("Edit");
        this.btnManufacturerDelete = new JButton("Delete");
        
	//initialize search components
	this.lblMfactSearch = new JLabel("Search by Manufacturer's Name");
	this.txtMfactSearch = new JTextField(15);
	this.txaMfactResults = new JTextArea(15, 30);
	this.spMfactResults = new JScrollPane(txaMfactResults);
	//</editor-fold>
	
	//<editor-fold desc="Initialize Product Components">
	
	//initialize JPanels
	this.pnlProduct = new JTabbedPane();
	this.pnlProductNew = new JPanel();
	this.pnlProductNorth = new JPanel();
	this.pnlProductCenter = new JPanel();
	this.pnlProductSouth = new JPanel();
	this.pnlProductLabelGrid = new JPanel();
	this.pnlProductFieldGrid = new JPanel();
	this.pnlProductSearch = new JPanel();
	this.pnlProductSearchNorth = new JPanel();
	this.pnlProductSearchCenter = new JPanel();
        this.pnlProductSouthED = new JPanel();
	
	//initialize JTextFields
	this.txtProductName = new JTextField(15);
	this.txtProductPrice = new JTextField(15);
	this.txtProductDiscount = new JTextField(15);
	
	//initialize JButton
	this.btnProductNew = new JButton("Create Product");
	this.btnProductEdit = new JButton("Edit");
	this.btnProductDelete = new JButton("Delete");
	
	//initialize JComboBox
	this.cboProductManufacturer = new JComboBox();
	
	//initialize Search components
	this.lblProductSearch = new JLabel("Search Product by Name");
	this.txtProductSearch = new JTextField(15);
        this.lblProductManuSearch = new JLabel("Search Product by Manufacturer's Name");
	this.txtProductManuSearch = new JTextField(15);
	this.txaProductResults = new JTextArea(15, 30);
	this.spProductResults = new JScrollPane(txaProductResults);
        
        //<editor-fold desc="Initialize Sales Components">
	
	//initialize JPanels
	this.pnlSales = new JTabbedPane();
	this.pnlSalesNew = new JPanel();
	this.pnlSalesNorth = new JPanel();
	this.pnlSalesCenter = new JPanel();
	this.pnlSalesSouth = new JPanel();
	this.pnlSalesLabelGrid = new JPanel();
	this.pnlSalesFieldGrid = new JPanel();
	this.pnlSalesSearch = new JPanel();
	this.pnlSalesSearchNorth = new JPanel();
	this.pnlSalesSearchCenter = new JPanel();
        this.pnlSalesSouthED = new JPanel();
	        
	//initialize JTextFields
	this.txtSalesProductName = new JTextField(15);
        this.txtSalesCustomer = new JTextField(15);
	this.txtSalesEmployee = new JTextField(15);
	this.txtSalesCommission = new JTextField(15);
        this.txtSalesDate = new JTextField(15);
	
	//initialize JButton
	this.btnSalesNew = new JButton("Create Sales");
        this.btnSalesEdit = new JButton("Edit");
        this.btnSalesDelete = new JButton("Delete");
		
	//initialize Search components
	this.lblSalesSearchLastName = new JLabel("Search Sales by Employee's Last Name");
	this.txtSalesSearchLastName = new JTextField(15);
        this.lblSalesSearchProductName = new JLabel("Search Sales by Product Name");
	this.txtSalesSearchProductName = new JTextField(15);
	this.txaSalesResults = new JTextArea(15, 30);
	this.spSalesResults = new JScrollPane(txaSalesResults);       
        //</editor-fold>
        
        //<editor-fold desc="Initialize Customer Components">
	
	//initialize JPanels
	this.pnlCustomer = new JTabbedPane();
	this.pnlCustomerNew = new JPanel();
	this.pnlCustomerNorth = new JPanel();
	this.pnlCustomerCenter = new JPanel();
	this.pnlCustomerSouth = new JPanel();
	this.pnlCustomerLabelGrid = new JPanel();
	this.pnlCustomerFieldGrid = new JPanel();
	this.pnlCustomerSearch = new JPanel();
	this.pnlCustomerSearchNorth = new JPanel();
	this.pnlCustomerSearchCenter = new JPanel();
        this.pnlCustomerSouthED = new JPanel();
	        
	//initialize JTextFields
	this.txtCustomerFirstName = new JTextField(15);
	this.txtCustomerLastName = new JTextField(15);
        this.txtCustomerSex = new JTextField(15);
        this.txtCustomerProvince = new JTextField(15);
        this.txtCustomerCity = new JTextField(15);
        this.txtCustomerAddress = new JTextField(15);
        
	this.txtCustomerPhone = new JTextField(15);
        this.txtCustomerYearOfBirth = new JTextField(15);
        this.txtCustomerMonthOfBirth = new JTextField(15);
        this.txtCustomerDayOfBirth = new JTextField(15);
	
	//initialize JButton
	this.btnCustomerNew = new JButton("Create Customer");
        this.btnCustomerEdit = new JButton("Edit");
        this.btnCustomerDelete = new JButton("Delete");
        this.btnCustomerSearch = new JButton("Search");

        // set handlers
        this.btnCustomerEdit.addActionListener(new EditCustomerButtonHandler());
        this.btnCustomerDelete.addActionListener(new DeleteCustomerButtonHandler());
        this.btnCustomerSearch.addActionListener(new SearchCustomerButtonHandler());
		
	//initialize Search components
	this.lblCustomerSearchLastName = new JLabel("Search by Customer's Last Name");
	this.txtCustomerSearchLastName = new JTextField(15);
        this.lblCustomerSearchPhoneNumber = new JLabel("Search Customer's by Phone Number");
	this.txtCustomerSearchPhoneNumber = new JTextField(15);
//	this.txaCustomerResults = new JTextArea(15, 30);
//	this.spCustomerResults = new JScrollPane(txaCustomerResults);
        this.customerJTable = new CustomerJTable();
        
        //create radio buttons and the radio button group for Customer
	this.rdoCustomerSexMale = new JRadioButton("Male");
	this.rdoCustomerSexFemale = new JRadioButton("Female");
	this.grpCustomerSex = new ButtonGroup();
	this.grpCustomerSex.add(rdoCustomerSexMale);
	this.grpCustomerSex.add(rdoCustomerSexFemale);
	
	//create panel for sex radio buttons
	this.pnlCustomerSex = new JPanel();
	this.pnlCustomerSex.add(rdoCustomerSexMale);
	this.pnlCustomerSex.add(rdoCustomerSexFemale);       
        //</editor-fold>
	
	//initialize exit components
	this.pnlExit = new JPanel();
	this.btnExit = new JButton("Exit");
	//add actionlistener to exit button
	this.btnExit.addActionListener(new ExitButtonHandler());
	
	setupGUI();
    }
    
    private void setupGUI() {
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(685,545);
        this.setResizable(false);
	this.setLocationRelativeTo(null);
	//create the view
	this.setContentPane(this.createContentPane());
	this.setVisible(true);
    }

    //creates the content pane of the interface
    private Container createContentPane() {
	//design and populate the greeting panel
	pnlGreeting.add(lblGreeting);
	
	//design and populate the employee tabbed pane
	createEmployeeTabbedPane();
	
	//design and populate the manufacturer tabbed pane
	createManufacturerTabbedPane();
	
	//design and populate the product tabbed pane
	createProductTabbedPane();
	
        // Design and populate the sales tabbed pane
        createSalesTabbedPane();
        
        // Design and populate the customer tabbed pane
        createCustomerTabbedPane();
        
	//design and populate the exit panel
	pnlExit.add(btnExit);
	
	//add the panels to the tabs
	pnlTabbedPane.addTab("Employees", pnlEmp);
	pnlTabbedPane.addTab("Manufacturers", pnlMfact);
	pnlTabbedPane.addTab("Products", pnlProduct);
        pnlTabbedPane.addTab("Sales", pnlSales);
        pnlTabbedPane.addTab("Customer", pnlCustomer);
	
        // add handler
        pnlTabbedPane.addChangeListener(new MainTabChangeListener());
	
	//populate the full screen of the application
	pnlFullScreen.setLayout(new BorderLayout());
	pnlFullScreen.add(pnlTabbedPane, BorderLayout.CENTER);
	pnlFullScreen.add(pnlGreeting, BorderLayout.NORTH);
	pnlFullScreen.add(pnlExit, BorderLayout.SOUTH);
	
	return pnlFullScreen;
    }

    private static final int TAB_IDX_CUSTOMER = 4;
    private static final int TAB_IDX_CUSTOMER_SEARCH = 0;
    /**
     * That is called when customer tab is clicked.
     */
    private class MainTabChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            // update manufacturer id combo box
System.out.println("Tab is changed");
            int selectedTabIndex = pnlTabbedPane.getSelectedIndex();
System.out.println("Selected tab:" + selectedTabIndex);
            switch (selectedTabIndex) {
                case TAB_IDX_CUSTOMER:
                    if (pnlCustomer.getSelectedIndex() == TAB_IDX_CUSTOMER_SEARCH) {
                        // search
                        customerJTable.buildTableInfoPanel(null);
                    }
                    break;
                default:
                    break;
            }
        }
        
    }
    
    private void createEmployeeTabbedPane() {
	//create the tabs
	createEmployeeSearchTab();
	createSalaryEmployeeTab();
	createHourlyEmployeeTab();
	createCommissionSalesEmployeeTab();
	createBasePlusCommissionEmployeeTab();
	
	//add the tabs
	pnlEmp.addTab("Search", pnlEmpSearch);
	pnlEmp.addTab("New Salary", pnlEmpS);
	pnlEmp.addTab("New Hourly", pnlEmpH);
	pnlEmp.addTab("New Commission-Sales", pnlEmpCS);
	pnlEmp.addTab("New Base Plus Commission", pnlEmpBPC);
    }
    
    private void createManufacturerTabbedPane() {
	createManufacturerSearchTab();
	createManufacturerTab();
	
	//add the tabs
	pnlMfact.addTab("Search", pnlMfactSearch);
	pnlMfact.addTab("New Manufacturer", pnlMfactNew);
    }
    
    private void createProductTabbedPane() {
	createProductSearchTab();
	createProductTab();
	
	//add the tabs
	pnlProduct.addTab("Search", pnlProductSearch);
	pnlProduct.addTab("New Product", pnlProductNew);
    }
    
    private void createSalesTabbedPane() {
	createSalesSearchTab();
	createSalesTab();
	
	//add the tabs
	pnlSales.addTab("Search", pnlSalesSearch);
	pnlSales.addTab("New Sales", pnlSalesNew);
    }
    
    private void createCustomerTabbedPane() {
	createCustomerSearchTab();
	createCustomersTab();
	
	//add the tabs
	pnlCustomer.addTab("Search", pnlCustomerSearch);
	pnlCustomer.addTab("New Customer", pnlCustomerNew);

        // set handler
        pnlCustomer.addChangeListener(new CustomerTabChangeListener());
    }
    
    /**
     * That is called when customer tab is clicked.
     */
    private class CustomerTabChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            // update manufacturer id combo box
System.out.println("Tab is changed");
System.out.println("Selected tab:" + pnlCustomer.getSelectedIndex());
            if (pnlCustomer.getSelectedIndex() == 0) {
                // search
                customerJTable.buildTableInfoPanel(null);
            }
        }
        
    }

    private void createEmployeeSearchTab() {
	//set as a border layout
	pnlEmpSearch.setLayout(new BorderLayout());
	
	//design the north panel
	pnlEmpSearchNorth.setLayout(new FlowLayout());
	pnlEmpSearchNorth.add(lblEmpSearchLastName);
	pnlEmpSearchNorth.add(txtEmpSearchLastName);
      //  pnlEmpSearchCenter.add(lblEmpSearchDepartment);
     //	pnlEmpSearchCenter.add(txtEmpSearchDepartment);
	
	//design the center panel
	pnlEmpSearchCenter.add(spEmpResults);
        
	pnlEmpSearch.add(pnlEmpSearchNorth, BorderLayout.NORTH);
	pnlEmpSearch.add(pnlEmpSearchCenter, BorderLayout.CENTER);  
        
        pnlEmpSouth.add(btnEmployeeEdit);
        pnlEmpSouth.add(btnEmployeeDelete);
	pnlEmpSearch.add(pnlEmpSouth, BorderLayout.SOUTH);
    }
    
    private void createSalaryEmployeeTab() {
	//set as a border layout
	pnlEmpS.setLayout(new BorderLayout());
	
	//create the label sub-panel of the center panel
	pnlEmpSLabelGrid.setLayout(new GridLayout(15,1));
	pnlEmpSLabelGrid.add(new JLabel("First Name"));
	pnlEmpSLabelGrid.add(new JLabel("Last Name"));
	pnlEmpSLabelGrid.add(new JLabel("Sex"));
	pnlEmpSLabelGrid.add(new JLabel("Address"));
        pnlEmpSLabelGrid.add(new JLabel("City"));
        pnlEmpSLabelGrid.add(new JLabel("Province"));
	pnlEmpSLabelGrid.add(new JLabel("Phone Number"));
	pnlEmpSLabelGrid.add(new JLabel("Department"));
	pnlEmpSLabelGrid.add(new JLabel("Position"));
	pnlEmpSLabelGrid.add(new JLabel("Social Security #"));
	pnlEmpSLabelGrid.add(new JLabel("Year Of Birth"));
	pnlEmpSLabelGrid.add(new JLabel("Month Of Birth"));
	pnlEmpSLabelGrid.add(new JLabel("Day Of Birth"));
	pnlEmpSLabelGrid.add(new JLabel("Salary Type"));
	pnlEmpSLabelGrid.add(new JLabel("Salary Amount"));
	
	//create the field sub-panel of the center panel
	pnlEmpSFieldGrid.setLayout(new GridLayout(15,1));
	pnlEmpSFieldGrid.add(txtEmpSFirstName);
	pnlEmpSFieldGrid.add(txtEmpSLastName);
	pnlEmpSFieldGrid.add(pnlEmpSSex);
	pnlEmpSFieldGrid.add(txtEmpSAddress);
        pnlEmpSFieldGrid.add(txtEmpSCity);
        pnlEmpSFieldGrid.add(txtEmpSProvince);
	pnlEmpSFieldGrid.add(txtEmpSPhoneNum);
	pnlEmpSFieldGrid.add(txtEmpSDepartment);
	pnlEmpSFieldGrid.add(txtEmpSPosition);
	pnlEmpSFieldGrid.add(txtEmpSSocialSecurityNum);
	pnlEmpSFieldGrid.add(txtEmpSYearOfBirth);
	pnlEmpSFieldGrid.add(txtEmpSMonthOfBirth);
	pnlEmpSFieldGrid.add(txtEmpSDayOfBirth);
	pnlEmpSFieldGrid.add(txtEmpSSalaryType);
	pnlEmpSFieldGrid.add(txtEmpSSalaryAmount);
	
	//create and add the center panel
	pnlEmpSCenter.setLayout(new BorderLayout());
	pnlEmpSCenter.add(pnlEmpSLabelGrid, BorderLayout.WEST);
	pnlEmpSCenter.add(pnlEmpSFieldGrid, BorderLayout.EAST);
	pnlEmpS.add(pnlEmpSCenter, BorderLayout.CENTER);
	
	//create and add the south panel
	pnlEmpSSouth.add(btnEmpSNew);
	pnlEmpS.add(pnlEmpSSouth, BorderLayout.SOUTH);
    }
    
    private void createHourlyEmployeeTab() {
	//set as a border layout
	pnlEmpH.setLayout(new BorderLayout());
	
	//create the label sub-panel of the center panel
	pnlEmpHLabelGrid.setLayout(new GridLayout(15,1));
	pnlEmpHLabelGrid.add(new JLabel("First Name"));
	pnlEmpHLabelGrid.add(new JLabel("Last Name"));
	pnlEmpHLabelGrid.add(new JLabel("Sex"));
	pnlEmpHLabelGrid.add(new JLabel("Address"));
        pnlEmpHLabelGrid.add(new JLabel("City"));
        pnlEmpHLabelGrid.add(new JLabel("Province"));
	pnlEmpHLabelGrid.add(new JLabel("Phone Number"));
	pnlEmpHLabelGrid.add(new JLabel("Department"));
	pnlEmpHLabelGrid.add(new JLabel("Position"));
	pnlEmpHLabelGrid.add(new JLabel("Social Security #"));
	pnlEmpHLabelGrid.add(new JLabel("Year Of Birth"));
	pnlEmpHLabelGrid.add(new JLabel("Month Of Birth"));
	pnlEmpHLabelGrid.add(new JLabel("Day Of Birth"));
	pnlEmpHLabelGrid.add(new JLabel("Wage"));
	
	//create the field sub-panel of the center panel
	pnlEmpHFieldGrid.setLayout(new GridLayout(15,1));
	pnlEmpHFieldGrid.add(txtEmpHFirstName);
	pnlEmpHFieldGrid.add(txtEmpHLastName);
	pnlEmpHFieldGrid.add(pnlEmpHSex);
	pnlEmpHFieldGrid.add(txtEmpHAddress);
        pnlEmpHFieldGrid.add(txtEmpHCity);
        pnlEmpHFieldGrid.add(txtEmpHProvince);
	pnlEmpHFieldGrid.add(txtEmpHPhoneNum);
	pnlEmpHFieldGrid.add(txtEmpHDepartment);
	pnlEmpHFieldGrid.add(txtEmpHPosition);
	pnlEmpHFieldGrid.add(txtEmpHSocialSecurityNum);
	pnlEmpHFieldGrid.add(txtEmpHYearOfBirth);
	pnlEmpHFieldGrid.add(txtEmpHMonthOfBirth);
	pnlEmpHFieldGrid.add(txtEmpHDayOfBirth);
	pnlEmpHFieldGrid.add(txtEmpHWage);
	
	//create and add the center panel
	pnlEmpHCenter.setLayout(new BorderLayout());
	pnlEmpHCenter.add(pnlEmpHLabelGrid, BorderLayout.WEST);
	pnlEmpHCenter.add(pnlEmpHFieldGrid, BorderLayout.EAST);
	pnlEmpH.add(pnlEmpHCenter, BorderLayout.CENTER);
	
	//create and add the south panel
	pnlEmpHSouth.add(btnEmpHNew);
	pnlEmpH.add(pnlEmpHSouth, BorderLayout.SOUTH);
    }
    
    private void createCommissionSalesEmployeeTab() {
	//set as a border layout
	pnlEmpCS.setLayout(new BorderLayout());
	
	//create the label sub-panel of the center panel
	pnlEmpCSLabelGrid.setLayout(new GridLayout(15,1));
	pnlEmpCSLabelGrid.add(new JLabel("First Name"));
	pnlEmpCSLabelGrid.add(new JLabel("Last Name"));
	pnlEmpCSLabelGrid.add(new JLabel("Sex"));
	pnlEmpCSLabelGrid.add(new JLabel("Address"));
        pnlEmpCSLabelGrid.add(new JLabel("City"));
        pnlEmpCSLabelGrid.add(new JLabel("Province"));
	pnlEmpCSLabelGrid.add(new JLabel("Phone Number"));
	pnlEmpCSLabelGrid.add(new JLabel("Department"));
	pnlEmpCSLabelGrid.add(new JLabel("Position"));
	pnlEmpCSLabelGrid.add(new JLabel("Social Security #"));
	pnlEmpCSLabelGrid.add(new JLabel("Year Of Birth"));
	pnlEmpCSLabelGrid.add(new JLabel("Month Of Birth"));
	pnlEmpCSLabelGrid.add(new JLabel("Day Of Birth"));
	pnlEmpCSLabelGrid.add(new JLabel("Commission Rates"));
	
	//create the field sub-panel of the center panel
	pnlEmpCSFieldGrid.setLayout(new GridLayout(15,1));
	pnlEmpCSFieldGrid.add(txtEmpCSFirstName);
	pnlEmpCSFieldGrid.add(txtEmpCSLastName);
	pnlEmpCSFieldGrid.add(pnlEmpCSSex);
	pnlEmpCSFieldGrid.add(txtEmpCSAddress);
        pnlEmpCSFieldGrid.add(txtEmpCSCity);
        pnlEmpCSFieldGrid.add(txtEmpCSProvince);
	pnlEmpCSFieldGrid.add(txtEmpCSPhoneNum);
	pnlEmpCSFieldGrid.add(txtEmpCSDepartment);
	pnlEmpCSFieldGrid.add(txtEmpCSPosition);
	pnlEmpCSFieldGrid.add(txtEmpCSSocialSecurityNum);
	pnlEmpCSFieldGrid.add(txtEmpCSYearOfBirth);
	pnlEmpCSFieldGrid.add(txtEmpCSMonthOfBirth);
	pnlEmpCSFieldGrid.add(txtEmpCSDayOfBirth);
	pnlEmpCSFieldGrid.add(txtEmpCSCommissionRates);
	
	//create and add the center panel
	pnlEmpCSCenter.setLayout(new BorderLayout());
	pnlEmpCSCenter.add(pnlEmpCSLabelGrid, BorderLayout.WEST);
	pnlEmpCSCenter.add(pnlEmpCSFieldGrid, BorderLayout.EAST);
	pnlEmpCS.add(pnlEmpCSCenter, BorderLayout.CENTER);
	
	//create and add the south panel
	pnlEmpCSSouth.add(btnEmpCSNew);
	pnlEmpCS.add(pnlEmpCSSouth, BorderLayout.SOUTH);
    }
    
    private void createBasePlusCommissionEmployeeTab() {
	//set as a border layout
	pnlEmpBPC.setLayout(new BorderLayout());
	
	//create the label sub-panel of the center panel
	pnlEmpBPCLabelGrid.setLayout(new GridLayout(15,1));
	pnlEmpBPCLabelGrid.add(new JLabel("First Name"));
	pnlEmpBPCLabelGrid.add(new JLabel("Last Name"));
	pnlEmpBPCLabelGrid.add(new JLabel("Sex"));
	pnlEmpBPCLabelGrid.add(new JLabel("Address"));
        pnlEmpBPCLabelGrid.add(new  JLabel("City"));
        pnlEmpBPCLabelGrid.add(new JLabel("Province"));
	pnlEmpBPCLabelGrid.add(new JLabel("Phone Number"));
	pnlEmpBPCLabelGrid.add(new JLabel("Department"));
	pnlEmpBPCLabelGrid.add(new JLabel("Position"));
	pnlEmpBPCLabelGrid.add(new JLabel("Social Security #"));
	pnlEmpBPCLabelGrid.add(new JLabel("Year Of Birth"));
	pnlEmpBPCLabelGrid.add(new JLabel("Month Of Birth"));
	pnlEmpBPCLabelGrid.add(new JLabel("Day Of Birth"));
	pnlEmpBPCLabelGrid.add(new JLabel("Commission Rates"));
	pnlEmpBPCLabelGrid.add(new JLabel("Base Salary"));
	
	//create the field sub-panel of the center panel
	pnlEmpBPCFieldGrid.setLayout(new GridLayout(15,1));
	pnlEmpBPCFieldGrid.add(txtEmpBPCFirstName);
	pnlEmpBPCFieldGrid.add(txtEmpBPCLastName);
	pnlEmpBPCFieldGrid.add(pnlEmpBPCSex);
	pnlEmpBPCFieldGrid.add(txtEmpBPCAddress);
        pnlEmpBPCFieldGrid.add(txtEmpBPCCity);
        pnlEmpBPCFieldGrid.add(txtEmpBPCProvince);
        pnlEmpBPCFieldGrid.add(txtEmpBPCPhoneNum);
	pnlEmpBPCFieldGrid.add(txtEmpBPCDepartment);
	pnlEmpBPCFieldGrid.add(txtEmpBPCPosition);
	pnlEmpBPCFieldGrid.add(txtEmpBPCSocialSecurityNum);
	pnlEmpBPCFieldGrid.add(txtEmpBPCYearOfBirth);
	pnlEmpBPCFieldGrid.add(txtEmpBPCMonthOfBirth);
	pnlEmpBPCFieldGrid.add(txtEmpBPCDayOfBirth);
	pnlEmpBPCFieldGrid.add(txtEmpBPCCommissionRates);
	pnlEmpBPCFieldGrid.add(txtEmpBPCBaseSalary);
	
	//create and add the center panel
	pnlEmpBPCCenter.setLayout(new BorderLayout());
	pnlEmpBPCCenter.add(pnlEmpBPCLabelGrid, BorderLayout.WEST);
	pnlEmpBPCCenter.add(pnlEmpBPCFieldGrid, BorderLayout.EAST);
	pnlEmpBPC.add(pnlEmpBPCCenter, BorderLayout.CENTER);
	
	//create and add the south panel
	pnlEmpBPCSouth.add(btnEmpBPCNew);
	pnlEmpBPC.add(pnlEmpBPCSouth, BorderLayout.SOUTH);
    }
    
    private void createManufacturerSearchTab() {
	//set as a border layout
	pnlMfactSearch.setLayout(new BorderLayout());
	
	//design the north panel
	pnlMfactSearchNorth.setLayout(new FlowLayout());
	pnlMfactSearchNorth.add(lblMfactSearch);
	pnlMfactSearchNorth.add(txtMfactSearch);
	
	//design the center panel
	pnlMfactSearchCenter.add(spMfactResults);
	
	pnlMfactSearch.add(pnlMfactSearchNorth, BorderLayout.NORTH);
	pnlMfactSearch.add(pnlMfactSearchCenter, BorderLayout.CENTER);
        
        pnlManufacturerSouth.add(btnManufacturerEdit);
        pnlManufacturerSouth.add(btnManufacturerDelete);
	pnlMfactSearch.add(pnlManufacturerSouth, BorderLayout.SOUTH);
        
    }
    
    private void createManufacturerTab() {
	//set as a border layout
	pnlMfactNew.setLayout(new BorderLayout());
	
	//create the label sub-panel of the center panel
	pnlMfactLabelGrid.setLayout(new GridLayout(13,1));
	pnlMfactLabelGrid.add(new JLabel("Name"));
	pnlMfactLabelGrid.add(new JLabel("Address"));
        pnlMfactLabelGrid.add(new JLabel("City"));
        pnlMfactLabelGrid.add(new JLabel("Province"));
	pnlMfactLabelGrid.add(new JLabel("Phone Number"));
	
	//create the field sub-panel of the center panel
	pnlMfactFieldGrid.setLayout(new GridLayout(13,1));
	pnlMfactFieldGrid.add(txtMfactName);
	pnlMfactFieldGrid.add(txtMfactAddress);
        pnlMfactFieldGrid.add(txtMfactCity);
        pnlMfactFieldGrid.add(txtMfactProvince);
	pnlMfactFieldGrid.add(txtMfactPhoneNum);
	
	//create and add the center panel
	pnlMfactCenter.setLayout(new BorderLayout());
	pnlMfactCenter.add(pnlMfactLabelGrid, BorderLayout.WEST);
	pnlMfactCenter.add(pnlMfactFieldGrid, BorderLayout.EAST);
	pnlMfactNew.add(pnlMfactCenter, BorderLayout.CENTER);
	
	//create and add the south panel
        pnlMfactSouth.add(btnMfactNew);
        
	pnlMfactNew.add(pnlMfactSouth, BorderLayout.SOUTH);
    }
    
    private void createProductSearchTab() {
	//set as a border layout
	pnlProductSearch.setLayout(new BorderLayout());
	
	//design the north panel
	pnlProductSearchNorth.setLayout(new FlowLayout());
	pnlProductSearchNorth.add(lblProductSearch);
	pnlProductSearchNorth.add(txtProductSearch);
        pnlProductSearchCenter.add(lblProductManuSearch);
	pnlProductSearchCenter.add(txtProductManuSearch);
	
	//design the center panel
	pnlProductSearchCenter.add(spProductResults);
	
	pnlProductSearch.add(pnlProductSearchNorth, BorderLayout.NORTH);
	pnlProductSearch.add(pnlProductSearchCenter, BorderLayout.CENTER);
        
        pnlProductSouthED.add(btnProductEdit);
        pnlProductSouthED.add(btnProductDelete);
	pnlProductSearch.add(pnlProductSouthED, BorderLayout.SOUTH);
    }
    
    private void createProductTab() {
	//set as a border layout
	pnlProductNew.setLayout(new BorderLayout());
	
	//create the label sub-panel of the center panel
	pnlProductLabelGrid.setLayout(new GridLayout(13,1));
	pnlProductLabelGrid.add(new JLabel("Name"));
	pnlProductLabelGrid.add(new JLabel("Price"));
	pnlProductLabelGrid.add(new JLabel("Discount"));
	pnlProductLabelGrid.add(new JLabel("Manufacturer"));
	
	//create the field sub-panel of the center panel
	pnlProductFieldGrid.setLayout(new GridLayout(13,1));
	pnlProductFieldGrid.add(txtProductName);
	pnlProductFieldGrid.add(txtProductPrice);
	pnlProductFieldGrid.add(txtProductDiscount);
	pnlProductFieldGrid.add(cboProductManufacturer);
        
        //add cbo Items
       ServiceClass.productList(cboProductManufacturer);   
	
	//create and add the center panel
	pnlProductCenter.setLayout(new BorderLayout());
	pnlProductCenter.add(pnlProductLabelGrid, BorderLayout.WEST);
	pnlProductCenter.add(pnlProductFieldGrid, BorderLayout.EAST);
	pnlProductNew.add(pnlProductCenter, BorderLayout.CENTER);
	
	//create and add the south panel
	pnlProductSouth.add(btnProductNew);
        btnProductNew.addActionListener(new CreateProductButtonHandler());
        pnlProductNew.add(pnlProductSouth, BorderLayout.SOUTH);
    }
    
    private void createSalesSearchTab() {
	//set as a border layout
	pnlSalesSearch.setLayout(new BorderLayout());
	
	//design the north panel
	pnlSalesSearchNorth.setLayout(new FlowLayout());
	pnlSalesSearchNorth.add(lblSalesSearchLastName);
	pnlSalesSearchNorth.add(txtSalesSearchLastName);
        pnlSalesSearchCenter.add(lblSalesSearchProductName);
	pnlSalesSearchCenter.add(txtSalesSearchProductName);
        	
	//design the center panel
	pnlSalesSearchCenter.add(spSalesResults);
	
	pnlSalesSearch.add(pnlSalesSearchNorth, BorderLayout.NORTH);
	pnlSalesSearch.add(pnlSalesSearchCenter, BorderLayout.CENTER);
        
        pnlSalesSouthED.add(btnSalesEdit);
        pnlSalesSouthED.add(btnSalesDelete);
	pnlSalesSearch.add(pnlSalesSouthED, BorderLayout.SOUTH);
    }
    
    private void createSalesTab() {
	//set as a border layout
	pnlSalesNew.setLayout(new BorderLayout());
	
	//create the label sub-panel of the center panel
	pnlSalesLabelGrid.setLayout(new GridLayout(13,1));
	pnlSalesLabelGrid.add(new JLabel("Product Name"));
        pnlSalesLabelGrid.add(new JLabel("Customer"));
	pnlSalesLabelGrid.add(new JLabel("Employee"));
	pnlSalesLabelGrid.add(new JLabel("Commission On Sale"));
        pnlSalesLabelGrid.add(new JLabel("Date Of Sale"));
	
	//create the field sub-panel of the center panel
	pnlSalesFieldGrid.setLayout(new GridLayout(13,1));
	pnlSalesFieldGrid.add(txtSalesProductName);
        pnlSalesFieldGrid.add(txtSalesCustomer);
	pnlSalesFieldGrid.add(txtSalesEmployee);
	pnlSalesFieldGrid.add(txtSalesCommission);
        pnlSalesFieldGrid.add(txtSalesDate);
	
	//create and add the center panel
	pnlSalesCenter.setLayout(new BorderLayout());
	pnlSalesCenter.add(pnlSalesLabelGrid, BorderLayout.WEST);
	pnlSalesCenter.add(pnlSalesFieldGrid, BorderLayout.EAST);
	pnlSalesNew.add(pnlSalesCenter, BorderLayout.CENTER);
	
	//create and add the south panel
	pnlSalesSouth.add(btnSalesNew);
        btnSalesNew.addActionListener(new CreateSalesButtonHandler());
	pnlSalesNew.add(pnlSalesSouth, BorderLayout.SOUTH);
    }
    
    private void createCustomerSearchTab() {
	//set as a border layout
	pnlCustomerSearch.setLayout(new BorderLayout());
	
	//design the north panel
	pnlCustomerSearchNorth.setLayout(new FlowLayout());
	pnlCustomerSearchNorth.add(lblCustomerSearchLastName);
	pnlCustomerSearchNorth.add(txtCustomerSearchLastName);
        pnlCustomerSearchNorth.add(btnCustomerSearch);
        pnlCustomerSearchCenter.add(lblCustomerSearchPhoneNumber);
	pnlCustomerSearchCenter.add(txtCustomerSearchPhoneNumber);
        	
	//design the center panel
//	pnlCustomerSearchCenter.add(spCustomerResults);
	pnlCustomerSearchCenter.add(this.customerJTable);
	
	pnlCustomerSearch.add(pnlCustomerSearchNorth, BorderLayout.NORTH);
	pnlCustomerSearch.add(pnlCustomerSearchCenter, BorderLayout.CENTER);
        
        pnlCustomerSouthED.add(btnCustomerEdit);
        pnlCustomerSouthED.add(btnCustomerDelete);
	pnlCustomerSearch.add(pnlCustomerSouthED, BorderLayout.SOUTH);
    }
            
    private void createCustomersTab() {
	//set as a border layout
	pnlCustomerNew.setLayout(new BorderLayout());
	
	//create the label sub-panel of the center panel
	pnlCustomerLabelGrid.setLayout(new GridLayout(13,1));
	pnlCustomerLabelGrid.add(new JLabel("Customer's First Name"));
	pnlCustomerLabelGrid.add(new JLabel("Customer's Last Name"));
        pnlCustomerLabelGrid.add(new JLabel("Sex"));
        pnlCustomerLabelGrid.add(new JLabel("Province"));
        pnlCustomerLabelGrid.add(new JLabel("City"));
        pnlCustomerLabelGrid.add(new JLabel("Address"));
	pnlCustomerLabelGrid.add(new JLabel("Phone Number"));
        pnlCustomerLabelGrid.add(new JLabel("Year Of Birth"));
        pnlCustomerLabelGrid.add(new JLabel("Month Of Birth"));
        pnlCustomerLabelGrid.add(new JLabel("Day Of Birth"));
	
	//create the field sub-panel of the center panel
	pnlCustomerFieldGrid.setLayout(new GridLayout(13,1));
	pnlCustomerFieldGrid.add(txtCustomerFirstName);
	pnlCustomerFieldGrid.add(txtCustomerLastName);
        pnlCustomerFieldGrid.add(pnlCustomerSex);
        pnlCustomerFieldGrid.add(txtCustomerProvince);
        pnlCustomerFieldGrid.add(txtCustomerCity);
        pnlCustomerFieldGrid.add(txtCustomerAddress);
	pnlCustomerFieldGrid.add(txtCustomerPhone);
        pnlCustomerFieldGrid.add(txtCustomerYearOfBirth);
        pnlCustomerFieldGrid.add(txtCustomerMonthOfBirth);
        pnlCustomerFieldGrid.add(txtCustomerDayOfBirth);
	
	//create and add the center panel
	pnlCustomerCenter.setLayout(new BorderLayout());
	pnlCustomerCenter.add(pnlCustomerLabelGrid, BorderLayout.WEST);
	pnlCustomerCenter.add(pnlCustomerFieldGrid, BorderLayout.EAST);
	pnlCustomerNew.add(pnlCustomerCenter, BorderLayout.CENTER);
	
	//create and add the south panel
	pnlCustomerSouth.add(btnCustomerNew);
	pnlCustomerNew.add(pnlCustomerSouth, BorderLayout.SOUTH);
    }
    
    
    //** ACTION LISTENERS **\\
    
    //button handler for the EXIT button
    private class ExitButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    if(Utils.showConfirmDialog("exit"))
            {
		System.exit(0);
            }
	}
    }
    
    private class CreateMfactButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    if(Utils.showConfirmDialog("create a new manufacturer")) {
		String fieldName = "";
		
		try{
		    //Validate Inputs
		    fieldName = "Name";
		    Validation.isValidName(txtMfactName.getText(), true);
		    fieldName = "Address";
		    Validation.isValid(txtMfactAddress.getText());
		    fieldName = "Phone number";
		    Validation.isValidPhoneNum(txtMfactPhoneNum.getText());
		    
		    //submit to the database
		    ServiceClass.insertMfact(txtMfactName.getText(), txtMfactAddress.getText(),
			    txtMfactCity.getText(), txtMfactProvince.getText(), txtMfactPhoneNum.getText());
		} catch(IllegalArgumentException error){
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		}
	    }           
	}
    }//end mfactButton
    
    private class CreateProductButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            if(Utils.showConfirmDialog("create a new product")) {
                String fieldName = "";
		
		try{
		    //validate inputs
		    fieldName = "Name";
		    Validation.isValidName(txtProductName.getText(), true);
		    fieldName = "Price";
		    Validation.isValid(txtProductPrice.getText());
		    fieldName = "Discount";
		    Validation.isValid(txtProductDiscount.getText());
		    
		    //Submit to Database
		    ServiceClass.insertProduct(txtProductName.getText(), parseDouble(txtProductPrice.getText()),
			    parseDouble(txtProductDiscount.getText()), cboProductManufacturer.getSelectedIndex());
		} catch(NumberFormatException exNfe) {
		    Utils.logError(exNfe);
		} catch(IllegalArgumentException exIae){
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		}
            }
	}
    }//end ProductButton  
    
    private class CreateSalesButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            if(Utils.showConfirmDialog("create a new sale")) {
	    //Validate Inputs
                try{
		    //TODO I think these need to be drop-down boxes to select available customers
		    Validation.isValid(txtSalesProductName.getText());
		    Validation.isValid(txtSalesCustomer.getText());
		    Validation.isValid(txtSalesEmployee.getText());
		    Validation.isValid(txtSalesCommission.getText());
		    Validation.isValid(txtSalesDate.getText());

		    //Submit to Database
		    
                }catch(IllegalArgumentException  error){
                    JOptionPane.showMessageDialog(null, "All fields must be provided");
                }
            }
	}
    }//end SalesButton    
    
    
    private class CreateCustomerButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            if(Utils.showConfirmDialog("create a new customer")) {
		String fieldName = "";
		
                try{
		    //Validate Inputs
		    fieldName = "First name";
		    Validation.isValidName(txtCustomerFirstName.getText(), false);
		    fieldName = "Last name";
		    Validation.isValidName(txtCustomerLastName.getText(), false);
		    //fieldName = "Sex";
		    //Validation.isValid(pnlCustomerSex);
		    fieldName = "Address";
		    Validation.isValid(txtCustomerAddress.getText());
		    fieldName = "City";
		    Validation.isValid(txtCustomerCity.getText());
		    fieldName = "Phone number";
		    Validation.isValidPhoneNum(txtCustomerPhone.getText());
		    fieldName = "Year of birth";
		    Validation.isValid(txtCustomerYearOfBirth.getText());
		    fieldName = "Month of birth";
		    Validation.isValid(txtCustomerMonthOfBirth.getText());
		    fieldName = "Day of birth";
		    Validation.isValid(txtCustomerDayOfBirth.getText());
		    
		    //Submit to Database
		    
		}catch(IllegalArgumentException exIae){
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		}
            }
	}
    }//end mfactButton    

    private class EditCustomerButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            if(Utils.showConfirmDialog("edit this customer")) {
		String fieldName = "";
		
                try{
		    //Validate Inputs
		    fieldName = "First name";
                    Validation.isValidName(customerJTable.getFname(), false);
		    fieldName = "Last name";
                    Validation.isValidName(customerJTable.getLname(), false);
		    //fieldName = "Gender";
                    //Validation.isValid(customerJTable.getGender());  // TODO validation for gender
		    fieldName = "Address";
                    Validation.isValid(customerJTable.getAddress());
		    fieldName = "City";
                    Validation.isValid(customerJTable.getCity());
                    fieldName = "Province";
		    Validation.isValid(customerJTable.getProvince());
                    fieldName = "Phone number";
                    Validation.isValidPhoneNum(customerJTable.getPhonenum());
                    fieldName = "Year of birth";
                    Validation.isValid(customerJTable.getYearOfBirthdate());
                    fieldName = "Month of birth";
                    Validation.isValid(customerJTable.getMonthOfBirthdate());
                    fieldName = "Day of birth";
                    Validation.isValid(customerJTable.getDayOfBirhdate());
		    
		    //Create customer object
		    Customer customer = new Customer(customerJTable.getFname(), customerJTable.getLname(),
                        customerJTable.getGender(), customerJTable.getProvince(), customerJTable.getCity(),
                        customerJTable.getAddress(), customerJTable.getPhonenum(),
                        Integer.parseInt(customerJTable.getYearOfBirthdate()),
                        Integer.parseInt(customerJTable.getMonthOfBirthdate()),
                        Integer.parseInt(customerJTable.getDayOfBirhdate()));
		    customer.setCustomerID(customerJTable.getCusid());
		    
		    try {
			System.out.println("debug customer: " + customer);
			// update
			CustomerHelper.update(customer);
			// research
			// set search keywords
			Customer condition = new Customer(null, txtCustomerSearchLastName.getText(),
				null, null, null, null, txtCustomerSearchPhoneNumber.getText(),
				0, 0, 0);
			System.out.println("debug customer: " + condition);
			// search
			customerJTable.buildTableInfoPanel(condition);
		    } catch(SQLException exSql) {
			Utils.logError(exSql);
			JOptionPane.showMessageDialog(null, "Update failed!");
		    }
		} catch(IllegalArgumentException exIae){
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		}
	    }
	}
    }//end EditCustomerButtonHandler

    private class DeleteCustomerButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            if(Utils.showConfirmDialog("delete this customer")) {
		//Submit to Database
            
                Customer customer = new Customer(null, null,
                        null, null, null, null, null,
                        0, 0, 0);
                customer.setCustomerID(customerJTable.getCusid());
		System.out.println("debug customer: " + customer);

                try {
                    // delete
                    CustomerHelper.delete(customer);
                    // research
                    // set search keywords
                    Customer condition = new Customer(null, txtCustomerSearchLastName.getText(),
                            null, null, null, null, txtCustomerSearchPhoneNumber.getText(),
                            0, 0, 0);
                    System.out.println("debug customer: " + condition);
                    // search
                    customerJTable.buildTableInfoPanel(null);
                } catch (SQLException sqlex) {
                    // error
                    Utils.logError(sqlex);
                    JOptionPane.showMessageDialog(null, "Delete is failed!");
                }
            }
	}
    }//end DeleteCustomerButtonHandler

    private class SearchCustomerButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            
            // set search keywords
            Customer condition = new Customer(null, txtCustomerSearchLastName.getText(),
                    null, null, null, null, txtCustomerSearchPhoneNumber.getText(),
                    0, 0, 0);
            System.out.println("debug customer: " + condition);
            // search
            customerJTable.buildTableInfoPanel(condition);
	}
    }//end SearchCustomerButtonHandler
}
