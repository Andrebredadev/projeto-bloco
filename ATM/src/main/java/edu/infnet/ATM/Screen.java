package edu.infnet.ATM;

/** Screen.java
 * Tela do ATM
 * @author andre
 *
 */

public class Screen {
	
	/** Retorna uma mensagem sem pé de linha
	 * 
	 * @param Message - Mensagem a ser exibida
	 */
	
	public void displayMessage (String Message) {
		
		System.out.print(Message);
		
	}
	
	/** Retorna uma mensagem com pé de linha
	 * 
	 * @param Message - Mensagem a ser exibida
	 */
	
	public void displayMessageLine (String Message) {
		System.out.println(Message);
		
	}
	
	/** Exibe valor em dolares
	 * 
	 * @param amount - Valor em Dolar
	 */
	
	public void displayDollarAmount(double amount) {
		System.out.printf("$%, .2f", amount);
		
	}

}
