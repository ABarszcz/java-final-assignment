/*
 * Assignment 2 - Part 2
 */
package GUI.JTable;

import Common.Utils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * Common JTable class.
 * 
 * @author Takaaki Goto
 */
public abstract class CommonJTable extends JPanel {

    /* properties */
    /** Information panel */
    protected JPanel infoDetailPanel;
    /** JTable */
    protected JTable jTable;
    /** selected row of the list */
    private JTextField txtSelectedRow;

    /* consts */
    /** number of columns per a row */
    private static final int H_GAP_INFO_DETAILS = 3;
    private static final int MAX_ROW = 9;
    private static final int COLS = 4;
    private static final int WIDTH_OF_THIS = 600;
    private static final int HEIGHT_OF_TABLE = 100;
    private static final int HEIGHT_OF_INFO = 215;

    /**
     * Constructor.
     */
    public CommonJTable () {
        super(new BorderLayout());

        // create a blank JTable
        JPanel tablePanel = new JPanel();
        this.jTable = new JTable();
        this.jTable.getSelectionModel().addListSelectionListener(new TableListSelectionListener());
        this.jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane jscroll = new JScrollPane(this.jTable);
        jscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tablePanel.add(jscroll);
        // todo create information area?
        super.add(tablePanel, BorderLayout.CENTER);
    }

    /**
     * It build table info panel.
     * 
     * @param key search key 
     */
//    public void buildTableInfoPanel(String tableName, Object key) {
    public void buildTableInfoPanel(Object key) {
        JPanel tablePanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel infoHead = new JPanel();
        this.infoDetailPanel = new JPanel();

        JLabel lblItemSelected = new JLabel("Selected Item:");
        JLabel lblSelectedRow = new JLabel("Selected Row");
        this.txtSelectedRow = new JTextField(15);
        this.txtSelectedRow.setEditable(false);

        DefaultTableModel tbl = null;
        try {
            tbl = this.selectByKey(key);

        } catch (SQLException sqlex) {
            Utils.logError(sqlex);
        }

        // create a JTable
        this.jTable = new JTable(tbl);
        this.jTable.getSelectionModel().addListSelectionListener(new TableListSelectionListener());
        this.jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane jscroll = new JScrollPane(this.jTable);
        jscroll.setPreferredSize(new Dimension(WIDTH_OF_THIS, HEIGHT_OF_TABLE));
        jscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tablePanel.add(jscroll);
        tablePanel.setLayout(new GridLayout(1, 1));
        tablePanel.setBackground(Color.CYAN); // TODO

        // remove the old information
        super.removeAll();
        super.repaint();
        super.add(tablePanel, BorderLayout.CENTER);

        // set columns' name dynamically
        infoHead.add(lblItemSelected);
        infoHead.add(new JLabel(""));
        infoHead.add(lblSelectedRow);
        infoHead.add(this.txtSelectedRow);

        int colcount = tbl.getColumnCount();
        Map<Integer, Integer> ignores = this.getIgnoreColMap();
        Map<Integer, Integer> readOnlys = this.getReadOnlyColMap();
        Map<Integer, String[]> radioBtns = this.getRadioBtnColMap();
        Map<Integer, Integer> cboBoxes = this.getComboBoxColMap();
        for (int i = 0; i < colcount; i++) {
            if (ignores.get(i) != null) {
                continue;
            }
            infoDetailPanel.add(new JLabel(tbl.getColumnName(i)));
            // set input field
            if (radioBtns.get(i) != null) {
                // radio button
                JPanel pnlRdoBtn = new JPanel(new GridLayout(0, radioBtns.get(i).length));
                ButtonGroup btnGrp = new ButtonGroup();
                for (String value : radioBtns.get(i)) {
                    JRadioButton rdoBtn = new JRadioButton(value);
                    pnlRdoBtn.add(rdoBtn);
                    btnGrp.add(rdoBtn);
                }
                infoDetailPanel.add(pnlRdoBtn);
            } else if (cboBoxes.get(i) != null) {
                // combo box
                JComboBox cboBox = new JComboBox();
                infoDetailPanel.add(cboBox);
            } else {
                // textField
                JTextField jTextField = new JTextField();
                if (readOnlys.get(i) != null) {
                    jTextField.setEditable(false);
                }
                infoDetailPanel.add(jTextField);
            }
        }

        int row;
        int remainder;

        row = infoDetailPanel.getComponentCount() / COLS;
        remainder = infoDetailPanel.getComponentCount() % COLS;
        row = row + (remainder == 0? 0 : 1);
        if (remainder != 0) {
            for (int i = remainder; i < COLS; i++) {
                infoDetailPanel.add(new JLabel(""));
            }
        }
        // add blank rows until the max row
        for (int i = row; i < MAX_ROW; i++) {
            for (int j = 0; j < COLS; j++) {
                infoDetailPanel.add(new JLabel(""));
            }
        }
        infoPanel.setLayout(new BorderLayout());
        infoHead.setLayout(new GridLayout(1, COLS, H_GAP_INFO_DETAILS, 0));
        infoDetailPanel.setLayout(new GridLayout(MAX_ROW, COLS, H_GAP_INFO_DETAILS, 0));

        infoPanel.add(infoHead, BorderLayout.NORTH);
        infoPanel.add(infoDetailPanel, BorderLayout.CENTER);
        infoPanel.setPreferredSize(new Dimension(WIDTH_OF_THIS, HEIGHT_OF_INFO));

        super.add(infoPanel, BorderLayout.SOUTH);
        super.validate();
    }

    /**
     * @return true if a record is selected, false otherwise.
     */
    public boolean isSelected() {
        return !Utils.isEmpty(this.txtSelectedRow.getText());
    }

    /**
     * Search by keywords.
     * 
     * @param condition
     * @return ResultSet
     * @throws SQLException 
     */
    protected abstract DefaultTableModel selectByKey(Object condition) throws SQLException;
    /**
     * @return table name
     */
    protected abstract String getTableName();
    /**
     * If set a key, it will be displayed as a combo box instead of text field on detail information.
     * 
     * @return 
     */
    protected abstract Map<Integer, Integer> getComboBoxColMap();
    /**
     * If set a key, it won't be displayed on detail information.
     * 
     * @return 
     */
    protected abstract Map<Integer, Integer> getIgnoreColMap();
    /**
     * If set a key, it will be displayed as a readonly text field on detail information.
     * 
     * @return 
     */
    protected abstract Map<Integer, Integer> getReadOnlyColMap();
    /**
     * If set a key, it will be displayed as a group of radio buttons on detail information.
     * 
     * @return 
     */
    protected abstract Map<Integer, String[]> getRadioBtnColMap();

    /**
     * It returns the text value.
     * 
     * @param index
     * @return 
     */
    public String getTextValue(int index) {
        String textValue = null;
        try {
            textValue = ((JTextField) this.infoDetailPanel.getComponent((index * 2) + 1)).getText();
        } catch (Exception e) {
            textValue = null;
        }
        return textValue;
    }

    /**
     * It returns the text value.
     * 
     * @param index
     * @return 
     */
    public String getRdoBtnValue(int index) {
        String textValue = null;
        try {
            JPanel pnl = ((JPanel) this.infoDetailPanel.getComponent((index * 2) + 1));
            for (int i = 0; i < pnl.getComponentCount(); i++) {
                if (((JRadioButton) pnl.getComponent(i)).isSelected()) {
                    textValue = ((JRadioButton) pnl.getComponent(i)).getText();
                    break;
                }
            }
        } catch (Exception e) {
            textValue = null;
        }
        return textValue;
    }

    /**
     * It returns the combo box.
     * 
     * @param index
     * @return 
     */
    public JComboBox getCboBox(int index) {
        JComboBox comboBox = null;
        try {
            comboBox = ((JComboBox) this.infoDetailPanel.getComponent((index * 2) + 1));
        } catch (Exception e) {
            comboBox = null;
        }
        return comboBox;
    }

    /**
     * It returns the text value.
     * 
     * @param index
     * @return 
     */
    public String getCboBoxValue(int index) {
        String textValue = null;
        try {
            JComboBox cboBox = getCboBox(index);
            if (cboBox.getSelectedIndex() != -1) {
                textValue = cboBox.getSelectedItem().toString();
            }
        } catch (Exception e) {
            textValue = null;
        }
        return textValue;
    }

    /**
     * Listener which is called when the table selection is changed.
     */
    private class TableListSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if (jTable.getSelectedRow() > -1) {
                /* set value from selected */
                // selected row
                txtSelectedRow.setText(Integer.toString(jTable.getSelectedRow() + 1));
                displayDetailInformation();
            }
        }
    } // end TableListSelectionListener

    /**
     * Display detail information from JTable.
     * If needed customize, override it.
     */
    protected void displayDetailInformation() {
        final int SELECTED_ROW = jTable.getSelectedRow();
        final int LOOP_COUNT = jTable.getColumnCount();
        for (int i = 0; i < LOOP_COUNT; i++) {
            String value = jTable.getValueAt(SELECTED_ROW, i).toString();
            if (this.getRadioBtnColMap().get(i) != null) {
                JPanel jTextField = (JPanel) infoDetailPanel.getComponent((i * 2) + 1);
                for (int j = 0; j < jTextField.getComponentCount(); j++) {
                    JRadioButton rdoBtn = (JRadioButton) jTextField.getComponent(j);
                    rdoBtn.setSelected(rdoBtn.getText().equals(value));
                }
            } else if (this.getComboBoxColMap().get(i) != null) {
                JComboBox cbo = (JComboBox) infoDetailPanel.getComponent((i * 2) + 1);
                cbo.setSelectedItem(value);
            } else {
                JTextField jTextField = (JTextField) infoDetailPanel.getComponent((i * 2) + 1);
                jTextField.setText(value);
            }
        }
    } // end displayDetailInformation
} // end class
