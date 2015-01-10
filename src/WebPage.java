import java.io.File;
import java.io.IOException;

// Jsoup dependencies
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;


public class WebPage {

	static final int LOCAL = 1;
	static final int WEB = 0;
	
	private int sourceType;
	private String fileName;
	private Document doc;
	private Boolean valid;
	private String html;
	private String bodyText;
	
	// Constructor takes in file name or path to file 
	public WebPage( String fileName, int sourceType ) {
		this.fileName = fileName;
		this.sourceType = sourceType;
		this.readDoc();
	}
	
	// Parse to given file
	public void readDoc() {
		try {
			if (sourceType == LOCAL) {
				this.doc = Jsoup.parse(new File(this.fileName), "UTF-8");
			} else {
				this.doc = Jsoup.connect("http://example.com/").get();
			}
			this.html = this.doc.html();
			this.bodyText = this.doc.select("body").text();
			this.valid = true;
		} catch (IOException e) {
			this.doc = null;
			this.html = null;
			this.bodyText = null;
			this.valid = false;
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/* Getters & Setters */
	
	public String getFileName() {
		return this.fileName;
	}
	
	public String getHTML() {
		return this.doc.html();
	}
	
	public Boolean isValid() {
		return this.valid;
	}
	
	public String getBodyText() {
		return this.bodyText;
	}
	
}
