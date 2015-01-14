import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
	private String origHTML;
	private String correctedHTML;
	private String wordText;
	private HashMap<String,Integer> wordMap;
	private ArrayList<String> misspelledWords;
	private ArrayList<String> suggestedWords;
	
	// Constructor takes in file name or website url along with indicator for which one
	// automatically calls readDoc to parse
	public WebPage( String fileName, int sourceType ) {
		this.fileName = fileName;
		this.sourceType = sourceType;
		this.readDoc();
		this.generateCorrectHTML();
		this.collectWordCount();
	}
	
	// Parse given file and extract information
	private void readDoc() {
		try {
			if (sourceType == LOCAL) {
				this.doc = Jsoup.parse(new File(this.fileName), "UTF-8");
			} else {
				this.doc = Jsoup.connect(this.fileName).get();
			}
			this.origHTML = this.doc.html();
			this.wordText = this.doc.select("body").text();
			this.valid = true;
		} catch (IOException e) {
			this.doc = null;
			this.origHTML = null;
			this.wordText = null;
			this.valid = false;
			e.printStackTrace();
		}
		
	}

	// Iterate through bodyText and hash words into hash map
	// hash map keeps track of how many instances of each word there is
	// * Not used in actual program but useful for testing
	private void collectWordCount() {
		this.wordMap = new HashMap<String,Integer>();
		for ( String word : this.wordText.replaceAll("[^a-zA-z']"," ").split(" ") ) {
			if (wordMap.containsKey(word)) {
				wordMap.put(word, wordMap.get(word) + 1);
			} else {
				wordMap.put(word, 1);
			}
	    }
	}
	
	private void generateCorrectHTML() {
		JazzySpellChecker spellCheck = new JazzySpellChecker(this.wordText, this.origHTML);
		this.correctedHTML = spellCheck.getSuggestedHTML();
		this.misspelledWords = spellCheck.getMisspelledWords();
		this.suggestedWords = spellCheck.getSuggestedWords();
	}
	
	
	// write best suggested HTML to new file
	public void writeNewHTML(String newName) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(newName, "UTF-8");
			writer.println(this.correctedHTML);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
	/* Getters & Setters */
	
	public String getFileName() {
		return this.fileName;
	}
	
	public String getHTML() {
		return this.origHTML;
	}
	
	public Boolean isValid() {
		return this.valid;
	}
	
	public String getBodyText() {
		return this.wordText;
	}
	
	public String getCorrectHTML() {
		return this.correctedHTML;
	}
	
	public ArrayList<String> getMisspelledWords() {
		return this.misspelledWords;
	}
	
	public ArrayList<String> getSuggestedWords() {
		return this.suggestedWords;
	}
	
	public void printWordMap() {
		Iterator<String> keySetIterator = this.wordMap.keySet().iterator();
		while (keySetIterator.hasNext()) {
			String tempKey = keySetIterator.next();
			System.out.println(tempKey + " : " + wordMap.get(tempKey));
		}
	}
	
}
