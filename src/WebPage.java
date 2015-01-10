import java.io.File;
import java.io.IOException;

// Jsoup dependencies
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;


public class WebPage {

	private String fileName;
	private Document doc;
	private Boolean valid;
	
	// Constructor takes in file name or path to file 
	public WebPage( String fileName ) {
		this.fileName = fileName;
		this.readDoc();
	}
	
	public void readDoc() {
		try {
//			this.doc = Jsoup.connect("http://example.com/").get();
			this.doc = Jsoup.parse(new File(this.fileName), "UTF-8");
			this.valid = true;
		} catch (IOException e) {
			this.doc = null;
			this.valid = false;
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/* Getters & Setters */
	
	public String getFileName() {
		return this.fileName;
	}
	
	public String getHTML() {
		if (this.valid) { 
			return this.doc.html();
		}
		return null;
	}
	
	public Boolean isValid() {
		return this.valid;
	}
	
}
