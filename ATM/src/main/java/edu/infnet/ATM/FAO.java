package edu.infnet.ATM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * FAO.java classe de acesso a arquivos
 * 
 * @author andre
 *
 */

public class FAO {

	/**
	 * Faz a leitura do arquivo Account.txt que sera usado como Banco de Dados
	 * Metodo de File IO
	 * 
	 * @param contas
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void readFile(ArrayList<Account> contas) throws FileNotFoundException, IOException {

		Path path = Paths.get("Accounts.txt");

		try {

			BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset());
			String line;
			String linha[] = new String[4];

			while ((line = reader.readLine()) != null) {

				System.out.println(line);
				linha = line.split(",");

				int accountNumber = Integer.parseInt(linha[0]);
				int pin = Integer.parseInt(linha[1]);
				double avaibleBalance = Double.parseDouble(linha[2]);
				double totalBalance = Double.parseDouble(linha[3]);

				Account contaAux = new Account(accountNumber, pin, avaibleBalance, totalBalance);
				contas.add(contaAux);

			}

			reader.close();

		} catch (FileNotFoundException x) {

			System.err.format("FilenotFoundException: %s%n", x);
		} catch (IOException ex) {
			System.err.format("IOException: %s%n", ex);
		}

		System.out.printf("%-17s%-10s%-11s%12s\n", "Conta", "PIN", "Saldo", "Saldo Total");

		for (Account conta : contas) {
			conta.toString();
		}
	}

	/**
	 * Faz as escritas no arquivo Account.txt atualizando o saldo e saldo Total da
	 * conta respectiva Metodo de File IO
	 * 
	 * @param contas
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	public void writeFile(ArrayList<Account> contas) throws FileNotFoundException, IOException {

		Path path = Paths.get("Accounts.txt");

		try {

			BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset());
			BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset());
			contas.forEach(data -> {

				//System.out.println(data.toStringWriter());
				try {
					writer.write(data.toStringWriter());
					writer.newLine();
				} catch (IOException e) {
					System.err.format("IOException %s%n", e);
				}
			});

			writer.flush();
			writer.close();
			reader.close();

		} catch (FileNotFoundException ex) {
			System.err.format("FileNotFoundException %s%n", ex);
		} catch (IOException e) {
			System.err.format("IOException %s%n", e);
		}

	}

}
