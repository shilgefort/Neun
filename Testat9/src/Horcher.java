
import java.util.Timer;
import java.io.*;
public class Horcher {
	private static File file;
	
	public static void main(String[]argv){
		
		if(argv.length==0){
			throw new IllegalArgumentException("Bitte Datei/Verzeichnis angeben!");
			
		}
		
		file=new File(argv[0]);
		
		if(!file.exists()){
			
			throw new IllegalArgumentException("Datei/Verzeichnis existiert nicht!");
			
		}
		
		
		MyTimerTask timerTask=new MyTimerTask(file); 
		final Timer timer=new Timer();
		timer.schedule(timerTask, 0, 1000);
		System.out.println("Das Programm wurde gestartet");
		System.out.println("Zum Beenden Strg+C druecken");
		
		Thread shutdownHook = new Thread() {
			public void run() {
				System.out.println("Das Programm wurde beendet");
				timer.cancel();
				
			}
		};
		
		Runtime.getRuntime().addShutdownHook(shutdownHook);
		
		
		
		
		
		
	}
	
}
	



		
	
	


