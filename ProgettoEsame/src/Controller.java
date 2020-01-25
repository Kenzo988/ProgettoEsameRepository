import java.util.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.Date;

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
		
		//System.out.println("numero righe "+_mycontroller.NumeroRighe("album_table"));
		//System.out.println("numero colonne "+_mycontroller.NumeroColonne("album_table"));
		
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
	
	/**
	 * Serve ad aggionare una jtable recuperando dati da un DB, ordinando i dati in base ad una colonna.
	 * nome_tabella è il nome dell'album che poi verrà formtattato per poterlo usare correttamente nella query.
	 * nome_colonna_ordine è il nome della colonna che si vuole usare per ordinare
	*/
	public void AggiornaTabella(JTable table, String nome_tabella, String nome_colonna_ordine)//aggiorna la jtable
	{
		if(nome_tabella.equals(""))
			nome_tabella = "album_table";
		if(nome_tabella.equals("album"))
			nome_tabella = "album_table";
		else
			nome_tabella = "table_" + nome_tabella;
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				_myconnessione.NomiColonneDB(nome_tabella, con)
				));
		
		while(table.getRowCount() > 0)
			((DefaultTableModel) table.getModel()).removeRow(0);
		
		Object[][] risultati = Lettura(nome_tabella, nome_colonna_ordine);
		for(int i=0; i<risultati.length; i++) 
		{
			((DefaultTableModel) table.getModel()).addRow(risultati[i]);
		}
	}
	
	Object[][] Lettura(String nome_tabella, String nome_colonna_ordine)//array con i risultati del db 
	{
		return _myconnessione.LetturaDB(nome_tabella, nome_colonna_ordine, con);
	}
	
	public int NumeroRighe(String nome_tabella)//se serve nel caso
	{
		return _myconnessione.NumeroRigheDB(nome_tabella, con);
	}
	
	public int NumeroColonne(String nome_tabella)//se serve nel caso
	{
		return _myconnessione.NumeroColonneDB(nome_tabella, con);
	}
	
	public void InserisciAlbum(String tipo, String nome_album, String nome_artista, String livello_artista, Date data_pubblicazione, Traccia[] traccia) 
	{
		_myconnessione.InserisciAlbumDB(tipo, nome_album, nome_artista, livello_artista, data_pubblicazione, traccia, con);
	}
	
	public void InserisciTraccia(int n_traccia, String nome_traccia, String nome_album, String nome_artista)
	{
		_myconnessione.InserisciTracciaDB(n_traccia, nome_traccia, nome_album, nome_artista, con);
	}
	
	public void ModificaTraccia(int n_traccia, String nome_traccia, String nome_album)
	{
		_myconnessione.ModificaTracciaDB(n_traccia, nome_traccia, nome_album, con);
	}
	
	public void Riordina()
	{
		_myconnessione.RiordinaDB("table_a", con);
	}
}
