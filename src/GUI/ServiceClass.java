/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;

/**
 *
 * @author muyx
 */
public class ServiceClass {
   
       
    private static final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200321034";
    private static final String username = "gc200321034";
    private static final String password = "KqxeZ*gk";
    
    private static Connection conn = null;
     
     //statement object
    private static Statement stat = null;
     
     //result set
    private static ResultSet rs = null;
    
    public static void insertMfact(String name, String address, String city, String province, String phoneNum){
        
        try{
         conn = DriverManager.getConnection(DB_URL, username, password);
            // Sets up the query.
            String sql = "INSERT INTO `MANUFACTURER`(`MFACTNAME`, `ADDRESS`, `CITY`, `PROVINCE`, `PHONENUM`) VALUES ('"+name+"','"+address+"','"+city+"','"+province+"','"+phoneNum+"')";
            
            stat = conn.createStatement();            
            
            
             // Executes the query. 
                stat.executeUpdate(sql);
                
                System.out.println("Insert: " + name + address+ city + province + phoneNum);
            
         }catch(SQLException error){
          //error   
          System.out.println(error);
          //print to file
          
          //
          
         }
          
         }//end insert
    
         public static void productList(JComboBox list){
              final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200321034";
     final String QRY = "SELECT * FROM MANUFACTURER";
      
       Connection conn = null;
     
     //statement object
     Statement stat = null;
     
     //result set
     ResultSet rs = null;
     
     //DB Connection
     try{
     conn = DriverManager.getConnection(DB_URL, "gc200321034", "KqxeZ*gk");
     stat = conn.createStatement();
     rs = stat.executeQuery(QRY);
     
     System.out.println("ok");
     
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
    
    
    
    
    
    public static void insertProduct(String name, double price, double discount, int mfact){
        
        try{
         conn = DriverManager.getConnection(DB_URL, username, password);
            // Sets up the query.
            String sql = "INSERT INTO `PRODUCT`(`PRODUCTNAME`, `PRICE`, `DISCOUNT`, `MANID`) VALUES ('"+name+"','"+price+"','"+discount+"','"+mfact+"')";
            
            stat = conn.createStatement();            
            
            
             // Executes the query. 
                stat.executeUpdate(sql);
                
                System.out.println("Insert: " + name + price+discount+mfact);
            
         }catch(SQLException error){
          //error   
          System.out.println(error);
          //print to file
          
          //
          
         }
          
         }//end insert

    }//end ServiceClass

