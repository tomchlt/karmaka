package karmaka;

import java.io.*;

public class Console {

	private InputStream input;
	private PrintStream output;

	public Console() {
		this.input = System.in;
		this.output = System.out;
	}

	public Console(InputStream in, OutputStream out) {
		this.input = in;
		this.output = new PrintStream(out);
	}

	public void afficher(String message) {
		output.println(message);
	}
	
	public void afficher(Object objet) {
		output.println(objet);
	}

	public int lireInt() {
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String chaine = null;
		try {
			chaine = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int resultat = Integer.parseInt(chaine);
		return resultat;
	}

	public String lire() {
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String chaine = null;
		try {
			chaine = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chaine;
	}

	public static void main(String[] args) {
		
	}

}
