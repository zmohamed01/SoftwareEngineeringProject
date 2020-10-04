/**
 * 
 */
package org.javaproject;

import java.util.Date;

/**
 * @author zmohamed01
 *
 */
public class Payment {

	private Integer customerNumber = 0;
	
	private String checkNumber = null;
	
	private Date paymentDate = null;
	
	private Double amount = 0.0;

	public Payment(Integer customerNumber, String checkNumber, Date paymentDate, Double amount) {
		super();
		this.customerNumber = customerNumber;
		this.checkNumber = checkNumber;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	public Integer getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCheckNumber() {
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
}
