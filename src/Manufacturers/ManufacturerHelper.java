/*
 * Assignment 2 - Part 2
 */
package Manufacturers;

import Customer.*;
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
 * It provides CRUD functionalities of Manufacturer table.
 * 
 * @author Takaaki Goto
 */
public class ManufacturerHelper {

    /** table name */
    public static final String TABLE_NAME = "MANUFACTURER";

    /**
     * Search by keywords and return ResultSet.
     * 
     * @param condition
     * @return ResultSet
     */
    public static ResultSet selectByKey(Manufacturer condition) throws SQLException {
        System.out.println("debug" + condition);
        // create sql
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(TABLE_NAME);
        if (condition != null) {
            if (!Utils.isEmpty(condition.getName())
                    || !Utils.isEmpty(condition.getPhoneNum())) {
                sql.append(" WHERE ");
            }
            boolean isFirstCondition = true;
            if (!Utils.isEmpty(condition.getName())) {
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
            if (!Utils.isEmpty(condition.getName())) {
                stmt.setString(parameterIndex++, "%" + ConnectionHelper.escape(condition.getName(), meta.getSearchStringEscape()) + "%");
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
     * @param manufacturer
     * @throws SQLException 
     */
    public static void update(Manufacturer manufacturer) throws SQLException {
        try {
            // connect to db
            ConnectionHelper.connect();
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(TABLE_NAME);
            sql.append("  SET ");
            sql.append("    MFACTNAME = ?");
            sql.append("  , ADDRESS = ?");
            sql.append("  , CITY = ?");
            sql.append("  , PROVINCE = ?");
            sql.append("  , PHONENUM = ?");
            sql.append(" WHERE");
            sql.append("    MFACTID = ?");
            // create statement
            PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
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
     * Delete a manufacturer.
     * 
     * @param manufacturer
     * @throws SQLException 
     */
    public static void delete(Manufacturer manufacturer) throws SQLException {
        try {
            // connect to db
            ConnectionHelper.connect();
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM ");
            sql.append(TABLE_NAME);
            sql.append("  WHERE ");
            sql.append("    MFACTID = ?");
            // create statement
            PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, manufacturer.getMfactID());
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
