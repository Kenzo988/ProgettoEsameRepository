import java.util.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller 
{
	private static Connection con = null;
	private ConnessioneDB _myconnessione;
	
	public static void main(String[] args) 
	{
		Controller _mycontroller = new Controller();
		//--------------------------------------
		_mycontroller.Login();
		//---------------------------------------
		/*_myconnessione.InserisciAlbumDB("a", "b", con);
		_myconnessione.InserisciTracciaDB(1, "a", "b", con);
		
		_myconnessione.InserisciAlbumDB("b", "b", con);
		_myconnessione.InserisciTracciaDB(1, "b", "b", con);*/
		
		System.out.println("numero righe "+_mycontroller.NumeroRighe("album_table"));
		System.out.println("numero colonne "+_mycontroller.NumeroColonne("album_table"));
		
		Bho bho = new Bho(_mycontroller);
		bho.frame.setVisible(true);
	}
	
	public Controller() 
	{
		_myconnessione = new ConnessioneDB();
	}
	
	void Login() 
	{
		do 
		{
			//recupero credenziali
			String utente, password;
			Scanner scanner = new Scanner(System.in);
			System.out.println("---login");

			System.out.println("inserisci utente:");
			utente = scanner.nextLine();

			System.out.println("inserisci password:");
			password = scanner.nextLine();

			// nuova connessione
			con = _myconnessione.CreaConnessione(utente, password, con);
			scanner.close();
		}
		while(con == null);
	}
	
	public void AggiornaTabella(JTable table, String nome_tabella)//aggiorna la jtable
	{
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				_myconnessione.NomiColonneDB(nome_tabella, con)
				));
		
		while(table.getRowCount() > 0)
			((DefaultTableModel) table.getModel()).removeRow(0);
		
		Object[][] risultati = Lettura(nome_tabella);
		for(int i=0; i<risultati.length; i++) 
		{
			((DefaultTableModel) table.getModel()).addRow(risultati[i]);
		}
	}
	
	Object[][] Lettura(String nome_tabella)//array con i risultati del db 
	{
		return _myconnessione.LetturaDB(nome_tabella, con);
	}
	
	public int NumeroRighe(String nome_tabella)//se serve nel caso
	{
		return _myconnessione.NumeroRigheDB(nome_tabella, con);
	}
	
	public int NumeroColonne(String nome_tabella)//se serve nel caso
	{
		return _myconnessione.NumeroColonneDB(nome_tabella, con);
	}
	
	public void InserisciAlbum() 
	{
		_myconnessione.InserisciAlbumDB("a", "b", con);
	}
	
	public void InserisciTraccia()
	{
		
	}
}
