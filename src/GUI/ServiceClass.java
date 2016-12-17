/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author muyx
 */
public class ServiceClass {
   
       
    private static final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200321034";
    private static final String username = "gc200321034";
    private static final String password = "KqxeZ*gk";
    
    //Connection object
    private static Connection conn = null;
     
     //statement object
    private static Statement stat = null;
     
     //result set
    private static ResultSet rs = null;
    
    public static void insertMfact(String name, String address, String city, String province, String phoneNum){
        System.out.print("here");
        try{
         conn = DriverManager.getConnection(DB_URL, username, password);

            String sql = "INSERT INTO `MANUFACTURER`(`MFACTNAME`, `ADDRESS`, `CITY`, `PROVINCE`, `PHONENUM`) VALUES ('"+name+"','"+address+"','"+city+"','"+province+"','"+phoneNum+"')";         
            stat = conn.createStatement();            
            stat.executeUpdate(sql);
                
            System.out.println("Insert: " + name + " " +  address + " " + city + " " + province + " " + phoneNum);
            JOptionPane.showMessageDialog(null,"Manufacturer was added to database"); 
            
         }catch(SQLException error){
          //error   
          System.out.println(error);
          //print to file
          
         }
          
    }//end insert
        
    //Create new product in the database     
    public static void insertProduct(String name, BigDecimal price, BigDecimal discount, String mfact){
        
        try{
         conn = DriverManager.getConnection(DB_URL, username, password);

            String sql = "INSERT INTO `PRODUCT`(`PRODUCTNAME`, `PRICE`, `DISCOUNT`, `MFACTNAME`) VALUES ('"+name+"',"+price+","+discount+",'"+mfact+"')";            
            stat = conn.createStatement();            
            stat.executeUpdate(sql);                
           
            System.out.println("Insert: " + name  + " " + price + " " + discount + " " + mfact);
            JOptionPane.showMessageDialog(null,"Product was added to database"); 

         }catch(SQLException error){
          //error   
          System.out.println(error);
          //print to file

         }
          
    }//end insert
    
    //Create new customer in the database
    public static void insertCustomer(String fName, String lName, String gender, String address, String city, String province, String phoneNum, int year, int month, int date){
        System.out.print("here");
        try{
         conn = DriverManager.getConnection(DB_URL, username, password);

        String sql = "INSERT INTO `CUSTOMER`(`FNAME`, `LNAME`, `GENDER`, `ADDRESS`, `CITY`, `PROVINCE`, `PHONENUM`, `BIRTHDATE`, `BIRTHMONTH`, `BIRTHYEAR`) VALUES ('"+fName+"','"+lName+"','"+gender+"','"+address+"','"+city+"','"+province+"','"+phoneNum+"', "+date+", "+month+", "+year+")";
        stat = conn.createStatement();            
        stat.executeUpdate(sql);
                
        System.out.println("Insert: " + fName + " " + lName + " " + gender + " " + address + " " + city + " " + province + " " + phoneNum + " " + year + " " + month + " " + date);
        JOptionPane.showMessageDialog(null,"Customer was added to database"); 

         }catch(SQLException error){
          //error   
          System.out.println(error);
          //print to file
         }
    }//end insert

    
//* Populate drop down lists *\\
    
    //Populate manucaturer list
    public static void mfactList(JComboBox list){
    
        final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200321034";
        final String QRY = "SELECT * FROM MANUFACTURER";

        //DB Connection
        try{
        conn = DriverManager.getConnection(DB_URL, "gc200321034", "KqxeZ*gk");
        stat = conn.createStatement();
        rs = stat.executeQuery(QRY);

        //fill in cbo
        while (rs.next())
               {
                   //finds types in result set
                   list.addItem(rs.getString("MFACTNAME"));
               }
        }
        catch(SQLException error){
            //error
            System.out.println(error.toString());
        }

    }
    
    
    
}//end ServiceClass

