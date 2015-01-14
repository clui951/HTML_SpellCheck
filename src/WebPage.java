import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import java.util.Iterator;

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
	private String correctedHtml;
	private String wordText;
	private HashMap<String,Integer> wordMap;
	
	// Constructor takes in file name or website url along with indicator for which one
	// automatically calls readDoc to parse
	public WebPage( String fileName, int sourceType ) {
		this.fileName = fileName;
		this.sourceType = sourceType;
		this.readDoc();
	}
	
	// Parse given file and extract information
	public void readDoc() {
		try {
			if (sourceType == LOCAL) {
				this.doc = Jsoup.parse(new File(this.fileName), "UTF-8");
			} else {
				this.doc = Jsoup.connect(this.fileName).get();
			}
			this.html = this.doc.html();
			this.wordText = this.doc.select("body").text();
			this.valid = true;
		} catch (IOException e) {
			this.doc = null;
			this.html = null;
			this.wordText = null;
			this.valid = false;
			e.printStackTrace();
		}
		
	}

	// Iterate through bodyText and hash words into hash map
	// hash map keeps track of how many instances of each word there is
	// * Not used in actual program but useful for testing
	public void collectWordCount() {
		this.wordMap = new HashMap<String,Integer>();
		for ( String word : this.wordText.replaceAll("[^a-zA-z']"," ").split(" ") ) {
			if (wordMap.containsKey(word)) {
				wordMap.put(word, wordMap.get(word) + 1);
			} else {
				wordMap.put(word, 1);
			}
	    }
	}
	
	
	
	/* Getters & Setters */
	
	public String getFileName() {
		return this.fileName;
	}
	
	public String getHTML() {
		return this.html;
	}
	
	public Boolean isValid() {
		return this.valid;
	}
	
	public String getBodyText() {
		return this.wordText;
	}
	
	public void printWordMap() {
		Iterator<String> keySetIterator = this.wordMap.keySet().iterator();
		while (keySetIterator.hasNext()) {
			String tempKey = keySetIterator.next();
			System.out.println(tempKey + " : " + wordMap.get(tempKey));
		}
	}
	
}
