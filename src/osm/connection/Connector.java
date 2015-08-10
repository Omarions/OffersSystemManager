/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osm.connection;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class Connector {
    private static Connection conn;
    private static final String URL="jdbc:mysql://localhost/osm_db";
    private static final String USER_NAME="root";
    private static final String PASSWORD="12345";
    private static final String FOR_NAME_CLASS="com.mysql.jdbc.Driver";
    
    public static Connection open(){
        try {
            Class.forName(FOR_NAME_CLASS);
            conn=(Connection)DriverManager.getConnection(URL,USER_NAME,PASSWORD);        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static void close(){
        try {
            if (conn!=null){
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
