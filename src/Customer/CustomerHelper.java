/*
 * Assignment 2 - Part 2
 */
package Customer;

import Common.ConnectionHelper;
import Common.Utils;
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
     * Search by keywords and return ResultSet.
     * 
     * @param condition
     * @return ResultSet
     */
    public static ResultSet selectByKey(Customer condition) throws SQLException {
        System.out.println("debug" + condition);
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
            System.out.println("mon:" + (customer.getDateOfBirth().get(Calendar.MONTH) + 1));
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
