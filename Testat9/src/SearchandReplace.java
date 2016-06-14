import java.io.*;
import io.FileVisitResult;
import io.FileVisitor;

public class SearchandReplace implements FileVisitor{
	private static String search;
	private static String replacement;
	private boolean flag;
	private static String s;
	
	/**
	 * Konstruktor fuer SearchandReplace
	 * @param flag	true, wenn alle Verzeichnisse durchsucht werden sollen
	 * @param search	String, nach dem gesucht wird
	 * @param replacement	String, der den gesuchten String ersetzen soll
	 */
	public SearchandReplace(boolean flag, String search, String replacement){
		this.flag=flag;
		SearchandReplace.search=search;
		SearchandReplace.replacement=replacement;
		
	}
	
	
	
	/**
	 * ersetzt in der Datei jeden gefundenen String search mit dem String replace 
	 * @param file	Datei in der gesucht wird
	 * @param search	gesuchter String
	 * @param replace	String, der den gesuchten String ersetzen soll
	 */
	private static void replace(File file, String search, String replace){
		try{
			//Datei zu String
			s=readFile(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}	
		//Ersetze im String alle Vorkommen von "search" durch "replace"
		s.replace(search, replace);
		try{
			FileWriter writer=new FileWriter(file);
			writer.write(s);
			writer.close();
			}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
	/**
	 * macht aus einer Datei einen String
	 * @param file
	 * @return	String, aufgebaut aus Datei
	 * @throws IOException
	 */
	private static String readFile(File file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         seperator = System.getProperty("line.separator");

	    try {
	    	//Solange nicht Dateiende
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(seperator);
	        }

	        return stringBuilder.toString();
	    } finally {
	    	
	        reader.close();
	    }
	}
	
		

	@Override
	/**
	 * wird nach dem Besuch eines Verzeichnis aufgerufen
	 */
	public FileVisitResult postVisitDirectory(File dir) {
		// TODO Auto-generated method stub
		return FileVisitResult.CONTINUE;
	}

	@Override
	/**
	 * Wird vor dem Besuch eines Verzeichnis aufgerufen
	 */
	public FileVisitResult preVisitDirectory(File dir) {
		if(flag==false){
			return FileVisitResult.SKIP_SUBTREE;
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	/**
	 * Wird aufgerufen, wenn Besuch einer Datei fehlschlaegt
	 */
	public FileVisitResult visitFailed(File dir) {
		// TODO Auto-generated method stub
		return FileVisitResult.TERMINATE;
	}

	@Override
	/**
	 * Wird aufgerufen, wenn Datei besucht wird
	 */
	public  FileVisitResult visitFile(File file) {
		// TODO Auto-generated method stub
		
		replace(file, search, replacement);
		
		return FileVisitResult.CONTINUE;
	}

}
