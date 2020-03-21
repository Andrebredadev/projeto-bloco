package edu.infnet.ATM;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ATM.java Representa um caixa eletronico
 * 
 * @author andre
 * @version 1.0
 * @see Screen
 * @see Keypad
 * @see CashDispenser
 * @see DepositSlot
 * @see BankDatabase
 */

public class ATM {

	private int currentAccountNumber;
	private boolean userAuthenticated;
	private Screen screen;
	private Keypad keypad;
	private CashDispenser cashDispenser;
	private DepositSlot depositSlot;
	private BankDatabase bankDatabase;

	private static final int BALANCE_INQUIRY = 1;
	private static final int WITHDRAWAL = 2;
	private static final int DEPOSIT = 3;
	private static final int SAIR = 4;

	public ATM() throws IOException, FileNotFoundException{

		userAuthenticated = false;
		currentAccountNumber = 0;
		screen = new Screen();
		keypad = new Keypad();
		cashDispenser = new CashDispenser();
		depositSlot = new DepositSlot();
		bankDatabase =  BankDatabase.getinstance(); //Inicia a instancia unica da classe BankDatabase(Singleton) que sera nosso Banco de Dados
	}
	
	/** Metodo que inicia nossa ATM
	 * 
	 */

	public void run() {

		while (true) {

			while (!userAuthenticated) {
				screen.displayMessageLine("\nBem-vindo!");
				autenticarUsuario();
			}

			performTransactions();
			userAuthenticated = false;
			currentAccountNumber = 0;
			screen.displayMessageLine(" \nObrigado! Adeus!");
		}
	}
	
	/** Metodo para autenticar o usuario consultando o Banco de dados
	 * 
	 */

	private void autenticarUsuario() {
		screen.displayMessage("\nInsira o numero da sua conta: ");
		int numeroConta = keypad.getInput();
		screen.displayMessage("\nInsira seu PIN: ");
		int numeroPIN = keypad.getInput();

		userAuthenticated = BankDatabase.getinstance().authenticateUser(numeroConta, numeroPIN);

		if (userAuthenticated) {
			currentAccountNumber = numeroConta;
		} else {
			screen.displayMessage("\nNumero da conta invalido");
		}
	}
	
	/** Metodo para exibir o menu principal e realizar transações
	 * 
	 */

	public void performTransactions() {

		Transaction transacaoAtual = null;

		boolean usuarioSaiu = false;

		while (!usuarioSaiu) {

			int selecaoMenu = exibirMenuPrincipal();

			switch (selecaoMenu) {

			case BALANCE_INQUIRY:
			case WITHDRAWAL:
			case DEPOSIT:
				transacaoAtual = createTransaction(selecaoMenu);
				transacaoAtual.execute();
				break;

			case SAIR:
				screen.displayMessage("\nSaindo");
				usuarioSaiu = true;
				break;

			default:
				screen.displayMessage("\nVoce não selecionou uma opção valida");
			}
		}

	}
	
	/** Retorna o objeto da subclasse Transaction especificado
	 * 
	 * @param type
	 * @return temp - Objeto recem-criado
	 */

	private Transaction createTransaction(int type) {
		
		Transaction temp = null;
		
		switch(type) {
		
		case BALANCE_INQUIRY:
			temp = new BalanceInquiry(currentAccountNumber, screen, BankDatabase.getinstance());
			break;
			
		case WITHDRAWAL:
			temp = new Withdrawal(currentAccountNumber, screen, BankDatabase.getinstance(), keypad, cashDispenser);
			break;
			
		case DEPOSIT:
			temp = new Deposit(currentAccountNumber, screen, BankDatabase.getinstance(), keypad, depositSlot);
			break;
		}
		
		return temp;

	}
	
	/**Metodo para exibir o menu principal e retora uma seleção
	 * 
	 * @return int - Escolha do usuario
	 */

	private int exibirMenuPrincipal() {
		
		screen.displayMessageLine("\nMenu Principal");
		screen.displayMessageLine("1 - Verificar Saldo");
		screen.displayMessageLine("2 - Realizar Saque");
		screen.displayMessageLine("3 - Realizar Deposito");
		screen.displayMessageLine("4 - Sair");
		screen.displayMessage("\nSelecione uma opção: ");
		return keypad.getInput();

	}

}
