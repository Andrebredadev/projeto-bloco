package edu.infnet.ATM;

/**
 * Withdrawal.java Representa uma transação ATM de consulta de saldo.
 * Withdrawal extende de Transaction logo temos uma herança
 * @author andre
 *
 */

public class Withdrawal extends Transaction {
	
	private int amount;
	private Keypad keypad;
	private CashDispenser cashDispenser;	

	private final static int CANCELAR = 6;

	public Withdrawal(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad,
			CashDispenser atmCashDispenser) {

		super(userAccountNumber, atmScreen, atmBankDatabase);

		keypad = atmKeypad;
		cashDispenser = atmCashDispenser;

	}
	
	/** Exibe o menu de quantias de saque e a opção de cancelar
	 * 
	 * @return escolha - Retorna valor inteiro da operação seleciona pelo usuario
	 */

	public int displayMenuofAmount() {

		int escolha = 0;

		Screen screen = getScreen();

		int quantia[] = { 0, 20, 40, 60, 100, 200 };

		while (escolha == 0) {

			screen.displayMessageLine("Menu de saque");
			screen.displayMessageLine("1 - $20");
			screen.displayMessageLine("2 - $40");
			screen.displayMessageLine("3 - $60");
			screen.displayMessageLine("4 - $100");
			screen.displayMessageLine("5 - $200");
			screen.displayMessageLine("6 - Cancelar transação");
			screen.displayMessage("Escolha uma quantidade para sacar: ");

			int input = keypad.getInput();

			switch (input) {

			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				escolha = quantia[input];
				break;

			case 6:
				escolha = CANCELAR;
				break;

			default:
				screen.displayMessageLine("\nSeleção invalida");

			}

		}

		return escolha;

	}
	
	/**Metodo que realiza a transação
	 * 
	 */

	public void execute() {

		boolean cedulasEntregue = false;
		double saldoDisponivel;

		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();

		do {

			amount = displayMenuofAmount();

			if (amount != CANCELAR) {

				saldoDisponivel = bankDatabase.getAvaibleBalance(getAccountNumber());

				if (amount <= saldoDisponivel) {

					if (cashDispenser.isSufficientCashAvaible(amount)) {

						bankDatabase.debit(getAccountNumber(), amount);

						cashDispenser.dispenseCash(amount);

						cedulasEntregue = true;

						screen.displayMessage("\nSeu dinheiro foi entregue");

					} else {

						screen.displayMessageLine(
								"\nDinheiro insuficiente no caixa" + "\n\nPor favor tente sacar uma quantia menor");
					}
				} else {

					screen.displayMessageLine("\n Não possui dinheiro suficiente na conta");

				}

			} else {
				screen.displayMessageLine("\nCancelando transação");
				return;
			}

		} while (!cedulasEntregue);

	}

}
