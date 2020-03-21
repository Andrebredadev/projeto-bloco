package edu.infnet.ATM;

/** Transaction.java
 * Superclasse abstrata que representa a noção de uma transação no ATM.
 * @author andre
 *
 */

public abstract class Transaction {
	
	private Integer accountNumber;
	private Screen screen;
	private BankDatabase bankDatabase;
	
	public Transaction (int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase ) {
		
		accountNumber = userAccountNumber;
		screen = atmScreen;
		bankDatabase = atmBankDatabase;
	}
	
	/**Metodo que retorna o numero da conta
	 * 
	 * @return accountNumber - numero da conta
	 */
	public int getAccountNumber() {
		return accountNumber;
		
	}	

	
	/** Metodo abstrado que realiza a transação (É sobrescrita por cada subclasse)
	 * Aqui ocorre Polimorfismo devido a essa caracterisca de sobrescrita para cada subclasse sem alterar a função do metodo
	 */
	
	abstract public void execute();
	
	/**Metodo para retorna referencia a tela
	 * 
	 * @return screen - Retorna a tela
	 */
	public Screen getScreen() {
		return screen;
		
	}
	
	/**Metodo para retorna o banco de dados
	 * 
	 * @return bankDatabase - retorna o banco de dados
	 */
	
	public BankDatabase getBankDatabase() {
		return bankDatabase;
	}

}
