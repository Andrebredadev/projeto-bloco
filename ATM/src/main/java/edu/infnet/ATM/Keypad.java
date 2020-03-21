package edu.infnet.ATM;

import java.util.InputMismatchException;
import java.util.Scanner;


/**Keypad.java
 * Representa o teclado numérico do ATM e recebe todas a entradas do usuário
 * @author andre
 *
 */

public class Keypad {
	
	private Scanner scanner;
	private Screen screen;
	
	
	public Keypad() {
		scanner = new Scanner(System.in);
	}
	
	/** Retorna um valor inteiro inserido pelo usuario
	 * 
	 * @return scanner.nextInt() - Retorna o Input do numero inserido pelo usuario, caso não seja um valor valido retorna 0
	 * @throws InputMismatchException
	 */
	public int getInput() throws InputMismatchException {
		try {
			return scanner.nextInt();
		} catch (InputMismatchException inputMismatchException) {
			
			scanner.nextLine();
			System.out.println("Digite apenas números");
			
		}
		
		return 0;
		
	}

}
