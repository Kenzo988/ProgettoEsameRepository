import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Bho {

	Controller ctrl;
	
	JFrame frame;
	private JTable table;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton AggiungiAlbumButt = new JButton("AggiungiAlbum");
		AggiungiAlbumButt.setBounds(10, 64, 132, 42);
		AggiungiAlbumButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// = new Controller();
				ctrl.InserisciAlbum();
				ctrl.AggiornaTabella(table, "album_table");
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(AggiungiAlbumButt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 117, 361, 133);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton AggiungiTraccie = new JButton("Aggiungi traccie");
		AggiungiTraccie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Object[][] traccie = {
						{0, "ciao", "a", "b"},
						{1, "coscos", "a", "b"}
				};
				for(int i = 0; i < traccie.length; i++)
				{
					ctrl.InserisciTraccia((int)traccie[i][0], traccie[i][1].toString(), traccie[i][2].toString(), traccie[i][3].toString());
				}
			}
		});
		AggiungiTraccie.setBounds(162, 64, 124, 42);
		frame.getContentPane().add(AggiungiTraccie);
	}
}
