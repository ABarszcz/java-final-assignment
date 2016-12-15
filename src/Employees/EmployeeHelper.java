/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employees;

import Common.ConnectionHelper;
import Employees.CommissionSalesEmployee;
import Employees.Employee;
import Employees.HourlyEmployee;
import Employees.SalaryEmployee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Takaaki Goto
 */
public class EmployeeHelper extends ConnectionHelper {

    public EmployeeHelper(String dbName, String user, String pass) {
        super(dbName, user, pass);
    }
    public static void create(Employee employee) throws SQLException {
//        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // connect to db
            connect();
            String sql = "INSERT INTO EMPLOYEE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = prepareStatement(sql);
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
            commit();
        } catch (SQLException sqle) {
            try {
                rollback();
                sqle.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeHelper.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
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
