import java.util.*;

import java.sql.Connection;

public class Controller 
{
	private static Connection con = null;
	private static ConnessioneDB _myconnessione;
	
	public static void main(String[] args) 
	{
		Controller _mycontroller = new Controller();
		_myconnessione = new ConnessioneDB();
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
		/*_myconnessione.InserisciAlbumDB("a", "b", con);
		_myconnessione.InserisciTracciaDB(1, "a", "b", con);
		
		_myconnessione.InserisciAlbumDB("b", "b", con);
		_myconnessione.InserisciTracciaDB(1, "b", "b", con);*/
		
		Bho bho = new Bho(_mycontroller);
		bho.frame.setVisible(true);
	}
	
	public void InserisciAlbum() 
	{
		_myconnessione.InserisciAlbumDB("a", "b", con);
	}
	
	public void InserisciTraccia()
	{
		
	}
}
