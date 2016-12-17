/*
 * Assignment 2 - Part 2
 */
package GUI.JTable;

import Manufacturers.Manufacturer;
import Manufacturers.ManufacturerHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * It provides functions to build JTable for Manufacturer tab.
 * 
 * @author Takaaki Goto
 */
public class ManufacturerJTable extends CommonJTable {
    /**
     * Constructor.
     */
    public ManufacturerJTable() {
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
    protected ResultSet selectByKey(Object condition) throws SQLException {
        return ManufacturerHelper.selectByKey((Manufacturer) condition);
    }

    /**
     * @return table name
     */
    @Override
    protected String getTableName() {
        return ManufacturerHelper.TABLE_NAME;
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
        keys.put(0, 0);     // MFACTID
        return keys;
    }
/* getters for MainGUI */
    public String getMfactid() {
        return super.getTextValue(0);
    }

    public String getMfactname() {
        return super.getTextValue(1);
    }

    public String getAddress() {
        return super.getTextValue(2);
    }

    public String getCity() {
        return super.getTextValue(3);
    }

    public String getProvince() {
        return super.getTextValue(4);
    }

    public String getPhonenum() {
        return super.getTextValue(5);
    }
    
}
