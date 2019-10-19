package edu.gcu.bootcamp.cst135.milestone.model;

import java.util.Date;

public class Customer {

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private Date dateOpened;

	private Saving saving;
	private Checking checking;
	private Loan loan;

	public Customer(String firstName, String lastName, String username, String password, Date dateOpened) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.dateOpened = dateOpened;

		saving = new Saving("-SAV12345", 2500, 200, 25, .06);
		checking = new Checking("-CHK23456", 500, 25);
		loan = new Loan("-LOAN12345", -5000, .08, 50.0);
	}

	public Saving getSaving() {
		return saving;
	}

	public void setSaving(Saving saving) {
		this.saving = saving;
	}

	public Checking getChecking() {
		return checking;
	}

	public void setChecking(Checking checking) {
		this.checking = checking;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;

	}

}