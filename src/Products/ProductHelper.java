/*
 * Assignment 2 - Part 2
 */
package Products;

import Products.*;
import Common.ConnectionHelper;
import Common.Utils;
import Employees.CommissionSalesEmployee;
import Employees.Employee;
import Employees.HourlyEmployee;
import Employees.SalaryEmployee;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * It provides CRUD functionalities of Product table.
 * 
 * @author Takaaki Goto
 */
public class ProductHelper {

    /** table name */
    public static final String TABLE_NAME = "PRODUCT";

    /**
     * Create a new Employee.
     * 
     * @param employee
     * @throws SQLException 
     */
    public static void create(Product employee) throws SQLException {

        PreparedStatement stmt = null;
        try {
            // connect to db
            ConnectionHelper.connect();
            // create sql
            String sql = "INSERT INTO " + TABLE_NAME + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = ConnectionHelper.prepareStatement(sql);
            // bind parameters
            int parameterIndex = 1;
/*
                private String name, productID;
    private BigDecimal price, discount;
    private Manufacturer mfact;
            */
            stmt.setString(parameterIndex++, employee.getProductID());
            stmt.setString(parameterIndex++, employee.getName());
            stmt.setBigDecimal(parameterIndex++, employee.getPrice());
            stmt.setBigDecimal(parameterIndex++, employee.getDiscount());
//            stmt.setString(parameterIndex++, employee.getManID);
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
    public static ResultSet selectByKey(Product condition) throws SQLException {
        Product product = (Product) condition;
        System.out.println("debug" + product);

        // create sql
/*
SELECT PR.*, MA.MFACRNAME FROM PRODUCT PR
JOIN MANUFACTURER MA
ON   PR.MFACTNAME = MA.MFACRNAME
WHERE PR.PRODUCTNAME LIKE '%pro%' AND MA.MFACRNAME LIKE '%%';
        */
//        StringBuilder sql = new StringBuilder();
//        sql.append("SELECT PR.*, MA.MFACRNAME, MA.MFACRNAME FROM ");
//        sql.append(TABLE_NAME);
//        sql.append(" PR ");
//        sql.append("JOIN ");
//        sql.append("MANUFACTURER");
//        sql.append(" MA ");
//        sql.append("ON   PR.MFACTNAME = MA.MFACRNAME");
//        if (condition != null) {
//            String manufacturerName = (product.getMfact() == null? "": product.getMfact().getName());
//            if (!Utils.isEmpty(product.getName())
//                    || !Utils.isEmpty(manufacturerName)) {
//                sql.append(" WHERE ");
//            }
//            boolean isFirstCondition = true;
//            if (!Utils.isEmpty(product.getName())) {
//                sql.append("PR.PRODUCTNAME LIKE ?");
//                isFirstCondition = false;
//            }
//            if (!Utils.isEmpty(manufacturerName)) {
//                if (!isFirstCondition) {
//                    sql.append(" AND ");
//                }
//                sql.append("MA.MFACRNAME LIKE ?");
//            }
//        }
//        // create statement
//        PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
//        if (condition != null) {
//            String manufacturerName = (product.getMfact() == null? "": product.getMfact().getName());
//            // bind parameters
//            DatabaseMetaData meta = ConnectionHelper.getMetaData();
//            int parameterIndex = 1;
//            if (!Utils.isEmpty(product.getName())) {
//                stmt.setString(parameterIndex++, "%" + ConnectionHelper.escape(product.getName(), meta.getSearchStringEscape()) + "%");
//            }
//            if (!Utils.isEmpty(manufacturerName)) {
//                stmt.setString(parameterIndex++, "%" + ConnectionHelper.escape(manufacturerName, meta.getSearchStringEscape()) + "%");
//            }
//        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(TABLE_NAME);
        if (condition != null) {
            String manufacturerName = (product.getMfact() == null? "": product.getMfact().getName());
            if (!Utils.isEmpty(product.getName())
                    || !Utils.isEmpty(manufacturerName)) {
                sql.append(" WHERE ");
            }
            boolean isFirstCondition = true;
            if (!Utils.isEmpty(product.getName())) {
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
        PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
        if (condition != null) {
            String manufacturerName = (product.getMfact() == null? "": product.getMfact().getName());
            // bind parameters
            DatabaseMetaData meta = ConnectionHelper.getMetaData();
            int parameterIndex = 1;
            if (!Utils.isEmpty(product.getName())) {
                stmt.setString(parameterIndex++, "%" + ConnectionHelper.escape(product.getName(), meta.getSearchStringEscape()) + "%");
            }
            if (!Utils.isEmpty(manufacturerName)) {
                stmt.setString(parameterIndex++, "%" + ConnectionHelper.escape(manufacturerName, meta.getSearchStringEscape()) + "%");
            }
        }
        // execute
        return stmt.executeQuery();
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
            ConnectionHelper.connect();
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(TABLE_NAME);
            sql.append("  SET ");
            sql.append("    PRODUCTNAME = ?");
            sql.append("  , PRICE = ?");
            sql.append("  , DISCOUNT = ?");
            sql.append("  , MFACTNAME = ?");
            sql.append("WHERE");
            sql.append("    PRODID = ?");
            // create statement
            PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
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
     * Delete a product.
     * 
     * @param product
     * @throws SQLException 
     */
    public static void delete(Product product) throws SQLException {
        try {
            // connect to db
            ConnectionHelper.connect();
            // create sql
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM ");
            sql.append(TABLE_NAME);
            sql.append("  WHERE ");
            sql.append("    PRODID = ?");
            // create statement
            PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
            // bind parameters
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, product.getProductID());
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
