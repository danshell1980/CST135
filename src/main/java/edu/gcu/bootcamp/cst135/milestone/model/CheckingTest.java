package edu.gcu.bootcamp.cst135.milestone.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckingTest {

	@Test
	public void testGetOverdraft() {
		final double overDraft = 50.40;
		Checking checking = new Checking("TEST", 0, overDraft);
		
		assertEquals("Overdraft test fails", checking.getOverdraft(), overDraft, 0.001);
	}

	@Test
	public void testSetOverdraft() {
		final double overDraft = 110.55;
		Checking checking = new Checking("TEST", 0, overDraft);
		checking.setOverdraft(overDraft*2);
		
		assertEquals("Overdraft test fails", checking.getOverdraft(), overDraft*2, 0.001);
	}

	@Test
	public void testGetAccountNumber() {
		final String AccountNumber = "FGHE1234";
		Saving saving = new Saving(AccountNumber,4000, 200.00, 15.00, .05);
		
		assertEquals("AccountNumber test fails", AccountNumber, saving.getAccountNumber());
	}

	@Test
	public void testSetAccountNumber() {
		final String AccountNumber = "FGHE1234";
		Saving saving = new Saving("TEST",4000, 200.00, 15.00, .05);
		
		saving.setAccountNumber(AccountNumber + "9");
		
		assertEquals("AccountNumber test fails", AccountNumber + "9", saving.getAccountNumber());

	}

	@Test
	public void testGetAccountBalance() {
		final double AccountBalance = 3450.40;
		Checking checking = new Checking("TEST", AccountBalance, 15);
		
		assertEquals("AccountBalance test fails", checking.getAccountBalance(), AccountBalance, 0.001);
	}

	@Test
	public void testSetAccountBalance() {
		final double AccountBalance = 1410.55;
		Checking checking = new Checking("TEST", AccountBalance, 15);
		checking.setAccountBalance(AccountBalance*2);
		
		assertEquals("AccountBalance test fails", checking.getAccountBalance(), AccountBalance*2, 0.001);
	}

}
