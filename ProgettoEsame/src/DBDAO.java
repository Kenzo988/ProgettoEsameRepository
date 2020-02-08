import java.sql.Date;

import javax.swing.JTable;

public interface DBDAO 
{
	public void AggiornaTabella(JTable table, String nome_tabella, String nome_colonna_ordine, boolean crescente);
	public int NumeroRighe(String nome_tabella);
	public int NumeroColonne(String nome_tabella);
	public void InserisciAlbum(String tipo, String nome_album, String nome_artista, Date data_pubblicazione, Traccia[] traccia);
	public void InserisciTraccia(String nome_traccia, String nome_album, String nome_artista);
	public void ModificaTraccia(int n_traccia, String nome_traccia, String nome_album);
	public void InserisciArtista(String nome_artista);
	public void EliminaArtista(String nome_artista);
	public void EliminaAlbum(String nome_album, String nome_artista);
	public void EliminaTraccia(int n_traccia, String nome_album, String nome_artista);
}