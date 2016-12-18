/*
 * Assignment 2 - Part 2
 */
package Common;

import Customer.Customer;
import Employees.BasePlusCommissionEmployee;
import Employees.CommissionSalesEmployee;
import Employees.Employee;
import Employees.HourlyEmployee;
import Employees.SalaryEmployee;
import Manufacturers.Manufacturer;
import Products.Product;
import Sale.Sale;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kayla Wiest
 */
public class SQLServiceClass {

    /* consts */
    /** connection information: URL */
    private static String _url;
    /** connection information: user */
    private static String _user;
    /** connection information: password */
    private static String _pass;
    /** table name: employee */
    public static final String TABLE_NAME_EMPLOYEE = "EMPLOYEE";
    /** table name: sales */
    public static final String TABLE_NAME_SALES = "SALES";
    /** table name: manufacturer */
    public static final String TABLE_NAME_MANUFACTURER = "MANUFACTURER";
    /** table name: product */
    public static final String TABLE_NAME_PRODUCT = "PRODUCT";
    /** table name: customer */
    public static final String TABLE_NAME_CUSTOMER = "CUSTOMER";

    /* properties */
    /** Connection object */
    private static Connection conn = null;

    /**
     * Set connecting information.
     * It should be called before connect().
     * 
     * @param url
     * @param user
     * @param pass 
     */
    public static void setConnectingInfo(String url, String user, String pass) {
        _url = url;
        _user = user;
        _pass = pass;
    }

    /**
     * Get DatabaseMetaData through Connection.
     *
     * @return DatabaseMetaData
     * @throws SQLException
     */
    public static DatabaseMetaData getMetaData() throws SQLException {
        return conn.getMetaData();
    }

    /**
     * Connect to the database.
     *
     * @throws SQLException
     */
    public static void connect() throws SQLException {
        conn = getConnection(true);
    }

    /**
     * Connect to the database.
     *
     * @param autoCommit
     * @throws SQLException
     */
    public static void connect(boolean autoCommit) throws SQLException {
        conn = getConnection(autoCommit);
    }

    /**
     * Get a connection.
     *
     * @return connection
     * @throws SQLException
     */
    private static Connection getConnection(boolean autoCommit) throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(_url, _user, _pass);
        }
        conn.setAutoCommit(autoCommit);
        return conn;
    }

    /**
     * Disconnect from the database.
     *
     * @throws SQLException
     */
    public static void disconnect() throws SQLException {
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

    /**
     * Create a statement and return it.
     *
     * @return
     * @throws SQLException
     */
    public static Statement createStatement() throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt;
    }

    /**
     * Create a prepared statement and return it.
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public static PreparedStatement prepareStatement(String sql) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt;
    }

    /**
     * Commit.
     *
     * @throws SQLException
     */
    public static void commit() throws SQLException {
        conn.commit();
    }

    /**
     * Rollback.
     *
     * @throws SQLException
     */
    public static void rollback() throws SQLException {
        conn.rollback();
    }

    //Create new salary employee in the database
    public static void insertSalaryEmployee(String fName, String lName, String gender, String address, String city, String province, String phoneNum, String department, String position, String sin, int year, int month, int date, BigDecimal salary){
        try{
        connect();

        String sql = "INSERT INTO `EMPLOYEE`(`FNAME`, `LNAME`, `GENDER`, `ADDRESS`, `CITY`, `PROVINCE`, `PHONENUM`, `DEPT`, `DEPTPOSITION`, `SSN`, `BIRTHYEAR`, `BIRTHMONTH`, `BIRTHDATE`, `SALARY`) VALUES ('"+fName+"','"+lName+"','"+gender+"','"+address+"','"+city+"','"+province+"','"+phoneNum+"', '"+department+"', '"+position+"', '"+sin+"', "+year+", "+month+", "+date+", "+salary+")";
        Statement stat = createStatement();            
        stat.executeUpdate(sql);
                
        System.out.println("Insert: " + fName + " " + lName + " " + gender + " " + address + " " + city + " " + province + " " + phoneNum + " " + department + " " + position + " " + sin + " " + year + " " + month + " " + date + " " + salary);
        JOptionPane.showMessageDialog(null,"Salary Employee was added to database"); 

         }catch(SQLException error){
          //error   
          System.out.println(error);
          //print to file
         }
    }//end insert
    
    //Create new hourly employee in the database
    public static void insertHourlyEmployee(String fName, String lName, String gender, String address, String city, String province, String phoneNum, String department, String position, String sin, int year, int month, int date, BigDecimal wage){
        try{
        connect();

        String sql = "INSERT INTO `EMPLOYEE`(`FNAME`, `LNAME`, `GENDER`, `ADDRESS`, `CITY`, `PROVINCE`, `PHONENUM`, `DEPT`, `DEPTPOSITION`, `SSN`, `BIRTHYEAR`, `BIRTHMONTH`, `BIRTHDATE`, `HOURLY`) VALUES ('"+fName+"','"+lName+"','"+gender+"','"+address+"','"+city+"','"+province+"','"+phoneNum+"', '"+department+"', '"+position+"', '"+sin+"', "+year+", "+month+", "+date+", "+wage+")";
        Statement stat = createStatement();            
        stat.executeUpdate(sql);
                
        System.out.println("Insert: " + fName + " " + lName + " " + gender + " " + address + " " + city + " " + province + " " + phoneNum + " " + department + " " + position + " " + sin + " " + year + " " + month + " " + date + " " + wage);
        JOptionPane.showMessageDialog(null,"Hourly Employee was added to database"); 

         }catch(SQLException error){
          //error   
          System.out.println(error);
          //print to file
         }
    }//end insert
    
        //Create new commission employee in the database
    public static void insertCommissionEmployee(String fName, String lName, String gender, String address, String city, String province, String phoneNum, String department, String position, String sin, int year, int month, int date, BigDecimal rate){
        try{
        connect();

        String sql = "INSERT INTO `EMPLOYEE`(`FNAME`, `LNAME`, `GENDER`, `ADDRESS`, `CITY`, `PROVINCE`, `PHONENUM`, `DEPT`, `DEPTPOSITION`, `SSN`, `BIRTHYEAR`, `BIRTHMONTH`, `BIRTHDATE`, `COMM`) VALUES ('"+fName+"','"+lName+"','"+gender+"','"+address+"','"+city+"','"+province+"','"+phoneNum+"', '"+department+"', '"+position+"', '"+sin+"', "+year+", "+month+", "+date+", "+rate+")";
        Statement stat = createStatement();            
        stat.executeUpdate(sql);
                
        System.out.println("Insert: " + fName + " " + lName + " " + gender + " " + address + " " + city + " " + province + " " + phoneNum + " " + department + " " + position + " " + sin + " " + year + " " + month + " " + date + " " + rate);
        JOptionPane.showMessageDialog(null,"Commission Employee was added to database"); 

         }catch(SQLException error){
          //error   
          System.out.println(error);
          //print to file
         }
    }//end insert
  
        //Create new base plus commission employee in the database
    public static void insertBasePlusCommissionEmployee(String fName, String lName, String gender, String address, String city, String province, String phoneNum, String department, String position, String sin, int year, int month, int date, BigDecimal rate, BigDecimal salary){
        try{
        connect();
        System.out.println("Insert: " + fName + " " + lName + " " + gender + " " + address + " " + city + " " + province + " " + phoneNum + " " + department + " " + position + " " + sin + " " + year + " " + month + " " + date + " " + rate + " " + salary);
        
        String sql = "INSERT INTO `EMPLOYEE`(`FNAME`, `LNAME`, `GENDER`, `ADDRESS`, `CITY`, `PROVINCE`, `PHONENUM`, `DEPT`, `DEPTPOSITION`, `SSN`, `BIRTHYEAR`, `BIRTHMONTH`, `BIRTHDATE`, `COMM`, `SALARY`) VALUES ('"+fName+"','"+lName+"','"+gender+"','"+address+"','"+city+"','"+province+"','"+phoneNum+"', '"+department+"', '"+position+"', '"+sin+"', "+year+", "+month+", "+date+", "+rate+", "+salary+")";
        Statement stat = createStatement();            
        stat.executeUpdate(sql);
                
        System.out.println("Insert: " + fName + " " + lName + " " + gender + " " + address + " " + city + " " + province + " " + phoneNum + " " + department + " " + position + " " + sin + " " + year + " " + month + " " + date + " " + rate + " " + salary);
        JOptionPane.showMessageDialog(null,"Commission Employee was added to database"); 

         }catch(SQLException error){
          //error   
          System.out.println(error);
          //print to file
         }
    }//end insert
    
    /**
     * Search employees by keywords and return DefaultTableModel.
     *
     * @param condition
     * @return DefaultTableModel
     * @throws SQLException
     */
    public static DefaultTableModel selectByKey(Employee condition) throws SQLException {
        System.out.println("debug" + condition);
        try {
            // connect to db
            SQLServiceClass.connect(false);
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(TABLE_NAME_EMPLOYEE);
            if (condition != null) {
                if (!Utils.isEmpty(condition.getLastName()) || !Utils.isEmpty(condition.getDepartment())) {
                    sql.append(" WHERE ");
                }
                boolean isFirstCondition = true;
                if (!Utils.isEmpty(condition.getLastName())) {
                    sql.append("LNAME LIKE ?");
                    isFirstCondition = false;
                }
                if (!Utils.isEmpty(condition.getDepartment())) {
                    if (!isFirstCondition) {
                        sql.append(" AND ");
                    }
                    sql.append("DEPT LIKE ?");
                }
            }
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            if (condition != null) {
                // bind parameters
                DatabaseMetaData meta = SQLServiceClass.getMetaData();
                int parameterIndex = 1;
                if (!Utils.isEmpty(condition.getLastName())) {
                    stmt.setString(parameterIndex++, "%" + Utils.escape(condition.getLastName(), meta.getSearchStringEscape()) + "%");
                }
                if (!Utils.isEmpty(condition.getDepartment())) {
                    stmt.setString(parameterIndex++, "%" + Utils.escape(condition.getDepartment(), meta.getSearchStringEscape()) + "%");
                }
            }
            // execute
            return buildTBModel(stmt.executeQuery());
        } finally {
            // disconnect
            disconnect();
        }
    }

    /**
     * Update an employee.
     *
     * @param employee
     * @throws SQLException
     */
    public static void update(Employee employee) throws SQLException {
        try {
            // connect to db
            SQLServiceClass.connect(false);
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(TABLE_NAME_EMPLOYEE);
            sql.append("  SET ");
            sql.append("    FNAME = ?");
            sql.append("  , LNAME = ?");
            sql.append("  , GENDER = ?");
            sql.append("  , ADDRESS = ?");
            sql.append("  , CITY = ?");
            sql.append("  , PROVINCE = ?");
            sql.append("  , PHONENUM = ?");
            sql.append("  , DEPT = ?");
            sql.append("  , DEPTPOSITION = ?");
            sql.append("  , SSN = ?");
            sql.append("  , BIRTHDATE = ?");
            sql.append("  , BIRTHMONTH = ?");
            sql.append("  , BIRTHYEAR = ?");
            if (employee instanceof BasePlusCommissionEmployee) {
                sql.append("  , SALARY = ?");
                sql.append("  , COMM = ?");
            } else if (employee instanceof SalaryEmployee) {
                sql.append("  , SALARY = ?");
            } else if (employee instanceof HourlyEmployee) {
                sql.append("  , HOURLY = ?");
            } else if (employee instanceof CommissionSalesEmployee) {
                if (((CommissionSalesEmployee) employee).getCommissionRates() != null) {
                    sql.append("  , SALARY = ?");
                }
            }
            sql.append(" WHERE");
            sql.append("    EMPID = ?");
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, employee.getFirstName());
            stmt.setString(parameterIndex++, employee.getLastName());
            stmt.setString(parameterIndex++, employee.getSex());
            stmt.setString(parameterIndex++, employee.getAddress());
            stmt.setString(parameterIndex++, employee.getCity());
            stmt.setString(parameterIndex++, employee.getProvince());
            stmt.setString(parameterIndex++, employee.getPhoneNum());
            stmt.setString(parameterIndex++, employee.getDepartment());
            stmt.setString(parameterIndex++, employee.getPosition());
            stmt.setString(parameterIndex++, employee.getSocialSecurityNum());
            stmt.setInt(parameterIndex++, employee.getDateOfBirth().get(Calendar.DAY_OF_MONTH));
            stmt.setInt(parameterIndex++, employee.getDateOfBirth().get(Calendar.MONTH) + 1);
            stmt.setInt(parameterIndex++, employee.getDateOfBirth().get(Calendar.YEAR));
            System.out.println("day:" + employee.getDateOfBirth().get(Calendar.DAY_OF_MONTH));
            System.out.println("mon:" + (employee.getDateOfBirth().get(Calendar.MONTH) + 1));
            System.out.println("yea:" + employee.getDateOfBirth().get(Calendar.YEAR));
            if (employee instanceof BasePlusCommissionEmployee) {
                stmt.setBigDecimal(parameterIndex++, ((BasePlusCommissionEmployee) employee).getBaseSalary());
                stmt.setBigDecimal(parameterIndex++, ((BasePlusCommissionEmployee) employee).getCommissionRates());
                sql.append("  , COMM = ?");
            } else if (employee instanceof SalaryEmployee) {
                stmt.setBigDecimal(parameterIndex++, ((SalaryEmployee) employee).getSalaryAmount());
            } else if (employee instanceof HourlyEmployee) {
                stmt.setBigDecimal(parameterIndex++, ((HourlyEmployee) employee).getWage());
            } else if (employee instanceof CommissionSalesEmployee) {
                if (((CommissionSalesEmployee) employee).getCommissionRates() != null) {
                    stmt.setBigDecimal(parameterIndex++, ((CommissionSalesEmployee) employee).getCommissionRates());
                }
            }
            // condition
            stmt.setString(parameterIndex++, employee.getEmployeeID());
            // execute
            stmt.executeUpdate();
            // commit
            SQLServiceClass.commit();
        } catch (SQLException sqle) {
            // rollback
            SQLServiceClass.rollback();
            throw sqle;
        } finally {
            // disconnect
            SQLServiceClass.disconnect();
        }
    }

    /**
     * Delete an employee.
     *
     * @param employee
     * @throws SQLException
     */
    public static void delete(Employee employee) throws SQLException {
        try {
            // connect to db
            SQLServiceClass.connect(false);
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM ");
            sql.append(TABLE_NAME_EMPLOYEE);
            sql.append("  WHERE ");
            sql.append("    EMPID = ?");
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, employee.getEmployeeID());
            // execute
            stmt.execute();
            // commit
            SQLServiceClass.commit();
        } catch (SQLException sqle) {
            // rollback
            SQLServiceClass.rollback();
            throw sqle;
        } finally {
            // disconnect
            SQLServiceClass.disconnect();
        }
    }
    
    //Create manufacture in database
    public static void insertMfact(String name, String address, String city, String province, String phoneNum){
        try{
        connect();

            String sql = "INSERT INTO `MANUFACTURER`(`MFACTNAME`, `ADDRESS`, `CITY`, `PROVINCE`, `PHONENUM`) VALUES ('"+name+"','"+address+"','"+city+"','"+province+"','"+phoneNum+"')";         
            Statement stat = createStatement();            
            stat.executeUpdate(sql);
                
            System.out.println("Insert: " + name + " " +  address + " " + city + " " + province + " " + phoneNum);
            JOptionPane.showMessageDialog(null,"Manufacturer was added to database"); 
            
         }catch(SQLException error){
          //error   
          System.out.println(error);
          //print to file
          
         }
          
    }//end insert
        
    /**
     * Search manufacturers by keywords and return DefaultTableModel.
     *
     * @param condition
     * @return DefaultTableModel
     * @throws SQLException
     */
    public static DefaultTableModel selectByKey(Manufacturer condition) throws SQLException {
        System.out.println("debug" + condition);
        try {
            // connect to db
            connect();
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(TABLE_NAME_MANUFACTURER);
            if (condition != null) {
                if (!Utils.isEmpty(condition.getName())) {
                    sql.append(" WHERE ");
                }
                boolean isFirstCondition = true;
                if (!Utils.isEmpty(condition.getName())) {
                    sql.append("MFACTNAME LIKE ?");
                    isFirstCondition = false;
                }
            }
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            if (condition != null) {
                // bind parameters
                DatabaseMetaData meta = SQLServiceClass.getMetaData();
                int parameterIndex = 1;
                if (!Utils.isEmpty(condition.getName())) {
                    stmt.setString(parameterIndex++, "%" + Utils.escape(condition.getName(), meta.getSearchStringEscape()) + "%");
                }
            }
            // execute
            return buildTBModel(stmt.executeQuery());
        } finally {
            // disconnect
            disconnect();
        }
    }

    /**
     * Update a manufacturer.
     *
     * @param manufacturer
     * @throws SQLException
     */
    public static void update(Manufacturer manufacturer) throws SQLException {
        try {
            // connect to db
            SQLServiceClass.connect(false);
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(TABLE_NAME_MANUFACTURER);
            sql.append("  SET ");
            sql.append("    MFACTNAME = ?");
            sql.append("  , ADDRESS = ?");
            sql.append("  , CITY = ?");
            sql.append("  , PROVINCE = ?");
            sql.append("  , PHONENUM = ?");
            sql.append(" WHERE");
            sql.append("    MFACTID = ?");
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, manufacturer.getName());
            stmt.setString(parameterIndex++, manufacturer.getAddress());
            stmt.setString(parameterIndex++, manufacturer.getCity());
            stmt.setString(parameterIndex++, manufacturer.getProvince());
            stmt.setString(parameterIndex++, manufacturer.getPhoneNum());
            // condition
            stmt.setString(parameterIndex++, manufacturer.getMfactID());
            // execute
            stmt.executeUpdate();
            // commit
            SQLServiceClass.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            // rollback
            SQLServiceClass.rollback();
            throw sqle;
        } finally {
            // disconnect
            SQLServiceClass.disconnect();
        }
    }

    /**
     * Delete a manufacturer.
     *
     * @param manufacturer
     * @throws SQLException
     */
    public static void delete(Manufacturer manufacturer) throws SQLException {
        try {
            // connect to db
            SQLServiceClass.connect(false);
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM ");
            sql.append(TABLE_NAME_MANUFACTURER);
            sql.append("  WHERE ");
            sql.append("    MFACTID = ?");
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, manufacturer.getMfactID());
            // execute
            stmt.execute();
            // commit
            SQLServiceClass.commit();
        } catch (SQLException sqle) {
            // rollback
            SQLServiceClass.rollback();
            throw sqle;
        } finally {
            // disconnect
            SQLServiceClass.disconnect();
        }
    }

    //Create new product in the database     
    public static void insertProduct(String name, BigDecimal price, BigDecimal discount, String mfact){    
        try{
        connect();

            String sql = "INSERT INTO `PRODUCT`(`PRODUCTNAME`, `PRICE`, `DISCOUNT`, `MFACTNAME`) VALUES ('"+name+"',"+price+","+discount+",'"+mfact+"')";            
            Statement stat = createStatement();            
            stat.executeUpdate(sql);                
           
            System.out.println("Insert: " + name  + " " + price + " " + discount + " " + mfact);
            JOptionPane.showMessageDialog(null,"Product was added to database"); 

         }catch(SQLException error){
          //error   
          System.out.println(error);
          //print to file

         }
          
    }//end insert
    
    /**
     * Search products by keywords and return DefaultTableModel.
     *
     * @param condition
     * @return DefaultTableModel
     * @throws SQLException
     */
    public static DefaultTableModel selectByKey(Product condition) throws SQLException {
        System.out.println("debug" + condition);
        try {
            // connect to db
            connect();
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(TABLE_NAME_PRODUCT);
            if (condition != null) {
                String manufacturerName = condition.getMfact() == null ? "" : condition.getMfact().getName();
                if (!Utils.isEmpty(condition.getName()) || !Utils.isEmpty(manufacturerName)) {
                    sql.append(" WHERE ");
                }
                boolean isFirstCondition = true;
                if (!Utils.isEmpty(condition.getName())) {
                    sql.append("PRODUCTNAME LIKE ?");
                    isFirstCondition = false;
                }
                if (!Utils.isEmpty(manufacturerName)) {
                    if (!isFirstCondition) {
                        sql.append(" AND ");
                    }
                    sql.append("MFACTNAME LIKE ?");
                }
            }
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            if (condition != null) {
                String manufacturerName = condition.getMfact() == null ? "" : condition.getMfact().getName();
                // bind parameters
                DatabaseMetaData meta = SQLServiceClass.getMetaData();
                int parameterIndex = 1;
                if (!Utils.isEmpty(condition.getName())) {
                    stmt.setString(parameterIndex++, "%" + Utils.escape(condition.getName(), meta.getSearchStringEscape()) + "%");
                }
                if (!Utils.isEmpty(manufacturerName)) {
                    stmt.setString(parameterIndex++, "%" + Utils.escape(manufacturerName, meta.getSearchStringEscape()) + "%");
                }
            }
            // execute
            return buildTBModel(stmt.executeQuery());
        } finally {
            // disconnect
            disconnect();
        }
    }

    /**
     * Update a product.
     *
     * @param product
     * @throws SQLException
     */
    public static void update(Product product) throws SQLException {
        try {
            // connect to db
            SQLServiceClass.connect(false);
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(TABLE_NAME_PRODUCT);
            sql.append("  SET ");
            sql.append("    PRODUCTNAME = ?");
            sql.append("  , PRICE = ?");
            sql.append("  , DISCOUNT = ?");
            sql.append("  , MFACTNAME = ?");
            sql.append("WHERE");
            sql.append("    PRODID = ?");
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, product.getName());
            stmt.setBigDecimal(parameterIndex++, product.getPrice());
            stmt.setBigDecimal(parameterIndex++, product.getDiscount());
            stmt.setString(parameterIndex++, product.getMfact().getName());
            // condition
            stmt.setString(parameterIndex++, product.getProductID());
            // execute
            stmt.executeUpdate();
            // commit
            SQLServiceClass.commit();
        } catch (SQLException sqle) {
            // rollback
            SQLServiceClass.rollback();
            throw sqle;
        } finally {
            // disconnect
            SQLServiceClass.disconnect();
        }
    }

    /**
     * Delete a product.
     *
     * @param product
     * @throws SQLException
     */
    public static void delete(Product product) throws SQLException {
        try {
            // connect to db
            SQLServiceClass.connect(false);
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM ");
            sql.append(TABLE_NAME_PRODUCT);
            sql.append("  WHERE ");
            sql.append("    PRODID = ?");
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, product.getProductID());
            // execute
            stmt.execute();
            // commit
            SQLServiceClass.commit();
        } catch (SQLException sqle) {
            // rollback
            SQLServiceClass.rollback();
            throw sqle;
        } finally {
            // disconnect
            SQLServiceClass.disconnect();
        }
    }

    //Create new customer in the database
    public static void insertCustomer(String fName, String lName, String gender, String address, String city, String province, String phoneNum, int year, int month, int date){
        try{
        connect();

        String sql = "INSERT INTO `CUSTOMER`(`FNAME`, `LNAME`, `GENDER`, `ADDRESS`, `CITY`, `PROVINCE`, `PHONENUM`, `BIRTHDATE`, `BIRTHMONTH`, `BIRTHYEAR`) VALUES ('"+fName+"','"+lName+"','"+gender+"','"+address+"','"+city+"','"+province+"','"+phoneNum+"', "+date+", "+month+", "+year+")";
        Statement stat = createStatement();            
        stat.executeUpdate(sql);
                
        System.out.println("Insert: " + fName + " " + lName + " " + gender + " " + address + " " + city + " " + province + " " + phoneNum + " " + year + " " + month + " " + date);
        JOptionPane.showMessageDialog(null,"Customer was added to database"); 

         }catch(SQLException error){
          //error   
          System.out.println(error);
          //print to file
         }
    }//end insert
    
    /**
     * Search customers by keywords and return DefaultTableModel.
     *
     * @param condition
     * @return DefaultTableModel
     * @throws SQLException
     */
    public static DefaultTableModel selectByKey(Customer condition) throws SQLException {
        System.out.println("debug" + condition);
        try {
            // connect to db
            connect();
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(TABLE_NAME_CUSTOMER);
            if (condition != null) {
                if (!Utils.isEmpty(condition.getLastName()) || !Utils.isEmpty(condition.getPhoneNum())) {
                    sql.append(" WHERE ");
                }
                boolean isFirstCondition = true;
                if (!Utils.isEmpty(condition.getLastName())) {
                    sql.append("LNAME LIKE ?");
                    isFirstCondition = false;
                }
                if (!Utils.isEmpty(condition.getPhoneNum())) {
                    if (!isFirstCondition) {
                        sql.append(" AND ");
                    }
                    sql.append("PHONENUM LIKE ?");
                }
            }
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            if (condition != null) {
                // bind parameters
                DatabaseMetaData meta = SQLServiceClass.getMetaData();
                int parameterIndex = 1;
                if (!Utils.isEmpty(condition.getLastName())) {
                    stmt.setString(parameterIndex++, "%" + Utils.escape(condition.getLastName(), meta.getSearchStringEscape()) + "%");
                }
                if (!Utils.isEmpty(condition.getPhoneNum())) {
                    stmt.setString(parameterIndex++, "%" + Utils.escape(condition.getPhoneNum(), meta.getSearchStringEscape()) + "%");
                }
            }
            // execute
            return buildTBModel(stmt.executeQuery());
        } finally {
            // disconnect
            disconnect();
        }
    }

    /**
     * Update a customer.
     *
     * @param customer
     * @throws SQLException
     */
    public static void update(Customer customer) throws SQLException {
        try {
            // connect to db
            SQLServiceClass.connect(false);
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(TABLE_NAME_CUSTOMER);
            sql.append("  SET ");
            sql.append("    FNAME = ?");
            sql.append("  , LNAME = ?");
            sql.append("  , GENDER = ?");
            sql.append("  , ADDRESS = ?");
            sql.append("  , CITY = ?");
            sql.append("  , PROVINCE = ?");
            sql.append("  , PHONENUM = ?");
            sql.append("  , BIRTHDATE = ?");
            sql.append("  , BIRTHMONTH = ?");
            sql.append("  , BIRTHYEAR = ?");
            sql.append(" WHERE");
            sql.append("    CUSID = ?");
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, customer.getFirstName());
            stmt.setString(parameterIndex++, customer.getLastName());
            stmt.setString(parameterIndex++, customer.getSex());
            stmt.setString(parameterIndex++, customer.getAddress());
            stmt.setString(parameterIndex++, customer.getCity());
            stmt.setString(parameterIndex++, customer.getProvince());
            stmt.setString(parameterIndex++, customer.getPhoneNum());
            stmt.setInt(parameterIndex++, customer.getDateOfBirth().get(Calendar.DAY_OF_MONTH));
            stmt.setInt(parameterIndex++, customer.getDateOfBirth().get(Calendar.MONTH) + 1);
            stmt.setInt(parameterIndex++, customer.getDateOfBirth().get(Calendar.YEAR));
            System.out.println("day:" + customer.getDateOfBirth().get(Calendar.DAY_OF_MONTH));
            System.out.println("mon:" + (customer.getDateOfBirth().get(Calendar.MONTH) + 1));
            System.out.println("yea:" + customer.getDateOfBirth().get(Calendar.YEAR));
            // condition
            stmt.setString(parameterIndex++, customer.getCustomerID());
            // execute
            stmt.executeUpdate();
            // commit
            SQLServiceClass.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            // rollback
            SQLServiceClass.rollback();
            throw sqle;
        } finally {
            // disconnect
            SQLServiceClass.disconnect();
        }
    }

    /**
     * Delete a customer.
     *
     * @param customer
     * @throws SQLException
     */
    public static void delete(Customer customer) throws SQLException {
        try {
            // connect to db
            SQLServiceClass.connect(false);
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM ");
            sql.append(TABLE_NAME_CUSTOMER);
            sql.append("  WHERE ");
            sql.append("    CUSID = ?");
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, customer.getCustomerID());
            // execute
            stmt.execute();
            // commit
            SQLServiceClass.commit();
        } catch (SQLException sqle) {
            // rollback
            SQLServiceClass.rollback();
            throw sqle;
        } finally {
            // disconnect
            SQLServiceClass.disconnect();
        }
    }
    
    //Create new customer in the database
    public static void insertSales(String product, String customer, String employee, BigDecimal comm){
        try{
        connect();

        String sql = "INSERT INTO `SALES`(`PRODUCT`, `CUSTOMER`, `EMPLOYEE`, `COMM`) VALUES('"+product+"','"+customer+"','"+employee+"',"+comm+")";
        Statement stat = createStatement();            
        stat.executeUpdate(sql);
                
        System.out.println("Insert: " + product + " " + customer + " " + employee + " " + comm);
        JOptionPane.showMessageDialog(null,"Sale was added to database"); 

         }catch(SQLException error){
          //error   
          System.out.println(error);
          //print to file
         }
    }//end insert

    /**
     * Search sales by keywords and return DefaultTableModel.
     *
     * @param condition
     * @return DefaultTableModel
     * @throws SQLException
     */
    public static DefaultTableModel selectByKey(Sale condition) throws SQLException {
        try {
            // connect to db
            connect();
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(TABLE_NAME_SALES);
            if (condition != null) {
                if (!Utils.isEmpty(condition.getProduct().getName()) || !Utils.isEmpty(condition.getEmployee().getFirstName())) {
                    sql.append(" WHERE ");
                }
                boolean isFirstCondition = true;
                if (!Utils.isEmpty(condition.getProduct().getName())) {
                    sql.append("PRODUCT LIKE ?");
                    isFirstCondition = false;
                }
                if (!Utils.isEmpty(condition.getEmployee().getFirstName())) {
                    if (!isFirstCondition) {
                        sql.append(" AND ");
                    }
                    sql.append("EMPLOYEE LIKE ?");
                }
            }
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            if (condition != null) {
                // bind parameters
                DatabaseMetaData meta = SQLServiceClass.getMetaData();
                int parameterIndex = 1;
                if (!Utils.isEmpty(condition.getProduct().getName())) {
                    stmt.setString(parameterIndex++, "%" + Utils.escape(condition.getProduct().getName(), meta.getSearchStringEscape()) + "%");
                }
                if (!Utils.isEmpty(condition.getEmployee().getFirstName())) {
                    stmt.setString(parameterIndex++, "%" + Utils.escape(condition.getEmployee().getFirstName(), meta.getSearchStringEscape()) + "%");
                }
            }
            // execute
            return buildTBModel(stmt.executeQuery());
        } finally {
            // disconnect
            disconnect();
        }
    }

    /**
     * Update a sale.
     *
     * @param sale
     * @throws SQLException
     */
    public static void update(Sale sale) throws SQLException {
        try {
            // connect to db
            SQLServiceClass.connect(false);
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(TABLE_NAME_SALES);
            sql.append("  SET ");
            sql.append("    PRODUCT = ?");
            sql.append("  , CUSTOMER = ?");
            sql.append("  , EMPLOYEE = ?");
            sql.append("  , COMM = ?");
            sql.append(" WHERE");
            sql.append("    SALEID = ?");
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, sale.getProduct().getName());
            stmt.setString(parameterIndex++, sale.getCustomer().getFirstName());
            stmt.setString(parameterIndex++, sale.getEmployee().getFirstName());
            stmt.setBigDecimal(parameterIndex++, sale.getComm());
            // condition
            stmt.setString(parameterIndex++, sale.getSalesID());
            // execute
            stmt.executeUpdate();
            // commit
            SQLServiceClass.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            // rollback
            SQLServiceClass.rollback();
            throw sqle;
        } finally {
            // disconnect
            SQLServiceClass.disconnect();
        }
    }

    /**
     * Delete a customer.
     *
     * @param customer
     * @throws SQLException
     */
    public static void delete(Sale customer) throws SQLException {
        try {
            // connect to db
            SQLServiceClass.connect(false);
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM ");
            sql.append(TABLE_NAME_SALES);
            sql.append("  WHERE ");
            sql.append("    SALEID = ?");
            // create statement
            PreparedStatement stmt = SQLServiceClass.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, customer.getSalesID());
            // execute
            stmt.execute();
            // commit
            SQLServiceClass.commit();
        } catch (SQLException sqle) {
            // rollback
            SQLServiceClass.rollback();
            throw sqle;
        } finally {
            // disconnect
            SQLServiceClass.disconnect();
        }
    }
    
//* Populate drop down lists *\\
    
    //Populate manucaturer list
    public static void mfactList(JComboBox list){
    
        final String QRY = "SELECT * FROM MANUFACTURER";

        //DB Connection
        try{
        connect();
        Statement stat = createStatement();
        ResultSet rs = stat.executeQuery(QRY);

        //fill in cbo
        while (rs.next())
               {
                   //finds types in result set
                   list.addItem(rs.getString("MFACTNAME"));
               }
        System.out.println("m ok");
        }
        catch(SQLException error){
            //error
            System.out.println(error.toString());
        }

    }
    
    //Populate product list
    public static void productList(JComboBox list){
    
        final String QRY = "SELECT * FROM PRODUCT";

        //DB Connection
        try{
        connect();
        Statement stat = createStatement();
        ResultSet rs = stat.executeQuery(QRY);

        //fill in cbo
        while (rs.next())
               {
                   //finds types in result set
                   list.addItem(rs.getString("PRODUCTNAME"));
               }
        System.out.println("p ok");
        }
        catch(SQLException error){
            //error
            System.out.println(error.toString());
        }

    }
    
    //Populate customer list
    public static void customerList(JComboBox list){
    
        final String QRY = "SELECT * FROM CUSTOMER";

        //DB Connection
        try{
        connect();
        Statement stat = createStatement();
        ResultSet rs = stat.executeQuery(QRY);

        //fill in cbo
        while (rs.next())
               {
                   //finds types in result set
                   list.addItem(rs.getString("FNAME") + " " + rs.getString("LNAME"));
               }
        System.out.println("c ok");
        }
        catch(SQLException error){
            //error
            System.out.println(error.toString());
        }

    }
    
    //Populate employee list
    public static void employeeList(JComboBox list){
    
        final String QRY = "SELECT * FROM EMPLOYEE";

        //DB Connection
        try{
        connect();
        Statement stat = createStatement();
        ResultSet rs = stat.executeQuery(QRY);

        //fill in cbo
        while (rs.next())
               {
                   //finds types in result set
                   list.addItem(rs.getString("FNAME") + " " + rs.getString("LNAME"));
               }
        System.out.println("e ok");

        }
        catch(SQLException error){
            //error
            System.out.println(error.toString());
        }

    }

    /**
     * It builds a table model.
     * 
     * @param result Result set
     * @return table model
     * @throws SQLException 
     */
    public static DefaultTableModel buildTBModel(ResultSet result) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        
        Vector<String> colNames = new Vector<String>();
        
        for (int column = 1; column <= metaData.getColumnCount(); column++) {
            colNames.add(metaData.getColumnLabel(column));
        }
        
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        
        while (result.next()) {
            
            Vector<Object> row = new Vector<Object>();
            for (String colName : colNames) {
                row.add(result.getObject(colName));
            }
            data.add(row);
        }
        System.out.println("colNames:[" + colNames + "]");
        System.out.println("data:[" + data + "]");
        return new DefaultTableModel(data, colNames);
    }

}//end SQLServiceClass
