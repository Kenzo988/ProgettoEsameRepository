import java.util.*;

import java.sql.Connection;

public class Controller 
{
	public static Connection con = null;
	
	public static void main(String[] args) 
	{
		Controller _mycontroller = new Controller();
		ConnessioneDB _myconnessione = new ConnessioneDB();
		//boolean connesso = false;
		//--------------------------------------
		do 
		{
		//login
		String utente, password;
		Scanner scanner = new Scanner(System.in);
		System.out.println("---login");
		
		System.out.println("inserisci utente:");
		utente = scanner.nextLine();
		
		System.out.println("inserisci password:");
		password = scanner.nextLine();
		
		//nuova connessione
		//connesso = _mycontroller.CreaConnessione(utente, password);
		con = _myconnessione.CreaConnessione(utente, password, con);
		scanner.close();
		}
		while(con == null);
		//---------------------------------------
		_myconnessione.InserisciAlbum("a", "b", con);
		_myconnessione.InserisciTraccia(1, "a", "b", con);
		
		_myconnessione.InserisciAlbum("b", "b", con);
		_myconnessione.InserisciTraccia(1, "b", "b", con);
	}
	
	public Controller ()
	{
		
	}
}
