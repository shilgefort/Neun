import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class Replacer extends BufferedWriter {
	private String replacement;
	/**
	 * Konstruktor
	 * @param out	beliebiger Writer
	 * @param replacement	String, der ersetzen soll
	 */
	public Replacer(Writer out, String replacement){
		super(out);
		this.replacement = replacement;
	}
	
	@Override
	/**
	 * schreibt in einen OutputStream und ersetzt alle groﬂgeschriebenen Woerter
	 * durch den String replacement
	 */
	public void write(String str) throws IOException {
		super.write(str.replaceAll("[A-Z]([a-z]|[A-Z])*", this.replacement));
	}
}