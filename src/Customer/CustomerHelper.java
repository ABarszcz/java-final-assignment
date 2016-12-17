/*
 * Assignment 2 - Part 2
 */
package Customer;

import Common.ConnectionHelper;
import Common.Utils;
import Employees.CommissionSalesEmployee;
import Employees.Employee;
import Employees.HourlyEmployee;
import Employees.SalaryEmployee;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * It provides CRUD functionalities of Customer table.
 * 
 * @author Takaaki Goto
 */
public class CustomerHelper {

    /** table name */
    public static final String TABLE_NAME = "CUSTOMER";

    /**
     * Create a new Employee.
     * 
     * @param employee
     * @throws SQLException 
     */
    public static void create(Employee employee) throws SQLException {

        PreparedStatement stmt = null;
        try {
            // connect to db
            ConnectionHelper.connect();
            // create sql
            String sql = "INSERT INTO " + TABLE_NAME + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = ConnectionHelper.prepareStatement(sql);
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, employee.getEmployeeID());
            stmt.setString(parameterIndex++, employee.getFirstName());
            stmt.setString(parameterIndex++, employee.getLastName());
            stmt.setString(parameterIndex++, employee.getSex());
            stmt.setString(parameterIndex++, employee.getProvince());
            stmt.setString(parameterIndex++, employee.getCity());
            stmt.setString(parameterIndex++, employee.getAddress());
            stmt.setString(parameterIndex++, employee.getPhoneNum());
            stmt.setString(parameterIndex++, employee.getDepartment());
            stmt.setString(parameterIndex++, employee.getPosition());
            stmt.setString(parameterIndex++, employee.getSocialSecurityNum());
            if (employee instanceof CommissionSalesEmployee) {
                // commission
                CommissionSalesEmployee commissionEmployee = (CommissionSalesEmployee) employee;
                stmt.setBigDecimal(parameterIndex++, commissionEmployee.getCommissionRates());
            } else if (employee instanceof HourlyEmployee) {
                // hourly
                HourlyEmployee hourlyEmployee = (HourlyEmployee) employee;
                stmt.setDouble(parameterIndex++, hourlyEmployee.getHoursWorked());
                stmt.setBigDecimal(parameterIndex++, hourlyEmployee.getWage());
            } else {
                // salary
                SalaryEmployee salaryEmployee = (SalaryEmployee) employee;
                stmt.setString(parameterIndex++, salaryEmployee.getSalaryType());
                stmt.setBigDecimal(parameterIndex++, salaryEmployee.getSalaryAmount());
            }
            // execute
            stmt.execute();
            // commit
            ConnectionHelper.commit();
        } catch (SQLException sqle) {
            try {
                Utils.logError(sqle);
                // rollback
                ConnectionHelper.rollback();
            } catch (SQLException sqle2) {
                Utils.logError(sqle2);
            }
        } finally {
            try {
                // disconnect
                ConnectionHelper.disconnect();
            } catch (SQLException sqle) {
                Utils.logError(sqle);
            }
        }
    }
    /**
     * Search by keywords and return ResultSet.
     * 
     * @param condition
     * @return ResultSet
     */
    public static ResultSet selectByKey(Object obj) throws SQLException {
        System.out.println("debug" + obj);
        Customer condition = (Customer) obj;
        // create sql
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(TABLE_NAME);
        if (condition != null) {
            if (!Utils.isEmpty(condition.getLastName())
                    || !Utils.isEmpty(condition.getPhoneNum())) {
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
        PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
        if (condition != null) {
            // bind parameters
            DatabaseMetaData meta = ConnectionHelper.getMetaData();
            int parameterIndex = 1;
            if (!Utils.isEmpty(condition.getLastName())) {
                stmt.setString(parameterIndex++, "%" + ConnectionHelper.escape(condition.getLastName(), meta.getSearchStringEscape()) + "%");
            }
            if (!Utils.isEmpty(condition.getPhoneNum())) {
                stmt.setString(parameterIndex++, "%" + ConnectionHelper.escape(condition.getPhoneNum(), meta.getSearchStringEscape()) + "%");
            }
        }
        // execute
        return stmt.executeQuery();
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
            ConnectionHelper.connect();
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(TABLE_NAME);
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
            PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
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
            System.out.println("mon:" + customer.getDateOfBirth().get(Calendar.MONTH) + 1);
            System.out.println("yea:" + customer.getDateOfBirth().get(Calendar.YEAR));
            // condition
            stmt.setString(parameterIndex++, customer.getCustomerID());
            // execute
            stmt.executeUpdate();
            // commit
            ConnectionHelper.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            // rollback
            ConnectionHelper.rollback();
            throw sqle;
        } finally {
            // disconnect
            ConnectionHelper.disconnect();
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
            ConnectionHelper.connect();
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM ");
            sql.append(TABLE_NAME);
            sql.append("  WHERE ");
            sql.append("    CUSID = ?");
            // create statement
            PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, customer.getCustomerID());
            // execute
            stmt.execute();
            // commit
            ConnectionHelper.commit();
        } catch (SQLException sqle) {
            // rollback
            ConnectionHelper.rollback();
            throw sqle;
        } finally {
            // disconnect
            ConnectionHelper.disconnect();
        }
    }

} // end class
