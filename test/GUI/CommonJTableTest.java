/*
 * Assignment 2 - Part 2
 */
package GUI;

import GUI.JTable.CommonJTable;
import Common.ConnectionHelper;
import Common.SQLServiceClass;
import Customer.Customer;
import GUI.JTable.CustomerJTable;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * The class tests CommonJTable class.
 * 
 * @author Takaaki Goto
 */
public class CommonJTableTest extends JFrame {
    /* properties */
    /** Search Button */
    private final Button btnSearch;
    /** Text field for search */
    private final JTextField txtTableName;
    /** JTable + Scroll, and detail information */
    private final CommonJTable commonJTable;
    /** Exit button */
    private final Button btnExit;

    /* consts */
    /** max try count to connect to a database */
    private final int MAX_TRY = 3;
    /** OK/Cancel */
    private final String[] buttonOptions = new String[]{"OK","Cansel"};
    /** index of Cancel button */
    private final int BTN_RESULT_CANCEL = 1;
    
    /**
     * Constructor.
     */
    public CommonJTableTest() {
        super("Java Database Tables");
        super.setLayout(new BorderLayout());

        /*** login panel ***/
        JPanel loginPanel = new JPanel();

        JLabel lblUserName = new JLabel("User Name:");
        JLabel lblPassword = new JLabel("Password:");
        JLabel lblDatabaseName = new JLabel("Database Name:");
        JTextField txtUserName = new JTextField(10);
//        txtUserName.setText(SQLServiceClass.DEFAULT_USER);
        JPasswordField txtPassword = new JPasswordField(10);
//        txtPassword.setText(SQLServiceClass.DEFAULT_PASS);
        JTextField txtDatabaseName = new JTextField(10);
        // set default values
//        txtDatabaseName.setText(SQLServiceClass.DB_NAME);

        loginPanel.add(lblDatabaseName);
        loginPanel.add(txtDatabaseName);
        loginPanel.add(lblUserName);
        loginPanel.add(txtUserName);
        loginPanel.add(lblPassword);
        loginPanel.add(txtPassword);

        boolean dbCredCheck = false;
        int loopCounter = 0;

        // db connection
        do {
            int btnResult = JOptionPane.showOptionDialog(null, loginPanel, "Enter Login Credentials",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, buttonOptions, buttonOptions[0]);
            System.out.println("btnResult:" + btnResult);
            if (btnResult == BTN_RESULT_CANCEL) {
                confirmExit();
                continue;
            }

            try {
                String dbName = txtDatabaseName.getText();
//                String url = "jdbc:" +  SQLServiceClass.DBMS + "://" +  SQLServiceClass.HOST + ":" + SQLServiceClass.PORT + "/" + dbName;
                String user = txtUserName.getText();
                String pass = new String(txtPassword.getPassword());

                /** DEBUG */
//                System.out.println("URL:     " + url);
                System.out.println("DB NAME: " + dbName);
                System.out.println("USER:    " + user);
                System.out.println("PASS:    " + pass);

                // connect
//                ConnectionHelper.initialize(dbName, user, pass);
//                new ConnectionHelper(dbName, user, pass);
                SQLServiceClass.connect(false);

                dbCredCheck = true;
            } catch(SQLException ex) {
                ex.printStackTrace();

                JOptionPane.showMessageDialog(null, "Your credentials wrer invalid. Please try again.");

                dbCredCheck = false;
                loopCounter++;

                // if try count reaches to max, exit program
                if (loopCounter >= MAX_TRY) {
                    dbCredCheck = true;

                    JOptionPane.showMessageDialog(null, "Failed");
                    System.exit(0);
                }
            }
        } while(!dbCredCheck);

        /*** Search Panel ***/
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Table Name"));
        this.txtTableName = new JTextField("races");
        searchPanel.add(this.txtTableName);
        this.btnSearch = new Button("Search");
        this.btnSearch.addActionListener(new SearchButtonHandler());
        searchPanel.add(this.btnSearch);

        /*** jTable panel ***/
        // TODO
        this.commonJTable = new CustomerJTable();

        /*** button panel ***/
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        // button
        this.btnExit = new Button("Exit");
        // add button handler
        this.btnExit.addActionListener(new ExitButtonHandler());
        btnPanel.add(this.btnExit);

        /*** add components to the frame ***/
        super.add(searchPanel, BorderLayout.NORTH);
        super.add(btnPanel, BorderLayout.SOUTH);
        super.add(this.commonJTable, BorderLayout.CENTER);

        // pack
        super.pack();
        super.setResizable(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    /*
     * Exit Button Handler.
     * 
     * @see ActionListener
     */
    private class ExitButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            // JOptionPane to inform that a button was clicked
            // Look at the ActionEvent class to find a method that
            // will determine the source of the acion event
            if (event.getSource() == btnExit) {
                confirmExit();
            }
        }
    } // end ExitButtonHandler

    /**
     * Search button handler.
     */
    private class SearchButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // JOptionPane to inform that a button was clicked
            // Look at the ActionEvent class to find a method that
            // will determine the source of the acion event
            if (event.getSource() == btnSearch) {
                // re-build the jtable
//                commonJTable.buildTableInfoPanel(txtTableName.getText());
//                Customer condition = new Customer(firstName, lastName, sex, province, city, address, phoneNum, FRAMEBITS, SOMEBITS, ALLBITS);
                commonJTable.buildTableInfoPanel(null);
                pack();   // TODO
            }
        }
    }

    /**
     * Displays dialog weather exit or not.
     */
    private static void confirmExit() {
        if (JOptionPane.showConfirmDialog(null, "Are you sure to close?", "Exit", JOptionPane.OK_CANCEL_OPTION) == 0) {
            System.exit(0);
        }
    } // end confirmExit

    /**
     * Main method of the class.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        new CommonJTableTest();
    } // main
} // end class