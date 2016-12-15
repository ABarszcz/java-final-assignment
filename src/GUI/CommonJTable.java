/*
 * Assignment 2 - Part 2
 */
package GUI;

import Common.ConnectionHelper;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
public class CommonJTable extends JPanel {

    /* properties */
    /** Information panel */
    private JPanel infoDetailPanel;
    /** JTable */
    private JTable jTable;
    /** selected row of the list */
    private JTextField txtSelectedRow;

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

    /**
     * Constructor.
     * 
     * @param tablename 
     */
    public CommonJTable(String tablename) {
        super(new BorderLayout());
        this.buildTableInfoPanel(tablename);
    }

    /**
     * It build table info panel.
     * 
     * @param tableName 
     */
    public void buildTableInfoPanel(String tableName) {
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

            try {
                // create sql
                String sql = "SELECT * FROM " + tableName;
                System.out.println("TABLE:" + tableName);
                // execute
                rs = stmt.executeQuery(sql);
            } catch (SQLException sqlex) {
                // table not found
                sqlex.printStackTrace();

                JOptionPane.showMessageDialog(null, "The table name is invalid. Please try again.");

                return;
            }

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
        int colcount = tbl.getColumnCount();

        for (int i = 0; i < colcount; i++) {
            infoDetailPanel.add(new JLabel(tbl.getColumnName(i)));
            JTextField jTextField = new JTextField();
            jTextField.setEditable(false);
            infoDetailPanel.add(jTextField);
        }
        tbl.getDataVector();

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
            colNames.add(metaData.getColumnName(column));
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
