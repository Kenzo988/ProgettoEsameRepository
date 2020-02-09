import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel frame;
	private JTextField usernameField;
	private JTextField passwordField;
	private boolean flag=false;

	//login al database
	public LoginFrame(Controller ctrl) {
		
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 277, 255);
		frame = new JPanel();
		frame.setForeground(Color.BLACK);
		frame.setBackground(Color.WHITE);
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frame);
		frame.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Corbel", Font.BOLD | Font.ITALIC, 21));
		lblLogin.setBounds(0, 11, 251, 34);
		frame.add(lblLogin);
		
		usernameField = new JTextField();
		usernameField.setBounds(97, 55, 138, 20);
		frame.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(10, 58, 77, 14);
		frame.add(lblUsername);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(97, 86, 138, 20);
		frame.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(10, 89, 77, 14);
		frame.add(lblPassword);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.setBounds(59, 136, 131, 48);
		frame.add(btnConferma);
		
		btnConferma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                
				 Login(ctrl);	
                 usernameField.setText("");
                 passwordField.setText("");
                 if (flag==true) {
                	 setVisible(false);
                 }else {
                	 JOptionPane.showMessageDialog(new JFrame(), "Password/username errato", "Errore Inserimento",
         					JOptionPane.ERROR_MESSAGE);
                 }
                 

			}
		});
	
	

		  usernameField.setText("postgres");
        passwordField.setText("password");
	}

	
	private void Login(Controller ctrl) {
		
		if ((usernameField.getText().length() > 0) && (passwordField.getText().length() > 0)) {
		
			flag=ctrl.Login(usernameField.getText(),passwordField.getText());
		
		if(flag==true)
		ctrl.openAlbumFrame();
	    } else {
	    	JOptionPane.showMessageDialog(new JFrame(), "Inserire Dati Login", "Errore Inserimento",
					JOptionPane.ERROR_MESSAGE);
	    }
	}
}
