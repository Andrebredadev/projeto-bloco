package edu.infnet.ATM;

/**Deposit.java Representa transação ATM de deposito de saldo
 * Deposit extende de Transaction logo temos uma herança
 * @author andre
 *
 */

public class Deposit extends Transaction {

	private Keypad keypad;
	private DepositSlot depositSlot;
	private Double amount;
	private final static int CANCELAR = 0;

	public Deposit(int userAccountNumber, Screen atmScreen, BankDatabase atmBankdatabase, Keypad atmKeypad,
			DepositSlot atmDepositSlot) {

		super(userAccountNumber, atmScreen, atmBankdatabase);

		keypad = atmKeypad;
		depositSlot = atmDepositSlot;

	}
	
	/* Solicita que o usuario insira uma quantia em centavos
	 * 
	 */

	private double promptForDepositAmount() {

		Screen screen = getScreen();

		screen.displayMessage("\nEntre com uma quantidade para deposito em " + "Centavos (ou 0 para cancelar)");
		int input = keypad.getInput();

		if (input == CANCELAR) {
			return CANCELAR;
		} else {
			return (double) input / 100;
		}

	}
	
	/*Realiza a transação
	 * 
	 */

	public void execute() {
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();

		amount = promptForDepositAmount();

		if (amount != CANCELAR) {
			screen.displayMessage("\nInsira envelope com deposito");
			screen.displayDollarAmount(amount);
			screen.displayMessageLine(".");

			boolean receberEnvelope = depositSlot.isEnvelopeReceived();

			if (receberEnvelope) {

				screen.displayMessageLine("\nSeu envelope foi depositado com sucesso");

				bankDatabase.credit(getAccountNumber(), amount);
			} else {
				screen.displayMessageLine("Voce não inseriu um envelope");
			}

		} else {
			screen.displayMessageLine("\nTransação cancelada");
		}

	}

}
