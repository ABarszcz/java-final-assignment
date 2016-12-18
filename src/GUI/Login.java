/*
 * Assignment 2 - Part 2
 */
package GUI;

import Common.SQLServiceClass;
import Common.Utils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
/**
 * Creates the Graphical User Interface for the Login window.
 * @author Noah Michael
 */

public class Login extends JFrame implements ActionListener {
    //hard-coded login credentials (Takaaki)
    final String DB_USER = "gc200292749";
    final String DB_PASS = "5KD5F^QT";
    //hard-coded login credentials (Kayla)
    //final String DB_USER = "gc200321034";
    //final String DB_PASS = "KqxeZ*gk";
    
    final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/" + DB_USER;
    
    // Submit button
    private final JButton btnSubmit;
    
    // GUI components
    private final JPanel fieldsPanel, outerPanel, btnPanel;
    private final JLabel lblUsername,lblPassword;
    private final JTextField  txtUsername,txtPassword;
    
    // Constructor
    public Login() {
        // Initializing the login components
        lblUsername = new JLabel("Username");
        txtUsername = new JTextField(15);
        txtUsername.setText(DB_USER);
        lblPassword = new JLabel("Password");
        txtPassword = new JPasswordField(15);
        txtPassword.setText(DB_PASS);
  
        // Initializing the submit button
        btnSubmit = new JButton("Submit");
   
        // Designing the panels
        fieldsPanel = new JPanel(new GridLayout(2,1));
	outerPanel = new JPanel(new BorderLayout());
	btnPanel = new JPanel();
        fieldsPanel.add(lblUsername);
        fieldsPanel.add(txtUsername);
        fieldsPanel.add(lblPassword);
        fieldsPanel.add(txtPassword);
	btnPanel.add(btnSubmit);
        outerPanel.add(fieldsPanel,BorderLayout.CENTER);
        outerPanel.add(btnPanel, BorderLayout.SOUTH);
	
	this.add(outerPanel);
	
        btnSubmit.addActionListener(this);
	
	setupGUI();
    }
    
    private void setupGUI() {
        this.setTitle("Login");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(300,120);
	//create the view
	this.setVisible(true);
    }
    
    /*
    Button handler for the submit button
    Validates if the user has correctly entered the credentials for login
    */
    public void actionPerformed(ActionEvent ae) {
        String value1=txtUsername.getText();
        String value2=txtPassword.getText();
	
        if (value1.equals(DB_USER) && value2.equals(DB_PASS)) {
	    //log in to the db
	    SQLServiceClass.setConnectingInfo(DB_URL, DB_USER, DB_PASS);
	    //this user is hard-coded as an admin
	    Utils.setIsAdmin(true);
	    //or you can test using them as not an admin
	    //Utils.setIsAdmin(false);
	    //load the main gui
            new MainGUI();
	    //set visible to false
	    this.setVisible(false);
        } //would do an elseif (other user/pass combination) then Utils.setIsAdmin(false)
        else {
            Utils.debug("Enter a valid username and password");
            JOptionPane.showMessageDialog(this,"Incorrect login or password",
            "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
/*
class LoginForm {
    public static void main(String arg[])
    {
        try 
        {
            Login frame=new Login();
            frame.setSize(400,100);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }       
    }

}
*/
