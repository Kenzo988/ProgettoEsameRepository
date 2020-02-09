import java.awt.Color;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class InserimentoAlbumFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Traccia[] traccia;
	private Album album;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField_2;
    private String[] tipoAlbum = { "EP" , "Album"};
    private JComboBox<String> comboBox;
	private boolean flag=false;
	private int i=0;
	private JTable table_1;
	private TableColumn tColumn, tColumn2;



	public InserimentoAlbumFrame(Controller ctrl) {
		frame = new JFrame();
		frame.setTitle("Inserimento Album");
		frame.setBounds(100, 100, 450, 300);
	
		setTitle("Album Data");

		setBounds(100, 100, 590, 453);

		getContentPane().setLayout(null);
		
		JLabel lblAggiungiAlbum = new JLabel("Aggiungi Album:");
		lblAggiungiAlbum.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAggiungiAlbum.setBounds(106, 11, 166, 21);
		getContentPane().add(lblAggiungiAlbum);
		
		JLabel lblTipoAlbum = new JLabel("Tipo Album:");
		lblTipoAlbum.setBounds(106, 36, 91, 14);
		getContentPane().add(lblTipoAlbum);
			
		comboBox = new JComboBox<String>();
		comboBox.setBounds(207, 33, 126, 20);
		getContentPane().add(comboBox);
		
		for(String a:tipoAlbum) {
			comboBox.addItem(a);
			}
		
		JLabel lblNomeart = new JLabel("Nome Artista:");
		lblNomeart.setBounds(106, 64, 91, 14);
		getContentPane().add(lblNomeart);
		
		
		textField = new JTextField();
		textField.setBounds(207, 61, 126, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNomeAlbum = new JLabel("Nome Album:");
		lblNomeAlbum.setBounds(106, 91, 91, 14);
		getContentPane().add(lblNomeAlbum);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(207, 88, 126, 20);
		getContentPane().add(textField_2);
		
		JLabel lblAggiungitraccia = new JLabel("Aggiungi Traccia:");
		lblAggiungitraccia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAggiungitraccia.setBounds(106, 116, 166, 17);
		getContentPane().add(lblAggiungitraccia);
		
		JLabel lblNomeTraccia = new JLabel("Nome Traccia:");
		lblNomeTraccia.setBounds(106, 142, 91, 14);
		getContentPane().add(lblNomeTraccia);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(207, 139, 126, 20);
		getContentPane().add(textField_1);
		
		JButton btnSalvaAlbum = new JButton("Salva Album");
		btnSalvaAlbum.setBounds(343, 32, 107, 73);
		getContentPane().add(btnSalvaAlbum);
		
		btnSalvaAlbum.addActionListener(new ActionListener() {
			


			@Override
			public void actionPerformed(ActionEvent e) {
				if(flag==false)
			    

				AggiungiAlbum();
				textField.setText("");
			    textField_2.setText("");
			}

			
		});
		
		JButton btnSalvaTraccia = new JButton("Salva Traccia");
		btnSalvaTraccia.setBounds(343, 139, 107, 21);
		getContentPane().add(btnSalvaTraccia);
		
		btnSalvaTraccia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    
				aggiungiTraccia();
			}

		});
		
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.setBounds(217, 170, 91, 20);
		getContentPane().add(btnConferma);
		
		btnConferma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				organizzaDati();
				ctrl.InserisciAlbum(album.getTipoAlbum(), album.getNomeAlbum(),
						album.getNomeArtista(),  album.getDataPubb(), traccia );
			}

			
		});
		
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 202, 546, 46);
		getContentPane().add(scrollPane);
		table = new JTable();
		table.setShowGrid(false);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Tipo Album", "Nome Album", 
				                                                               "Artista","Data Pubblicazione" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		

		
		
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 252, 546, 151);
		getContentPane().add(scrollPane2);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0 Traccia", "Nome Traccia"
			}
		));
		table_1.getColumnModel().getColumn(0).setMaxWidth(100);
		
		ColumnRender();
		scrollPane.setViewportView(table);
		scrollPane2.setViewportView(table_1);

		
		
	}

	
	private void AggiungiAlbum() {
        album = new Album(comboBox.getItemAt(comboBox.getSelectedIndex()),textField_2.getText(),
                				textField.getText(), 0.0, getdata(),0 , 0);
       
        
        DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
	    if(dtm.getColumnCount()==4) {
	    dtm.addRow(new Object[] { album.getTipoAlbum(), album.getNomeAlbum(), album.getNomeArtista(), 
	    		                  album.getDataPubb()});
	    }
	    System.out.println("album aggiunto: "+album.getNomeAlbum());
        flag=true;
	}

	private Date getdata() {
		Date date = new Date(System.currentTimeMillis());
    	return date;
	}
	

	private void aggiungiTraccia() {
	    i++;
	
		DefaultTableModel dtm = (DefaultTableModel) this.table_1.getModel();
	    if(dtm.getColumnCount()==2) {
	    	dtm.addRow(new Object[] {i, textField_1.getText()});
	    }
		textField_1.setText("");

	    System.out.println("tracce aggiunte: "+textField_1.getText());
	}
	
	
	//Centra e Colora le singole celle
	class ColumnColorRenderer extends DefaultTableCellRenderer {
		   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Color backgroundColor, foregroundColor;
		
		   public ColumnColorRenderer(Color backgroundColor, Color foregroundColor) {
		      super();
		      this.backgroundColor = backgroundColor;
		      this.foregroundColor = foregroundColor;
		      this.setHorizontalAlignment(SwingConstants.CENTER);
		      
		   }
		}
	
	public void ColumnRender() {
	     for(int i=0; i<4; i++) {
	         tColumn = table.getColumnModel().getColumn(i);
	         tColumn.setCellRenderer(new ColumnColorRenderer(Color.white, Color.black));
	     
	    	 }
	
	     for(int i=0; i<2; i++) {
	         tColumn2 = table_1.getColumnModel().getColumn(i);
	         tColumn2.setCellRenderer(new ColumnColorRenderer(Color.white, Color.black));
	       	}

	}
	//prepara l'invio delle tracce al database
	private void organizzaDati() {

		traccia = new Traccia[table_1.getModel().getRowCount()];
		for(int j=0; j<table_1.getModel().getRowCount(); j++) {
			
			traccia[j]=new Traccia();
			traccia[j].n_traccia= j+1;
			traccia[j].nome_traccia= (String) table_1.getModel().getValueAt(j, 1);
			traccia[j].nome_album=album.getNomeAlbum();
			traccia[j].nome_artista=album.getNomeArtista();
		}
	}
}
