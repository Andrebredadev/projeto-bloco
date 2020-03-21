package edu.infnet.ATM;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Classe principal para estudo de caso ATM
 *
 */
public class App 
{	

    public static void main( String[] args ) throws IOException, FileNotFoundException
    {
        ATM atm = new ATM();
        atm.run();
    }
}
