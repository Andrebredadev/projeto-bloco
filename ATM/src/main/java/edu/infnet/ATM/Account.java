package edu.infnet.ATM;


/**Account.java
 * Representa uma conta bancária com quatro atributos.
 * @author andre
 *
 */

public class Account {
	
	private Integer accountNumber;
	private Integer pin;
	private Double avaibleBalance;
	private Double totalBalance;	
	
	public Account (int numeroConta, int numeroPIN, Double saldoDisponivel, Double saldoTotal) {
		
		accountNumber = numeroConta;
		pin = numeroPIN;
		avaibleBalance = saldoDisponivel;
		totalBalance = saldoTotal;
		
	}
	
	/** Metodo para validar PIN inserido pelo usuario
	 * 
	 * @param userPIN - PIN inserido pelo usuario para acessar conta bancaria
	 * @return true - Se o PIN for igual aos disponiveis no BD, se não retorna false
	 */
	public boolean validatePIN(int userPIN) {
		
		if (userPIN == pin) {
			return true;
		}
		
		return false;
		
	}
	
	/** Metodo que retorna o saldo disponivel
	 * 
	 * @return avaibleBalance - retorna o saldo disponivel na conta do usuario
	 */
	
	public Double getAvaibleBalance() {
		
		return avaibleBalance;
		
	}
	
	/** Metodo que retorna o saldo total
	 * 
	 * @return totalBalance - retorna o saldo total da conta do usuario
	 */
	
	public Double getTotalBalance() {
		
		return totalBalance;
		
	}
	
	/** Metodo para creditar uma quantia na conta
	 * 
	 * @param quantidade - Quantidade a ser inserida na conta
	 */
	
	public void credit(double quantidade) {
		totalBalance += quantidade;	
		
	}
	
	/** Metodo para debitar uma quantia da conta
	 * 
	 * @param quantidade - Quantidade a ser debitada
	 */
	
	public void debit(double quantidade) {
		avaibleBalance -= quantidade;
		totalBalance -= quantidade;
		
	}
	
	/** Metodo para retorna o numero da conta
	 * 
	 * @return accountNumber - numero da conta a ser retornado
	 */
	
	public int getAccountNumber() {
		return accountNumber;
		
	}
	
	@Override
	public String toString() {
		System.out.printf(" %d; \t%d; \t$%,.2f; \t$%,.2f %n", accountNumber, pin, avaibleBalance, totalBalance );
		return null;
	}
	
	public String toStringWriter() {
		return accountNumber+","+pin+","+avaibleBalance+","+totalBalance;
	}

}
