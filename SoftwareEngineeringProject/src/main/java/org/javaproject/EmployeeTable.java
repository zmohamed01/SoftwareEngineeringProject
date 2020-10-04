/**
 * 
 */
package org.javaproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author zmohamed01
 *
 */
public class EmployeeTable {


	  private Connection con = null;
	  private ArrayList<Employee> employeeList = null;
	  
	  public EmployeeTable(){
		try {
			con = DBConnection.connect();
		}
		catch(Exception e) {
			System.out.println(e);
		} 
	}

	  
		public ArrayList<Employee> viewTable()
			    throws SQLException {

			    Statement stmt = null;
			    String query = "Select * From  Employees";
			     
			    try {
			        stmt = con.createStatement();
			        ResultSet rs = stmt.executeQuery(query);
			        
			     employeeList = new ArrayList<Employee>();
			        
			        while (rs.next()) {
			        	
			        	   Employee employee = new Employee(rs.getInt("employeeNumber"), rs.getString("lastName")
			  		    		 , rs.getString("firstName"), rs.getString("extension"), rs.getString("email")
			  		    		 , rs.getString("officeCode"), rs.getInt("reportsTo"), rs.getString("jobTitle"));
			        	

			            
			            employeeList.add(employee);
			           			   
			        }
			       return employeeList;
			        
			    } finally {
			        if (stmt != null) { stmt.close(); }

		}
		}
		
		
		

		public String listBostonEmployees() throws SQLException {
			
			Iterator<Employee> it = viewTable().iterator();
			
			String sent = "";
			
			
				try {
					
					while (it.hasNext()) {
						Employee num = it.next();
						if(num.getOfficeCode().matches("2")) {
							
	
						sent += "Employee Number:" + num.getEmployeeNumber() + "\t" + 
					"First Name:" + num.getFirstName() + "\t" + 
							"Last Name:" + num.getLastName() + "\t" + 
					"Email:" + num.getEmail() + "\t" + 
							"Extension:" + num.getExtension() + "\t" + 
					"Reports To:" + num.getReportsTo() + "\t" + 
							"Job Title:" + num.getJobTitle() + "\t" + 
					"Office Code:" + num.getOfficeCode() + "\n" ;
					
				}		
				}	
			
			return sent;
			} 
			finally {
				if (con != null) con.close();
			}
			}
		

	
}
