package edu.infnet.ATM;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * BankDatabase.java Classe representa um acesso a BD das contas dos usuarios
 * 
 * @author andre
 *
 */

public class BankDatabase {

	private ArrayList<Account> accounts;
	private FAO arq = new FAO();
	
	private static BankDatabase instance = null; // Variavel criada para usar no metodo getInstance para sabermos se há instancia da classe BankDatabase
	
	/** Construtor com visibildiade privada para aplicar o padrão Singleton
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */


	private BankDatabase() throws IOException, FileNotFoundException {
		
		accounts = new ArrayList<Account>();

		
		try {
			
			arq.readFile(accounts);
			
		} catch (FileNotFoundException x) {
			System.err.format("FilenotFoundException: %s%n", x);
			
			
		} catch (IOException ex) {
			System.err.format("IOException: %s%n", ex);
		}
		
		try {
			
			arq.writeFile(accounts);
			
		} catch (FileNotFoundException x) {
			
			System.err.format("FilenotFoundException: %s%n", x);

		} catch (IOException ex){
			
			System.err.format("IOException: %s%n", ex);
			
		}

	}
	
	/** Recupera objeto Account que tem o numero de conta especificado
	 * 
	 * @param accountNumber - Numero de conta especificado
	 * @return contaAtual - Retorna numero da conta atual especificada
	 */

	private Account getAccount(int accountNumber) {

		for (Account contaAtual : accounts) {

			if (contaAtual.getAccountNumber() == accountNumber) {
				return contaAtual;
			}

		}

		return null;

	}
	
	/** Metodo que verifica se o numero da conta e PIn inseridos pelo usuario e os do Banco correspondem
	 * 
	 * @param userAccountNumber - Numero de conta inserido pelo usuario
	 * @param userPIN - PIN inserido pelo usuario
	 * @return
	 */

	public boolean authenticateUser(int userAccountNumber, int userPIN) {

		Account contaUsuario = getAccount(userAccountNumber);

		if (contaUsuario != null) {

			return contaUsuario.validatePIN(userPIN);

		} else {
			return false;
		}

	}
	
	/** Metodo que retorna o saldo disponivel em Account de acordo com o numero de conta especificado
	 * 
	 * @param userAccountNumber - Numero de conta inserido pelo usuario
	 * @return
	 */

	public double getAvaibleBalance(int userAccountNumber) {

		return getAccount(userAccountNumber).getAvaibleBalance();

	}
	
	/** Metodo que retorna o saldo total em Account de acordo com o numero de conta especificado
	 * 
	 * @param userAccountNumber - Numero de conta inserido pelo usuario
	 * @return
	 */

	public double getTotalBalance(int userAccountNumber) {

		return getAccount(userAccountNumber).getTotalBalance();

	}
	
	/**Metodo que credita uma quantia em Account de acordo com o numero de conta especificado
	 * 
	 * @param userAccountNumber - Numero de conta inserido pelo usuario
	 * @param amount - Valor a ser inserido na conta
	 */

	public void credit(int userAccountNumber, double amount) {
		getAccount(userAccountNumber).credit(amount);

	}
	
	/**Metodo que debita uma quantia em Account de acordo com o numero de conta especificado
	 * 
	 * @param userAccountNumber - Numero de conta inserido pelo usuario
	 * @param amount - Valor a ser debitado na conta
	 */

	public void debit(int userAccountNumber, double amount) {
		getAccount(userAccountNumber).debit(amount);

	}
	
	/**Metodo que verifica se há uma instancia de BankDatabase, assim fazendo com que haja apenas uma instancia dessa classe no ATM, fazendo assim o padrão Singleton
	 * 
	 * @return
	 */
	
	public static BankDatabase getinstance() {
		
		if(instance == null) {
			try {
				
				instance = new BankDatabase();
				
			} catch (FileNotFoundException x) {
				
				System.err.format("FilenotFoundException: %s%n", x);
				
			} catch (IOException ex) {
				
				System.err.format("IOException: %s%n", ex);
			}
		}
		
		return instance;
		
	}

}
