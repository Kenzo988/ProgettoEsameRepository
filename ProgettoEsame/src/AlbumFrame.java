import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;



import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.ListSelectionModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;


public class AlbumFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTable table;
	private JButton btnAggiungialbum;
	private JScrollPane rightPane;
	private TableColumn tColumn;
	private TableColumn tColumn2;

	private JScrollPane leftPane;
	private JTable table_1;
	private JLabel lblresultSelezione;
	private int aColumn;
	private JLabel lblListaAlbums;
    private String selectedData;
    private String selectedData2;
    private String selectedData3;

	private String[] ListaOrdine= {"Tipo album", "Nome album", "Nome artista", "Followers", "Views totali", "Data pubblicazione", "Retribuzione"};
	private JComboBox <String> comboBox;
	private JButton btnModificaTraccia;
	private JButton btnAggiungiTraccia;



	public AlbumFrame(Controller main) {
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setForeground(Color.WHITE);
		InserimentoAlbumFrame frame2 = new InserimentoAlbumFrame(main,this);
		GestioneArtistaFrame frame3= new GestioneArtistaFrame(main,this);

		frame = new JFrame();

		setTitle("Album Data");
		setBounds(100, 100, 1920, 788);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setForeground(Color.WHITE);
		frame.setBackground(Color.BLACK);
		
	
		frame.getContentPane().setLayout(new BoxLayout(frame, BoxLayout.X_AXIS));
		
		

		leftPane = new JScrollPane();
		leftPane.setBounds(100, 100, 800, 300);
		
		rightPane = new JScrollPane();
		rightPane.setBounds(100, 100, 800, 300);

		
		table_1 = new JTable();
		table_1.setForeground(Color.WHITE);
		table_1.setBackground(Color.BLACK);
		table_1.setFillsViewportHeight(true);
	
		
		
		rightPane.setViewportView(table_1);
		
		
		
		btnAggiungialbum = new JButton("Aggiungi Album");
		btnAggiungialbum.setBackground(Color.BLACK);
		btnAggiungialbum.setForeground(Color.WHITE);
		btnAggiungialbum.setAlignmentX(Component.BOTTOM_ALIGNMENT);
		
		btnAggiungialbum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame2.setVisible(true);
			}
		});
		
		
		
	    table = new JTable();
	    table.setForeground(Color.WHITE);
	    table.setBackground(Color.BLACK);
	    table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    
	    table.setFillsViewportHeight(true);
	
		
		main.AggiornaTabella(table, "album", "tipo", true);
		
		
		System.out.println("aggiornato");
		
		

		ColumnRender();
		TableEvents(main);
		tableEventsRight();
        setFalse();
		

		leftPane.setViewportView(table);
		
		JLabel lblAlbumSelezionato = new JLabel("Album Selezionato:");
		lblAlbumSelezionato.setForeground(Color.WHITE);
		lblAlbumSelezionato.setFont(new Font("Tahoma", Font.BOLD, 14));
		
        lblresultSelezione = new JLabel("");
        lblresultSelezione.setHorizontalAlignment(SwingConstants.LEFT);
		lblresultSelezione.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblListaAlbums = new JLabel("Lista Albums:");
		lblListaAlbums.setForeground(Color.WHITE);
		lblListaAlbums.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnEliminaAlbum = new JButton("Elimina Album");
		btnEliminaAlbum.setBackground(Color.BLACK);
		btnEliminaAlbum.setForeground(Color.WHITE);
		btnEliminaAlbum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EliminaAlbum(main);
				
			}

			
		});
		
		
		btnEliminaAlbum.setAlignmentX(1.0f);
		
		JButton btnEliminaTracce = new JButton("Elimina Traccia");
		btnEliminaTracce.setBackground(Color.BLACK);
		btnEliminaTracce.setForeground(Color.WHITE);
		btnEliminaTracce.setAlignmentX(1.0f);
		
		btnEliminaTracce.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedData3!=null)
				eliminaTrackSelezionata(selectedData3,main);				
			}

		});
		
		JLabel lblOrdinaAlbums = new JLabel("Ordina Albums per:");
		lblOrdinaAlbums.setForeground(Color.WHITE);
		lblOrdinaAlbums.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		comboBox = new JComboBox<String>();
		for(String a:ListaOrdine) {
			comboBox.addItem(a);
			}
		comboBox.setBounds(99, 33, 98, 20);
		getContentPane().add(comboBox);
		
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OrdineComboBox(main);
				
			}
		});
		
		JButton btnEliminaTutteLe = new JButton("Elimina Tutte le tracce");
		btnEliminaTutteLe.setBackground(Color.BLACK);
		btnEliminaTutteLe.setForeground(Color.WHITE);
		btnEliminaTutteLe.setAlignmentX(1.0f);
		
		btnEliminaTutteLe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EliminaTutteLeTracce(main);
				
			}

			
		});
		
		btnModificaTraccia = new JButton("Modifica traccia");
		btnModificaTraccia.setBackground(Color.BLACK);
		btnModificaTraccia.setForeground(Color.WHITE);
		btnModificaTraccia.setAlignmentX(1.0f);
		
        btnModificaTraccia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
               modificaTraccia(main);				
			}

			
		});
		
		btnAggiungiTraccia = new JButton("Aggiungi Traccia");
		btnAggiungiTraccia.setBackground(Color.BLACK);
		btnAggiungiTraccia.setForeground(Color.WHITE);
		btnAggiungiTraccia.setAlignmentX(1.0f);
		
		btnAggiungiTraccia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aggiungiTraccia(main);
				
			}

			
		});
		
		JButton btnAggiungiArtista = new JButton("Gestisci Artisti");
		btnAggiungiArtista.setBackground(Color.BLACK);
		btnAggiungiArtista.setForeground(Color.WHITE);
		btnAggiungiArtista.setAlignmentX(1.0f);
		
		btnAggiungiArtista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame3.setVisible(true);
				
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(leftPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 834, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblListaAlbums, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 373, Short.MAX_VALUE)
							.addComponent(lblOrdinaAlbums)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
							.addGap(10)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(lblAlbumSelezionato)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblresultSelezione, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
						.addComponent(rightPane, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAggiungialbum, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(btnAggiungiTraccia, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(btnModificaTraccia, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(btnEliminaTracce, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(btnEliminaAlbum, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(btnAggiungiArtista, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(btnEliminaTutteLe, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblresultSelezione, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblOrdinaAlbums, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblListaAlbums, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblAlbumSelezionato)))
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAggiungialbum, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAggiungiTraccia, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAggiungiArtista, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(58)
							.addComponent(btnModificaTraccia, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminaAlbum, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminaTracce, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminaTutteLe, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addComponent(leftPane, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE)
						.addComponent(rightPane, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		

	}
	
	
	
	private void OrdineComboBox(Controller main) {
		String tmp=comboBox.getItemAt(comboBox.getSelectedIndex());
		if(tmp=="Tipo album")
		main.AggiornaTabella(table, "album", "tipo", true);
		if(tmp=="Nome album")
			main.AggiornaTabella(table, "album", "nome_album", true);
		if(tmp=="Nome artista")
			main.AggiornaTabella(table, "album", "artista", true);
		if(tmp=="Followers")
			main.AggiornaTabella(table, "album", "followers", false);
		if(tmp=="Views totali")
			main.AggiornaTabella(table, "album", "views_totali", false);
		if(tmp=="Data pubblicazione")
			main.AggiornaTabella(table, "album", "data_pubblicazione", false);
		if(tmp=="Retribuzione")
			main.AggiornaTabella(table, "album", "retribuzione", false);
		
		ColumnRender();
	}



	private void TableEvents(Controller main) {
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {

		public void valueChanged(ListSelectionEvent e) {
	         selectedData = null;
	         selectedData2 = null;

	        int[] selectedRow = table.getSelectedRows();
	        int[] selectedColumns = table.getSelectedColumns();

	        for (int i = 0; i < selectedRow.length; i++) {
	          for (int j = 0; j < selectedColumns.length; j++) {
	            selectedData = (String) table.getValueAt(selectedRow[i], 1);
	            selectedData2 = (String) table.getValueAt(selectedRow[i], 2);
                 rowRender(selectedRow[i]);
	          }
	        }
	        
	        if(selectedData!=null && selectedData2!=null) {
	        lblresultSelezione.setText(selectedData);
	        lblresultSelezione.setForeground(Color.RED);
	        setRightPane(main,selectedData,selectedData2);
	        }
	      }	
	    });
		
	}
	
	
	private void tableEventsRight() {
		ListSelectionModel cellSelectionModel = table_1.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {

		public void valueChanged(ListSelectionEvent e) {
	         selectedData3 = null;
	         

	        int[] selectedRow = table_1.getSelectedRows();
	        int[] selectedColumns = table_1.getSelectedColumns();

	        for (int i = 0; i < selectedRow.length; i++) {
	          for (int j = 0; j < selectedColumns.length; j++) {
	            selectedData3 = (String) table_1.getValueAt(selectedRow[i], 0);
	            rowRenderRight(selectedRow[i]);

	          }}
		}
		});
	}
     
	private void rowRenderRight(int selectedRow) {
		
		for(int z=0; z<table_1.getColumnCount();z++) {
			
		   tColumn2 = table_1.getColumnModel().getColumn(z);
		   tColumn2.setCellRenderer(new RowColorRenderer(Color.lightGray, Color.white,selectedRow));  	 
		}
	}
	
	private void setRightPane(Controller main,String selectedData, String selectedData2) {
		main.AggiornaTabella(table_1, selectedData+"_"+selectedData2, "n", true);
	    ColumnRender2();

		while(table_1.getColumnCount()!=3 ){
		    
			aColumn=2; 
		    TableColumn tcol = table_1.getColumnModel().getColumn(aColumn);
		    if(table_1.getColumnName(4)!="views_totali") {
		    table_1.removeColumn(tcol);
		    }
		    if(table_1.getColumnCount()!=3) {
		    tcol = table_1.getColumnModel().getColumn(2);
		    table_1.removeColumn(tcol);
		    }
            table_1.getColumnModel().getColumn(0).setMaxWidth(20);
            table_1.getColumnModel().getColumn(2).setMaxWidth(100);
            table_1.getColumnModel().getColumn(2).setMinWidth(0);
        }
	}
	
	



	   private void RimuoviRow() {
		  DefaultTableModel dtm = (DefaultTableModel) this.table_1.getModel();
		  while(dtm.getRowCount()!=0) {
			  dtm.removeRow(0);
		  }
		  table_1.revalidate();
		  lblresultSelezione.setText("");
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
			   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
				      Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				            
				    cell.setBackground(backgroundColor);
					return cell;
				      }
			}
		
		private void ColumnRender2() {
			for(int i=0; i<table_1.getColumnCount(); i++) {
		         tColumn = table_1.getColumnModel().getColumn(i);
		         tColumn.setCellRenderer(new ColumnColorRenderer(Color.black, Color.white));			
		}
			}
		
		public void ColumnRender() {
			
		    	 for(int i=0; i<table.getColumnCount(); i++) {
			         tColumn = table.getColumnModel().getColumn(i);
			         tColumn.setCellRenderer(new ColumnColorRenderer(Color.black, Color.white));		     
		    	 }
			}
		   //Centra e Colora le singole celle (row)
				class RowColorRenderer extends DefaultTableCellRenderer {
				
					/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
					Color backgroundColor, foregroundColor;
					int n;
					
					   public RowColorRenderer(Color backgroundColor, Color foregroundColor,int n) {
					      super();
					      this.backgroundColor = backgroundColor;
					      this.foregroundColor = foregroundColor;
					      this.n=n;
					      this.setHorizontalAlignment(SwingConstants.CENTER);
					      
					   }
		           public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
	    		   Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
						      
						          if(row==n)
						    	  cell.setBackground(backgroundColor);
						          else cell.setBackground(Color.black);
							     
						      
						      return cell;
						      
				
		           }
				}
				
				
		private void rowRender(int selectedRow) {
			
			for(int z=0; z<table.getColumnCount();z++) {
				
			         tColumn = table.getColumnModel().getColumn(z);
			         tColumn.setCellRenderer(new RowColorRenderer(Color.lightGray, Color.white,selectedRow));    	 
			}
		}
		

		
		public void RefreshTable(Controller main) {
			main.AggiornaTabella(table, "album", "artista", true);
			ColumnRender();
			


		}
		
		public void setFalse() {
			table.setDefaultEditor(Object.class, null);
			table_1.setDefaultEditor(Object.class, null);

		}
		
		private void EliminaAlbum(Controller main) {
			if(selectedData!=null && selectedData2!=null) {
				int dialogButton = 0;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Vuoi Davvero Eliminare L'Album: "+selectedData,
						                                          "Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
				
				main.EliminaAlbum(selectedData, selectedData2);
				RimuoviRow();

				RefreshTable(main);
				}
			}
			
		}
		

		private void eliminaTrackSelezionata(String n_track,Controller main) {
			if(n_track!=null) {
			int n=Integer.parseInt(n_track);
			int dialogButton = 0;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Vuoi Davvero Eliminare La traccia selezionata? questa azione non pu� essere annullata",
					                                          "Warning",dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
			
			main.EliminaTraccia(n, selectedData, selectedData2);
			setRightPane(main, selectedData, selectedData2);
			}
			}				
			
		}
		
		private void EliminaTutteLeTracce(Controller main) {
			if(selectedData!=null && selectedData2!=null) {
				int dialogButton = 0;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Vuoi Davvero Eliminare tutte le tracce? questa azione non pu� essere annullata",
						                                          "Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
				int i=0;
				while(i<=table_1.getRowCount()) {
				main.EliminaTraccia(0, selectedData, selectedData2);
				System.out.println("row count "+ table_1.getRowCount());

				i++;
				System.out.println("elimino tracks");
				}
				setRightPane(main, selectedData, selectedData2);
				ColumnRender2();
				table_1.revalidate();
			}
			}
			
		}
		
		private void modificaTraccia(Controller main) {
			if(selectedData3!=null) {
			int row= Integer.parseInt(selectedData3);
			
			
			 JFrame frame = new JFrame();
			    String message = "Inserisci nuovo nome per la traccia: "+table_1.getValueAt(row-1, 1);
			    String new_name = JOptionPane.showInputDialog(frame, message);
			   
			    if(new_name.length()>0 ) {
			main.ModificaTraccia(row, new_name, selectedData, selectedData2);
			setRightPane(main, selectedData, selectedData2);
			    }else JOptionPane.showMessageDialog(new JFrame(),
					    "Reinserire valori", "Errore Inserimento",JOptionPane.ERROR_MESSAGE);
			}
		}

		
		private void aggiungiTraccia(Controller main) {
			if(selectedData!=null && selectedData2!=null) {
				boolean flag=false;
			
				 JFrame frame = new JFrame();
				    String message = "Inserisci una nuova traccia:";
				    String new_name = JOptionPane.showInputDialog(frame, message);
				    
				    if(new_name.length()>0)
						flag = main.InserisciTraccia(new_name, selectedData, selectedData2);
         		    if(flag==true) {
						setRightPane(main, selectedData, selectedData2);
				    }else JOptionPane.showMessageDialog(new JFrame(),
						    "Reinserire valori", "Errore Inserimento",JOptionPane.ERROR_MESSAGE);

				}
			
		}
		
		
}

		
