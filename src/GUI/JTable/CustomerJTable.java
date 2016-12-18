/*
 * Assignment 2 - Part 2
 */
package GUI.JTable;

import Common.SQLServiceClass;
import Customer.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 * It provides functions to build JTable for Customer tab.
 * 
 * @author Takaaki Goto
 */
public class CustomerJTable extends CommonJTable {
    /**
     * Constructor.
     */
    public CustomerJTable() {
        super();
    }

    /**
     * Search by keywords.
     * 
     * @param condition
     * @return ResultSet
     * @throws SQLException 
     */
    @Override
    protected DefaultTableModel selectByKey(Object condition) throws SQLException {
        return SQLServiceClass.selectByKey((Customer) condition);
    }

    /**
     * @return table name
     */
    @Override
    protected String getTableName() {
        return SQLServiceClass.TABLE_NAME_CUSTOMER;
    }

    /**
     * If set a key, it will be displayed as a combo box instead of text field on detail information.
     * 
     * @return 
     */
    @Override
    protected Map<Integer, Integer> getComboBoxColMap() {
        Map<Integer, Integer> keys = new HashMap<>();
        return keys;
    }

    /**
     * If set a key, it won't be displayed on detail information.
     * 
     * @return 
     */
    @Override
    protected Map<Integer, Integer> getIgnoreColMap() {
        Map<Integer, Integer> keys = new HashMap<>();
        return keys;
    }

    /**
     * If set a key, it will be displayed as a readonly text field on detail information.
     * 
     * @return 
     */
    @Override
    protected Map<Integer, Integer> getReadOnlyColMap() {
        Map<Integer, Integer> keys = new HashMap<>();
        keys.put(0, 0);     // CUSID
        return keys;
    }

    /**
     * If set a key, it will be displayed as a group of radio buttons on detail information.
     * 
     * @return 
     */
    @Override
    protected Map<Integer, String[]> getRadioBtnColMap() {
        Map<Integer, String[]> keys = new HashMap<>();
        keys.put(3, new String[] {"Male", "Female"});
        return keys;
    }
/* getters for MainGUI */
    public String getCusid() {
        return super.getTextValue(0);
    }

    public String getFname() {
        return super.getTextValue(1);
    }

    public String getLname() {
        return super.getTextValue(2);
    }

    public String getGender() {
        return super.getRdoBtnValue(3);
    }

    public String getAddress() {
        return super.getTextValue(4);
    }

    public String getCity() {
        return super.getTextValue(5);
    }

    public String getProvince() {
        return super.getTextValue(6);
    }

    public String getPhonenum() {
        return super.getTextValue(7);
    }

    public String getDayOfBirthdate() {
        return super.getTextValue(8);
    }
    
    public String getMonthOfBirthdate() {
        return super.getTextValue(9);
    }

    public String getYearOfBirthdate() {
        return super.getTextValue(10);
    }
    
}
