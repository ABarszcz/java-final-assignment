/*
 * Assignment 2 - Part 2
 */
package GUI.JTable;

import Common.SQLServiceClass;
import Common.Utils;
import Employees.Employee;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * It provides functions to build JTable for Employee tab.
 * 
 * @author Takaaki Goto
 */
public class EmployeeJTable extends CommonJTable {
    /* consts */
    private static final int IDX_HOURLY = 14;
    private static final int IDX_SALARY = 15;
    private static final int IDX_COMM = 16;

    /**
     * Constructor.
     */
    public EmployeeJTable() {
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
        return SQLServiceClass.selectByKey((Employee) condition);
    }

    /**
     * @return table name
     */
    @Override
    protected String getTableName() {
        return SQLServiceClass.TABLE_NAME_EMPLOYEE;
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
        keys.put(0, 0);     // EMPID
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
        keys.put(3, new String[]{"Male", "Female"});
        return keys;
    }

    /**
     * If needed special settings to the detail information, override it. 
     */
    @Override
    protected void displayDetailInformation() {
        final int SELECTED_ROW = jTable.getSelectedRow();
        final int LOOP_COUNT = jTable.getColumnCount() - 3;

        for (int i = 0; i < LOOP_COUNT; i++) {
            String value = jTable.getValueAt(SELECTED_ROW, i).toString();
            if (this.getRadioBtnColMap().get(i) != null) {
                JPanel jTextField = (JPanel) infoDetailPanel.getComponent((i * 2) + 1);
                for (int j = 0; j < jTextField.getComponentCount(); j++) {
                    JRadioButton rdoBtn = (JRadioButton) jTextField.getComponent(j);
                    rdoBtn.setSelected(rdoBtn.getText().equals(value));
                }
            } else {
                JTextField jTextField = (JTextField) infoDetailPanel.getComponent((i * 2) + 1);
                jTextField.setText(value);
            }
        }

        BigDecimal hourly = null;
        if (jTable.getValueAt(SELECTED_ROW, IDX_HOURLY) != null) {
            hourly = new BigDecimal(jTable.getValueAt(SELECTED_ROW, IDX_HOURLY).toString());
        }
        BigDecimal salary = null;
        if (jTable.getValueAt(SELECTED_ROW, IDX_SALARY) != null) {
            salary = new BigDecimal(jTable.getValueAt(SELECTED_ROW, IDX_SALARY).toString());            
        }
        BigDecimal comm = null;
        if (jTable.getValueAt(SELECTED_ROW, IDX_COMM) != null) {
            comm = new BigDecimal(jTable.getValueAt(SELECTED_ROW, IDX_COMM).toString());
        }

        JTextField txtHourly = (JTextField) infoDetailPanel.getComponent((IDX_HOURLY * 2) + 1);
        JTextField txtSalary = (JTextField) infoDetailPanel.getComponent((IDX_SALARY * 2) + 1);
        JTextField txtComm = (JTextField) infoDetailPanel.getComponent((IDX_COMM * 2) + 1);

        final int EMPLOYEE_TYPE = Utils.judgeEmployeeType(hourly, salary, comm);
        switch (EMPLOYEE_TYPE) {
            case Utils.EMP_TYPE_BASE_PLUS_COMMISSION:
                // base plus commission
                txtHourly.setEnabled(false);
                txtSalary.setEnabled(true);
                txtComm.setEnabled(true);
                txtHourly.setText("");
                txtSalary.setText(jTable.getValueAt(SELECTED_ROW, IDX_SALARY).toString());
                txtComm.setText(jTable.getValueAt(SELECTED_ROW, IDX_COMM).toString());
                break;
            case Utils.EMP_TYPE_SALARY:
                // salary
                txtHourly.setEnabled(false);
                txtSalary.setEnabled(true);
                txtComm.setEnabled(false);
                txtHourly.setText("");
                txtSalary.setText(jTable.getValueAt(SELECTED_ROW, IDX_SALARY).toString());
                txtComm.setText("");
                break;
            case Utils.EMP_TYPE_HOURLY:
                // hourly
                txtHourly.setEnabled(true);
                txtSalary.setEnabled(false);
                txtComm.setEnabled(false);
                txtHourly.setText(jTable.getValueAt(SELECTED_ROW, IDX_HOURLY).toString());
                txtSalary.setText("");
                txtComm.setText("");
                break;
            case Utils.EMP_TYPE_COMMISSION:
                // commission
                txtHourly.setEnabled(false);
                txtSalary.setEnabled(false);
                txtComm.setEnabled(true);
                txtHourly.setText("");
                txtSalary.setText("");
                txtComm.setText(jTable.getValueAt(SELECTED_ROW, IDX_COMM).toString());
                break;
            default:
                // error data
                txtHourly.setEnabled(false);
                txtSalary.setEnabled(false);
                txtComm.setEnabled(false);
                break;
        }
    }
/* getters for MainGUI */
    public String getEmpid() {
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

    public String getDept() {
        return super.getTextValue(8);
    }

    public String getDeptposition() {
        return super.getTextValue(9);
    }

    public String getSsn() {
        return super.getTextValue(10);
    }

    public String getYearOfBirthdate() {
        return super.getTextValue(11);
    }

    public String getMonthOfBirthdate() {
        return super.getTextValue(12);
    }

    public String getDayOfBirthdate() {
        return super.getTextValue(13);
    }
    
    public String getHourly() {
        return super.getTextValue(14);
    }

    public String getSalary() {
        return super.getTextValue(15);
    }

    public String getComm() {
        return super.getTextValue(16);
    }
}
