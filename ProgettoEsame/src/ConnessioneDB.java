import java.util.*;

import java.sql.*;
import java.sql.Date;

public class ConnessioneDB
{
	public Connection CreaConnessione(String utente, String password, Connection con) 
	{

		//utente default
		if(utente.equals(""))
			utente = "postgres";
		if(password.equals(""))
			password = "password";
		
		try
		{
			//prendo i driver
			Class.forName("org.postgresql.Driver");
			System.out.println("driver trovato");
		}
		catch(ClassNotFoundException e) 
		{
			System.out.println("non trovo il driver");
		}
		
		String url = "jdbc:postgresql://localhost/ProgettoEsame";
		Properties props = new Properties();
		props.setProperty("user", utente);
		props.setProperty("password", password);
		
		try 
		{
			//creazione connessione
			con = DriverManager.getConnection(url, props);
			System.out.println("connesso");
			return con;
		}
		catch(SQLException e) 
		{
			System.err.println("connessione fallita");
			return null;
		}
	}
	
	public void InserisciAlbumDB(String nome_album, String nome_artista, Connection con) 
	{
		try 
		{
			String query;
			query = "SELECT FROM album_table WHERE nome_album = '"+ nome_album +"' AND artista = '" + nome_artista +"'";
			PreparedStatement s;
			s = con.prepareStatement(query);
			ResultSet rs = s.executeQuery();
			
			if(!rs.next()) 
			{
				// inserimento album
				query = "INSERT INTO album_table " + String.format("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

				s = con.prepareStatement(query);
				s.setString(1, "ep");// tipo album
				s.setString(2, nome_album);// nome album
				s.setString(3, nome_artista);// nome artista
				s.setString(4, "bronzo");// livello artista
				s.setInt(5, 15);// numero follower
				s.setInt(6, 125);// views totali
				s.setDate(7, Date.valueOf("2000-01-01"));// data pubblicazione
				s.setFloat(8, 15.55f);// retribuzione
				s.executeUpdate();

				s.close();
				System.out.println("album inserito");
			}
			else
				System.out.println("album già esistente");
		}
		catch(SQLException e) 
		{
			System.err.println("inserimento album fallito");
		}
	}
	
	public void InserisciTracciaDB(int traccia, String nome_album, String nome_artista, Connection con)
	{
		try
		{
			String nome_table = "table_" + nome_album;//, check;
			//check = String.format("SELECT FROM ProgettoEsame  WHERE relname = '?');");
			PreparedStatement s;
			//s = con.prepareStatement(check);
			//s.setString(1, nome_table);
			//s.executeUpdate();
			DatabaseMetaData metadata = con.getMetaData();
			ResultSet rs = metadata.getTables(null, null, nome_table, null);
			if(!rs.next()) 
			{
				try
				{
					//creazione subtabella
					String query = "CREATE TABLE " + nome_table + " (traccia integer, album text, artista text)";
					s = con.prepareStatement(query);
					s.executeUpdate();
					// aggiunta pk
					/*String nome_pk = nome_table + "_pk";
					query = "ALTER TABLE " + nome_table + " ADD CONSTRAINT " + nome_pk + " PRIMARY KEY(traccia)";
					s = con.prepareStatement(query);
					s.executeUpdate();*/
					// aggiunta fk
					String nome_fk = nome_table + "_fk";
					query = "ALTER TABLE " + nome_table + " ADD CONSTRAINT " + nome_fk
							+ " FOREIGN KEY(album, artista) REFERENCES album_table (nome_album, artista)";
					s = con.prepareStatement(query);
					s.executeUpdate();
				
					System.out.println("tabella creata");
				} 
				catch (SQLException e) 
				{
					System.err.println("creazione tabella fallito");
				}
			}
			// inserimento traccia
			try 
			{
				String query = "INSERT INTO " + nome_table + String.format(" VALUES (?, ?, ?)");

				s = con.prepareStatement(query);
				s.setInt(1, traccia);// numero traccia
				s.setString(2, nome_album);// nome album
				s.setString(3, nome_artista);// nome artista
				s.executeUpdate();

				s.close();
				System.out.println("traccia inserita");
			} 
			catch (SQLException e) 
			{
				System.err.println("inserimento fallito");
			}
		}
		catch(SQLException e) 
		{
			System.err.println("errore sql");
		}
	}
}
