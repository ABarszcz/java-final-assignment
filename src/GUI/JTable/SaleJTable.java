/*
 * Assignment 2 - Part 2
 */
package GUI.JTable;

import Common.SQLServiceClass;
import Sale.Sale;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 * It provides functions to build JTable for Customer tab.
 * 
 * @author Takaaki Goto
 */
public class SaleJTable extends CommonJTable {

    /** index Manufacturer name */
    public static final int IDX_PRODUCT = 1;
    /** index Manufacturer name */
    public static final int IDX_CUSTOMER = 2;
    /** index Manufacturer name */
    public static final int IDX_EMPLOYEE = 3;

    /**
     * Constructor.
     */
    public SaleJTable() {
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
        return SQLServiceClass.selectByKey((Sale) condition);
    }

    /**
     * @return table name
     */
    @Override
    protected String getTableName() {
        return SQLServiceClass.TABLE_NAME_SALES;
    }

    /**
     * If set a key, it will be displayed as a combo box instead of text field on detail information.
     * 
     * @return 
     */
    @Override
    protected Map<Integer, Integer> getComboBoxColMap() {
        Map<Integer, Integer> keys = new HashMap<>();
        keys.put(IDX_PRODUCT, IDX_PRODUCT);
        keys.put(IDX_CUSTOMER, IDX_CUSTOMER);
        keys.put(IDX_EMPLOYEE, IDX_EMPLOYEE);
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
        keys.put(0, 0);     // SALEID
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
        return keys;
    }

/* getters for MainGUI */
    public String getSaleid() {
        return super.getTextValue(0);
    }

    public JComboBox getProduct() {
        return super.getCboBox(1);
    }

    public JComboBox getCustomer() {
        return super.getCboBox(2);
    }

    public JComboBox getEmployee() {
        return super.getCboBox(3);
    }

    public String getComm() {
        return super.getTextValue(4);
    }
    
}
