import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.ListSelectionModel;


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


	public AlbumFrame(Controller main) {
		InserimentoAlbumFrame frame2 = new InserimentoAlbumFrame(main,this);
		frame = new JFrame();

		setTitle("Album Data");
		setBounds(100, 100, 1200, 788);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setForeground(Color.BLACK);
		frame.setBackground(Color.WHITE);
		
	
		frame.getContentPane().setLayout(new BoxLayout(frame, BoxLayout.X_AXIS));
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		

		leftPane = new JScrollPane();
		leftPane.setBounds(100, 100, 500, 300);
		getContentPane().add(leftPane);
		
		rightPane = new JScrollPane();
        getContentPane().add(rightPane)
		
		;
		
		table_1 = new JTable();
		table_1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table_1.setFillsViewportHeight(true);
		table_1.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "N° Track", "NomeTraccia" }) {

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
                });
		table_1.getColumnModel().getColumn(0).setMaxWidth(80);
		rightPane.setViewportView(table_1);
		
		
		
		btnAggiungialbum = new JButton("AggiungiAlbum");
		btnAggiungialbum.setAlignmentX(Component.BOTTOM_ALIGNMENT);

		getContentPane().add(btnAggiungialbum);
		
		btnAggiungialbum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame2.setVisible(true);
			}
		});
		
		
		
	    table = new JTable();
	    table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    
	   table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Tipo", "Nome Album", 
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
		});
		
		
		main.AggiornaTabella(table, "album", "artista", true);
		
		System.out.println("aggiornato");
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(4).setMaxWidth(60);
		table.getColumnModel().getColumn(5).setMaxWidth(60);

		ColumnRender();

		leftPane.setViewportView(table);
		
		

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
		     for(int i=0; i<8; i++) {
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
			main.AggiornaTabella(table,"album", "artista", true);
		}
	
	
	
}
