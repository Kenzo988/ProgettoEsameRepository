import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
	
	Connection con = null;

	/*public static void main(String[] args) 
	{
		
		
		
		try
		{
			//creazione subtabella
			String query = "CREATE TABLE album1(traccia varchar(30), album text, artista text)";
			PreparedStatement s = con.prepareStatement(query);
			s.executeUpdate();
			//aggiunta pk
			query = "ALTER TABLE album1 ADD CONSTRAINT album1_pk PRIMARY KEY(album, artista)";
			s = con.prepareStatement(query);
			s.executeUpdate();
			//aggiunta fk
			query = "ALTER TABLE album1 ADD CONSTRAINT album1_fk FOREIGN KEY(album, artista) REFERENCES album_table (nome_album, artista)";
			s = con.prepareStatement(query);
			s.executeUpdate();
			
			s.close();
			System.out.println("Table creata");
		}
		catch(SQLException e) 
		{
			System.err.println("Creazione table fallito");
		}
	}*/
	
	public boolean CreaConnessione(String utente, String password) 
	{
		try
		{
			//prendo i driver
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver trovato");
		}
		catch(ClassNotFoundException e) 
		{
			System.out.println("Non trovo il driver");
		}
		
		String url = "jdbc:postgresql://localhost/ProgettoEsame";
		Properties props = new Properties();
		props.setProperty("user", utente);
		props.setProperty("password", password);
		
		try 
		{
			//creazione connessione
			con = DriverManager.getConnection(url, props);
			System.out.println("Connesso");
			return true;
		}
		catch(SQLException e) 
		{
			System.err.println("Connessione fallita");
			return false;
		}
	}
	
	public void InserisciAlbum(String nome_album, String nome_artista) 
	{
		try 
		{
			//inserimento riga
			String query = "INSERT INTO album_table " + String.format("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
			PreparedStatement s = con.prepareStatement(query);
			s.setString(1, "ep");//tipo album
			s.setString(2, nome_album);//nome album
			s.setString(3, nome_artista);//nome artista
			s.setString(4, "bronzo");//livello artista
			s.setInt(5, 15);//numero follower
			s.setInt(6, 125);//views totali
			s.setDate(7, Date.valueOf("2000-01-01"));//data pubblicazione
			s.setFloat(8, 15.55f);//retribuzione
			s.executeUpdate();
			
			s.close();
			System.out.println("Inserito");
		}
		catch(SQLException e) 
		{
			System.err.println("Inserimento fallito");
		}
		
	}
}
