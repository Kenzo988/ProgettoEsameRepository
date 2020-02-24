import java.util.*;

import java.sql.*;
import java.sql.Date;

public class ConnessioneDB
{
	public Connection CreaConnessione(String utente, String password, Connection con) 
	{

		//utente default
		/*if(utente.equals(""))
			utente = "postgres";
		if(password.equals(""))
			password = "password";*/
		
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
	
	public Object[][] LetturaDB(String nome_tabella, String nome_colonna_ordine, boolean crescente, Connection con)//restituisce i valori del db in un array 2d
	{
		try 
		{
			String specifica_ordine;
			if(crescente)
				specifica_ordine = " ASC";
			else
				specifica_ordine = " DESC";
			
			String query = "SELECT * FROM " + nome_tabella + " ORDER BY "+ nome_colonna_ordine + specifica_ordine + ";";
			
			PreparedStatement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			int n_colonne = NumeroColonneDB(nome_tabella, con);
			Object[][] risultati = new Object[NumeroRigheDB(nome_tabella, con)][n_colonne];

			while(rs.next())
			{
				for(int j=0; j<n_colonne; j++)
				{
					risultati[rs.getRow()-1][j] = rs.getString(j+1);
				}
			}
			rs.close();
			
			return risultati;
		}
		catch(SQLException e) 
		{
			System.err.println("lettura fallita");
			return null;
		}
	}
	
	public int NumeroRigheDB(String nome_tabella, Connection con)//restituisce il numero di righe
	{
		try 
		{
			String query = "SELECT COUNT(*) FROM " + nome_tabella + ";";
			
			PreparedStatement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			rs.next();
			
			return rs.getInt(1);
		}
		catch(SQLException e) 
		{
			System.err.println("lettura DB fallita");
			return 0;
		}
	}
	
	public String[] NomiColonneDB(String nome_tabella, Connection con)//resituisce i nomi delle colonne
	{
		try 
		{
			ResultSetMetaData rsmd = GetRSMetaData(nome_tabella, con);
			
			int n_colonne = rsmd.getColumnCount();
			String[] colonne = new String[n_colonne];
			
			for(int i=0; i<n_colonne; i++)
			{
				colonne[i]=rsmd.getColumnName(i+1);
			}
			
			return colonne;
		}
		catch(SQLException e) 
		{
			System.err.println("lettura fallita");
			return null;
		}
	}
	
	public int NumeroColonneDB(String nome_tabella, Connection con)//restituisce il numero di colonne
	{
		try 
		{
			ResultSetMetaData rsmd = GetRSMetaData(nome_tabella, con);
			
			int n_colonne = rsmd.getColumnCount();
			
			return n_colonne;
		}
		catch(SQLException e) 
		{
			System.err.println("lettura fallita");
			return 0;
		}
	}
	
	ResultSetMetaData GetRSMetaData (String nome_tabella, Connection con)
	{
		try 
		{
			String query = "SELECT * FROM " + nome_tabella + ";";
			
			PreparedStatement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			
			return rs.getMetaData();
		}
		catch(SQLException e) 
		{
			System.err.println("lettura fallita");
			return null;
		}
	}
	
	public boolean InserisciAlbumDB(String tipo, String nome_album, String nome_artista, Date data_pubblicazione, Traccia[] traccia, Connection con) 
	{
		try 
		{
			String query;
			PreparedStatement s;
			
			query = "SELECT FROM artista_table WHERE nome_artista = '" + nome_artista + "'";
			s = con.prepareStatement(query);
			ResultSet rs = s.executeQuery();
			if(!rs.next())
			{
				System.err.println("artista non presente");
				return false;
			}
			
			query = "SELECT FROM album_table WHERE nome_album = '"+ nome_album +"' AND artista = '" + nome_artista +"'";
			s = con.prepareStatement(query);
			rs = s.executeQuery();
			
			if(!rs.next()) 
			{
				// inserimento album
				query = "INSERT INTO album_table (tipo, nome_album, artista, views_totali, data_pubblicazione)" + String.format("VALUES (?, ?, ?, 0, ?)");

				s = con.prepareStatement(query);
				s.setString(1, tipo);// tipo album
				s.setString(2, nome_album);// nome album
				s.setString(3, nome_artista);// nome artista
				s.setDate(4, data_pubblicazione/*Date.valueOf("2000-01-01")*/);// data pubblicazione
				s.executeUpdate();

				s.close();
				System.out.println("album inserito");
			}
			else
			{
				System.out.println("album già esistente");
				return false;
			}
		}
		catch(SQLException e) 
		{
			System.err.println("inserimento album fallito");
			return false;
		}
		
		for(int i = 0; i < traccia.length; i++)
		{
			if(!InserisciTracciaDB(traccia[i].nome_traccia, nome_album, nome_artista, con))
			{
				System.err.println("errore inserimento traccia");
				return false;
			}
		}
		
		return true;
	}
	
	public boolean InserisciTracciaDB(String nome_traccia, String nome_album, String nome_artista, Connection con)
	{
		try
		{
			String query = "SELECT FROM album_table WHERE nome_album = '"+ nome_album +"' AND artista = '" + nome_artista +"'";
			PreparedStatement s;
			s = con.prepareStatement(query);
			ResultSet rs = s.executeQuery();
			
			if(rs.next())
			{
				String nome_table = "table_" + nome_album + "_" + nome_artista;
				DatabaseMetaData metadata = con.getMetaData();
				rs = metadata.getTables(null, null, nome_table, null);
				if (!rs.next()) 
				{
					try 
					{
						// creazione tabella
						query = "CREATE TABLE " + nome_table
								+ " (n integer unique primary key, traccia text , album text, artista text, views_traccia integer)";
						s = con.prepareStatement(query);
						s.executeUpdate();
						// aggiunta fk
						String nome_fk = nome_table + "_fk";
						query = "ALTER TABLE " + nome_table + " ADD CONSTRAINT " + nome_fk
								+ " FOREIGN KEY(album, artista) REFERENCES album_table (nome_album, artista) ON DELETE CASCADE";
						s = con.prepareStatement(query);
						s.executeUpdate();
						s.close();
						System.out.println("tabella creata");

						// inserimento trigger
						InserisciTriggerTracciaTable(nome_table, nome_album, nome_artista, con);
					} 
					catch (SQLException e)
					{
						System.err.println("creazione tabella fallito");
						e.printStackTrace();
						return false;
					}
				}
				// inserimento traccia
				try 
				{
					query = "SELECT traccia FROM " + nome_table + " WHERE traccia = ?";
					s = con.prepareStatement(query);
					s.setString(1, nome_traccia);
					rs = s.executeQuery();
					if (!rs.next()) 
					{
						query = "INSERT INTO " + nome_table + String.format(" VALUES (?, ?, ?, ?, 0)");
						// + "SELECT FROM " + nome_table + " WHERE traccia <> " + nome_traccia;

						s = con.prepareStatement(query);
						s.setInt(1, NumeroRigheDB(nome_table, con) + 1);// numero traccia
						s.setString(2, nome_traccia);// nome traccia
						s.setString(3, nome_album);// nome album
						s.setString(4, nome_artista);// nome artista
						s.executeUpdate();

						s.close();
						System.out.println("traccia inserita");
					}
					else
					{
						System.out.println("traccia già presente");
						return false;
					}
				}
				catch (SQLException e) 
				{
					System.err.println("inserimento fallito");
					e.printStackTrace();
					return false;
				}
			}
			else
			{
				System.err.println("album non presente");//return false
			}
		}
		catch(SQLException e) 
		{
			System.err.println("errore sql");
			return false;
		}
		
		return true;
	}
	
	void InserisciTriggerTracciaTable(String nome_table, String nome_album, String nome_artista, Connection con) 
	{
		//creazione function
		String nome_function = "function_" + nome_table;
		try
		{
			String query = 
					"CREATE OR REPLACE FUNCTION public." + nome_function + "() RETURNs trigger LANGUAGE 'plpgsql' VOLATILE COST 100 AS  "+ 
					"$BODY$BEGIN " + 
					"UPDATE album_table " + 
					"SET views_totali = (SELECT SUM(views_traccia) FROM "+ nome_table +") " + 
					"WHERE nome_album = '" + nome_album +"' AND artista = '" + nome_artista + "'; " + 
					"RETURN NEW; " + 
					"END;$BODY$;" +
					"ALTER FUNCTION public." + nome_function + "() " + 
					"OWNER TO postgres; ";

			PreparedStatement s = con.prepareStatement(query);
			
			s.executeUpdate();
			s.close();
		}
		catch(SQLException e)
		{
			System.err.println("inserimento function fallito");
			e.printStackTrace();
		}
		
		//creazione trigger
		try
		{
			String query =
					"CREATE TRIGGER " + "trigger_"+ nome_table + " " + 
					"AFTER UPDATE OF views_traccia " + 
					"ON " + nome_table + " "+ 
					"FOR EACH ROW " + 
					"EXECUTE PROCEDURE public." + nome_function +"();";
			
			PreparedStatement s = con.prepareStatement(query);
			
			s.executeUpdate();
			s.close();
		}
		catch(SQLException e)
		{
			System.err.println("inserimento trigger fallito");
			e.printStackTrace();
		}
	}
	
	public void ModificaTracciaDB(int n_traccia, String nome_traccia, String nome_album, String nome_artista, Connection con) //per modificare una traccia
	{
		try
		{
			String nome_table = "table_" + nome_album + "_" + nome_artista;
			String query = "UPDATE " + nome_table + String.format(" SET traccia = ?") + " WHERE n = " + n_traccia;
			PreparedStatement s = con.prepareStatement(query);
			s.setString(1, nome_traccia);
			s.executeUpdate();
			s.close();
			System.out.println("update traccia eseguito");
		}
		catch(SQLException e) 
		{
			System.err.println("errore update");
			e.printStackTrace();
		}
	}
	
	public boolean InserisciArtista(String nome_artista, Connection con)
	{
		try
		{
			String query = "SELECT * FROM artista_table WHERE nome_artista = ?";
			PreparedStatement s = con.prepareStatement(query);
			s.setString(1, nome_artista);
			ResultSet rs = s.executeQuery();
			if(!rs.next())
			{
				query = "INSERT INTO artista_table VALUES(?, 0)";
				s = con.prepareStatement(query);
				s.setString(1, nome_artista);
				s.executeUpdate();
				s.close();
				System.out.println("artista inserito");
			}
			else
			{
				System.out.println("artista già esistente");
				return false;
			}
			rs.close();
		}
		catch(SQLException e)
		{
			System.err.println("errore insert artista");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void EliminaArtista(String nome_artista, Connection con)
	{
		//drop album
		try
		{
			String query = "SELECT * FROM album_table WHERE artista = ?";
			PreparedStatement s = con.prepareStatement(query);
			s.setString(1, nome_artista);
			ResultSet rs = s.executeQuery();
			while(rs.next())
			{
				//drop table
				query = "DROP TABLE " + "table_" + rs.getString(2) + "_" + nome_artista;
				s = con.prepareStatement(query);
				s.executeUpdate();
				//drop function
				query = "DROP FUNCTION " + "function_table_" + rs.getString(2) + "_" + nome_artista;
				s = con.prepareStatement(query);
				s.executeUpdate();
			}
			s.close();
		}
		catch(SQLException e)
		{
			System.err.println("errore recupero ed eliminazione album");
			e.printStackTrace();
		}
		//elimina arista
		try
		{
			String query = "DELETE FROM artista_table WHERE nome_artista = ?";
			PreparedStatement s = con.prepareStatement(query);
			s.setString(1, nome_artista);
			s.executeUpdate();
			s.close();
			System.out.println("artista eliminato");
		}
		catch(SQLException e)
		{
			System.err.println("errore delete artista");
			e.printStackTrace();
		}
	}
	
	public void EliminaAlbum(String nome_album, String nome_artista, Connection con)
	{
		try 
		{
			String query;
			PreparedStatement s;
			// drop table
			query = "DROP TABLE " + "table_" + nome_album + "_" + nome_artista;
			s = con.prepareStatement(query);
			s.executeUpdate();
			// drop function
			query = "DROP FUNCTION " + "function_table_" + nome_album + "_" + nome_artista;
			s = con.prepareStatement(query);
			s.executeUpdate();
			//delete album
			query = "DELETE FROM album_table WHERE nome_album = ? AND artista = ?";
			s = con.prepareStatement(query);
			s.setString(1, nome_album);
			s.setString(2, nome_artista);
			s.executeUpdate();
			
			s.close();
			System.out.println("album eliminato");			
		} catch (SQLException e) {
			System.err.println("errore recupero ed eliminazione album");
			e.printStackTrace();
		}
	}
	
	public void EliminaTraccia(int n_traccia, String nome_album, String nome_artista, Connection con)
	{
		try
		{
			//elimino
			String nome_table = "table_" + nome_album + "_" + nome_artista;
			String query = "DELETE FROM " + nome_table + " WHERE n = ?";
			PreparedStatement s = con.prepareStatement(query);
			s.setInt(1, n_traccia);
			s.execute();
			//aggiorno numero traccia
			for(int i = 0; i <= NumeroRigheDB(nome_table, con); i++) 
			{
				query = "UPDATE " + nome_table + " SET n = n - 1" + " WHERE n = ? + ?";
				s = con.prepareStatement(query);
				s.setInt(1, n_traccia);
				s.setInt(2, i);
				s.executeUpdate();
			}
			s.close();
		}
		catch(SQLException e)
		{
			System.err.println("errore delete traccia");
			e.printStackTrace();
		}
	}
}
