/*
 * Assignment 2 - Part 2
 */
package Employees;

import Common.ConnectionHelper;
import Common.Utils;
import Employees.CommissionSalesEmployee;
import Employees.Employee;
import Employees.HourlyEmployee;
import Employees.SalaryEmployee;
import java.math.BigDecimal;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JTextField;

/**
 * It provides CRUD functionalities of Customer table.
 * 
 * @author Takaaki Goto
 */
public class EmployeeHelper {

    /** table name */
    public static final String TABLE_NAME = "EMPLOYEE";
    // Employee type
    public static final int EMP_TYPE_NONE = 0;
    public static final int EMP_TYPE_BASE_PLUS_COMMISSION = 1;
    public static final int EMP_TYPE_COMMISSION = 2;
    public static final int EMP_TYPE_HOURLY = 3;
    public static final int EMP_TYPE_SALARY = 4;

    /**
     * Search by keywords and return ResultSet.
     * 
     * @param condition
     * @return ResultSet
     */
    public static ResultSet selectByKey(Employee condition) throws SQLException {
        System.out.println("debug" + condition);
        // create sql
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(TABLE_NAME);
        if (condition != null) {
            if (!Utils.isEmpty(condition.getLastName())
                    || !Utils.isEmpty(condition.getDepartment())) {
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
        PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
        if (condition != null) {
            // bind parameters
            DatabaseMetaData meta = ConnectionHelper.getMetaData();
            int parameterIndex = 1;
            if (!Utils.isEmpty(condition.getLastName())) {
                stmt.setString(parameterIndex++, "%" + ConnectionHelper.escape(condition.getLastName(), meta.getSearchStringEscape()) + "%");
            }
            if (!Utils.isEmpty(condition.getDepartment())) {
                stmt.setString(parameterIndex++, "%" + ConnectionHelper.escape(condition.getDepartment(), meta.getSearchStringEscape()) + "%");
            }
        }
        // execute
        return stmt.executeQuery();
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
            PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
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

    /**
     * Delete an employee.
     * 
     * @param employee
     * @throws SQLException 
     */
    public static void delete(Employee employee) throws SQLException {
        try {
            // connect to db
            ConnectionHelper.connect();
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM ");
            sql.append(TABLE_NAME);
            sql.append("  WHERE ");
            sql.append("    EMPID = ?");
            // create statement
            PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, employee.getEmployeeID());
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

    /**
     * It returns the employee type.
     * 
     * @param hourly
     * @param salary
     * @param comm
     * @return 
     */
    public static int getEmployeeType(BigDecimal hourly, BigDecimal salary, BigDecimal comm) {

//        if (Utils.isZero(hourly) && !Utils.isZero(salary) && !Utils.isZero(comm)) {
//            // base plus commission
//            return EMP_TYPE_BASE_PLUS_COMMISSION;
//        } else if (Utils.isZero(hourly) && !Utils.isZero(salary) && Utils.isZero(comm)) {
//            // salary
//            return EMP_TYPE_SALARY;
//        } else if (!Utils.isZero(hourly) && Utils.isZero(salary) && Utils.isZero(comm)) {
//            // hourly
//            return EMP_TYPE_HOURLY;
//        } else if (Utils.isZero(hourly) && Utils.isZero(salary) && !Utils.isZero(comm)) {
//            // commission
//            return EMP_TYPE_COMMISSION;
//        } else {
//            // error data
//            return EMP_TYPE_NONE;
//        }
        if (hourly == null && salary != null && comm != null) {
            // base plus commission
            return EMP_TYPE_BASE_PLUS_COMMISSION;
        } else if (hourly == null &&  salary != null && comm == null) {
            // salary
            return EMP_TYPE_SALARY;
        } else if (hourly != null &&  salary == null && comm == null) {
            // hourly
            return EMP_TYPE_HOURLY;
        } else if (hourly == null &&  salary == null &&  comm != null) {
            // commission
            return EMP_TYPE_COMMISSION;
        } else {
            // error data
            return EMP_TYPE_NONE;
        }
    }
} // end class
