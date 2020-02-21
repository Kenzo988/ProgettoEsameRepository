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

import com.sun.javafx.scene.control.SelectedCellsMap;

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



	public AlbumFrame(Controller main) {
		InserimentoAlbumFrame frame2 = new InserimentoAlbumFrame(main,this);
		frame = new JFrame();

		setTitle("Album Data");
		setBounds(100, 100, 1300, 788);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setForeground(Color.BLACK);
		frame.setBackground(Color.WHITE);
		
	
		frame.getContentPane().setLayout(new BoxLayout(frame, BoxLayout.X_AXIS));
		
		

		leftPane = new JScrollPane();
		leftPane.setBounds(100, 100, 800, 300);
		
		rightPane = new JScrollPane();
		rightPane.setBounds(100, 100, 800, 300);

		
		table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
	
		
		
		rightPane.setViewportView(table_1);
		
		
		
		btnAggiungialbum = new JButton("AggiungiAlbum");
		btnAggiungialbum.setAlignmentX(Component.BOTTOM_ALIGNMENT);
		
		btnAggiungialbum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame2.setVisible(true);
			}
		});
		
		
		
	    table = new JTable();
	    table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    
	    table.setFillsViewportHeight(true);
	
		
		main.AggiornaTabella(table, "album", "artista", true);
		
		
		System.out.println("aggiornato");
		
		

		ColumnRender();
		TableEvents(main);
		tableEventsRight();
        setFalse();
		

		leftPane.setViewportView(table);
		
		JLabel lblAlbumSelezionato = new JLabel("Album Selezionato:");
		lblAlbumSelezionato.setFont(new Font("Tahoma", Font.BOLD, 14));
		
        lblresultSelezione = new JLabel("");
        lblresultSelezione.setHorizontalAlignment(SwingConstants.LEFT);
		lblresultSelezione.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblListaAlbums = new JLabel("Lista Albums:");
		lblListaAlbums.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnEliminaAlbum = new JButton("Elimina Album");
		btnEliminaAlbum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EliminaAlbum(main);
				
			}

			
		});
		
		
		btnEliminaAlbum.setAlignmentX(1.0f);
		
		JButton btnEliminaTracce = new JButton("Elimina Traccia");
		btnEliminaTracce.setAlignmentX(1.0f);
		
		btnEliminaTracce.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminaTrackSelezionata(selectedData3,main);				
			}

		});
		
		JLabel lblOrdinaAlbums = new JLabel("Ordina Albums per:");
		lblOrdinaAlbums.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		comboBox = new JComboBox<String>();
		for(String a:ListaOrdine) {
			comboBox.addItem(a);
			}
		comboBox.setBounds(99, 33, 98, 20);
		getContentPane().add(comboBox);
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblListaAlbums, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 363, Short.MAX_VALUE)
							.addComponent(lblOrdinaAlbums)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
						.addComponent(leftPane, GroupLayout.PREFERRED_SIZE, 834, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(lblAlbumSelezionato)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblresultSelezione, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(rightPane, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEliminaTracce, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
						.addComponent(btnEliminaAlbum, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
						.addComponent(btnAggiungialbum, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGap(64))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblresultSelezione, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBox)
							.addComponent(lblOrdinaAlbums, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblListaAlbums, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblAlbumSelezionato)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAggiungialbum, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminaAlbum, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminaTracce, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addComponent(leftPane, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE)
						.addComponent(rightPane, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(91, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		

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
	        System.out.println("Selected: " + selectedData+ " "+ selectedData2);
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
		System.out.println("track n: "+selectedData3);}
		});
	}
     
	private void rowRenderRight(int selectedRow) {
		
		for(int z=0; z<table_1.getColumnCount();z++) {
			
		   tColumn2 = table_1.getColumnModel().getColumn(z);
		   tColumn2.setCellRenderer(new RowColorRenderer(Color.lightGray, Color.black,selectedRow));  	 
		}
	}
	
	private void setRightPane(Controller main,String selectedData, String selectedData2) {
		main.AggiornaTabella(table_1, selectedData+"_"+selectedData2, "n", true);
	    ColumnRender();

		while(table_1.getColumnCount()!=2) {
		      
			aColumn=2; 
		    TableColumn tcol = table_1.getColumnModel().getColumn(aColumn);
		    table_1.removeColumn(tcol);
            table_1.getColumnModel().getColumn(0).setMaxWidth(20);
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
		
		public void ColumnRender() {
			if(table_1.getColumnCount()>1) {
		     for(int i=0; i<table_1.getColumnCount(); i++) {
		         tColumn = table_1.getColumnModel().getColumn(i);
		         tColumn.setCellRenderer(new ColumnColorRenderer(Color.white, Color.black));
		     }
		     }else {
		    	 for(int i=0; i<table.getColumnCount(); i++) {
			         tColumn = table.getColumnModel().getColumn(i);
			         tColumn.setCellRenderer(new ColumnColorRenderer(Color.white, Color.black));
		    	 
		     }
		     
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
						          else cell.setBackground(Color.white);
							     
						      
						      return cell;
						      
				
		           }
				}
				
				
		private void rowRender(int selectedRow) {
			
			for(int z=0; z<table.getColumnCount();z++) {
				
			         tColumn = table.getColumnModel().getColumn(z);
			         tColumn.setCellRenderer(new RowColorRenderer(Color.lightGray, Color.black,selectedRow));
			         
			     
			    	 
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
				RefreshTable(main);
				RimuoviRow();
				}
			}
			
		}
		

		private void eliminaTrackSelezionata(String n_track,Controller main) {
			if(n_track!=null) {
			int n=Integer.parseInt(n_track);
			int dialogButton = 0;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Vuoi Davvero Eliminare La traccia selezionata?",
					                                          "Warning",dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
			
			main.EliminaTraccia(n, selectedData, selectedData2);
			setRightPane(main, selectedData, selectedData2);
			}
			}				
			
		}
}

		
