/**
 * 
 */
package org.javaproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author zmohamed01
 *
 */
public class DBConnection {


	 public static Connection connect() throws SQLException {
	    Connection conn = null;
		 try {
	        
	        String url = "jdbc:mysql://localhost:3306/classicmodels";
	        String username = "root";
	        String password = "";
	      
	        
	         conn =  DriverManager.getConnection(url, username, password);
	        
	        } catch (Exception e) {
	            System.out.println(e);
	        }

	        return conn;
	    } 
	 
	
}
