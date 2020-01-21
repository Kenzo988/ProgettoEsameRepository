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

	public static void main(String[] args) 
	{
		Connection con = null;
		
		try
		{
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver trovato");
		}
		catch(ClassNotFoundException e) 
		{
			System.out.println("Non trovo il driver");
		}
		
		String url = "jdbc:postgresql://localhost/ProgettoEsame";
		Properties props = new Properties();
		props.setProperty("user","postgres");
		props.setProperty("password","password");
		
		try 
		{
			con = DriverManager.getConnection(url, props);
			System.out.println("Connesso");
		}
		catch(SQLException e) 
		{
			System.err.println("Connessione fallita");
		}
		
		try 
		{
			String query = "INSERT INTO album_table " + String.format("VALUES ('%s', '%s', '%s', '%s', %d, %d, ?, ?)", "album","Album1", "Artista1", "bronzo", 15, 124);
			
			PreparedStatement s = con.prepareStatement(query);
			s.setDate(1, Date.valueOf("2000-01-01"));
			s.setFloat(2, 15.55f);
			s.executeUpdate();
			
			s.close();
			System.out.println("inserito");
		}
		catch(SQLException e) 
		{
			System.err.println("inserimento fallito");
		}
	}

}
