/*
 * Assignment 2 - Part 2
 */
package Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Takaaki Goto
 */
public class ConnectionHelper {
    /* consts */
    public static final String DBMS = "mysql";
    public static final String HOST = "sql.computerstudi.es";
    // @todo delete or change
    public static final String NAME = "gc200292749";
    public static final String PORT = "3306";
    // TODO delete or change
    public static final String DEFAULT_USER = "gc200292749";
    // TODO delete or change
    public static final String DEFAULT_PASS = "5KD5F^QT";
    public static final String URL = "jdbc:" + DBMS + "://" + HOST + ":" + PORT + "/";

    /* properties */
    private static String _dbName;
    private static String _user;
    private static String _pass;
    private static Connection conn = null;

    /**
     * Constructor.
     * @param dbName
     * @param user
     * @param pass
     */
    protected ConnectionHelper(String dbName, String user, String pass) {
        _dbName = dbName;
        _user = user;
        _pass = pass;
    }
    
    /**
     * Initialize the class.
     * It should be called before connect().
     * @param user
     * @param pass 
     */
    /* just use the constructor instead of creating a new method
    public static void initialize(String dbName, String user, String pass) {
        _dbName = dbName;
        _user = user;
        _pass = pass;
    }
    */
    
    /**
     * Get a connection.
     * 
     * @return connection
     * @throws SQLException 
     */
    private static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(URL + _dbName, _user, _pass);
            conn.setAutoCommit(false);
        }
        return conn;
    }
    
    /**
     * Connect to the database.
     * 
     * @throws SQLException 
     */
    public static void connect() throws SQLException {
        conn = getConnection();
    }
    /**
     * Disconnect from the database.
     * 
     * @throws SQLException 
     */
    public static void disconnect() throws SQLException {
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

    /**
     * Create a statement and return it.
     * 
     * @return
     * @throws SQLException 
     */
    public static Statement createStatement() throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt;
    }

    /**
     * Create a prepared statement and return it.
     * 
     * @param sql
     * @return
     * @throws SQLException 
     */
    public static PreparedStatement prepareStatement(String sql) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt;
    }
    
    /**
     * Commit.
     * 
     * @throws SQLException 
     */
    public static void commit() throws SQLException {
        conn.commit();
    }

    /**
     * Rollback.
     * 
     * @throws SQLException 
     */
    public static void rollback() throws SQLException {
        conn.rollback();
    }
}
