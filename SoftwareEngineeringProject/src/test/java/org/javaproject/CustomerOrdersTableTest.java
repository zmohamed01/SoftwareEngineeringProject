package org.javaproject;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.javaproject.CustomerOrdersTable;
import org.javaproject.DBConnection;
import org.junit.Test;

public class CustomerOrdersTableTest {

	 public String SQLListOver25000() throws SQLException{

			Statement stmt = null;
			
			try {
				
				String output = "";
				Connection con = DBConnection.connect();
				
				String query = "select Customers.customerNumber, " + 
						"                 Customers.customerName, " + 
						"                 Orders.ordernumber," + 
						"				 sum(OrderDetails.priceEach*OrderDetails.quantityOrdered) as value" + 
						"			from Customers" + 
						"			join Orders" + 
						"			  on Customers.customerNumber = Orders.customerNumber" + 
						"			join OrderDetails" + 
						"			  on OrderDetails.orderNumber = Orders.orderNumber" + 
						"			group by orders.ordernumber" + 
						"		   having value > 25000";
			
				stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
	
		        
		        while (rs.next()) {
		        	  String customerName = rs.getString("customername");
		        	  Integer orderNumber = rs.getInt("orderNumber");
		        	 
		        	  if (output == null) {
		        		  throw new NullPointerException();
		        		  
		        	  }else {
		        		  
		        	  output +=  "Order Number: " + orderNumber + "\t"+ "Customer Name: " + customerName + "\n" ;
		        
		        	  }
		
		        }	  return output;
			} finally {
		        if (stmt != null) { stmt.close(); }

	}
	}

	@Test
	public void testListOver25000() throws SQLException {
		CustomerOrdersTable co = new CustomerOrdersTable ();
		assertEquals(SQLListOver25000(), co.linkCustomerName());
	
	}
}
