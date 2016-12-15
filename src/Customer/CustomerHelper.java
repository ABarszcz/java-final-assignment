/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import Employees.*;
import Common.ConnectionHelper;
import Employees.CommissionSalesEmployee;
import Employees.Employee;
import Employees.HourlyEmployee;
import Employees.SalaryEmployee;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Takaaki Goto
 */
public class CustomerHelper {

//    public CustomerHelper() {
////        super(dbName, user, pass);
//    }
    public static void create(Employee employee) throws SQLException {
//        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // connect to db
            ConnectionHelper.connect();
            String sql = "INSERT INTO EMPLOYEE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = ConnectionHelper.prepareStatement(sql);
            // bind parameters
            int index = 0;
            stmt.setString(index++, employee.getEmployeeID());
            stmt.setString(index++, employee.getFirstName());
            stmt.setString(index++, employee.getLastName());
            stmt.setString(index++, employee.getSex());
            stmt.setString(index++, employee.getProvince());
            stmt.setString(index++, employee.getCity());
            stmt.setString(index++, employee.getAddress());
            stmt.setString(index++, employee.getPhoneNum());
            stmt.setString(index++, employee.getDepartment());
            stmt.setString(index++, employee.getPosition());
            stmt.setString(index++, employee.getSocialSecurityNum());
            if (employee instanceof CommissionSalesEmployee) {
                // commission
                CommissionSalesEmployee commissionEmployee = (CommissionSalesEmployee) employee;
                stmt.setBigDecimal(index++, commissionEmployee.getCommissionRates());
            } else if (employee instanceof HourlyEmployee) {
                // hourly
                HourlyEmployee hourlyEmployee = (HourlyEmployee) employee;
                stmt.setDouble(index++, hourlyEmployee.getHoursWorked());
                stmt.setBigDecimal(index++, hourlyEmployee.getWage());
            } else {
                // salary
                SalaryEmployee salaryEmployee = (SalaryEmployee) employee;
                stmt.setString(index++, salaryEmployee.getSalaryType());
                stmt.setBigDecimal(index++, salaryEmployee.getSalaryAmount());
            }
            // execute
            stmt.execute();
            // commit
            ConnectionHelper.commit();
        } catch (SQLException sqle) {
            try {
                ConnectionHelper.rollback();
                sqle.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                ConnectionHelper.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerHelper.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }
    
    public static boolean update(Customer customer) throws SQLException {
        // connect
        ConnectionHelper.connect();
        System.out.println("debug 030");

        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE ");
        sql.append("CUSTOMER");
        sql.append("  SET ");
        sql.append("    FNAME = ?");
        sql.append("  , LNAME = ?");
        sql.append("  , GENDER = ?");
        sql.append("  , ADDRESS = ?");
        sql.append("  , CITY = ?");
        sql.append("  , PROVINCE = ?");
        sql.append("  , PHONENUM = ?");
        sql.append("  , BIRTHDATE = ?");
        sql.append("WHERE");
        sql.append("    CUSID = ?");
        //
        System.out.println("debug 040");
        PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
        int col = 1;
        stmt.setString(col++, customer.getFirstName());
        stmt.setString(col++, customer.getLastName());
        stmt.setString(col++, customer.getSex());
        stmt.setString(col++, customer.getAddress());
        stmt.setString(col++, customer.getCity());
        stmt.setString(col++, customer.getProvince());
        stmt.setString(col++, customer.getPhoneNum());
        stmt.setDate(col++, new Date(customer.getDateOfBirth().getTimeInMillis()));
        // condition
        stmt.setString(col++, customer.getCustomerID());
        System.out.println("debug 050");
        int result = stmt.executeUpdate();
        System.out.println("debug updated:" + result);
        ConnectionHelper.commit();
        ConnectionHelper.disconnect();
        return true;
    }

    public static void delete(Customer customer) throws SQLException {
        try {
            // connect
            ConnectionHelper.connect();
            System.out.println("debug 030");

            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM ");
            sql.append("CUSTOMER");
            sql.append("  WHERE ");
            sql.append("    CUSID = ?");
            //
            System.out.println("debug 040");
            PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
            int col = 1;
            // condition
            stmt.setString(col++, customer.getCustomerID());
            System.out.println("debug 050");
            boolean result = stmt.execute();
            System.out.println("debug updated:" + result);
            // commit
            ConnectionHelper.commit();
        } catch(SQLException sqle) {
            sqle.printStackTrace();
            // rollback
            ConnectionHelper.rollback();
        } finally {
            // disconnect
            ConnectionHelper.disconnect();
        }
        return;
    }
    /**
     * Search by last name and returns its result set.
     * @param condition
     * @return 
     */
    public static List<Employee> selectByLastName(Employee condition) {
        // connecgt
        // prepare statement
        // sql
        // execute
        // return
        return null;
    }
}
