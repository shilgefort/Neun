import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.Writer;

public class Reader extends PipedReader implements Runnable {
	private static final String SEPERATOR = System.getProperty("line.separator");
	
	private Writer out;
	/**
	 * Konstruktor
	 * @param out Writer, in den geschreiben werden soll
	 */
	public Reader(Writer out){
		this.out = out;
	}
	/**
	 * wird ausgefuehrt, wenn Thread gestartet wird
	 * schreibt alles, was er liest, in den OutputStream
	 */
	public void run() {
		BufferedReader bufRead = new BufferedReader(this);
		
		
			
			try {
				String line;
				while ((line = bufRead.readLine()) != null){
					this.out.write(line);
					this.out.write(SEPERATOR);
					this.out.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
}