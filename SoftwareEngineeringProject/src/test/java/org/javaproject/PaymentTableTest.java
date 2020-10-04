package org.javaproject;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.javaproject.DBConnection;
import org.javaproject.PaymentTable;
import org.junit.Test;

public class PaymentTableTest {

	 public String SQLListOfPayments() throws SQLException{

			Statement stmt = null;
			
			try {
				
				String output = "";
				Connection con = DBConnection.connect();
				
				String query = "select * from Payments where amount > (2 * (select avg(amount) from Payments))";
			
				stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
	
		        
		        while (rs.next()) {
		        	  Integer customerNumber = rs.getInt("customernumber");
		        	  String checkNumber = rs.getString("checknumber");
		        	  Date paymentDate = rs.getDate("paymentdate");
		        	  Double amount =  rs.getDouble("amount");
		        	  
		        	  if (output == null) {
		        		  throw new NullPointerException();
		        		  
		        	  }else {

		        
		        	  output +=  "Customer Number: "+ customerNumber + "\t" +"Check Number: " + checkNumber + 
								"\t" + "Payment date :" + paymentDate + "\t" + "Amount: " + amount + "\n" ;
		        	  }
		
		        }	  return output;
			} finally {
		        if (stmt != null) { stmt.close(); }

	}
	}
	
	

	@Test
	public void testListOfAllGreaterPayments() throws SQLException {
		PaymentTable pt = new PaymentTable();
		assertEquals(SQLListOfPayments(), pt.listPy());
				
	}
	
	
}
