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
import java.sql.Date;
import java.util.Vector;

import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.ListSelectionModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;


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
	private JScrollPane leftPane;
	private JTable table_1;
	private JLabel lblresultSelezione;
	private int aColumn;
	private JLabel lblListaAlbums;


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
		table_1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
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
		/*table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Tipo", "Nome Album", 
				                                                               "Artista", "Lv Artista", "Followers", "Views Tot",
				                                                               "Data Pubblicazione", "Retribuzione Album" }) {
		
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class,Integer.class, Integer.class, Date.class, Double.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			 @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		});*/
		
		
		main.AggiornaTabella(table, "album", "artista", true);
		
		System.out.println("aggiornato");
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(4).setMaxWidth(60);
		table.getColumnModel().getColumn(5).setMaxWidth(60);

		ColumnRender();
		TableEvents(main); 
		

		leftPane.setViewportView(table);
		
		JLabel lblAlbumSelezionato = new JLabel("Album Selezionato:");
		lblAlbumSelezionato.setFont(new Font("Tahoma", Font.BOLD, 14));
		
        lblresultSelezione = new JLabel("");
        lblresultSelezione.setHorizontalAlignment(SwingConstants.LEFT);
		lblresultSelezione.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblListaAlbums = new JLabel("Lista Albums");
		lblListaAlbums.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblListaAlbums, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(679))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(leftPane, GroupLayout.PREFERRED_SIZE, 805, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rightPane, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
							.addComponent(btnAggiungialbum)
							.addGap(28))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAlbumSelezionato)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblresultSelezione, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(155, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblAlbumSelezionato)
							.addComponent(lblListaAlbums, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblresultSelezione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAggiungialbum, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(rightPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE)
						.addComponent(leftPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		

	}
	
	private void TableEvents(Controller main) {
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	        String selectedData = null;
	        String selectedData2 = null;

	        int[] selectedRow = table.getSelectedRows();
	        int[] selectedColumns = table.getSelectedColumns();

	        for (int i = 0; i < selectedRow.length; i++) {
	          for (int j = 0; j < selectedColumns.length; j++) {
	            selectedData = (String) table.getValueAt(selectedRow[i], 1);
	            selectedData2 = (String) table.getValueAt(selectedRow[i], 2);
	          }
	        }
	        System.out.println("Selected: " + selectedData+ " "+ selectedData2);
	        lblresultSelezione.setText(selectedData);
	        lblresultSelezione.setForeground(Color.RED);
	        setRightPane(main,selectedData,selectedData2);
	        
	      }

		
	    });
		
	}

	
	private void setRightPane(Controller main,String selectedData, String selectedData2) {
		// TODO Auto-generated method stub
		main.AggiornaTabella(table_1, selectedData+"_"+selectedData2, "n", true);
	  /*  DefaultTableModel tableModel = (DefaultTableModel) table_1.getModel();
		tableModel=(new DefaultTableModel(new Object[][] {}, new String[] { "N� Track", "NomeTraccia" }) {

            private static final long serialVersionUID = 1L;
            @SuppressWarnings("rawtypes")
            Class[] columnTypes = new Class[] { Integer.class, String.class};

            public Class<?> getColumnClass(int columnIndex) {
                   return columnTypes[columnIndex];
             }
              @Override
              public boolean isCellEditable(int row, int column) {
              //all cells false
              return false;
              }
            });*/

		while(table_1.getColumnCount()!=2) {
			aColumn=2; 
				  TableColumn tcol = table_1.getColumnModel().getColumn(aColumn);
		table_1.removeColumn(tcol);
			}
			
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
		     for(int i=0; i<7; i++) {
		         tColumn = table.getColumnModel().getColumn(i);
		         tColumn.setCellRenderer(new ColumnColorRenderer(Color.white, Color.black));
		         
		     
		    	 }
		/*
		     for(int i=0; i<2; i++) {
		         tColumn2 = table_1.getColumnModel().getColumn(i);
		         tColumn2.setCellRenderer(new ColumnColorRenderer(Color.white, Color.black));
		       	}
*/
		}
		
		public void RefreshTable(Controller main) {
			//main.AggiornaTabella(table, "album", "n", true);
		}
}