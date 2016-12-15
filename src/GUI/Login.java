package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
/**
 * Creates the Graphical User Interface for the Login window.
 * @author Noah Michael
 */

public class Login extends JFrame implements ActionListener {
    
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
        lblPassword = new JLabel("Password");
        txtPassword = new JPasswordField(15);
  
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
        if (value1.equals("test") && value2.equals("test")) {
            MainGUI page = new MainGUI();
            page.setVisible(true);
            page.setLocationRelativeTo(null);
	    this.setVisible(false);
        }
        else {
            System.out.println("Enter a valid username and password");
            JOptionPane.showMessageDialog(this,"Incorrect login or password",
            "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}

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
 