package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Creates the Graphical User Interface for the application.
 * @author Anthony Barszcz
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
	    pnlEmpHCenter, pnlEmpCSCenter, pnlEmpBPCCenter, pnlEmpSSouth, pnlEmpHSouth,
	    pnlEmpCSSouth, pnlEmpBPCSouth, pnlEmpSLabelGrid, pnlEmpHLabelGrid,
	    pnlEmpCSLabelGrid, pnlEmpBPCLabelGrid, pnlEmpSFieldGrid, pnlEmpHFieldGrid,
	    pnlEmpCSFieldGrid, pnlEmpBPCFieldGrid, pnlEmpSSex, pnlEmpHSex, pnlEmpCSSex,
	    pnlEmpBPCSex, pnlEmpSearch, pnlEmpSearchNorth, pnlEmpSearchCenter;
    private final JLabel lblEmpSearch;
    private final JTextField txtEmpSearch;
    private final JTextArea txaEmpResults;
    private final JScrollPane spEmpResults;
    
    //text fields for Salary employee
    private final JTextField txtEmpSFirstName,
	    txtEmpSLastName, txtEmpSAddress, txtEmpSPhoneNum, txtEmpSDepartment,
	    txtEmpSPosition, txtEmpSSocialSecurityNum, txtEmpSYearOfBirth,
	    txtEmpSMonthOfBirth, txtEmpSDayOfBirth, txtEmpSSalaryType, txtEmpSSalaryAmount,
	    txtEmpSWage, txtEmpSCommissionRates, txtEmpSBaseSalary;
    
    //radio buttons and the radio button group for Salary employee
    JRadioButton rdoEmpSSexMale, rdoEmpSSexFemale;
    ButtonGroup grpEmpSSex;
    
    //text fields for Hourly employee
    private final JTextField txtEmpHFirstName,
	    txtEmpHLastName, txtEmpHAddress, txtEmpHPhoneNum, txtEmpHDepartment,
	    txtEmpHPosition, txtEmpHSocialSecurityNum, txtEmpHYearOfBirth,
	    txtEmpHMonthOfBirth, txtEmpHDayOfBirth, txtEmpHSalaryType, txtEmpHSalaryAmount,
	    txtEmpHWage, txtEmpHCommissionRates, txtEmpHBaseSalary;
    
    //create radio buttons and the radio button group for Hourly employee
    JRadioButton rdoEmpHSexMale, rdoEmpHSexFemale;
    ButtonGroup grpEmpHSex;
    
    //text fields for Commission-Sales employee
    private final JTextField txtEmpCSFirstName,
	    txtEmpCSLastName, txtEmpCSAddress, txtEmpCSPhoneNum, txtEmpCSDepartment,
	    txtEmpCSPosition, txtEmpCSSocialSecurityNum, txtEmpCSYearOfBirth,
	    txtEmpCSMonthOfBirth, txtEmpCSDayOfBirth, txtEmpCSSalaryType, txtEmpCSSalaryAmount,
	    txtEmpCSWage, txtEmpCSCommissionRates, txtEmpCSBaseSalary;
    
    //create radio buttons and the radio button group for Commission-Sales employee
    JRadioButton rdoEmpCSSexMale, rdoEmpCSSexFemale;
    ButtonGroup grpEmpCSSex;
    
    //text fields for Base Plus Commission employee
    private final JTextField txtEmpBPCFirstName,
	    txtEmpBPCLastName, txtEmpBPCAddress, txtEmpBPCPhoneNum, txtEmpBPCDepartment,
	    txtEmpBPCPosition, txtEmpBPCSocialSecurityNum, txtEmpBPCYearOfBirth,
	    txtEmpBPCMonthOfBirth, txtEmpBPCDayOfBirth, txtEmpBPCSalaryType, txtEmpBPCSalaryAmount,
	    txtEmpBPCWage, txtEmpBPCCommissionRates, txtEmpBPCBaseSalary;
    
    //create radio buttons and the radio button group for Base Plus Commission employee
    JRadioButton rdoEmpBPCSexMale, rdoEmpBPCSexFemale;
    ButtonGroup grpEmpBPCSex;
    
    //create "create new" buttons
    private final JButton btnEmpSNew, btnEmpHNew, btnEmpCSNew, btnEmpBPCNew;
    
    //</editor-fold>
    
    //<editor-fold desc="Manufacturer GUI Components">
    private final JTabbedPane pnlMfact;
    private final JPanel pnlMfactNew, pnlMfactNorth, pnlMfactCenter, pnlMfactSouth,
	    pnlMfactLabelGrid, pnlMfactFieldGrid, pnlMfactSearch, pnlMfactSearchNorth,
	    pnlMfactSearchCenter;
    private final JTextField txtMfactSearch, txtMfactName, txtMfactAddress,
	    txtMfactPhoneNum;
    private final JButton btnMfactNew;
    private final JLabel lblMfactSearch;
    private final JTextArea txaMfactResults;
    private final JScrollPane spMfactResults;
    
    //</editor-fold>
    
    //<editor-fold desc="Product GUI Components">
    private final JTabbedPane pnlProduct;
    private final JPanel pnlProductNew, pnlProductNorth, pnlProductCenter,
	    pnlProductSouth, pnlProductLabelGrid, pnlProductFieldGrid, pnlProductSearch,
	    pnlProductSearchNorth, pnlProductSearchCenter;
    private final JTextField txtProductSearch, txtProductName, txtProductPrice,
	    txtProductDiscount;
    private final JComboBox cboProductManufacturer;
    private final JButton btnProductNew;
    private final JLabel lblProductSearch;
    private final JTextArea txaProductResults;
    private final JScrollPane spProductResults;
    
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
	
	//initialize salary JTextFields
	this.txtEmpSFirstName = new JTextField();
	this.txtEmpSLastName = new JTextField();
	this.txtEmpSAddress = new JTextField();
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
	this.lblEmpSearch = new JLabel("Search");
	this.txtEmpSearch = new JTextField(15);
	this.txaEmpResults = new JTextArea(15, 30);
	this.spEmpResults = new JScrollPane(txaEmpResults);
	
	//initialize JButtons
	this.btnEmpSNew = new JButton("Create Salary Employee");
	this.btnEmpHNew = new JButton("Create Hourly Employee");
	this.btnEmpCSNew = new JButton("Create Commission-Sales Employee");
	this.btnEmpBPCNew = new JButton("Create Base Plus Commission Employee");
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
	
	//initialize JTextFields
	this.txtMfactAddress = new JTextField(15);
	this.txtMfactName = new JTextField(15);
	this.txtMfactPhoneNum = new JTextField(15);
	
	//initialize JButton
	this.btnMfactNew = new JButton("Create Manufacturer");
	
	//initialize search components
	this.lblMfactSearch = new JLabel("Search Manufacturers");
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
	
	//initialize JTextFields
	this.txtProductName = new JTextField(15);
	this.txtProductPrice = new JTextField(15);
	this.txtProductDiscount = new JTextField(15);
	
	//initialize JButton
	this.btnProductNew = new JButton("Create Product");
	
	//initialize JComboBox
	this.cboProductManufacturer = new JComboBox();
	
	//initialize Search components
	this.lblProductSearch = new JLabel("Search Products");
	this.txtProductSearch = new JTextField(15);
	this.txaProductResults = new JTextArea(15, 30);
	this.spProductResults = new JScrollPane(txaProductResults);
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
	this.setSize(365,545);
	//create the vew
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
	
	//design and populate the exit panel
	pnlExit.add(btnExit);
	
	//add the panels to the tabs
	pnlTabbedPane.addTab("Employees", pnlEmp);
	pnlTabbedPane.addTab("Manufacturers", pnlMfact);
	pnlTabbedPane.addTab("Products", pnlProduct);
	
	//populate the full screen of the application
	pnlFullScreen.setLayout(new BorderLayout());
	pnlFullScreen.add(pnlTabbedPane, BorderLayout.CENTER);
	pnlFullScreen.add(pnlGreeting, BorderLayout.NORTH);
	pnlFullScreen.add(pnlExit, BorderLayout.SOUTH);
	
	return pnlFullScreen;
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
    
    private void createEmployeeSearchTab() {
	//set as a border layout
	pnlEmpSearch.setLayout(new BorderLayout());
	
	//design the north panel
	pnlEmpSearchNorth.setLayout(new FlowLayout());
	pnlEmpSearchNorth.add(lblEmpSearch);
	pnlEmpSearchNorth.add(txtEmpSearch);
	
	//design the center panel
	pnlEmpSearchCenter.add(spEmpResults);
	
	pnlEmpSearch.add(pnlEmpSearchNorth, BorderLayout.NORTH);
	pnlEmpSearch.add(pnlEmpSearchCenter, BorderLayout.CENTER);
    }
    
    private void createSalaryEmployeeTab() {
	//set as a border layout
	pnlEmpS.setLayout(new BorderLayout());
	
	//create the label sub-panel of the center panel
	pnlEmpSLabelGrid.setLayout(new GridLayout(13,1));
	pnlEmpSLabelGrid.add(new JLabel("First Name"));
	pnlEmpSLabelGrid.add(new JLabel("Last Name"));
	pnlEmpSLabelGrid.add(new JLabel("Sex"));
	pnlEmpSLabelGrid.add(new JLabel("Address"));
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
	pnlEmpSFieldGrid.setLayout(new GridLayout(13,1));
	pnlEmpSFieldGrid.add(txtEmpSFirstName);
	pnlEmpSFieldGrid.add(txtEmpSLastName);
	pnlEmpSFieldGrid.add(pnlEmpSSex);
	pnlEmpSFieldGrid.add(txtEmpSAddress);
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
	pnlEmpHLabelGrid.setLayout(new GridLayout(13,1));
	pnlEmpHLabelGrid.add(new JLabel("First Name"));
	pnlEmpHLabelGrid.add(new JLabel("Last Name"));
	pnlEmpHLabelGrid.add(new JLabel("Sex"));
	pnlEmpHLabelGrid.add(new JLabel("Address"));
	pnlEmpHLabelGrid.add(new JLabel("Phone Number"));
	pnlEmpHLabelGrid.add(new JLabel("Department"));
	pnlEmpHLabelGrid.add(new JLabel("Position"));
	pnlEmpHLabelGrid.add(new JLabel("Social Security #"));
	pnlEmpHLabelGrid.add(new JLabel("Year Of Birth"));
	pnlEmpHLabelGrid.add(new JLabel("Month Of Birth"));
	pnlEmpHLabelGrid.add(new JLabel("Day Of Birth"));
	pnlEmpHLabelGrid.add(new JLabel("Wage"));
	
	//create the field sub-panel of the center panel
	pnlEmpHFieldGrid.setLayout(new GridLayout(13,1));
	pnlEmpHFieldGrid.add(txtEmpHFirstName);
	pnlEmpHFieldGrid.add(txtEmpHLastName);
	pnlEmpHFieldGrid.add(pnlEmpHSex);
	pnlEmpHFieldGrid.add(txtEmpHAddress);
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
	pnlEmpCSLabelGrid.setLayout(new GridLayout(13,1));
	pnlEmpCSLabelGrid.add(new JLabel("First Name"));
	pnlEmpCSLabelGrid.add(new JLabel("Last Name"));
	pnlEmpCSLabelGrid.add(new JLabel("Sex"));
	pnlEmpCSLabelGrid.add(new JLabel("Address"));
	pnlEmpCSLabelGrid.add(new JLabel("Phone Number"));
	pnlEmpCSLabelGrid.add(new JLabel("Department"));
	pnlEmpCSLabelGrid.add(new JLabel("Position"));
	pnlEmpCSLabelGrid.add(new JLabel("Social Security #"));
	pnlEmpCSLabelGrid.add(new JLabel("Year Of Birth"));
	pnlEmpCSLabelGrid.add(new JLabel("Month Of Birth"));
	pnlEmpCSLabelGrid.add(new JLabel("Day Of Birth"));
	pnlEmpCSLabelGrid.add(new JLabel("Commission Rates"));
	
	//create the field sub-panel of the center panel
	pnlEmpCSFieldGrid.setLayout(new GridLayout(13,1));
	pnlEmpCSFieldGrid.add(txtEmpCSFirstName);
	pnlEmpCSFieldGrid.add(txtEmpCSLastName);
	pnlEmpCSFieldGrid.add(pnlEmpCSSex);
	pnlEmpCSFieldGrid.add(txtEmpCSAddress);
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
	pnlEmpBPCLabelGrid.setLayout(new GridLayout(13,1));
	pnlEmpBPCLabelGrid.add(new JLabel("First Name"));
	pnlEmpBPCLabelGrid.add(new JLabel("Last Name"));
	pnlEmpBPCLabelGrid.add(new JLabel("Sex"));
	pnlEmpBPCLabelGrid.add(new JLabel("Address"));
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
	pnlEmpBPCFieldGrid.setLayout(new GridLayout(13,1));
	pnlEmpBPCFieldGrid.add(txtEmpBPCFirstName);
	pnlEmpBPCFieldGrid.add(txtEmpBPCLastName);
	pnlEmpBPCFieldGrid.add(pnlEmpBPCSex);
	pnlEmpBPCFieldGrid.add(txtEmpBPCAddress);
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
    }
    
    private void createManufacturerTab() {
	//set as a border layout
	pnlMfactNew.setLayout(new BorderLayout());
	
	//create the label sub-panel of the center panel
	pnlMfactLabelGrid.setLayout(new GridLayout(13,1));
	pnlMfactLabelGrid.add(new JLabel("Name"));
	pnlMfactLabelGrid.add(new JLabel("Address"));
	pnlMfactLabelGrid.add(new JLabel("Phone Number"));
	
	//create the field sub-panel of the center panel
	pnlMfactFieldGrid.setLayout(new GridLayout(13,1));
	pnlMfactFieldGrid.add(txtMfactName);
	pnlMfactFieldGrid.add(txtMfactAddress);
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
	
	//design the center panel
	pnlProductSearchCenter.add(spProductResults);
	
	pnlProductSearch.add(pnlProductSearchNorth, BorderLayout.NORTH);
	pnlProductSearch.add(pnlProductSearchCenter, BorderLayout.CENTER);
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
	
	//create and add the center panel
	pnlProductCenter.setLayout(new BorderLayout());
	pnlProductCenter.add(pnlProductLabelGrid, BorderLayout.WEST);
	pnlProductCenter.add(pnlProductFieldGrid, BorderLayout.EAST);
	pnlProductNew.add(pnlProductCenter, BorderLayout.CENTER);
	
	//create and add the south panel
	pnlProductSouth.add(btnProductNew);
	pnlProductNew.add(pnlProductSouth, BorderLayout.SOUTH);
    }
    
    //button handler for the exit button
    private class ExitButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    System.exit(0);
	}
    }
}
