/*
 * Assignment 2 - Part 2
 */
package GUI.JTable;

import Common.ConnectionHelper;
import Common.Utils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    private JPanel infoDetailPanel;

    public JPanel getInfoDetailPanel() {
        return infoDetailPanel;
    }
    /** JTable */
    private JTable jTable;
    /** selected row of the list */
    private JTextField txtSelectedRow;
//    /** table name */
//    private String tableName;

    /* consts */
    /** number of columns per a row */
    private static final int COLS = 4;

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

//    /**
//     * Constructor.
//     * 
//     * @param tablename 
//     */
////    public CommonJTable(String tablename) {
//    public CommonJTable() {
//        super(new BorderLayout());
////        this.tableName = tablename;
//        this.buildTableInfoPanel(null);
//    }

//    public void buildTableInfoPanel(String tableName) {
//        this.tableName = tableName;
//        this.buildTableInfoPanel(null);
//    }
//
    /**
     * It build table info panel.
     * 
     * @param tableName 
     */
//    public void buildTableInfoPanel(String tableName, Object key) {
    public void buildTableInfoPanel(Object key) {
        JPanel tablePanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel infoHead = new JPanel();
        this.infoDetailPanel = new JPanel();

        JLabel lblItemSelected = new JLabel("Selected Item:");
        JLabel lblBlank = new JLabel("");
        JLabel lblSelectedRow = new JLabel("Selected Row");
        this.txtSelectedRow = new JTextField(15);
        this.txtSelectedRow.setEditable(false);

        Statement stmt;
        ResultSet rs = null;
        DefaultTableModel tbl = null;
        try {
            // connect to db
            ConnectionHelper.connect();
            // create statement
            stmt = ConnectionHelper.createStatement();
System.out.println("debug key:" + key);
//            if (key == null) {
//                try {
//                    // select all
//                    // create sql
//                    String sql = "SELECT * FROM " + this.getTableName();
//                    System.out.println("TABLE:" + this.getTableName());
//                    // execute
//                    rs = stmt.executeQuery(sql);
//                } catch (SQLException sqlex) {
//                    // table not found
//                    sqlex.printStackTrace();
//
//                    JOptionPane.showMessageDialog(null, "The table name is invalid. Please try again.");
//
//                    return;
//                }
//            } else {
//                // select by key
//                rs = this.selectByKey(key);
//            }
            rs = this.selectByKey(key);

            tbl = this.buildTBModel(rs);

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } finally {
            try {
                ConnectionHelper.disconnect();
            } catch (SQLException sqlex) {
                // ignore disconnection error
            }
        }

        // create a JTable
        this.jTable = new JTable(tbl);
        this.jTable.getSelectionModel().addListSelectionListener(new TableListSelectionListener());
        this.jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane jscroll = new JScrollPane(this.jTable);
        jscroll.setPreferredSize(new Dimension(500, 100));
        jscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tablePanel.add(jscroll);

        // remove the old information
        super.removeAll();
        super.repaint();
        super.add(tablePanel, BorderLayout.CENTER);

        // set columns' name dynamically
        infoHead.add(lblItemSelected);
        infoHead.add(new JLabel(""));
        infoHead.add(lblSelectedRow);
        infoHead.add(this.txtSelectedRow);
        /*
        int colcount = tbl.getColumnCount();

        for (int i = 0; i < colcount; i++) {
            infoDetailPanel.add(new JLabel(tbl.getColumnName(i)));
            JTextField jTextField = new JTextField();
//            jTextField.setEditable(false);
            infoDetailPanel.add(jTextField);
        }
//        tbl.getDataVector();
        */
        setDetailInfo(tbl);

        int row;
        int remainder;

        row = infoDetailPanel.getComponentCount() / COLS;
        remainder = infoDetailPanel.getComponentCount() % COLS;
        row = row + (remainder == 0? 0 : 1);
        if (remainder != 0) {
            for (int i = remainder; i < COLS; i++) {
                infoDetailPanel.add(lblBlank);
            }
        }
        infoPanel.setLayout(new BorderLayout());
        infoHead.setLayout(new GridLayout(1, COLS, 10, 10));
        infoDetailPanel.setLayout(new GridLayout(row, COLS, 10, 10));

        infoPanel.add(infoHead, BorderLayout.NORTH);
        infoPanel.add(infoDetailPanel, BorderLayout.CENTER);

        super.add(infoPanel, BorderLayout.SOUTH);
        super.validate();
    }
//    protected ResultSet selectAll() throws SQLException {
//        // connect to db
//        ConnectionHelper.connect();
//        // create statement
//        Statement stmt = ConnectionHelper.createStatement();
//        // select all
//        // create sql
//        String sql = "SELECT * FROM " + this.getTableName();
//        System.out.println("TABLE:" + this.getTableName());
//        // execute
//        ResultSet rs = stmt.executeQuery(sql);
//        ConnectionHelper.disconnect();
//        return rs;
//    }
    /**
     * @return true if a record is selected, false otherwise.
     */
    public boolean isSelected() {
        return !Utils.isEmpty(this.txtSelectedRow.getText());
    }

    protected abstract ResultSet selectByKey(Object obj) throws SQLException;
    protected abstract String getTableName();
    protected abstract Map<Integer, Integer> getComboBoxColMap();
    protected abstract Map<Integer, Integer> getIgnoreColMap();
    protected abstract Map<Integer, Integer> getReadOnlyColMap();

    public String getTextValue(int index) {
        String textValue = null;
        try {
            textValue = ((JTextField) this.infoDetailPanel.getComponent((index * 2) + 1)).getText();
        } catch (Exception e) {
            textValue = null;
        }
        return textValue;
    }

    protected void setDetailInfo(DefaultTableModel tbl) {
        int colcount = tbl.getColumnCount();
        Map<Integer, Integer> ignores = this.getIgnoreColMap();
        Map<Integer, Integer> readOnlys = this.getReadOnlyColMap();
        for (int i = 0; i < colcount; i++) {
            if (ignores.get(i) != null) {
                continue;
            }
            infoDetailPanel.add(new JLabel(tbl.getColumnName(i)));
            JTextField jTextField = new JTextField();
            if (readOnlys.get(i) != null) {
                jTextField.setEditable(false);                
            }
            infoDetailPanel.add(jTextField);
        }
    }

    /**
     * It builds a table model.
     * 
     * @param result Result set
     * @return table model
     * @throws SQLException 
     */
    public DefaultTableModel buildTBModel(ResultSet result) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        
        Vector<String> colNames = new Vector<String>();
        
        for (int column = 1; column <= metaData.getColumnCount(); column++) {
//            colNames.add(metaData.getColumnName(column));
            colNames.add(metaData.getColumnLabel(column));
        }
        
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        
        while (result.next()) {
            
            Vector<Object> row = new Vector<Object>();
            for (String colName : colNames) {
                row.add(result.getObject(colName));
            }
            data.add(row);
        }
        System.out.println("colNames:[" + colNames + "]");
        System.out.println("data:[" + data + "]");
        return new DefaultTableModel(data, colNames);
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
                int loopCount = jTable.getColumnCount();
                for (int i = 0; i < loopCount; i++) {
                    JTextField jTextField = (JTextField) infoDetailPanel.getComponent((i * 2) + 1);
                    jTextField.setText(jTable.getValueAt(jTable.getSelectedRow(), i).toString());
                }
            }
        }
    } // end TableListSelectionListener

}
