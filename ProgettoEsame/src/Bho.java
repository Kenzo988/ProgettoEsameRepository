import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;

public class Bho {

	Controller ctrl;
	
	JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bho window = new Bho(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Bho(Controller controller) {
		ctrl = controller;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 808, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton AggiungiAlbumButt = new JButton("Aggiungi album");
		AggiungiAlbumButt.setBounds(10, 64, 132, 42);
		AggiungiAlbumButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Traccia[] traccia = new Traccia[3];
				traccia[0] = new Traccia(); traccia[1] = new Traccia(); traccia[2] = new Traccia();
				traccia[0].n_traccia = 1; traccia[0].nome_traccia = "non lo so"; traccia[0].nome_album = "cose"; traccia[0].nome_artista = "kenzo";
				traccia[1].n_traccia = 2; traccia[1].nome_traccia = "forse se"; traccia[1].nome_album = "cose"; traccia[1].nome_artista = "kenzo";
				traccia[2].n_traccia = 3; traccia[2].nome_traccia = "forse no"; traccia[1].nome_album = "cose"; traccia[1].nome_artista = "kenzo";
				
				ctrl.InserisciAlbum("album", "cose", "kenzo", Date.valueOf("2001-01-01"), traccia);
				ctrl.AggiornaTabella(table, "album", "artista", true);
				ctrl.AggiornaTabella(table_1, "cose_kenzo", "n", true);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(AggiungiAlbumButt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 117, 367, 133);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		ctrl.AggiornaTabella(table, "album", "artista", true);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(377, 117, 286, 133);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton AggiungiTraccie = new JButton("Aggiungi traccie");
		AggiungiTraccie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ctrl.InserisciTraccia("ciao", "cose", "kenzo");
				ctrl.AggiornaTabella(table_1, "cose_kenzo", "n", true);
				/*Traccia[] traccia = new Traccia[2];
				traccia[0] = new Traccia(); traccia[1] = new Traccia();
				traccia[0].n_traccia = 3; traccia[0].nome_traccia = "non lo so"; traccia[0].nome_album = "a"; traccia[0].nome_artista = "b";
				traccia[1].n_traccia = 4; traccia[1].nome_traccia = "forse se"; traccia[1].nome_album = "a"; traccia[1].nome_artista = "b";
				
				for(int i = 0; i < traccia.length; i++)
				{
					ctrl.InserisciTraccia(traccia[i].n_traccia, traccia[i].nome_traccia, traccia[i].nome_album, traccia[i].nome_artista);
				}
				ctrl.AggiornaTabella(table_1, "table_a", "n");*/
			}
		});
		AggiungiTraccie.setBounds(152, 64, 124, 42);
		frame.getContentPane().add(AggiungiTraccie);
		
		JButton ModificaTracciaButt = new JButton("Modifica traccia");
		ModificaTracciaButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ctrl.ModificaTraccia(1, "prima_canzone", "cose_kenzo");
				ctrl.AggiornaTabella(table_1, "cose_kenzo", "n", true);
			}
		});
		ModificaTracciaButt.setBounds(286, 64, 139, 42);
		frame.getContentPane().add(ModificaTracciaButt);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(673, 117, 119, 133);
		frame.getContentPane().add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		ctrl.AggiornaTabella(table_2, "artista_table", "followers_artista", true);
		
		JButton EliminaArtista = new JButton("EliminaArtista");
		EliminaArtista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.EliminaArtista("kenzo");
				ctrl.AggiornaTabella(table_2, "artista_table", "followers_artista", true);
			}
		});
		EliminaArtista.setBounds(673, 64, 119, 42);
		frame.getContentPane().add(EliminaArtista);
		
		JButton AggiungiArtista = new JButton("Aggiungi Artista");
		AggiungiArtista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ctrl.InserisciArtista("kenzo");
				ctrl.AggiornaTabella(table_2, "artista_table", "followers_artista", true);
			}
		});
		AggiungiArtista.setBounds(673, 11, 119, 42);
		frame.getContentPane().add(AggiungiArtista);
		
		JButton EliminaTraccia = new JButton("Elimina Traccia");
		EliminaTraccia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ctrl.EliminaTraccia(1, "cose", "kenzo");
				ctrl.AggiornaTabella(table_1, "cose_kenzo", "n", true);
			}
		});
		EliminaTraccia.setBounds(435, 64, 124, 42);
		frame.getContentPane().add(EliminaTraccia);
		
		JButton EliminaAlbum = new JButton("Elimina Album");
		EliminaAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ctrl.EliminaAlbum("cose", "kenzo");
				ctrl.AggiornaTabella(table, "album", "artista", true);
			}
		});
		EliminaAlbum.setBounds(10, 23, 132, 35);
		frame.getContentPane().add(EliminaAlbum);
	}
}
