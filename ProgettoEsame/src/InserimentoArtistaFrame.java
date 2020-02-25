
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class InserimentoArtistaFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JFrame frame;
	private JTable table;
	private int aColumn;

	public InserimentoArtistaFrame(Controller main){
		setTitle("Aggiungi Artista");
		frame = new JFrame();
		setBounds(100, 100, 450, 300);

		getContentPane().setLayout(null);
		
		JLabel lblListaArtisti = new JLabel("Lista artisti:");
		lblListaArtisti.setBounds(10, 11, 88, 14);
		getContentPane().add(lblListaArtisti);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 321, 213);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		main.AggiornaTabella(table, "album", "artista", false);
		organizzaTabella();
		
	}

	private void organizzaTabella() {
		int i=0;
          // 
		    
			aColumn=0; 
			for(i=0; i<2; i++) {
		    TableColumn tcol = table.getColumnModel().getColumn(aColumn);
		    
		    table.removeColumn(tcol);
			}
			
			aColumn=1;
		    while(table.getColumnCount()!=1 ){
		    	TableColumn tcol = table.getColumnModel().getColumn(aColumn);
			    
			    table.removeColumn(tcol);
		    }
		    /*   if(i!=3) {
		    	System.out.println("tcol "+table.getColumnName(0));
		    table.removeColumn(tcol);
		    }
		    i++;
		   if(table.getColumnCount()!=1) {
		    tcol = table.getColumnModel().getColumn(2);
		    table.removeColumn(tcol);
		    }*/
          

        
		
		
		
	}
}

