/*
 * Assignment 2 - Part 2
 */
package GUI.JTable;

import Common.SQLServiceClass;
import Products.Product;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 * It provides functions to build JTable for Product tab.
 * 
 * @author Takaaki Goto
 */
public class ProductJTable extends CommonJTable {

    /** index Manufacturer name */
    public static final int IDX_MAN_NAME = 4;

    /**
     * Constructor.
     */
    public ProductJTable() {
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
        return SQLServiceClass.selectByKey((Product) condition);
    }

    /**
     * @return table name
     */
    @Override
    protected String getTableName() {
        return SQLServiceClass.TABLE_NAME_PRODUCT;
    }

    /**
     * If set a key, it will be displayed as a combo box instead of text field on detail information.
     * 
     * @return 
     */
    @Override
    protected Map<Integer, Integer> getComboBoxColMap() {
        Map<Integer, Integer> keys = new HashMap<>();
        keys.put(IDX_MAN_NAME, IDX_MAN_NAME);
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
        keys.put(0, 0);     // PRODID
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
    public String getProdid() {
        return super.getTextValue(0);
    }

    public String getProductname() {
        return super.getTextValue(1);
    }

    public String getPrice() {
        return super.getTextValue(2);
    }

    public String getDiscount() {
        return super.getTextValue(3);
    }

    public JComboBox getManname() {
        return super.getCboBox(4);
    }

}
