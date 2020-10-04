/**
 * 
 */
package org.javaproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zmohamed01
 *
 */
public class CustomerOrdersTable {

	 private Connection con = null;
	 private List<Customer> customerList = null;
	 private List<Order> orderList = null;
	 private List<OrderDetails> orderDetailsList = null;

	  
	  public CustomerOrdersTable(){
		try {
			con = DBConnection.connect();
		}
		catch(Exception e) {
			System.out.println(e);
		} 
	}
	
	  
	  public List<Integer> getHighOrderNums() throws SQLException {

		  Statement stmt = null;
		  String query = "Select * From  OrderDetails";
		  
		  try {
			  stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery(query);
		          List<Integer> list = new ArrayList<Integer>();
		          List<Integer> list2 = new ArrayList<Integer>();
		        
		       orderDetailsList = new ArrayList<OrderDetails>();

		        while (rs.next()) {
		        	OrderDetails orderDetails = new OrderDetails(rs.getInt("orderNumber"), rs.getString("productCode")
		        			   , rs.getInt("quantityOrdered"), rs.getDouble("priceEach"), rs.getInt("orderLineNumber"));
		        	
		             orderDetailsList.add(orderDetails);
		        	
		               }
		             
		             Map<Integer, Double> orderDetailsMap = new HashMap<Integer, Double>();

				        for (OrderDetails od :orderDetailsList ) {
				        	
				        	Integer ordNum = od.getOrderNumber();
				        	
				        	Double total = orderDetailsMap.containsKey(ordNum) ? orderDetailsMap.get(ordNum) : 0;
				        	
				        	 total +=  (od.getPriceEach() * od.getQuantityOrdered());

				        	
			
				        	orderDetailsMap.put(ordNum, total);

				        	
				        }
				        
				        for (Integer key :  orderDetailsMap.keySet()) {
				        	if (orderDetailsMap.get(key) > 25000) {
				        		list.add(key);
				        	
				        	for (Integer el : list) {
				        		if(!list2.contains(el)) {
				        			list2.add(el);
				        		
				        		}
				        	}

				        	}
				        }

		  return list2;

		
		    }finally {
		        if (stmt != null) { stmt.close(); }

		  	  
		  }
		  
	  }
	  
	  
	  public Map<Integer, Integer> getOrderAndCusNums() throws SQLException {
		    Statement stmt = null;
		    String query = "Select * From  Orders";
		     
		    try {
		        stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        
		        Map<Integer, Integer> orderMap2 = new HashMap<Integer, Integer>();
		        
		        List<Integer> list = new ArrayList<Integer>();
		        
		        orderList = new ArrayList<Order>();
		        
		        while (rs.next()) {
		        	
		        	   Order order = new Order(rs.getInt("orderNumber"), rs.getDate("orderDate"), rs.getDate("requiredDate")
		        			   , rs.getDate("shippedDate"), rs.getString("status"), rs.getString("comments"), rs.getInt("customerNumber"));
		        	
		        	   		orderList.add(order);
		        	 }   	
		        
		        Map<Integer, Integer> orderMap = new HashMap<Integer, Integer>();
		        
		        	   	  for (Order o :orderList ) {
		        	   		  
		        	   		  Integer ordNum = o.getOrderNumber();
		        	   		  Integer cusNum = o.getCustomerNumber();
		        	   		  
		        	   		orderMap.put(ordNum, cusNum);
		        	   	  }	
		        	   		
		        	
		        	 for (Map.Entry<Integer, Integer> m : orderMap.entrySet()) {
		        		 if (getHighOrderNums().contains(m.getKey())) {

		        			list.add(m.getValue());
		        			list.add(m.getKey());
		        			
		        				
		        			orderMap2.put(m.getKey(), m.getValue());

		        		}
		        		 }
		        	
		     
		        
		        	 return orderMap2;
		        
		    } finally {
		        if (stmt != null) { stmt.close(); }

	}
		  

	  }
	  
	
	  public String linkCustomerName() throws SQLException {
		  
		  	String listOfInfo = "";
		    Statement stmt = null;
		    String query = "Select * From  Customers";
		    
		    try {
		        stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        Map<Integer, String> cusMap = new HashMap< Integer, String>();


	        	Map<Integer, Integer> valuesList = getOrderAndCusNums();

	
		        customerList = new ArrayList<Customer>();

		        
		        while (rs.next()) {
		        	
		        	   Customer customer = new Customer(rs.getInt("customerNumber"), rs.getString("customerName")
		  		    		 , rs.getString("contactLastName"), rs.getString("contactFirstName"), rs.getString("phone")
		  		    		 , rs.getString("addressLine1"), rs.getString("addressLine2"), rs.getString("city"), rs.getString("state")
		  		    		 , rs.getString("postalCode"), rs.getString("country"), rs.getInt("salesRepEmployeeNumber"), rs.getDouble("creditLimit"));
		        	

		             customerList.add(customer);
	  
		           			   
		        }
		        
		        
		        for (Customer c : customerList) {
		        	String cusName = c.getCustomerName();
		        	Integer cusNumb = c.getCustomerNumber();

			        cusMap.put( cusNumb, cusName);

		       } 	
		        
		        Map< Integer, String> finalMap = new  HashMap< Integer, String>();
		        valuesList.forEach((key,value) -> finalMap.put(key, cusMap.get(value)) );

		    	TreeMap<Integer, String> sortedTree = new TreeMap<Integer, String>();
	        	sortedTree.putAll(finalMap);
	        	
	        for (Map.Entry<Integer, String> m : sortedTree.entrySet()) {
	        	 
	        	 listOfInfo += "Order Number: " + m.getKey() + "\t" + "Customer Name: " + m.getValue() + "\n" ;
		        }
	
		 
		       return listOfInfo;
		        
		    } finally {
		        if (stmt != null) { stmt.close(); }

	}
	
		  
	  }
	  
}
