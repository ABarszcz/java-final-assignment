/*
 * Assignment 2 - Part 2
 */
package GUI.JTable;

import Products.Product;
import Products.ProductHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * It provides functions to build JTable for Product tab.
 * 
 * @author Takaaki Goto
 */
public class ProductJTable extends CommonJTable {
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
    protected ResultSet selectByKey(Object condition) throws SQLException {
        return ProductHelper.selectByKey((Product) condition);
    }
//    /**
//     * 
//     * @param tbl 
//     */
//    @Override
//    protected void setDetailInfo(DefaultTableModel tbl) {
//        int colcount = tbl.getColumnCount();
//        
//        for (int i = 0; i < colcount; i++) {
//            super.getInfoDetailPanel().add(new JLabel(tbl.getColumnName(i)));
//            JTextField jTextField = new JTextField();
//            // set uneditable to PKs
//            if (i == 0) {
//                // CUSID
//                jTextField.setEditable(false);
//            }
//            super.getInfoDetailPanel().add(jTextField);
//        }
//    }
    /**
     * @return table name
     */
    @Override
    protected String getTableName() {
        return ProductHelper.TABLE_NAME;
    }

    /**
     * If set a key, it will be displayed as a combo box instead of text field on detail information.
     * 
     * @return 
     */
    @Override
    protected Map<Integer, Integer> getComboBoxColMap() {
        Map<Integer, Integer> keys = new HashMap<>();
        keys.put(4, 5);     // MANID & MANNAME
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
        keys.put(4, 4);     // MANID
        keys.put(5, 5);     // MANNAME
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
//
//    public String getManid() {
//        return super.getTextValue(4);
//    }

    public String getManname() {
        return super.getTextValue(4);
    }

}
