/**
 * 
 */
package org.javaproject;

/**
 * @author zmohamed01
 *
 */
public class OrderDetails {
	
	private Integer orderNumber = 0;
	
	private String productCode = null;
	
	private Integer quantityOrdered = 0;
	
	private Double priceEach = 0.0;
	
	private Integer orderLineNumber = 0;

	public OrderDetails(Integer orderNumber, String productCode, Integer quantityOrdered, Double priceEach,
			Integer orderLineNumber) {
		super();
		this.orderNumber = orderNumber;
		this.productCode = productCode;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getQuantityOrdered() {
		return this.quantityOrdered;
	}

	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public Double getPriceEach() {
		return this.priceEach;
	}

	public void setPriceEach(Double priceEach) {
		this.priceEach = priceEach;
	}

	public Integer getOrderLineNumber() {
		return this.orderLineNumber;
	}

	public void setOrderLineNumber(Integer orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}
	
	
	

}
