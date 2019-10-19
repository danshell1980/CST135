
package edu.gcu.bootcamp.cst135.milestone.FullStackBanking;

import edu.gcu.bootcamp.cst135.milestone.controller.Bank;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * 
 * @author dansh
 *
 */

public class FullStackBanking {

	public static void main(String[] args) {
		final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		try {
			try {
				MyLogger.setup();
			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println("We’re sorry an error occurred.  Our engineers are working on the problem");
			}
			Bank bank = new Bank();
			bank.viewCustomerMenu();
		} catch (Exception ex) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			LOGGER.severe(sw.toString());
		}

	}

}
