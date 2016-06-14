import java.io.IOException;
import java.io.OutputStreamWriter;

public class Translator{
	public static final String MARKLAR = "Marklar";
	
	public static void main (String[] args){
		//Lege neuen Writer fuer Input mit System.in und neuen Reader fuer Output mit System.out an
		final Writer out = new Writer(System.in);
		final Reader in = new Reader(new Replacer(new OutputStreamWriter(System.out), MARKLAR));
		//verbinde Writer und Reader
		try{
			in.connect(out);
		} catch(IOException e){
			e.printStackTrace();
		}
		//shutdownHook
		Thread shutdownHook = new Thread() {
			public void run() {
				try{
					out.close();
					in.close();
					System.out.println("Das Programm wurde beendet");
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		};
		//Lege zwei Threads an; eins fuer input, eins fuer output
		Thread output=new Thread(out);
		Thread input=new Thread(in);
		output.start();
		input.start();
		
		Runtime.getRuntime().addShutdownHook(shutdownHook);
	}
}