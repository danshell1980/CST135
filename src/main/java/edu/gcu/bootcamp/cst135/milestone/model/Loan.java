package edu.gcu.bootcamp.cst135.milestone.model;

public class Loan extends Account {

	private double interestRate;
	private double lateFee;

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getLateFee() {
		return lateFee;
	}

	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}

	public Loan(String accountNumber, double accountBalance, double interestRate, double lateFee) {
		super(accountNumber, accountBalance);

		this.interestRate = interestRate;
		this.lateFee = lateFee;

	}

	public double doEndofMonthInterest() {
		double interest = interestRate * this.getAccountBalance();
		return interest;
	}

	public void checkLateFee() {
		lateFee = 0;
	}

}
