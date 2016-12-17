/*
 * Assignment 2 - Part 2
 */
package Sale;

import Manufacturers.*;
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
 * It provides CRUD functionalities of Sale table.
 * 
 * @author Takaaki Goto
 */
public class SaleHelper {

    /** table name */
    public static final String TABLE_NAME = "SALES";

    /**
     * Search by keywords and return ResultSet.
     * 
     * @param obj
     * @return ResultSet
     */
    public static ResultSet selectByKey(Object obj) throws SQLException {
        System.out.println("debug" + obj);
        Sale condition = (Sale) obj;
        // create sql
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(TABLE_NAME);
        if (condition != null) {
            if (!Utils.isEmpty(condition.getProduct().getName())
                    || !Utils.isEmpty(condition.getEmployee().getFirstName())) {
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
        PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
        if (condition != null) {
            // bind parameters
            DatabaseMetaData meta = ConnectionHelper.getMetaData();
            int parameterIndex = 1;
            if (!Utils.isEmpty(condition.getProduct().getName())) {
                stmt.setString(parameterIndex++, "%" + ConnectionHelper.escape(condition.getProduct().getName(), meta.getSearchStringEscape()) + "%");
            }
            if (!Utils.isEmpty(condition.getEmployee().getFirstName())) {
                stmt.setString(parameterIndex++, "%" + ConnectionHelper.escape(condition.getEmployee().getFirstName(), meta.getSearchStringEscape()) + "%");
            }
        }
        // execute
        return stmt.executeQuery();
    }

    /**
     * Update a customer.
     * 
     * @param sale
     * @throws SQLException 
     */
    public static void update(Sale sale) throws SQLException {
        try {
            // connect to db
            ConnectionHelper.connect();
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(TABLE_NAME);
            sql.append("  SET ");
            sql.append("    PRODUCT = ?");
            sql.append("  , CUSTOMER = ?");
            sql.append("  , EMPLOYEE = ?");
            sql.append("  , COMM = ?");
            sql.append(" WHERE");
            sql.append("    SALEID = ?");
            // create statement
            PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
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
    public static void delete(Sale customer) throws SQLException {
        try {
            // connect to db
            ConnectionHelper.connect();
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM ");
            sql.append(TABLE_NAME);
            sql.append("  WHERE ");
            sql.append("    SALEID = ?");
            // create statement
            PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, customer.getSalesID());
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
