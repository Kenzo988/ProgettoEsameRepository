
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

public class GestioneArtistaFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JFrame frame;
	private JTable table;
	private int aColumn;
	private String selectedData;
	private TableColumn tColumn;

	public GestioneArtistaFrame(Controller main, AlbumFrame albumFrame){
		setTitle("Aggiungi Artista");
		frame = new JFrame();
		setBounds(100, 100, 490, 300);

		getContentPane().setLayout(null);
		
		JLabel lblListaArtisti = new JLabel("Gestione artisti:");
		lblListaArtisti.setBounds(10, 11, 129, 14);
		getContentPane().add(lblListaArtisti);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 321, 213);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		
		main.AggiornaTabella(table, "artista", "nome_artista", false);
		tableEvents();
		
		JButton btnAggiungiArtista = new JButton("Nuovo artista");
		btnAggiungiArtista.setBounds(341, 47, 123, 23);
		getContentPane().add(btnAggiungiArtista);
		
		btnAggiungiArtista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AggiungiArtista(main);
			}

		});
		
		JButton btnEliminaArtista = new JButton("Elimina artista");
		btnEliminaArtista.setBounds(341, 81, 123, 23);
		getContentPane().add(btnEliminaArtista);
		
		btnEliminaArtista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminaArtista(main,albumFrame);
				
			}

			
		});
		
		
		organizzaTabella();
		
	}

	private void organizzaTabella() {
			aColumn=1; 
		    TableColumn tcol = table.getColumnModel().getColumn(aColumn);
		    table.removeColumn(tcol);
				
	}
	

	private void AggiungiArtista(Controller main) {
		boolean flag=false;
		JFrame frame = new JFrame();
	    String message = "Inserisci un nuovo artista:";
	    String new_name = JOptionPane.showInputDialog(frame, message);
	    if (new_name == null) {
	    }
	    if(new_name.length()>0)
			new_name=new_name.replaceAll(" ", "_");
			flag=main.InserisciArtista(new_name);
			
		
		if(flag==true) { 	
			refreshTable(main);
			

	    }else JOptionPane.showMessageDialog(new JFrame(),
			    "Reinserire valori", "Errore Inserimento",JOptionPane.ERROR_MESSAGE);

	
	}
	
	private void eliminaArtista(Controller main,AlbumFrame albumFrame) {
		if(selectedData!=null ) {
			int dialogButton = 0;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Vuoi Davvero Eliminare L'Artista: ["+selectedData+"], Questa azione non può essere annullata",
					                                          "Warning",dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
			
				main.EliminaArtista(selectedData);
				refreshTable(main);
				albumFrame.RefreshTable(main);

			}
		}
		
		
	}
	
	

	private void refreshTable(Controller main) {
		main.AggiornaTabella(table, "artista", "nome_artista", false);
		organizzaTabella();
		
	}
	
	private void tableEvents() {
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {

		public void valueChanged(ListSelectionEvent e) {
	         selectedData = null;
	         

	        int[] selectedRow = table.getSelectedRows();
	        int[] selectedColumns = table.getSelectedColumns();

	        for (int i = 0; i < selectedRow.length; i++) {
	          for (int j = 0; j < selectedColumns.length; j++) {
	            selectedData = (String) table.getValueAt(selectedRow[i], 0);
	            rowRender(selectedRow[i]);

	          }}
		System.out.println("track n: "+selectedData);}
		});
	}
	
       private void rowRender(int selectedRow) {
		
		for(int z=0; z<table.getColumnCount();z++) {
			
		   tColumn = table.getColumnModel().getColumn(z);
		   tColumn.setCellRenderer(new RowColorRenderer(Color.lightGray, Color.black,selectedRow));  	 
		}
	}
	
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
	      
	   }
       public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
       Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		      
		          if(row==n)
		    	  cell.setBackground(backgroundColor);
		          else cell.setBackground(Color.white);
			     
		      
		      return cell;
      }
     }
	
}

