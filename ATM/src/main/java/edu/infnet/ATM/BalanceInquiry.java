package edu.infnet.ATM;

/** BalanceInquiry.java
 * Representa uma transação ATM de consulta de saldo.
 * BalanceInquiry extende de Transaction logo temos uma herança
 * @author andre
 *
 */

public class BalanceInquiry extends Transaction {
	
	public BalanceInquiry (int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase);
		
	}
	
	/**Metodo que realiza a transação(sobrescrito da super classe Transaction)
	 * 
	 */
	public void execute() {
		
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		double saldoDisponivel = bankDatabase.getAvaibleBalance(getAccountNumber());
		
		double saldoTotal = bankDatabase.getTotalBalance(getAccountNumber());
		
		screen.displayMessageLine("\nInformação de saldo");
		screen.displayMessage("Saldo disponivel: ");
		screen.displayDollarAmount(saldoDisponivel);
		
		screen.displayMessage("\n Saldo total: ");
		screen.displayDollarAmount(saldoTotal);
		screen.displayMessageLine("");
		
	}
	
	

}
