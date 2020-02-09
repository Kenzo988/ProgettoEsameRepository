import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AlbumFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel frame;
	private JTable table;
	private final JButton btnAggiungialbum = new JButton("AggiungiAlbum");


	public AlbumFrame(Controller main) {
		InserimentoAlbumFrame frame2 = new InserimentoAlbumFrame(main);
		setTitle("Album Data");
		setBounds(100, 100, 590, 544);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame = new JPanel();
		frame.setForeground(Color.BLACK);
		frame.setBackground(Color.WHITE);
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(frame);
		frame.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1052, 458);
		frame.add(panel);
		
		
		
		/*
		DefaultTableModel tab = new DefaultTableModel(new Object[][] {}, new String[] { 
				"Tipo Album", "Nome Album", "Artista", "Lv Artista", "Followers", 
				"Views Tot.", "Data Pubblicazione", "Retribuzione" }) {
			 
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class,
					Integer.class, Integer.class, String.class, Double.class};

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
		}
			public boolean isCellEditable(int row, int column)
		    {
		      return false;//celle non editabili
		    }
		};*/
		table = new JTable();
	
		main.AggiornaTabella(table, "album", "artista", true);
		System.out.println("aggiornato");

		
		panel.add(table);
 		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(1072, 11, 288, 635);
		frame.add(panel_1);
		btnAggiungialbum.setBounds(10, 474, 207, 31);
		frame.add(btnAggiungialbum);
		
		btnAggiungialbum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame2.setVisible(true);
			}
		});

	}
	
	public void AggiungiAlbum(ArrayList<Album> list2) {
		// TODO Auto-generated method stub
		for(Album a:list2) {
			
		DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
	    if(dtm.getColumnCount()==8) {
	    dtm.addRow(new Object[] { a.getTipoAlbum(), a.getTipoAlbum(), a.getNomeArtista(),
	    		a.getFollowers(), a.getViewsTot(), a.getDataPubb(), a.getRetribuzioneAlbum() });
	    }

		}
	}
}
