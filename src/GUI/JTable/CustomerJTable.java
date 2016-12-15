/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.JTable;

import Common.ConnectionHelper;
import Common.Utils;
import Customer.Customer;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Takaaki Goto
 */
public class CustomerJTable extends CommonJTable {

    public CustomerJTable() {
        super();
    }

    @Override
    protected ResultSet selectByKey(Object obj) throws SQLException {
        System.out.println("debug" + obj);
        Customer condition = (Customer) obj;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM ");
        sql.append(this.getTableName());
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
        PreparedStatement stmt = ConnectionHelper.prepareStatement(sql.toString());
        DatabaseMetaData meta = ConnectionHelper.getMetaData();
        int count = 1;
        if (!Utils.isEmpty(condition.getLastName())) {
            stmt.setString(count++, "%" + ConnectionHelper.escape(condition.getLastName(), meta.getSearchStringEscape()) + "%");
        }
        if (!Utils.isEmpty(condition.getPhoneNum())) {
            stmt.setString(count++, "%" + ConnectionHelper.escape(condition.getPhoneNum(), meta.getSearchStringEscape()) + "%");
        }
        
        return stmt.executeQuery();
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
    @Override
    protected String getTableName() {
        return "CUSTOMER";
    }

    @Override
    protected Map<Integer, Integer> getIgnoreColMap() {
        Map<Integer, Integer> keys = new HashMap<>();
//        keys.put(0, 0);     // CUSID
        return keys;
    }

    @Override
    protected Map<Integer, Integer> getReadOnlyColMap() {
        Map<Integer, Integer> keys = new HashMap<>();
        keys.put(0, 0);     // CUSID
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
        return super.getTextValue(3);
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

    public String getBirthdate() {
        return super.getTextValue(8);
    }
    //0123456789
    //2005-12-50
    public String getYearOfBirthdate() {
        return super.getTextValue(8).substring(0, 4);
    }    
    
    public String getMonthOfBirthdate() {
        return super.getTextValue(8).substring(5, 7);
    }
    
    public String getDayOfBirhdate() {
        return super.getTextValue(8).substring(8, 10);
    }
    
}
