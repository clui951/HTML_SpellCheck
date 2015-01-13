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
	private String wordText;
	private HashMap<String,Integer> wordMap;
	
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
	public void collectWords() {
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
