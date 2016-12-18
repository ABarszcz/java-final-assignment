/*
 * Assignment 2 - Part 2
 */
package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author muyx
 */
public class LoginK {
    
    
      private static JTextField name;
    private static JTable table;

 public LoginK() {
 
    //!! = second week of class
    String userName = "", password ="";

     //CONNECTION
     
     //connection string
     final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200321034";
     
     //connection object
     Connection conn = null;
     
     //statement object
     Statement stat = null;
     
     //**LOGIN WINDOW **\\
     JPanel loginPanel = new JPanel();
     JLabel lblUsername = new JLabel("User Name: "), lblPassword = new JLabel("Password: ");
     JTextField txtUsername = new JTextField(10);
     JPasswordField txtPassword = new JPasswordField(10);
     
     loginPanel.add(lblUsername);
     loginPanel.add(txtUsername);
     txtUsername.setText("gc200321034");
     loginPanel.add(lblPassword);
     loginPanel.add(txtPassword);
     txtPassword.setText("KqxeZ*gk");
     
     boolean ok = false;//check db log in
     int loopCounter = 0;
     
     String[] btnOptions = new String[]{"Login"};
      
     do{
         JOptionPane.showOptionDialog(null, loginPanel, "Login", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, btnOptions, btnOptions[0]);
         
         try{
                                                //url, username, password //!!user = and password = 
            conn = DriverManager.getConnection(DB_URL, userName = txtUsername.getText(), password = new String(txtPassword.getPassword()));
            ok = true;
             
         }catch(SQLException error){
      
            //error.printStackTrace();
            
            if(loopCounter >=3){

            JOptionPane.showMessageDialog(null, "Failure to login 3 times" );            
            ok = false; 
            System.exit(0);
            }
            
            JOptionPane.showMessageDialog(null, "Invalid Input" );  
            
            ok = false; 
            loopCounter++;
         }
       
     }while(!ok);
     
     //if login
     if(conn != null){
             
             MainGUI gui = new MainGUI();
             gui.setVisible(true);           

         
     }//end if login
     
    }//end main



}


