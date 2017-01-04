/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.matt.bowlingcms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matt
 */
public class DBUtilities {
    private static final String DB = "localhost:3306";
    private static final String ROOT_USERNAME = "";
    private static final String ROOT_PASSWORD = "";
    private static final String RO_USERNAME = "test";
    private static final String RO_PASSWORD = "test";
    
//    private static ResultSet exec(PreparedStatement p){
//        try {
//            ResultSet r = p.executeQuery();
//            return r;
//        }
//        catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
    
    public static Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection c = DriverManager.getConnection(DB, ROOT_USERNAME, ROOT_PASSWORD);
            return c;
        } catch (SQLException e) {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public static void closeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static PreparedStatement getPreparedStatement(Connection c, String query) {
        try {
            PreparedStatement ps = c.prepareStatement(query);
            return ps;
        } catch (SQLException e) {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public static int exec(PreparedStatement p) {
        try {
            p.executeUpdate();
            return p.getUpdateCount();
        } catch (SQLException ex) {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public static void closePreparedStatement(PreparedStatement ps) {
        try {
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            if(statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
