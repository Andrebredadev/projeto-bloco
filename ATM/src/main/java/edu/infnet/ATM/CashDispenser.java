package edu.infnet.ATM;

/**
 * CashDispenser.java Representa o dispensador de cédulas do ATM (simulação)
 * 
 * @author andre
 *
 */

public class CashDispenser {

	private final static int INITIAL_COUNT = 500;
	private int count;

	public CashDispenser() {

		count = INITIAL_COUNT;
	}
	
	/** Metodo para realizar a entrega a quantia de cedulas especificadas
	 * 
	 * @param amount - Quantia de cedulas pedido para saque
	 */

	public void dispenseCash(int amount) {
		
		int cedulaRequerida = amount / 20;
		count -= cedulaRequerida;

	}
	
	/**Metodo para verificar se a maquina ATM possui cedulas suficiente para saque
	 * 
	 * @param amount - Quantia de cedulas pedida para saque
	 * @return
	 */

	public boolean isSufficientCashAvaible(int amount) {

		int cedulasGasta = amount / 20;		

		if (count >= cedulasGasta) {
			return true;
		} else {
			return false;
		}

	}
}
