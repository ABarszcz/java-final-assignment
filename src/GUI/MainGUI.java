/*
 * Assignment 2 - Part 2
 */
package GUI;

import Common.SQLServiceClass;
import Common.Utils;
import Common.Validation;
import Customer.*;
import Employees.*;
import GUI.JTable.CustomerJTable;
import GUI.JTable.EmployeeJTable;
import GUI.JTable.ManufacturerJTable;
import GUI.JTable.ProductJTable;
import GUI.JTable.SaleJTable;
import Manufacturers.*;
import Products.*;
import Sale.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static javax.xml.bind.DatatypeConverter.parseDecimal;

/**
 * Creates the Graphical User Interface for the application.
 * @author Noah Michael
 */
public class MainGUI extends JFrame {
//add start takaaki
    /* consts */
    private static final int TAB_IDX_EMPLOYEE = 0;
    private static final int TAB_IDX_MANUFACTURER = 1;
    private static final int TAB_IDX_PRODUCT = 2;
    private static final int TAB_IDX_SALE = 3;
    private static final int TAB_IDX_CUSTOMER = 4;
    private static final int TAB_IDX_EMPLOYEE_SEARCH = 0;
    private static final int TAB_IDX_MANUFACTURER_SEARCH = 0;
    private static final int TAB_IDX_PRODUCT_SEARCH = 0;
    private static final int TAB_IDX_SALE_SEARCH = 0;
    private static final int TAB_IDX_CUSTOMER_SEARCH = 0;
//add e n d takaaki
    //lists to hold objects
    ArrayList<Employee> empList = new ArrayList<Employee>();
    ArrayList<Manufacturer> mfactList = new ArrayList<Manufacturer>();
    ArrayList<Product> productList = new ArrayList<Product>();
    ArrayList<Customer> custList = new ArrayList<Customer>();
    ArrayList<Sale> saleList = new ArrayList<Sale>();
    
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
//modify start takaaki
//    private final JTextArea txaEmpResults;
//    private final JScrollPane spEmpResults;
    private final EmployeeJTable employeeJTable;
//modify e n d takaaki
    
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
//modify start takaaki
//    private final JButton btnEmpSNew, btnEmpHNew, btnEmpCSNew, btnEmpBPCNew,btnEmployeeEdit, btnEmployeeDelete;
    private final JButton btnEmpSNew, btnEmpHNew, btnEmpCSNew, btnEmpBPCNew, btnEmployeeSearch, btnEmployeeEdit, btnEmployeeDelete;
//modify e n d takaaki
    
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
//modify start takaaki
//    private final JTextArea txaMfactResults;
//    private final JScrollPane spMfactResults;
    private final ManufacturerJTable mfactJTable;
//modify e n d takaaki
    
//modify start takaaki
//    private final JButton btnManufacturerEdit, btnManufacturerDelete;
    private final JButton btnManufacturerSearch, btnManufacturerEdit, btnManufacturerDelete;
//modify e n d takaaki
    
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
//modify start takaaki
//    private final JTextArea txaProductResults;
//    private final JScrollPane spProductResults;
    private final ProductJTable productJTable;
//modify e n d takaaki
    
//modify start takaaki
//    private final JButton btnProductEdit, btnProductDelete;
    private final JButton btnProductSearch, btnProductEdit, btnProductDelete;
//modify e n d takaaki
    
    //</editor-fold>
    
    //<editor-fold desc="Sales GUI Components">
    // pnlSalesSouthED; E = Edit, D = Delete
    private final JTabbedPane pnlSales;
    private final JPanel pnlSalesNew, pnlSalesNorth, pnlSalesCenter,
	    pnlSalesSouth, pnlSalesLabelGrid, pnlSalesFieldGrid, pnlSalesSearch,
	    pnlSalesSearchNorth, pnlSalesSearchCenter, pnlSalesSouthED;
    private final JTextField txtSalesSearchLastName, txtSalesSearchProductName, txtSalesCommission;
    private final JComboBox cboSalesProductName, cboSalesCustomer, cboSalesEmployee;
    private final JButton btnSalesNew;
    private final JLabel lblSalesSearchLastName, lblSalesSearchProductName;
//modify start takaaki
//    private final JTextArea txaSalesResults;
//    private final JScrollPane spSalesResults;
    private final SaleJTable saleJTable;
//modify e n d takaaki
    
//modify start takaaki
//    private final JButton btnSalesEdit, btnSalesDelete;
    private final JButton btnSalesSearch, btnSalesEdit, btnSalesDelete;
//modify e n d takaaki
    
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
//modify start takaaki
//	this.txaEmpResults = new JTextArea(15, 30);
//	this.spEmpResults = new JScrollPane(txaEmpResults);
        this.employeeJTable = new EmployeeJTable();

        // add handler
        this.txtEmpSearchLastName.addActionListener(new SearchEmployeeButtonHandler());
        this.txtEmpSearchDepartment.addActionListener(new SearchEmployeeButtonHandler());
//modify e n d takaaki
        
	//initialize JButtons
	this.btnEmpSNew = new JButton("Create Salary Employee");
        btnEmpSNew.addActionListener(new CreateSalaryEmployeeButtonHandler());
	this.btnEmpHNew = new JButton("Create Hourly Employee");
        btnEmpHNew.addActionListener(new CreateHourlyEmployeeButtonHandler());
	this.btnEmpCSNew = new JButton("Create Commission-Sales Employee");
	this.btnEmpBPCNew = new JButton("Create Base Plus Commission Employee");
        btnEmpBPCNew.addActionListener(new CreateBasePlusCommissionEmployeeButtonHandler());
        this.btnEmployeeEdit = new JButton("Edit");
        this.btnEmployeeDelete = new JButton("Delete");
//add start takaaki
        this.btnEmployeeSearch = new JButton("Search");

        // set handlers
        this.btnEmployeeEdit.addActionListener(new EditEmployeeButtonHandler());
        this.btnEmployeeDelete.addActionListener(new DeleteEmployeeButtonHandler());
        this.btnEmployeeSearch.addActionListener(new SearchEmployeeButtonHandler());
//add e n d takaaki
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
//add start takaaki
        this.btnManufacturerSearch = new JButton("Search");

        // set handlers
        this.btnManufacturerEdit.addActionListener(new EditManufacturerButtonHandler());
        this.btnManufacturerDelete.addActionListener(new DeleteManufacturerButtonHandler());
        this.btnManufacturerSearch.addActionListener(new SearchManufacturerButtonHandler());
//add e n d takaaki
        
	//initialize search components
	this.lblMfactSearch = new JLabel("Search by Manufacturer's Name");
	this.txtMfactSearch = new JTextField(15);
//modify start takaaki
//	this.txaMfactResults = new JTextArea(15, 30);
//	this.spMfactResults = new JScrollPane(txaMfactResults);
        this.mfactJTable = new ManufacturerJTable();

        // add handler
        this.txtMfactSearch.addActionListener(new SearchManufacturerButtonHandler());
//modify e n d takaaki
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
//add start takaaki
        this.btnProductSearch = new JButton("Search");

        // set handlers
        this.btnProductEdit.addActionListener(new EditProductButtonHandler());
        this.btnProductDelete.addActionListener(new DeleteProductButtonHandler());
        this.btnProductSearch.addActionListener(new SearchProductButtonHandler());
//add e n d takaaki
	
	//initialize JComboBox
	this.cboProductManufacturer = new JComboBox();
	
	//initialize Search components
	this.lblProductSearch = new JLabel("Search Product by Name");
	this.txtProductSearch = new JTextField(15);
        this.lblProductManuSearch = new JLabel("Search Product by Manufacturer's Name");
	this.txtProductManuSearch = new JTextField(15);
//modify start takaaki
//        this.txaProductResults = new JTextArea(15, 30);
//	this.spProductResults = new JScrollPane(txaProductResults);
        this.productJTable = new ProductJTable();

        // add handler
        this.txtProductSearch.addActionListener(new SearchProductButtonHandler());
        this.txtProductManuSearch.addActionListener(new SearchProductButtonHandler());
//modify e n d takaaki
        
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
	        
	//initialize        
	this.cboSalesProductName = new JComboBox();
        SQLServiceClass.productList(cboSalesProductName);
        this.cboSalesCustomer = new JComboBox();
        SQLServiceClass.customerList(cboSalesCustomer);
	this.cboSalesEmployee = new JComboBox();
        SQLServiceClass.employeeList(cboSalesEmployee);
	this.txtSalesCommission = new JTextField(15);

	
	//initialize JButton
	this.btnSalesNew = new JButton("Create Sales");
        this.btnSalesEdit = new JButton("Edit");
        this.btnSalesDelete = new JButton("Delete");
//add start takaaki
        this.btnSalesSearch = new JButton("Search");

        // set handlers
        this.btnSalesEdit.addActionListener(new EditSalesButtonHandler());
        this.btnSalesDelete.addActionListener(new DeleteSalesButtonHandler());
        this.btnSalesSearch.addActionListener(new SearchSalesButtonHandler());
//add e n d takaaki
		
	//initialize Search components
	this.lblSalesSearchLastName = new JLabel("Search Sales by Employee's Name");
	this.txtSalesSearchLastName = new JTextField(15);
        this.lblSalesSearchProductName = new JLabel("Search Sales by Product Name");
	this.txtSalesSearchProductName = new JTextField(15);
//modify start takaaki
//	this.txaSalesResults = new JTextArea(15, 30);
//	this.spSalesResults = new JScrollPane(txaSalesResults);       
        this.saleJTable = new SaleJTable();

        // add handler
        this.txtSalesSearchLastName.addActionListener(new SearchSalesButtonHandler());
        this.txtSalesSearchProductName.addActionListener(new SearchSalesButtonHandler());
//modify e n d takaaki
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
        btnCustomerNew.addActionListener(new CreateCustomerButtonHandler());
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
        
// add start takaaki
        // add handler
        this.txtCustomerSearchLastName.addActionListener(new SearchCustomerButtonHandler());
        this.txtCustomerSearchPhoneNumber.addActionListener(new SearchCustomerButtonHandler());
// add e n d takaaki
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
	this.setSize(715,565);
        this.setResizable(false);
	this.setLocationRelativeTo(null);
	//create the view
	this.setContentPane(this.createContentPane());
        // initial search
        this.employeeJTable.buildTableInfoPanel(null);
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
//add start takaaki
                case TAB_IDX_EMPLOYEE:
                    if (pnlEmp.getSelectedIndex() == TAB_IDX_EMPLOYEE_SEARCH) {
                        // search
                        employeeJTable.buildTableInfoPanel(null);
                    }
                    break;
                case TAB_IDX_MANUFACTURER:
                    if (pnlMfact.getSelectedIndex() == TAB_IDX_MANUFACTURER_SEARCH) {
                        // search
                        mfactJTable.buildTableInfoPanel(null);
                    }
                    break;
//add e n d takaaki
//add start takaaki
                case TAB_IDX_PRODUCT:
                    if (pnlProduct.getSelectedIndex() == TAB_IDX_PRODUCT_SEARCH) {
                        // search
                        buildProductTableInfoPanel(null);
                    }
                    break;
//add e n d takaaki
                case TAB_IDX_CUSTOMER:
                    if (pnlCustomer.getSelectedIndex() == TAB_IDX_CUSTOMER_SEARCH) {
                        // search
                        customerJTable.buildTableInfoPanel(null);
                    }
                    break;
//add start takaaki
                case TAB_IDX_SALE:
                    if (pnlSales.getSelectedIndex() == TAB_IDX_SALE_SEARCH) {
                        // search
                        buildSalesTableInfoPanel(null);
                    }
                    break;
//add e n d takaaki
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
//add start takaaki

        // set handler
        pnlEmp.addChangeListener(new EmployeeTabChangeListener());
//add e n d takaaki
    }

//add start takaaki
    /**
     * That is called when employee tab is clicked.
     */
    private class EmployeeTabChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            if (pnlEmp.getSelectedIndex() == 0) {
                // search
                employeeJTable.buildTableInfoPanel(null);
            }
        }
        
    }
//add e n d takaaki
    
    private void createManufacturerTabbedPane() {
	createManufacturerSearchTab();
	createManufacturerTab();
	
	//add the tabs
	pnlMfact.addTab("Search", pnlMfactSearch);
	pnlMfact.addTab("New Manufacturer", pnlMfactNew);
//add start takaaki

        // set handler
        pnlMfact.addChangeListener(new ManufacturerTabChangeListener());
//add e n d takaaki
    }
//add start takaaki
    /**
     * That is called when manufacturer tab is clicked.
     */
    private class ManufacturerTabChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            if (pnlMfact.getSelectedIndex() == 0) {
                // search
                mfactJTable.buildTableInfoPanel(null);
            }
        }
        
    }
//add e n d takaaki
    
    private void createProductTabbedPane() {
	createProductSearchTab();
	createProductTab();
	
	//add the tabs
	pnlProduct.addTab("Search", pnlProductSearch);
	pnlProduct.addTab("New Product", pnlProductNew);
//add start takaaki

        // set handler
        pnlProduct.addChangeListener(new ProductTabChangeListener());
//add e n d takaaki
    }
//add
    /**
     * That is called when product tab is clicked.
     */
    private class ProductTabChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            if (pnlProduct.getSelectedIndex() == 0) {
                // search
                buildProductTableInfoPanel(null);
            }
        }
        
    }
// end
    
    private void createSalesTabbedPane() {
	createSalesSearchTab();
	createSalesTab();
	
	//add the tabs
	pnlSales.addTab("Search", pnlSalesSearch);
	pnlSales.addTab("New Sales", pnlSalesNew);
//add start takaaki

        // set handler
        pnlSales.addChangeListener(new SaleTabChangeListener());
//add e n d takaaki
    }

//add start takaaki
    /**
     * That is called when sales tab is clicked.
     */
    private class SaleTabChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            if (pnlSales.getSelectedIndex() == 0) {
                // search
                buildSalesTableInfoPanel(null);
            }
        }
        
    }
//add e n d takaaki
    
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
//add start takaaki
        pnlEmpSearchNorth.add(btnEmployeeSearch);
//add e n d takaaki
        pnlEmpSearchCenter.add(lblEmpSearchDepartment);
     	pnlEmpSearchCenter.add(txtEmpSearchDepartment);
	
	//design the center panel
//modify start takaaki
//	pnlEmpSearchCenter.add(spEmpResults);
        pnlEmpSearchCenter.add(this.employeeJTable);
//modify e n d takaaki
        
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
	//pnlEmpSLabelGrid.add(new JLabel("Salary Type"));
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
	//pnlEmpSFieldGrid.add(txtEmpSSalaryType);
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
        btnEmpCSNew.addActionListener(new CreateCommissionEmployeeButtonHandler());
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
//add start takaaki
        pnlMfactSearchNorth.add(btnManufacturerSearch);
//add e n d takaaki
	
	//design the center panel
//modify start takaaki
//	pnlMfactSearchCenter.add(spMfactResults);
        pnlMfactSearchCenter.add(this.mfactJTable);
//modify e n d takaaki
	
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
//add start takaaki
        pnlProductSearchNorth.add(btnProductSearch);
//add e n d takaaki
        pnlProductSearchCenter.add(lblProductManuSearch);
	pnlProductSearchCenter.add(txtProductManuSearch);
	
	//design the center panel
//modify start takaaki
//        pnlProductSearchCenter.add(spProductResults);
        pnlProductSearchCenter.add(this.productJTable);
//modify e n d takaaki
	
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
       SQLServiceClass.mfactList(cboProductManufacturer);   
	
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
//add start takaaki
        pnlSalesSearchNorth.add(btnSalesSearch);
//add e n d takaaki
        pnlSalesSearchCenter.add(lblSalesSearchProductName);
	pnlSalesSearchCenter.add(txtSalesSearchProductName);
        	
	//design the center panel
//modify start takaaki
//	pnlSalesSearchCenter.add(spSalesResults);
        pnlSalesSearchCenter.add(this.saleJTable);
//modify e n d takaaki
	
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

	
	//create the field sub-panel of the center panel
	pnlSalesFieldGrid.setLayout(new GridLayout(13,1));
	pnlSalesFieldGrid.add(cboSalesProductName);
        pnlSalesFieldGrid.add(cboSalesCustomer);
	pnlSalesFieldGrid.add(cboSalesEmployee);

	
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
        pnlCustomerLabelGrid.add(new JLabel("Address"));
         pnlCustomerLabelGrid.add(new JLabel("City"));
        pnlCustomerLabelGrid.add(new JLabel("Province"));
        pnlCustomerLabelGrid.add(new JLabel("Phone Number"));
        pnlCustomerLabelGrid.add(new JLabel("Year Of Birth"));
        pnlCustomerLabelGrid.add(new JLabel("Month Of Birth"));
        pnlCustomerLabelGrid.add(new JLabel("Day Of Birth"));
	
	//create the field sub-panel of the center panel
	pnlCustomerFieldGrid.setLayout(new GridLayout(13,1));
	pnlCustomerFieldGrid.add(txtCustomerFirstName);
	pnlCustomerFieldGrid.add(txtCustomerLastName);
        pnlCustomerFieldGrid.add(pnlCustomerSex);
        pnlCustomerFieldGrid.add(txtCustomerAddress);
        pnlCustomerFieldGrid.add(txtCustomerCity);
        pnlCustomerFieldGrid.add(txtCustomerProvince);     
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
    
    private class CreateSalaryEmployeeButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Utils.showConfirmDialog("create a new salary employee")) {
                    String fieldName = "";
		
		//Validate Inputs
		try{
                    fieldName = "First name";
		    Validation.isValidName(txtEmpSFirstName.getText(), false);
		    fieldName = "Last name";
		    Validation.isValidName(txtEmpSLastName.getText(), false);
		    //TODO get the sex
		    fieldName = "Sex";
		    Validation.isValidRdoBtns(pnlEmpSSex);
		    fieldName = "Address";
		    Validation.isValid(txtEmpSAddress.getText());
		    fieldName = "City";
		    Validation.isValid(txtEmpSCity.getText());
                    fieldName = "Province";
                    Validation.isValid(txtEmpSProvince.getText());
                    fieldName = "Phone number";
		    Validation.isValidPhoneNum(txtEmpSPhoneNum.getText());
                    fieldName = "Department";
                    Validation.isValid(txtEmpSDepartment.getText());
                    fieldName = "Posistion";
                    Validation.isValid(txtEmpSPosition.getText());
                    fieldName = "S.I.N.";
                    Validation.isValid(txtEmpSSocialSecurityNum.getText());
		    fieldName = "Year of birth";
		    Validation.isValid(txtEmpSYearOfBirth.getText());
		    fieldName = "Month of birth";
		    Validation.isValid(txtEmpSMonthOfBirth.getText());
		    fieldName = "Day of birth";
		    Validation.isValid(txtEmpSDayOfBirth.getText());
                    //fieldName = "Salary Type";
                    //Validation.isValid(txtEmpSSalaryType.getText());
                    fieldName = "Salary Amount";
                    Validation.isValid(txtEmpSSalaryAmount.getText());
		    //Select Gender
                    String gender = "Not Selected";
                    if(rdoEmpSSexMale.isSelected()){
                         gender = "Male";
                    }
                    if(rdoEmpSSexFemale.isSelected()){
                         gender = "Female";
                    }
                     //Create object
                    SalaryEmployee e1 = new SalaryEmployee(txtEmpSFirstName.getText(), txtEmpSLastName.getText(), gender, txtEmpSAddress.getText(), 
                                                           txtEmpSCity.getText(), txtEmpSProvince.getText(), txtEmpSPhoneNum.getText(), txtEmpSDepartment.getText(), 
                                                           txtEmpSPosition.getText(), txtEmpSSocialSecurityNum.getText(), parseInt(txtEmpSYearOfBirth.getText()),
                                                           parseInt(txtEmpSMonthOfBirth.getText()), parseInt(txtEmpSDayOfBirth.getText()),parseDecimal(txtEmpSSalaryAmount.getText())
                                                                   );
                    //Add to database
                    SQLServiceClass.insertSalaryEmployee(e1.getFirstName(), e1.getLastName(), e1.getSex(), e1.getAddress(), e1.getCity(), e1.getProvince(), e1.getPhoneNum(), 
                                                      e1.getDepartment(), e1.getPosition(), e1.getSocialSecurityNum(), e1.getYear(), e1.getDateOfBirth().getTime().getMonth(), 
                                                      e1.getDateOfBirth().getTime().getDate(), e1.getSalaryAmount());
                
		} catch(IllegalArgumentException error){
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		} catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_CREATE);
                }
	    }           
	}
    }//end employee


    private class CreateHourlyEmployeeButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Utils.showConfirmDialog("create a new hourly employee")) {
                    String fieldName = "";
                    
		//Validate Inputs
		try{
                    fieldName = "First name";
		    Validation.isValidName(txtEmpHFirstName.getText(), false);
		    fieldName = "Last name";
		    Validation.isValidName(txtEmpHLastName.getText(), false);
		    //TODO get the sex
		    fieldName = "Sex";
		    Validation.isValidRdoBtns(pnlEmpHSex);
		    fieldName = "Address";
		    Validation.isValid(txtEmpHAddress.getText());
		    fieldName = "City";
		    Validation.isValid(txtEmpHCity.getText());
                    fieldName = "Province";
                    Validation.isValid(txtEmpHProvince.getText());
                    fieldName = "Phone number";
		    Validation.isValidPhoneNum(txtEmpHPhoneNum.getText());
                    fieldName = "Department";
                    Validation.isValid(txtEmpHDepartment.getText());
                    fieldName = "Posistion";
                    Validation.isValid(txtEmpHPosition.getText());
                    fieldName = "S.I.N.";
                    Validation.isValid(txtEmpHSocialSecurityNum.getText());
		    fieldName = "Year of birth";
		    Validation.isValid(txtEmpHYearOfBirth.getText());
		    fieldName = "Month of birth";
		    Validation.isValid(txtEmpHMonthOfBirth.getText());
		    fieldName = "Day of birth";
		    Validation.isValid(txtEmpHDayOfBirth.getText());
                    //fieldName = "Salary Type";
                    //Validation.isValid(txtEmpSSalaryType.getText());
                    fieldName = "Wage";
                    Validation.isValid(txtEmpHWage.getText());
		    //Select Gender
                    String gender = "Not Selected";
                    if(rdoEmpHSexMale.isSelected()){
                         gender = "Male";
                    }
                    if(rdoEmpHSexFemale.isSelected()){
                         gender = "Female";
                    }
                     //Create object
                    HourlyEmployee e1 = new HourlyEmployee(txtEmpHFirstName.getText(), txtEmpHLastName.getText(), gender, txtEmpHAddress.getText(), 
                                                           txtEmpHCity.getText(), txtEmpHProvince.getText(), txtEmpHPhoneNum.getText(), txtEmpHDepartment.getText(), 
                                                           txtEmpHPosition.getText(), txtEmpHSocialSecurityNum.getText(), parseInt(txtEmpHYearOfBirth.getText()),
                                                           parseInt(txtEmpHMonthOfBirth.getText()), parseInt(txtEmpHDayOfBirth.getText()),parseDecimal(txtEmpHWage.getText())
                                                                   );
                    //Add to database
                    SQLServiceClass.insertHourlyEmployee(e1.getFirstName(), e1.getLastName(), e1.getSex(), e1.getAddress(), e1.getCity(), e1.getProvince(), e1.getPhoneNum(), 
                                                      e1.getDepartment(), e1.getPosition(), e1.getSocialSecurityNum(), e1.getYear(), e1.getDateOfBirth().getTime().getMonth(), 
                                                      e1.getDateOfBirth().getTime().getDate(), e1.getWage());
                
		} catch(IllegalArgumentException error){
                        System.out.print(error);
		   // JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		} catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_DELETE);
                }
	    }           
	}
    }//end employee 
    
    //Create new commission employee
    private class CreateCommissionEmployeeButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Utils.showConfirmDialog("create a new commission sale employee")) {
                    String fieldName = "";
		
		//Validate Inputs
		try{
                    fieldName = "First name";
		    Validation.isValidName(txtEmpCSFirstName.getText(), false);
		    fieldName = "Last name";
		    Validation.isValidName(txtEmpCSLastName.getText(), false);
		    //TODO get the sex
		    fieldName = "Sex";
		    Validation.isValidRdoBtns(pnlEmpCSSex);
		    fieldName = "Address";
		    Validation.isValid(txtEmpCSAddress.getText());
		    fieldName = "City";
		    Validation.isValid(txtEmpCSCity.getText());
                    fieldName = "Province";
                    Validation.isValid(txtEmpCSProvince.getText());
                    fieldName = "Phone number";
		    Validation.isValidPhoneNum(txtEmpCSPhoneNum.getText());
                    fieldName = "Department";
                    Validation.isValid(txtEmpCSDepartment.getText());
                    fieldName = "Posistion";
                    Validation.isValid(txtEmpCSPosition.getText());
                    fieldName = "S.I.N.";
                    Validation.isValid(txtEmpCSSocialSecurityNum.getText());
		    fieldName = "Year of birth";
		    Validation.isValid(txtEmpCSYearOfBirth.getText());
		    fieldName = "Month of birth";
		    Validation.isValid(txtEmpCSMonthOfBirth.getText());
		    fieldName = "Day of birth";
		    Validation.isValid(txtEmpCSDayOfBirth.getText());
                    fieldName = "Commission";
                    Validation.isValid(txtEmpCSCommissionRates.getText());
		    //Select Gender
                    String gender = "Not Selected";
                    if(rdoEmpCSSexMale.isSelected()){
                         gender = "Male";
                    }
                    if(rdoEmpCSSexFemale.isSelected()){
                         gender = "Female";
                    }
                     //Create object
                    CommissionSalesEmployee e1 = new CommissionSalesEmployee(txtEmpCSFirstName.getText(), txtEmpCSLastName.getText(), gender, txtEmpCSAddress.getText(), 
                                                           txtEmpCSCity.getText(), txtEmpCSProvince.getText(), txtEmpCSPhoneNum.getText(), txtEmpCSDepartment.getText(), 
                                                           txtEmpCSPosition.getText(), txtEmpCSSocialSecurityNum.getText(), parseInt(txtEmpCSYearOfBirth.getText()),
                                                           parseInt(txtEmpCSMonthOfBirth.getText()), parseInt(txtEmpCSDayOfBirth.getText()),parseDecimal(txtEmpCSCommissionRates.getText())
                                                                   );
                    //Add to database
                    SQLServiceClass.insertCommissionEmployee(e1.getFirstName(), e1.getLastName(), e1.getSex(), e1.getAddress(), e1.getCity(), e1.getProvince(), e1.getPhoneNum(), 
                                                      e1.getDepartment(), e1.getPosition(), e1.getSocialSecurityNum(), e1.getYear(), e1.getDateOfBirth().getTime().getMonth(), 
                                                      e1.getDateOfBirth().getTime().getDate(), e1.getCommissionRates());
                
		} catch(IllegalArgumentException error){
                        System.out.print(error);
		   // JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		} catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_CREATE);
                }
	    }           
	}
    }//end employee
    
    //Create new Base plus commission employee
    private class CreateBasePlusCommissionEmployeeButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Utils.showConfirmDialog("create a new base plus commission sale employee")) {
                    String fieldName = "";
		
		//Validate Inputs
		try{
                    fieldName = "First name";
		    Validation.isValidName(txtEmpBPCFirstName.getText(), false);
		    fieldName = "Last name";
		    Validation.isValidName(txtEmpBPCLastName.getText(), false);
		    //TODO get the sex
		    fieldName = "Sex";
		    Validation.isValidRdoBtns(pnlEmpBPCSex);
		    fieldName = "Address";
		    Validation.isValid(txtEmpBPCAddress.getText());
		    fieldName = "City";
		    Validation.isValid(txtEmpBPCCity.getText());
                    fieldName = "Province";
                    Validation.isValid(txtEmpBPCProvince.getText());
                    fieldName = "Phone number";
		    Validation.isValidPhoneNum(txtEmpBPCPhoneNum.getText());
                    fieldName = "Department";
                    Validation.isValid(txtEmpBPCDepartment.getText());
                    fieldName = "Posistion";
                    Validation.isValid(txtEmpBPCPosition.getText());
                    fieldName = "S.I.N.";
                    Validation.isValid(txtEmpBPCSocialSecurityNum.getText());
		    fieldName = "Year of birth";
		    Validation.isValid(txtEmpBPCYearOfBirth.getText());
		    fieldName = "Month of birth";
		    Validation.isValid(txtEmpBPCMonthOfBirth.getText());
		    fieldName = "Day of birth";
		    Validation.isValid(txtEmpBPCDayOfBirth.getText());
                    fieldName = "Commission";
                    Validation.isValid(txtEmpBPCCommissionRates.getText());
                    fieldName = "Base Salary";
                    Validation.isValid(txtEmpBPCBaseSalary.getText());
		    //Select Gender
                    String gender = "Not Selected";
                    if(rdoEmpBPCSexMale.isSelected()){
                         gender = "Male";
                    }
                    if(rdoEmpBPCSexFemale.isSelected()){
                         gender = "Female";
                    }
                    //Create object
                    fieldName = "sql";
                    BasePlusCommissionEmployee e1 = new BasePlusCommissionEmployee(txtEmpBPCFirstName.getText(), txtEmpBPCLastName.getText(), gender, txtEmpBPCAddress.getText(), 
                                                           txtEmpBPCCity.getText(), txtEmpBPCProvince.getText(), txtEmpBPCPhoneNum.getText(), txtEmpBPCDepartment.getText(), 
                                                           txtEmpBPCPosition.getText(), txtEmpBPCSocialSecurityNum.getText(), parseInt(txtEmpBPCYearOfBirth.getText()),
                                                           parseInt(txtEmpBPCMonthOfBirth.getText()), parseInt(txtEmpBPCDayOfBirth.getText()),parseDecimal(txtEmpBPCCommissionRates.getText()), parseDecimal(txtEmpBPCBaseSalary.getText()));
                                                                   
                    //Add to database
                    SQLServiceClass.insertBasePlusCommissionEmployee(e1.getFirstName(), e1.getLastName(), e1.getSex(), e1.getAddress(), e1.getCity(), e1.getProvince(), e1.getPhoneNum(), 
                                                      e1.getDepartment(), e1.getPosition(), e1.getSocialSecurityNum(), e1.getYear(), e1.getDateOfBirth().getTime().getMonth(), 
                                                      e1.getDateOfBirth().getTime().getDate(), e1.getCommissionRates(), e1.getBaseSalary());
        
		} catch(IllegalArgumentException error){
                        System.out.print(error);
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		} catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_CREATE);
                }
	    }           
	}
    }//end employee
    
//add start takaaki
    /**
     * Handler for edit button on Employee tab.
     */
    private class EditEmployeeButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            // if not selected, do nothing
            if (!employeeJTable.isSelected()) {
                return;
            }

            if(Utils.showConfirmDialog("edit this employee")) {
		String fieldName = "";
		
                boolean check = true;
                try{
		    //Validate Inputs
		    fieldName = "First name";
                    Validation.isValidName(employeeJTable.getFname(), false);
		    fieldName = "Last name";
                    Validation.isValidName(employeeJTable.getLname(), false);
		    fieldName = "Gender";
                    Validation.isValid(employeeJTable.getGender());
		    fieldName = "Address";
                    Validation.isValid(employeeJTable.getAddress());
		    fieldName = "City";
                    Validation.isValid(employeeJTable.getCity());
                    fieldName = "Province";
		    Validation.isValid(employeeJTable.getProvince());
                    fieldName = "Phone number";
                    Validation.isValidPhoneNum(employeeJTable.getPhonenum());
                    fieldName = "Department";
		    Validation.isValid(employeeJTable.getDept());
                    fieldName = "Posistion";
		    Validation.isValid(employeeJTable.getDeptposition());
                    fieldName = "S.I.N.";
		    Validation.isValid(employeeJTable.getSsn());
                    fieldName = "Year of birth";
                    Validation.isValid(employeeJTable.getYearOfBirthdate());
                    fieldName = "Month of birth";
                    Validation.isValid(employeeJTable.getMonthOfBirthdate());
                    fieldName = "Day of birth";
                    System.out.println("Debug:");
                    Validation.isValid(employeeJTable.getDayOfBirthdate());
                    System.out.println("Debug day of birth:" + employeeJTable.getDayOfBirthdate());
                    System.out.println("Debug getHourly:" + employeeJTable.getHourly());
                    System.out.println("Debug getSalary:" + employeeJTable.getSalary());
                    System.out.println("Debug getComm:" + employeeJTable.getComm());
                    BigDecimal hourly = (Utils.isEmpty(employeeJTable.getHourly()) ? null : new BigDecimal(employeeJTable.getHourly()));
                    BigDecimal salary = (Utils.isEmpty(employeeJTable.getSalary()) ? null : new BigDecimal(employeeJTable.getSalary()));
                    BigDecimal comm = (Utils.isEmpty(employeeJTable.getComm()) ? null : new BigDecimal(employeeJTable.getComm()));

                    //Create customer object
		    Employee employee;
                    final int EMPLOYEE_TYPE = Utils.judgeEmployeeType(hourly, salary, comm);
                    switch (EMPLOYEE_TYPE) {
                        case Utils.EMP_TYPE_BASE_PLUS_COMMISSION:
                            fieldName = "Salary Amount";
                            Validation.isValid(salary);
                            fieldName = "Commission";
                            Validation.isValid(comm);

                            employee = new BasePlusCommissionEmployee(employeeJTable.getFname(), employeeJTable.getLname(),
                                    employeeJTable.getGender(), employeeJTable.getAddress(), employeeJTable.getCity(),
                                    employeeJTable.getProvince(), employeeJTable.getPhonenum(), employeeJTable.getDept(),
                                    employeeJTable.getDeptposition(), employeeJTable.getSsn(),
                                    Integer.parseInt(employeeJTable.getYearOfBirthdate()),
                                    Integer.parseInt(employeeJTable.getMonthOfBirthdate()),
                                    Integer.parseInt(employeeJTable.getDayOfBirthdate()),
                                    comm, salary);
                            break;
                        case Utils.EMP_TYPE_COMMISSION:
                            fieldName = "Commission";
                            Validation.isValid(comm);

                            employee = new CommissionSalesEmployee(employeeJTable.getFname(), employeeJTable.getLname(),
                                    employeeJTable.getGender(), employeeJTable.getAddress(), employeeJTable.getCity(),
                                    employeeJTable.getProvince(), employeeJTable.getPhonenum(), employeeJTable.getDept(),
                                    employeeJTable.getDeptposition(), employeeJTable.getSsn(),
                                    Integer.parseInt(employeeJTable.getYearOfBirthdate()),
                                    Integer.parseInt(employeeJTable.getMonthOfBirthdate()),
                                    Integer.parseInt(employeeJTable.getDayOfBirthdate()),
                                    comm);
                            break;
                        case Utils.EMP_TYPE_HOURLY:
                            fieldName = "Wage";
                            Validation.isValid(hourly);

                            employee = new HourlyEmployee(employeeJTable.getFname(), employeeJTable.getLname(),
                                    employeeJTable.getGender(), employeeJTable.getAddress(), employeeJTable.getCity(),
                                    employeeJTable.getProvince(), employeeJTable.getPhonenum(), employeeJTable.getDept(),
                                    employeeJTable.getDeptposition(), employeeJTable.getSsn(),
                                    Integer.parseInt(employeeJTable.getYearOfBirthdate()),
                                    Integer.parseInt(employeeJTable.getMonthOfBirthdate()),
                                    Integer.parseInt(employeeJTable.getDayOfBirthdate()),
                                    hourly);
                            break;
                        case Utils.EMP_TYPE_SALARY:
                            fieldName = "Salary Amount";
                            Validation.isValid(salary);

                            employee = new SalaryEmployee(employeeJTable.getFname(), employeeJTable.getLname(),
                                    employeeJTable.getGender(), employeeJTable.getAddress(), employeeJTable.getCity(),
                                    employeeJTable.getProvince(), employeeJTable.getPhonenum(), employeeJTable.getDept(),
                                    employeeJTable.getDeptposition(), employeeJTable.getSsn(),
                                    Integer.parseInt(employeeJTable.getYearOfBirthdate()),
                                    Integer.parseInt(employeeJTable.getMonthOfBirthdate()),
                                    Integer.parseInt(employeeJTable.getDayOfBirthdate()),
                                    salary);
                            break;
                        default:
                            // update only basic info
                            employee = new SalaryEmployee(employeeJTable.getFname(), employeeJTable.getLname(),
                                    employeeJTable.getGender(), employeeJTable.getAddress(), employeeJTable.getCity(),
                                    employeeJTable.getProvince(), employeeJTable.getPhonenum(), employeeJTable.getDept(),
                                    employeeJTable.getDeptposition(), employeeJTable.getSsn(),
                                    Integer.parseInt(employeeJTable.getYearOfBirthdate()),
                                    Integer.parseInt(employeeJTable.getMonthOfBirthdate()),
                                    Integer.parseInt(employeeJTable.getDayOfBirthdate()),
                                    null);
                            break;
                    }

		    employee.setEmployeeID(employeeJTable.getEmpid());
		    
                    if (check != true) {
                        return;
                    }
		    try {
			System.out.println("debug employee: " + employee);
			// update
			SQLServiceClass.update(employee);
			// research
			// set search keywords
                        Employee condition  = new SalaryEmployee(null, txtEmpSearchLastName.getText(),
                            null, null, null, null, null, txtEmpSearchDepartment.getText(),
                            null, null, 0, 0, 0, null);
			System.out.println("debug employee: " + condition);
			// search
			employeeJTable.buildTableInfoPanel(condition);
		    } catch (SQLException sqlex) {
                        Utils.logError(sqlex);
                        Utils.showError(Utils.ACTION_EDIT);
                    }
		} catch(IllegalArgumentException exIae){
                    Utils.logError(exIae);
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		}
	    }
	}
    }//end EditEmployeeButtonHandler

    /**
     * Handler for delete button on Employee tab.
     */
    private class DeleteEmployeeButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            // if not selected, do nothing
            if (!employeeJTable.isSelected()) {
                return;
            }

            if(Utils.showConfirmDialog("delete this employee")) {
		//Submit to Database
            
                Employee employee = new SalaryEmployee(null);
                employee.setEmployeeID(employeeJTable.getEmpid());

                try {
                    // delete
                    SQLServiceClass.delete(employee);
                    // re-search
                    // set search keywords
                    Employee condition  = new SalaryEmployee(null, txtEmpSearchLastName.getText(),
                        null, null, null, null, null, txtEmpSearchDepartment.getText(),
                        null, null, 0, 0, 0, null);
                    System.out.println("debug employee: " + condition);
                    // search
                    employeeJTable.buildTableInfoPanel(null);
                } catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_DELETE);
                }
            }
	}
    }//end DeleteEmployeeButtonHandler

    /**
     * Handler for search button on Employee tab.
     */
    private class SearchEmployeeButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            
            // set search keywords
            Employee condition  = new SalaryEmployee(null, txtEmpSearchLastName.getText(),
                null, null, null, null, null, txtEmpSearchDepartment.getText(),
                null, null, 0, 0, 0, null);
            System.out.println("debug employee: " + condition);
            // search
            employeeJTable.buildTableInfoPanel(condition);
	}
    }//end SearchEmployeeButtonHandler

    private class CreateMfactButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    if(Utils.showConfirmDialog("create a new manufacturer")) {
		String fieldName = "";
		
		try{
		    //Validate Inputs
		    fieldName = "Name";
		    Validation.isValidName(txtMfactName.getText(), true);
		    fieldName = "Province";
		    Validation.isValid(txtMfactProvince.getText());
		    fieldName = "City";
		    Validation.isValid(txtMfactCity.getText());
		    fieldName = "Address";
		    Validation.isValid(txtMfactAddress.getText());
		    fieldName = "Phone number";
		    Validation.isValidPhoneNum(txtMfactPhoneNum.getText());
		    
                    Manufacturer m1 = new Manufacturer(txtMfactName.getText(),
			    txtMfactProvince.getText(), txtMfactCity.getText(),
			    txtMfactAddress.getText(), txtMfactPhoneNum.getText());
		    //create object
		    mfactList.add(m1);
		    
		    //submit to the database
		    SQLServiceClass.insertMfact(m1.getName(), m1.getAddress(), m1.getCity(), m1.getProvince(), m1.getPhoneNum());
		} catch(IllegalArgumentException error){
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		} catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_CREATE);
                }
	    }           
	}
    }//end CreateMfactButtonHandler
    
// add start takaaki
    /**
     * Handler for edit button on Manufacturers tab.
     */
    private class EditManufacturerButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            // if not selected, do nothing
            if (!mfactJTable.isSelected()) {
                return;
            }

            if(Utils.showConfirmDialog("edit this manufacturer")) {
		String fieldName = "";
		
                boolean check = true;
                try{
		    //Validate Inputs
		    fieldName = "Name";
                    Validation.isValidName(mfactJTable.getMfactname(), true);
		    fieldName = "Province";
                    Validation.isValid(mfactJTable.getProvince());
		    fieldName = "City";
                    Validation.isValid(mfactJTable.getCity());
                    fieldName = "Address";
                    Validation.isValid(mfactJTable.getAddress());
		    fieldName = "Phone Number";
                    Validation.isValidPhoneNum(mfactJTable.getPhonenum());
		    
		    //Create sale object
		    Manufacturer sale = new Manufacturer(mfactJTable.getMfactname(),
                            mfactJTable.getProvince(),
                            mfactJTable.getCity(),
                            mfactJTable.getAddress(),
                            mfactJTable.getPhonenum());
		    sale.setMfactID(mfactJTable.getMfactid());
		    
                    if (check != true) {
                        return;
                    }
		    try {
			System.out.println("debug manufacturer: " + sale);
			// update
			SQLServiceClass.update(sale);
			// re-search
                        // set search keywords
                        Manufacturer condition = new Manufacturer(txtMfactSearch.getText());
                        System.out.println("debug manufacturer: " + condition);
                        // search
                        mfactJTable.buildTableInfoPanel(condition);
		    } catch (SQLException sqlex) {
                        Utils.logError(sqlex);
                        Utils.showError(Utils.ACTION_EDIT);
                    }
		} catch(IllegalArgumentException exIae){
                    Utils.logError(exIae);
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		}
	    }
	}
    }//end EditManufacturerButtonHandler

    /**
     * Handler for delete button on Manufacturers tab.
     */
    private class DeleteManufacturerButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            // if not selected, do nothing
            if (!mfactJTable.isSelected()) {
                return;
            }

            if(Utils.showConfirmDialog("delete this manufacturer")) {
		//Submit to Database
            
                Manufacturer manufacturer = new Manufacturer(null);
                manufacturer.setMfactID(mfactJTable.getMfactid());
		System.out.println("debug manufacturer: " + manufacturer);

                try {
                    // delete
                    SQLServiceClass.delete(manufacturer);
                    // re-search
                    // set search keywords
                    Manufacturer condition = new Manufacturer(txtMfactSearch.getText());
                    System.out.println("debug manufacturer: " + condition);
                    // search
                    mfactJTable.buildTableInfoPanel(condition);
                } catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_DELETE);
                }
            }
	}
    }//end DeleteManufacturerButtonHandler

    /**
     * Handler for search button on Manufacturers tab.
     */
    private class SearchManufacturerButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            
            // set search keywords
            Manufacturer condition = new Manufacturer(txtMfactSearch.getText());
            System.out.println("debug manufacturer: " + condition);
            // search
            mfactJTable.buildTableInfoPanel(condition);
	}
    }//end SearchManufacturerButtonHandler
//add e n d takaaki

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
		    fieldName = "Manufacturer";
                    Validation.isValid(cboProductManufacturer);
                    
                    //Create manufacturer
                    Manufacturer m1= new Manufacturer(cboProductManufacturer.getSelectedItem().toString());
                    //Create Product
                    Product p1 = new Product(txtProductName.getText(), parseDecimal(txtProductPrice.getText()), parseDecimal(txtProductDiscount.getText()), m1);
		    
		    //Submit to Database
		    SQLServiceClass.insertProduct(p1.getName(), p1.getPrice(), p1.getDiscount(), p1.getMfact().getName());
		} catch(NumberFormatException exNfe) {
		    Utils.logError(exNfe);
		} catch(IllegalArgumentException error){
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		} catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_CREATE);
                }
            }
	}
    }//end CreateProductButtonHandler
    
//add start takaaki
    /**
     * Handler for edit button on Product tab.
     */
    private class EditProductButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            // if not selected, do nothing
            if (!productJTable.isSelected()) {
                return;
            }

            if(Utils.showConfirmDialog("edit this product")) {
                boolean check = true;
                String fieldName = "";
		
		//validate inputs
		try{
		    fieldName = "Name";
                    Validation.isValidName(productJTable.getProductname(), true);
		    fieldName = "Price";
                    Validation.isValid(productJTable.getPrice());
		    fieldName = "Discount";
                    Validation.isValid(productJTable.getDiscount());
		    fieldName = "Manufacturer";
                    Validation.isValid(productJTable.getManname());
		}catch(IllegalArgumentException error){
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
                    check = false;
                }
                if (check != true) {
                    return;
                }
                //Submit to Database
                Manufacturer mfact = null;
                mfact = new Manufacturer(productJTable.getManname().getSelectedItem().toString(), null, null, null, null);
                Product product = new Product(productJTable.getProductname(),
                        new BigDecimal(productJTable.getPrice()), new BigDecimal(productJTable.getDiscount()), mfact);

                product.setProductID(productJTable.getProdid());
            System.out.println("debug Product: " + product);
                try {
                    // update
                    SQLServiceClass.update(product);
                    // re-search
                    // set search keywords
                    Manufacturer conditionMfact = null;
                    if (!Utils.isEmpty(txtProductManuSearch.getText())) {
                        conditionMfact = new Manufacturer(txtProductManuSearch.getText(), null, null, null, null);
                    }
                    Product condition = new Product(txtProductSearch.getText(), null, null, conditionMfact);
                    System.out.println("debug Product: " + condition);
                    // search
                    buildProductTableInfoPanel(condition);
                } catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_EDIT);
                }
            }
	}
    }//end EditProductButtonHandler

    /**
     * Handler for delete button on Product tab.
     */
    private class DeleteProductButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            // if not selected, do nothing
            if (!productJTable.isSelected()) {
                return;
            }

            if(Utils.showConfirmDialog("delete this product")) {
            //Submit to Database
            
                Product product = new Product(null, null, null, null);
                product.setProductID(productJTable.getProdid());

                try {
                    // delete
                    SQLServiceClass.delete(product);
                    // re-search
                    // set search keywords
                    Manufacturer conditionMfact = null;
                    if (!Utils.isEmpty(txtProductManuSearch.getText())) {
                        conditionMfact = new Manufacturer(txtProductManuSearch.getText(), null, null, null, null);
                    }
                    Product condition = new Product(txtProductSearch.getText(), null, null, conditionMfact);
                    System.out.println("debug Product: " + condition);
                    // search
                    buildProductTableInfoPanel(condition);
                } catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_DELETE);
                }
            }
	}
    }//end DeleteProductButtonHandler

    /**
     * Search product.
     */
    private void buildProductTableInfoPanel(Object condition) {
        productJTable.buildTableInfoPanel(condition);
        // state manufacturer name combo box
        JComboBox cboManName = productJTable.getManname();
        SQLServiceClass.mfactList(cboManName);
        cboManName.setSelectedIndex(-1);
    }

    /**
     * Handler for search button on Product tab.
     */
    private class SearchProductButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            
            // set search keywords
            Manufacturer conditionMfact = null;
            if (!Utils.isEmpty(txtProductManuSearch.getText())) {
                conditionMfact = new Manufacturer(txtProductManuSearch.getText(), null, null, null, null);
            }
            Product condition = new Product(txtProductSearch.getText(), null, null, conditionMfact);
            System.out.println("debug Product: " + condition);
            // search
            buildProductTableInfoPanel(condition);
	}
    }//end SearchProductButtonHandler
//add e n d takaaki

    private class CreateSalesButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            if(Utils.showConfirmDialog("create a new sale")) {
	    //Validate Inputs
            String fieldName = "";
                try{
                    fieldName = "Commission";
		    Validation.isValid(txtSalesCommission.getText());
                    
                    //Create product
                    Product p1 = new Product(cboSalesProductName.getSelectedItem().toString());
                    //Create custoemr
                    Customer c1= new Customer(cboSalesCustomer.getSelectedItem().toString());
                    //Create employee
                    SalaryEmployee e1 = new SalaryEmployee(cboSalesEmployee.getSelectedItem().toString());
                    //Create sale
                    Sale s1 = new Sale(p1, c1, e1, parseDecimal(txtSalesCommission.getText()));
		       
                 
		    //Submit to Database
                    SQLServiceClass.insertSales(s1.getProduct().getName(),s1.getCustomer().getFirstName(),s1.getEmployee().getFirstName(), s1.getComm());

                }catch(IllegalArgumentException  error){
                    JOptionPane.showMessageDialog(null, "All fields must be provided");
                } catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_CREATE);
                }
            }
	}
    }//end CreateSalesButtonHandler    
    
// add start takaaki
    /**
     * Handler for edit button on Sales tab.
     */
    private class EditSalesButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            // if not selected, do nothing
            if (!saleJTable.isSelected()) {
                return;
            }

            if(Utils.showConfirmDialog("edit this sale")) {
		String fieldName = "";
		
                try{
		    //Validate Inputs
                    fieldName = "Product";
                    Validation.isValid(saleJTable.getProduct());
                    fieldName = "Customer";
                    Validation.isValid(saleJTable.getCustomer());
                    fieldName = "Employee";
                    Validation.isValid(saleJTable.getEmployee());
                    fieldName = "Commission";
                    BigDecimal comm = new BigDecimal(saleJTable.getComm());
                    Validation.isValid(comm);

		    //Create sale object
		    Sale sale = new Sale(new Product(saleJTable.getProduct().getSelectedItem().toString()),
                            new Customer(saleJTable.getCustomer().getSelectedItem().toString()),
                            new SalaryEmployee(saleJTable.getEmployee().getSelectedItem().toString()),
                            comm);
		    sale.setSaleID(saleJTable.getSaleid());
		    
		    try {
			// update
			SQLServiceClass.update(sale);
			// re-search
                        // set search keywords
                        Product product = new Product(txtSalesSearchProductName.getText());
                        Customer customer = new Customer(null);
                        Employee employee = new SalaryEmployee(txtSalesSearchLastName.getText());
                        Sale condition = new Sale(product, customer, employee, null);
                        // search
                        buildSalesTableInfoPanel(condition);
		    } catch (SQLException sqlex) {
                        Utils.logError(sqlex);
                        Utils.showError(Utils.ACTION_EDIT);
                    }
		} catch(IllegalArgumentException exIae){
                    Utils.logError(exIae);
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		}
	    }
	}
    }//end EditSalesButtonHandler

    /**
     * Handler for delete button on Sales tab.
     */
    private class DeleteSalesButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            // if not selected, do nothing
            if (!saleJTable.isSelected()) {
                return;
            }

            if(Utils.showConfirmDialog("delete this sale")) {
		//Submit to Database
            
                Sale sale = new Sale(null, null, null, null);
                sale.setSaleID(saleJTable.getSaleid());

                try {
                    // delete
                    SQLServiceClass.delete(sale);
                    // re-search
                    // set search keywords
                    Product product = new Product(txtSalesSearchProductName.getText());
                    Customer customer = new Customer(null);
                    Employee employee = new SalaryEmployee(txtSalesSearchLastName.getText());
                    Sale condition = new Sale(product, customer, employee, null);
                    // search
                    buildSalesTableInfoPanel(condition);
                } catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_DELETE);
                }
            }
	}
    }//end DeleteSalesButtonHandler

    /**
     * Search sales.
     */
    private void buildSalesTableInfoPanel(Object condition) {
        saleJTable.buildTableInfoPanel(condition);
        // state product combo boxs
        JComboBox cbo = saleJTable.getProduct();
        SQLServiceClass.productList(cbo);
        cbo.setSelectedIndex(-1);
        // state customer combo box
        cbo = saleJTable.getCustomer();
        SQLServiceClass.customerList(cbo);
        cbo.setSelectedIndex(-1);
        // state employee combo box
        cbo = saleJTable.getEmployee();
        SQLServiceClass.employeeList(cbo);
        cbo.setSelectedIndex(-1);
    }

    /**
     * Handler for search button on Sales tab.
     */
    private class SearchSalesButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
            
            // set search keywords
            Product product = new Product(txtSalesSearchProductName.getText());
            Customer customer = new Customer(null);
            Employee employee = new SalaryEmployee(txtSalesSearchLastName.getText());
            Sale condition = new Sale(product, customer, employee, null);
            // search
            buildSalesTableInfoPanel(condition);
	}
    }//end SearchSalesButtonHandler
//add e n d takaaki
    
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
		    //TODO get the sex
		    fieldName = "Sex";
		    Validation.isValidRdoBtns(pnlCustomerSex);
		    fieldName = "Address";
		    Validation.isValid(txtCustomerAddress.getText());
		    fieldName = "City";
		    Validation.isValid(txtCustomerCity.getText());
                    fieldName = "Province";
                    Validation.isValid(txtCustomerProvince.getText());
		    fieldName = "Phone number";
		    Validation.isValidPhoneNum(txtCustomerPhone.getText());
		    fieldName = "Year of birth";
		    Validation.isValid(txtCustomerYearOfBirth.getText());
		    fieldName = "Month of birth";
		    Validation.isValid(txtCustomerMonthOfBirth.getText());
		    fieldName = "Day of birth";
		    Validation.isValid(txtCustomerDayOfBirth.getText());
		    
		    //Select gender
                     String gender = "Not Selected";
                     if(rdoCustomerSexMale.isSelected()){
                         gender = "Male";
                     }
                     if(rdoCustomerSexFemale.isSelected()){
                         gender = "Female";
                     }
                    //create object
		    Customer c1 = new Customer(txtCustomerFirstName.getText(), txtCustomerLastName.getText(), gender, txtCustomerAddress.getText(), txtCustomerCity.getText(), txtCustomerProvince.getText(), txtCustomerPhone.getText(), parseInt(txtCustomerYearOfBirth.getText()), parseInt(txtCustomerMonthOfBirth.getText()), parseInt(txtCustomerDayOfBirth.getText()));
		    
		    //Submit to Database
                    SQLServiceClass.insertCustomer(c1.getFirstName(), c1.getLastName(), c1.getSex(), c1.getAddress(), c1.getCity(), c1.getProvince(), c1.getPhoneNum(), c1.getYear(), c1.getDateOfBirth().getTime().getMonth(), c1.getDateOfBirth().getTime().getDate());
		    
		    
		    
		    //Submit to Database
		    
		}catch(IllegalArgumentException exIae){
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		} catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_CREATE);
                }
            }
	}
    }//end createCustomerButtonHandler

    /**
     * Handler for edit button on Customer tab.
     */
    private class EditCustomerButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
//add start takaaki
            // if not selected, do nothing
            if (!customerJTable.isSelected()) {
                return;
            }

//add e n d takaaki
            if(Utils.showConfirmDialog("edit this customer")) {
		String fieldName = "";
		
                boolean check = true;
                try{
		    //Validate Inputs
		    fieldName = "First name";
                    Validation.isValidName(customerJTable.getFname(), false);
		    fieldName = "Last name";
                    Validation.isValidName(customerJTable.getLname(), false);
		    fieldName = "Gender";
                    Validation.isValid(customerJTable.getGender());
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
                    Validation.isValid(customerJTable.getDayOfBirthdate());
		    
		    //Create customer object
		    Customer customer = new Customer(customerJTable.getFname(), customerJTable.getLname(),
                        customerJTable.getGender(), customerJTable.getProvince(), customerJTable.getCity(),
                        customerJTable.getAddress(), customerJTable.getPhonenum(),
                        Integer.parseInt(customerJTable.getYearOfBirthdate()),
                        Integer.parseInt(customerJTable.getMonthOfBirthdate()),
                        Integer.parseInt(customerJTable.getDayOfBirthdate()));
		    customer.setCustomerID(customerJTable.getCusid());
		    
                    if (check != true) {
                        return;
                    }
		    try {
			System.out.println("debug customer: " + customer);
			// update
			SQLServiceClass.update(customer);
			// research
			// set search keywords
			Customer condition = new Customer(null, txtCustomerSearchLastName.getText(),
				null, null, null, null, txtCustomerSearchPhoneNumber.getText(),
				0, 0, 0);
			System.out.println("debug customer: " + condition);
			// search
			customerJTable.buildTableInfoPanel(condition);
		    } catch (SQLException sqlex) {
                        Utils.logError(sqlex);
                        Utils.showError(Utils.ACTION_EDIT);
                    }
		} catch(IllegalArgumentException exIae){
                    Utils.logError(exIae);
		    JOptionPane.showMessageDialog(null, fieldName + " is invalid");
		}
	    }
	}
    }//end EditCustomerButtonHandler

    /**
     * Handler for delete button on Customer tab.
     */
    private class DeleteCustomerButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
//add start takaaki
            // if not selected, do nothing
            if (!customerJTable.isSelected()) {
                return;
            }

//add e n d takaaki
            if(Utils.showConfirmDialog("delete this customer")) {
		//Submit to Database
            
                Customer customer = new Customer(null, null,
                        null, null, null, null, null,
                        0, 0, 0);
                customer.setCustomerID(customerJTable.getCusid());
		System.out.println("debug customer: " + customer);

                try {
                    // delete
                    SQLServiceClass.delete(customer);
                    // re-search
                    // set search keywords
                    Customer condition = new Customer(null, txtCustomerSearchLastName.getText(),
                            null, null, null, null, txtCustomerSearchPhoneNumber.getText(),
                            0, 0, 0);
                    System.out.println("debug customer: " + condition);
                    // search
                    customerJTable.buildTableInfoPanel(condition);
                } catch (SQLException sqlex) {
                    Utils.logError(sqlex);
                    Utils.showError(Utils.ACTION_DELETE);
                }
            }
	}
    }//end DeleteCustomerButtonHandler

    /**
     * Handler for search button on Customer tab.
     */
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
