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

		//login frame
		LoginFrame login_frame = new LoginFrame(_mycontroller);
		login_frame.setVisible(true);
	}
	
	public Controller() 
	{
		_myconnessione = new ConnessioneDB();
	}
	
	/**effettua il login al database con un nome utente e password
	*/
	public boolean Login(String utente, String password) 
	{
		con = _myconnessione.CreaConnessione(utente, password, con);
		if(con != null)
			return true;
		else
			return false;
		
		/*do 
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
		while(con == null);*/
	}
	
	public void OpenAlbumFrame(Controller _mycontroller)
	{
		AlbumFrame album_frame = new AlbumFrame(_mycontroller);
		album_frame.setVisible(true);
	}
	
	/**
	 * Serve ad aggionare una jtable recuperando dati da un DB, ordinando i dati in base ad una colonna.
	 * nome_tabella è il nome dell'album che poi verrà formtattato per poterlo usare correttamente nella query.
	 * (il nome dell'album è compreso del nome dell'artista con un underscore es(nomealbum_nomeartista))
	 * nome_colonna_ordine è il nome della colonna che si vuole usare per ordinare
	 * crescente è per specificare il tipo di ordine
	*/
	public void AggiornaTabella(JTable table, String nome_tabella, String nome_colonna_ordine, boolean crescente)//aggiorna la jtable
	{
		if(nome_tabella.equals(""))
			nome_tabella = "album_table";
		if(nome_tabella.equals("album"))
			nome_tabella = "album_table";
		else if(!nome_tabella.equals("artista_table"))
			nome_tabella = "table_" + nome_tabella;
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				_myconnessione.NomiColonneDB(nome_tabella, con)
				));
		
		while(table.getRowCount() > 0)
			((DefaultTableModel) table.getModel()).removeRow(0);
		
		Object[][] risultati = Lettura(nome_tabella, nome_colonna_ordine, crescente);
		for(int i=0; i<risultati.length; i++) 
		{
			((DefaultTableModel) table.getModel()).addRow(risultati[i]);
		}
	}
	/** restituscie un array object con i risultati della lettura dalla tabella
	*/
	public Object[][] Lettura(String nome_tabella, String nome_colonna_ordine, boolean crescente)//array con i risultati del db 
	{
		return _myconnessione.LetturaDB(nome_tabella, nome_colonna_ordine, crescente, con);
	}
	
	/** restituisce il numero di righe di una tabella (nomeTabella_nomeArtista)
	*/
	public int NumeroRighe(String nome_tabella)//se serve nel caso
	{
		return _myconnessione.NumeroRigheDB(nome_tabella, con);
	}
	
	/** restituisce il numero di colonne di una tabella (nomeTabella_nomeArtista)
	*/
	public int NumeroColonne(String nome_tabella)//se serve nel caso
	{
		return _myconnessione.NumeroColonneDB(nome_tabella, con);
	}
	
	/** inserisce un album andando compreso di traccie
	 * tipo indica se è un ep/singolo/album
	*/
	public void InserisciAlbum(String tipo, String nome_album, String nome_artista, Date data_pubblicazione, Traccia[] traccia) 
	{
		_myconnessione.InserisciAlbumDB(tipo, nome_album, nome_artista, data_pubblicazione, traccia, con);
	}
	
	/** inserisce una traccia singolo in un album
	*/
	public void InserisciTraccia(String nome_traccia, String nome_album, String nome_artista)
	{
		_myconnessione.InserisciTracciaDB(nome_traccia, nome_album, nome_artista, con);
	}
	
	/** modifica il nome di una traccia
	*/
	public void ModificaTraccia(int n_traccia, String nome_traccia, String nome_album, String nome_artista)
	{
		_myconnessione.ModificaTracciaDB(n_traccia, nome_traccia, nome_album, nome_artista, con);
	}
	
	/** inserisce un nuovo artista
	*/
	public void InserisciArtista(String nome_artista)
	{
		_myconnessione.InserisciArtista(nome_artista, con);
	}
	
	/** elimina un artista e tutti gli album ad esso collegati
	*/
	public void EliminaArtista(String nome_artista)
	{
		_myconnessione.EliminaArtista(nome_artista, con);
	}
	
	/** elimina l'album e la tabella delle traccie
	*/
	public void EliminaAlbum(String nome_album, String nome_artista)
	{
		_myconnessione.EliminaAlbum(nome_album, nome_artista, con);
	}
	
	/** elimina una singola traccia
	*/
	public void EliminaTraccia(int n_traccia, String nome_album, String nome_artista)
	{
		_myconnessione.EliminaTraccia(n_traccia, nome_album, nome_artista, con);
	}
}
