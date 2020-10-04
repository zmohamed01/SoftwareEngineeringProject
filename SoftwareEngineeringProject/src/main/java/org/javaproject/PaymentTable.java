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
public class PaymentTable {

	 private Connection con;
	 private ArrayList<Payment> paymentList = null;


	  public PaymentTable(){
			try {
				con = DBConnection.connect();
			}
			catch(Exception e) {
				System.out.println(e);
			} 
		}

	

	public ArrayList<Payment> viewTable()
		    throws SQLException {

		    Statement stmt = null;
		    String query = "Select * From  Payments";
		     
		    try {
		        stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        
		        paymentList = new ArrayList<Payment>();
		        
		        while (rs.next()) {
		        	
			        Payment payment = new Payment(rs.getInt("customerNumber"), rs.getString("checkNumber"), rs.getDate("paymentDate"), rs.getDouble("amount"));

		        
		            paymentList.add(payment);
		           			   
		        }
		       return paymentList;
		        		   
		    } finally {
		        if (stmt != null) { stmt.close(); }
		    
	
	}
	
	}
	
	public double avgPy() throws SQLException {
		
	
			Iterator<Payment> it = viewTable().iterator();
			double totPy = 0.0;
			double finalPy = 0.0;
			while (it.hasNext()) {
				Payment num = it.next();
				
				if (num.getAmount() == 0 ) {
					throw new IllegalArgumentException() ;
					
				}else {
					totPy +=num.getAmount();
					finalPy = (2 * totPy) / viewTable().size();
				}
				
			} 
			
			
			return finalPy;
			
	}

	
	

	public String listPy() throws SQLException {
		
		Iterator<Payment> it =  viewTable().iterator();
		
		String sent = "";
		final double  val = this.avgPy();
		try{
			while (it.hasNext()) {
			Payment num = it.next();
			
			
			if (num.getAmount() > val) {
				
				sent += "Customer Number: "+ num.getCustomerNumber() + "\t" +"Check Number: " + num.getCheckNumber() + 
						"\t" + "Payment date :" + num.getPaymentDate() + "\t" + "Amount: " + num.getAmount() + "\n" ;
				

			}
		} 
		
		
		return sent;
		
	}finally {
		if (con != null) con.close();
	}

}
	
}
