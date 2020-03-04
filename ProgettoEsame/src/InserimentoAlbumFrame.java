import java.awt.Color;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JFrame frame;
	private JTextField nomeTraccia;
	private JTable table;
	private JTextField nomeAlbum;
    private String[] tipoAlbum = { "ep" , "album", "singolo"};
    private String[] nomeArtisti;
    private JComboBox<String> comboBox;
	private int i=0;
	private JTable table_1;
	private TableColumn tColumn, tColumn2;
	private String nAlbum;
	private String nArtista;
	private JComboBox<String> comboBox_1;



	public InserimentoAlbumFrame(Controller ctrl, AlbumFrame albumFrame) {
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setBackground(Color.BLACK);
		frame = new JFrame();
		frame.setTitle("Inserimento Album");
	
		setTitle("Album Data");

		setBounds(100, 100, 582, 453);

		getContentPane().setLayout(null);
		
		JLabel lblAggiungiAlbum = new JLabel("Aggiungi Album:");
		lblAggiungiAlbum.setForeground(Color.WHITE);
		lblAggiungiAlbum.setBackground(Color.BLACK);
		lblAggiungiAlbum.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAggiungiAlbum.setBounds(10, 11, 166, 21);
		getContentPane().add(lblAggiungiAlbum);
		
		JLabel lblTipoAlbum = new JLabel("Tipo Album:");
		lblTipoAlbum.setForeground(Color.WHITE);
		lblTipoAlbum.setBackground(Color.BLACK);
		lblTipoAlbum.setBounds(10, 36, 91, 14);
		getContentPane().add(lblTipoAlbum);
			
		comboBox = new JComboBox<String>();
		comboBox.setBounds(99, 33, 98, 20);
		getContentPane().add(comboBox);
		
		for(String a:tipoAlbum) {
			comboBox.addItem(a);
			}
		
		JLabel lblNomeart = new JLabel("Nome Artista:");
		lblNomeart.setForeground(Color.WHITE);
		lblNomeart.setBackground(Color.BLACK);
		lblNomeart.setBounds(10, 64, 91, 14);
		getContentPane().add(lblNomeart);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(99, 61, 237, 20);
		getContentPane().add(comboBox_1);
		
		initializeNomeArtisti(ctrl);
		for(String a:nomeArtisti) {
			comboBox_1.addItem(a);
			}

		
		JLabel lblNomeAlbum = new JLabel("Nome Album:");
		lblNomeAlbum.setForeground(Color.WHITE);
		lblNomeAlbum.setBackground(Color.BLACK);
		lblNomeAlbum.setBounds(10, 91, 91, 14);
		getContentPane().add(lblNomeAlbum);
		
		nomeAlbum = new JTextField();
		nomeAlbum.setColumns(10);
		nomeAlbum.setBounds(99, 88, 340, 20);
		getContentPane().add(nomeAlbum);
		
		JLabel lblAggiungitraccia = new JLabel("Aggiungi Traccia:");
		lblAggiungitraccia.setForeground(Color.WHITE);
		lblAggiungitraccia.setBackground(Color.BLACK);
		lblAggiungitraccia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAggiungitraccia.setBounds(10, 116, 166, 17);
		getContentPane().add(lblAggiungitraccia);
		
		JLabel lblNomeTraccia = new JLabel("Nome Traccia:");
		lblNomeTraccia.setForeground(Color.WHITE);
		lblNomeTraccia.setBackground(Color.BLACK);
		lblNomeTraccia.setBounds(10, 142, 91, 14);
		getContentPane().add(lblNomeTraccia);
		
		nomeTraccia = new JTextField();
		nomeTraccia.setColumns(10);
		nomeTraccia.setBounds(99, 139, 340, 20);
		getContentPane().add(nomeTraccia);
		
		JButton btnSalvaAlbum = new JButton("Salva Album");
		btnSalvaAlbum.setForeground(Color.WHITE);
		btnSalvaAlbum.setBackground(Color.BLACK);
		btnSalvaAlbum.setBounds(449, 33, 107, 73);
		getContentPane().add(btnSalvaAlbum);
		
		btnSalvaAlbum.addActionListener(new ActionListener() {
			


			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		        if(dtm.getRowCount()>0)
		        dtm.removeRow(0);
				AggiungiAlbum();
				
					
			}});
		
		JButton btnSalvaTraccia = new JButton("Salva Traccia");
		btnSalvaTraccia.setForeground(Color.WHITE);
		btnSalvaTraccia.setBackground(Color.BLACK);
		btnSalvaTraccia.setBounds(449, 140, 107, 21);
		getContentPane().add(btnSalvaTraccia);
		
		btnSalvaTraccia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    
				aggiungiTraccia();
			}	

		});
		
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.setForeground(Color.WHITE);
		btnConferma.setBackground(Color.BLACK);
		btnConferma.setBounds(205, 170, 141, 21);
		getContentPane().add(btnConferma);
		
		btnConferma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag=false;

				organizzaDati();
				
				if(nomeAlbum.getText().length() >0 && table_1.getRowCount()>0) {
					
				flag=ctrl.InserisciAlbum(comboBox.getItemAt(comboBox.getSelectedIndex()),
						            nAlbum,comboBox_1.getItemAt(comboBox_1.getSelectedIndex()), 
						            getDate(), traccia );
				}else {
					JOptionPane.showMessageDialog(new JFrame(),
						    "Reinserire valori", "Errore Inserimento",JOptionPane.ERROR_MESSAGE);
							}
				
				if(flag==false){
					JOptionPane.showMessageDialog(new JFrame(),
				    "Artista non presente, aggiungilo prima alla lista", "errore inserimento",JOptionPane.ERROR_MESSAGE);
					}
				
				if(flag==true) 
					albumFrame.RefreshTable(ctrl);
			    
				rimuoviTraccia();
			    nomeAlbum.setText("");

				
			}

			
		});
		
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(10, 202, 546, 42);
		getContentPane().add(scrollPane);
		table = new JTable();
		table.setBackground(Color.BLACK);
		table.setForeground(Color.WHITE);
		table.setFillsViewportHeight(true);
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
		table_1.setFillsViewportHeight(true);
		table_1.setForeground(Color.WHITE);
		table_1.setBackground(Color.BLACK);
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
		
		JButton btnRefresh = new JButton("refresh\r\n");
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setBackground(Color.BLACK);
		btnRefresh.setBounds(346, 60, 93, 23);
		getContentPane().add(btnRefresh);
		
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox_1.removeAllItems();
				initializeNomeArtisti(ctrl);
				for(String a:nomeArtisti) {
					comboBox_1.addItem(a);
					}
				
			}
		});
		
		

		
		
	}

	
	private void initializeNomeArtisti(Controller ctrl) {
		JTable table_tmp= new JTable();
		ctrl.AggiornaTabella(table_tmp, "artista", "nome_artista", true);
		nomeArtisti = new String[table_tmp.getModel().getRowCount()];

        for(int i=0; i<table_tmp.getRowCount(); i++) {
        	nomeArtisti[i] = new String();

        	nomeArtisti[i] = (String) table_tmp.getValueAt(i, 0);
        }
        table_tmp=null;
		
	}


	private void AggiungiAlbum() {
      
       
        
        DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
        /* if(dtm.getRowCount()>0)
        dtm.removeRow(0);*/
	    if(dtm.getColumnCount()==4) {
	    dtm.addRow(new Object[] { comboBox.getItemAt(comboBox.getSelectedIndex()),
	    		                  nomeAlbum.getText(), comboBox_1.getItemAt(comboBox_1.getSelectedIndex()), 
	    		                  getDate()});
	    }
	    
	    System.out.println("album aggiunto: "+nomeAlbum.getText());
        
	}

	private Date getDate() {
		Date date = new Date(System.currentTimeMillis());
    	return date;
	}
	

	private void aggiungiTraccia() {
	    i++;
	
		DefaultTableModel dtm = (DefaultTableModel) this.table_1.getModel();
	    if(dtm.getColumnCount()==2) {
	    	dtm.addRow(new Object[] {i, nomeTraccia.getText()});
	    }
	    System.out.println("tracce aggiunte: "+nomeTraccia.getText());

		nomeTraccia.setText("");

	}
	
	private void rimuoviTraccia() {
		DefaultTableModel dtm = (DefaultTableModel) this.table_1.getModel();
		DefaultTableModel dtm2 = (DefaultTableModel) this.table.getModel();

	   
	    	while(dtm.getRowCount()!=0) {
			dtm.removeRow(0);
	    	}
			traccia=null;
        if(dtm2.getRowCount()>0)
	    dtm2.removeRow(0);	
        i=0;
	    table.revalidate();


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
	         tColumn.setCellRenderer(new ColumnColorRenderer(Color.black, Color.white));
	     
	    	 }
	
	     for(int i=0; i<2; i++) {
	         tColumn2 = table_1.getColumnModel().getColumn(i);
	         tColumn2.setCellRenderer(new ColumnColorRenderer(Color.black, Color.white));
	       	}

	}
	//prepara l'invio al database
	private void organizzaDati() {
		nAlbum=nomeAlbum.getText();
		nArtista = comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
		nAlbum=nAlbum.replaceAll(" ", "_");
		nArtista=nArtista.replaceAll(" ", "_");


		traccia = new Traccia[table_1.getModel().getRowCount()];
		for(int j=0; j<table_1.getModel().getRowCount(); j++) {
			
			traccia[j] = new Traccia();
			traccia[j].n_traccia = j+1;
			traccia[j].nome_traccia = (String) table_1.getModel().getValueAt(j, 1);
			traccia[j].nome_album = nomeAlbum.getText();
			traccia[j].nome_artista = comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
		}
	}
}
