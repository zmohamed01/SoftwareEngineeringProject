package org.javaproject;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.javaproject.DBConnection;
import org.javaproject.EmployeeTable;
import org.junit.Test;

public class EmployeeTableTest {

	 public String SQLListOfBostonEmployees() throws SQLException{
			
		
			Statement stmt = null;
			
			try {
				
				String output = "";
				Connection con = DBConnection.connect();
				
				
				String query = "select firstname, lastname, employeenumber, extension, email, officecode, reportsto, jobtitle from Employees where officecode=2";
			
				stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
	
		        
		        while (rs.next()) {
		        	  String firstName = rs.getString("firstname");
		        	  String lastName = rs.getString("lastname");
		        	  String employeeNum = rs.getString("employeenumber");
		        	  String email = rs.getString("email");
		        	  String extension = rs.getString("extension");
		        	  String reportsTo = rs.getString("reportsto");
		        	  String jobTitle = rs.getString("jobtitle");
		        	  String officeCode = rs.getString("officecode");
		        	  
		        	  if (output == null) {
		        		  throw new NullPointerException();
		        		  
		        	  }else {
		   
		        	  output += "Employee Number:" + employeeNum + "\t" + "First Name:" + firstName + "\t" + "Last Name:" + lastName + "\t" + 
					"Email:" + email + "\t" + "Extension:" + extension + "\t" + "Reports To:" + reportsTo + "\t" + "Job Title:" + jobTitle + "\t" + 
						"Office Code:" + officeCode + "\n";
		        
		        	  }
		        }	  return output;
			} finally {
		        if (stmt != null) { stmt.close(); }

	}
	}

	  @Test
		public void testListBostonEmployees() throws SQLException{
			EmployeeTable et = new EmployeeTable();
			 assertEquals(SQLListOfBostonEmployees(), et.listBostonEmployees() );
	  }
	  


	
}
