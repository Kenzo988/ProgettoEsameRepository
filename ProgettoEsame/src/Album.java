import java.sql.Date;


public class Album {
	//
	private String TipoAlbum;
	private String NomeArtista;
	private Double retribuzioneAlbum;
	private Date dataPubbl;
	private int Views;
	private int Followers;
	private String NomeAlbum;
	
	public Album(String TipoAlbum,String NomeAlbum, String NomeArtista, Double retribuzioneAlbum,
			Date dataPubbl, int Views, int Followers) {
		
		setTipoAlbum(TipoAlbum);
		setNomeAlbum(NomeAlbum);
		setNomeArtista(NomeArtista);
		setRetribuzioneAlbum(retribuzioneAlbum);
		setDataPubblicazione(dataPubbl);
		setViews(Views);
		setFollowers(Followers);
		
	}
	
	private void setNomeAlbum(String album) {
		this.NomeAlbum=album;
	}

	private void setFollowers(int followers2) {

		this.Followers=followers2;
	}

	private void setViews(int views2) {

		this.Views=views2;
	}

	private void setDataPubblicazione(Date dataPubbl2) {

		this.dataPubbl=dataPubbl2;
	}

	private void setRetribuzioneAlbum(Double retribuzioneAlbum2) {

		this.retribuzioneAlbum=retribuzioneAlbum2;
	}

	private void setNomeArtista(String nomeArtista2) {

		this.NomeArtista=nomeArtista2;
	}

	private void setTipoAlbum(String tipoAlbum2) {

		this.TipoAlbum=tipoAlbum2;
	}

	public String getTipoAlbum() {
		// TODO Auto-generated method stub
		return TipoAlbum;
	}
	
	public String getNomeAlbum() {
		return NomeAlbum;
	}

	public String getNomeArtista() {
		// TODO Auto-generated method stub
		return NomeArtista;
	}

	public Double getRetribuzioneAlbum() {
		// TODO Auto-generated method stub
		return retribuzioneAlbum;
	}

	public Date getDataPubb() {
		// TODO Auto-generated method stub
		return dataPubbl;
	}

	public int getViewsTot() {
		// TODO Auto-generated method stub
		return Views;
	}

	public int getFollowers() {
		// TODO Auto-generated method stub
		return Followers;
	}
	
	public String getLivelloArtista() {
		return null;
	}

}
