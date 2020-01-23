import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		Controller _mycontroller = new Controller();
		boolean connesso = false;
		//--------------------------------------
		do {
		//login
		String utente, password;
		Scanner scanner = new Scanner(System.in);
		System.out.println("---login");
		
		System.out.println("inserisci utente:");
		utente = scanner.nextLine();
		
		System.out.println("inserisci password:");
		password = scanner.nextLine();

		//utente default
		if(utente.equals(""))
			utente = "postgres";
		if(password.equals(""))
			password = "password";
		
		//nuova connessione
		connesso = _mycontroller.CreaConnessione(utente, password);
		}
		while(!connesso);
		//---------------------------------------

	}

}
