

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedWriter;

public class Writer extends PipedWriter implements Runnable {
	private static final String SEPERATOR = System.getProperty("line.separator");
	
	private InputStream input;
	/**
	 * Konstruktor
	 * @param in InputStream, aus dem gelesen werden soll
	 */
	public Writer(InputStream in) {
		this.input = in;
	}
	
	@Override 
	/**
	 * wird ausgefuehrt, wenn Thread gestartet wird
	 * schreibt Zeile, wenn kein Dateipfad
	 */
	public void run() {
		BufferedReader bufRead = new BufferedReader(new InputStreamReader(this.input));
		String line;
		try {
			//Solange nicht am Ende der Datei
			while ((line = bufRead.readLine()) != null) {
				//wenn kein Dateipfad, schreibe die Zeile
				if(!writeFile(line)) {
					this.write(line + SEPERATOR);
					this.flush();
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Schreibt die aktuelle Zeile, wenn kein Dateipfad, andernfalls schreibt den
	 * Inhalt der angegebenen Datei
	 * @param name	Datei
	 * @return	true, wenn angegebener String Dateipfad war, sonst false
	 */
	private boolean writeFile(String name) {
		File f = new File(name);
		//wenn gueltiger Dateipfad uebergeben
		if(f.exists() && f.isFile()) {
			try {
				//gehe in die Datei 
				BufferedReader bufRead = new BufferedReader(new FileReader(f));
				String line;
				//schreibe jede einzelne Zeile aus der Datei
				while ((line = bufRead.readLine()) != null) {
					//Wenn kein Dateipfad
					if(!writeFile(line)) {
					//schreibe die Zeile 
						this.write(line + SEPERATOR);
						
					}
				}
				bufRead.close();
				return true;
			} catch (IOException ex) {
				return false;
			}
		}
		//Wenn kein gueltiger Dateipfad
		else {
			return false;
		}
	}
}


