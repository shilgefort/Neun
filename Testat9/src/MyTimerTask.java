import java.io.File;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask{
	private final long SPACE;
	private final File file;
	/**
	 * Konstruktor fuer MyTimerTask
	 * @param file	Datei, die ueberprueft werden soll
	 */
	public MyTimerTask(File file){
		
		this.file=file;
		
		SPACE=file.getFreeSpace();

	}
	
		
	
	@Override
	/**
	 * wird ausgefuehrt, wenn Thread gestartet wird
	 */
	public void run() {
		long newSpace=file.getFreeSpace();
		
		if(newSpace!=SPACE){
			System.out.printf("Aenderung in Datei/Verzeichnis um %d Bytes %n" ,(SPACE-newSpace));
			
				
				
			
			
		 }
	
		}
	}