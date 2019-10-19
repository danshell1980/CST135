package edu.gcu.bootcamp.cst135.milestone.model;

import java.util.Date;

public class Transaction {
	
	private Date transactionDate;
	private String accountName;
	private double amount;
	private String type;

	public Transaction(String accountName, double amount, String type) {
		super();
		this.transactionDate = new Date();
		this.accountName = accountName;
		this.amount = amount;
		this.type = type;
	}
	
	
	
	
	public Date getTransactionDate() {
		return transactionDate;
	}




	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}




	public String getAccountName() {
		return accountName;
	}




	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}




	public double getAmount() {
		return amount;
	}




	public void setAmount(double amount) {
		this.amount = amount;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	@Override
	public String toString() {
		return "Transaction Date: " + transactionDate + " | Account Name: " + accountName + " | Amount: "
				+ amount + " | type: " + type;
	}

}
