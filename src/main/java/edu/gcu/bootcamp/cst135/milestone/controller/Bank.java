package edu.gcu.bootcamp.cst135.milestone.controller;
//test
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

import edu.gcu.bootcamp.cst135.milestone.model.Customer;
import edu.gcu.bootcamp.cst135.milestone.model.Transaction;

public class Bank {
	private Customer customer;

	Boolean loggedIn = false;

	public Bank() throws ParseException {
		super();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date yourDate = df.parse("2011-01-14");

		customer = new Customer("John", "Doe", "johndoe1950", "password123", yourDate);
		// TODO Auto-generated constructor stub
	}

	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	Scanner scanner = new Scanner(System.in);
//	public Saving saving = new Saving("-SAV12345", 5000, 200, 25, .06);
//	public Checking checking = new Checking("-CHK23456", 5000, 25);
//	public Loan loan = new Loan("-LOAN12345", 20000, .08, 50.0);

	private void viewExitScreen() {
		System.out.println("Goodbye, Have a good day!");
	}

	public void viewCustomerMenu() {

		int option;

		do {
			if (loggedIn) {
				System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("                MAIN MENU");
				System.out.println("                GCU BANK");
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("Pick an option: ");
				System.out.println("-----------------------");
				System.out.println(" 1: Deposit to Checking");
				System.out.println(" 2: Deposit to Savings");
				System.out.println(" 3: Withdraw from Checking");
				System.out.println(" 4: Withdraw from Savings");
				System.out.println(" 5: Make Loan Payment");
				System.out.println(" 6: Get balance");
				System.out.println(" 7: Get monthly statement");
				System.out.println("------------------------");
				System.out.println(" 9: : Logout");
			} else {
				System.out.println("==========");
				System.out.println("Main Menu");
				System.out.println("==========");
				System.out.println(" 1: Log-in");
				System.out.println(" 9: Exit Bank");

			}

			option = getUserMenuChoice();

			if (loggedIn) {
				processCustomerMenu(option);
			} else {
				if (option == 1) {
					Login();
				} else if (option == 9) {
					viewExitScreen();
				}

			}

		} while (option != 9);

	}

	private void Login() {
		System.out.printf("Enter Username: ");
		String username = scanner.nextLine();
		System.out.printf("\nEnter Password: ");
		String password = scanner.nextLine();

		if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
			System.out.println("Welcome " + customer);

			System.out.println("You are logged in");
			loggedIn = true;
		} else {
			System.out.println("Incorrect username/password combination");
		}

	}

	private int getUserMenuChoice() {
		boolean success = false;
		int result = 0;
		while (!success) {
			String option = scanner.nextLine();
			success = true;
			try {
				result = Integer.parseInt(option);
			} catch (NumberFormatException e) {
				System.out.println("Expecting a numeric menu choice.  Please try again.");
				success = false;
			}
		}
		return result;
	}

	private void processCustomerMenu(int parseInt) {

		switch (parseInt) {
		case 1:
//			int x = 1/0;
			logger.info("User deposits into checking.");
			viewDepositChecking();
			viewBalances();
			break;
		case 2:
			logger.info("User deposits into savings");
			viewDepositSavings();
			viewBalances();
			break;
		case 3:
			logger.info("User withdrawls from checking");
			viewWithdrawalChecking();
			viewBalances();
			break;
		case 4:
			logger.info("User withdrawls from savings");
			viewWithdrawalSavings();
			viewBalances();
			break;
		case 5:
			logger.info("User makes payment on loan");
			viewLoanPayment();
			viewBalances();
			break;
		case 6:
			logger.info("User views balances");
			viewBalances();
			break;
		case 7:
			logger.info("End of month processing");
			viewEndOfMonth();
			viewBalances();
			break;
		case 9:
			logger.info("user exits");
			viewExitScreen();
			break;
		default:
			viewCustomerMenu();
		}
	}

	private void viewEndOfMonth() {

		System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("               END OF MONTH");
		System.out.println("                 GCU BANK");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");

		if (customer.getSaving().getAccountBalance() < customer.getSaving().getMinBalance()) {
			System.out.printf("A $%.2f service fee is being assessed for below minimum balance in savings",
					customer.getSaving().getServiceFee());
			customer.getSaving()
					.setAccountBalance(customer.getSaving().getAccountBalance() - customer.getSaving().getServiceFee());
		}
		if (customer.getSaving().getAccountBalance() > 0) {
			customer.getSaving().setAccountBalance(customer.getSaving().getAccountBalance()
					+ (customer.getSaving().getInterest() * customer.getSaving().getAccountBalance()));
		}

		if (customer.getLoan().getLateFee() > 0) {
			logger.info("Late fee assessed for loan");
			System.out.printf("A late fee is being assessed for your loan: \n$");
			System.out.println(customer.getLoan().getLateFee());
			customer.getLoan()
					.setAccountBalance(customer.getLoan().getAccountBalance() - customer.getLoan().getLateFee());
		}

		System.out.printf("\nYour loan as increased due to interest by: \n$");
		System.out.println(customer.getLoan().doEndofMonthInterest());
		customer.getLoan()
				.setAccountBalance(customer.getLoan().getAccountBalance() + customer.getLoan().doEndofMonthInterest());

		System.out.println("\n----------------\nTRANSACTIONS:");
		for (int i = 0; i < transactions.size(); i++) {
			System.out.println((i + 1) + ": " + transactions.get(i));
		}
	}

	private void viewWithdrawalChecking() {

		System.out.println("How much would you like to withdraw: ");
		double input = getUserAmount();

//			scanner.nextLine();
		processWithdrawalChecking(input);

	}

	private void processWithdrawalChecking(double input) {

		if (customer.getChecking().getAccountBalance() < input) {
			System.out.println("A $" + customer.getChecking().getOverdraft()
					+ " overdraft fee will be assessed if you continue. Continue Y or N?");
			if (scanner.nextLine().toLowerCase().equals("y")) {
				customer.getChecking().setAccountBalance(
						customer.getChecking().getAccountBalance() - input - customer.getChecking().getOverdraft());
				transactions.add(
						new Transaction(customer.getChecking().getAccountNumber(), input, "Widthdrawl from checking"));
			} else
				viewWithdrawalChecking();
		} else {
			customer.getChecking().setAccountBalance(customer.getChecking().getAccountBalance() - input);
			transactions
					.add(new Transaction(customer.getChecking().getAccountNumber(), input, "Widthdrawl from checking"));
		}
	}

	private void viewLoanPayment() {

		System.out.println("How much would you like to pay: ");
		double input = getUserAmount();
		processLoanPayment(input);
		customer.getLoan().checkLateFee();

	}

	private void processLoanPayment(double input) {

		customer.getLoan().setAccountBalance(customer.getLoan().getAccountBalance() + input);
		transactions.add(new Transaction(customer.getLoan().getAccountNumber(), input, "Loan payment"));
	}

	private void viewDepositSavings() {

		System.out.println("How much would you like to deposit: ");
		double input = getUserAmount();
//			scanner.nextLine();
		processDepositSavings(input);

	}

	private void processDepositSavings(double input) {

		customer.getSaving().setAccountBalance(customer.getSaving().getAccountBalance() + input);
		transactions.add(new Transaction(customer.getSaving().getAccountNumber(), input, "Deposit Savings"));
	}

	private void viewDepositChecking() {

		System.out.println("How much would you like to deposit: ");
		double input = getUserAmount();
//			scanner.nextLine();
		processDepositChecking(input);

	}

	private void processDepositChecking(double input) {

		customer.getChecking().setAccountBalance(customer.getChecking().getAccountBalance() + input);
		transactions.add(new Transaction(customer.getChecking().getAccountNumber(), input, "Deposit to checking"));
	}

	private void viewWithdrawalSavings() {
		// int x = 1/0;
		System.out.println("How much would you like to withdraw: ");
		double input = getUserAmount();
//			scanner.nextLine();
		processWithdrawalSavings(input);
	}

	private double getUserAmount() {

		boolean success = false;
		double result = 0;
		while (!success) {
			String option = scanner.nextLine();
			success = true;
			try {
				result = Double.parseDouble(option);
			} catch (NumberFormatException e) {
				System.out.println("Expecting an amount to enter.  Please try again.");
				success = false;
			}
		}
		return result;
	}

	private void processWithdrawalSavings(double input) {
		customer.getSaving().setAccountBalance(customer.getSaving().getAccountBalance() - input);
		transactions.add(new Transaction(customer.getSaving().getAccountNumber(), input, "Widthdrawl from saving"));

	}

	private void viewBalances() {

		System.out.println("\n------------------------");
		System.out.println(customer.getSaving().toString());
		System.out.println(customer.getChecking().toString());
		System.out.println(customer.getLoan().toString());
		System.out.println("------------------------");
	}

}