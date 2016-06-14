
import java.io.File;
import io.FileSystem;


public class SearchReplace {
	private static SearchandReplace v;
	private static final String USAGE="java SearchandReplace [-r] Search Replacement {Files and Directories}";
	private static String search;
	private static String replacement;
	private static boolean r=false;
	private static File path;
	private static FileSystem fileSystem;
	private static String[]files;
	
	public static void main(String[]argv){
		//Fehlerhafter Aufruf, drucke Benutzung
		if(argv.length==0){
			throw new IllegalArgumentException(USAGE);
		}
		//Wenn Parameter "-r" angegeben wurde
		if(argv[0]=="-r"){
			files=new String[argv.length-3];
			r=true;
			search=argv[1];
			replacement=argv[2];
			for(int i=3;i<argv.length;i++){
				files[i-3]=argv[i];
			
			}
		}
		//Wenn "-r" nicht angegeben wurde
		else{
			files=new String[argv.length-2];
			search=argv[0];
			replacement=argv[1];
			for(int i=2;i<argv.length;i++){
				files[i-2]=argv[i];
			}
			
		}
		//Laufvariable fuer while-Schleife
		int j=0;
		//SearchandReplace als Visitor
		v=new SearchandReplace(r, search, replacement);
		//legt neues Fileobjekt an und uebergibt es an FileSystem, SearchandReplace besucht FileSystem
		while(j<files.length){
			path=new File(files[j]);
			if(!path.exists()){
				throw new IllegalArgumentException(""+path+"existiert nicht");
			}
		
			fileSystem=new FileSystem(path);
			fileSystem.accept(v);
			j++;
		}	
	}
}
