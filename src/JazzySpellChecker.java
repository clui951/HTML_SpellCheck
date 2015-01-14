import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.engine.Word;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;
import com.swabunga.spell.event.SpellChecker;
import com.swabunga.spell.event.StringWordTokenizer;
import com.swabunga.spell.event.TeXWordFinder;

public class JazzySpellChecker {

	private String origText;
	private SpellChecker spellChecker;
	private ArrayList<String> misspelledWords;
	private ArrayList<String> suggestedWords;
	private String origHTML;
	private String suggHTML;
	

	// set up the dictionary once for all future uses
	private static SpellDictionaryHashMap dictionaryHashMap;
	static {
		File dict = new File("../dictionary/dictionary.txt");
		try {
			dictionaryHashMap = new SpellDictionaryHashMap(dict);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Constructor takes in string to spell check
	public JazzySpellChecker(String inputText, String inputHTML) {
		this.origText = inputText;
		this.origHTML = inputHTML;
		this.suggHTML = inputHTML;
		spellChecker = new SpellChecker(dictionaryHashMap);
		this.collectMisspelledWords();
		this.collectSuggestedWords();
		this.generateBestGuessHTML();
	}
	
	// Called by Constructor to store all misspelled words into our ArrayList misspelledWords
	private void collectMisspelledWords() {
		this.misspelledWords = new ArrayList<String>();
		for (String word : this.origText.replaceAll("[^a-zA-z']", " ").split(" ")) {
			StringWordTokenizer wordTok = new StringWordTokenizer(word, new TeXWordFinder());
			if (spellChecker.checkSpelling(wordTok) == 1) {
				misspelledWords.add(word);
			}
		}  
	 }
	
	// Called by Constructor to generate ArrayList of a best guess for each misspelled word
	private void collectSuggestedWords() {
		this.suggestedWords = new ArrayList<String>();
		for (String wrongWord : this.misspelledWords) {
			List<Word> singleWordSuggestions = spellChecker.getSuggestions(wrongWord, 0);
			if (singleWordSuggestions.size() != 0) {
				String suggestedWord = singleWordSuggestions.get(0).getWord();
				this.suggestedWords.add( suggestedWord );
			} else {
				// no replacement word found
				this.suggestedWords.add(wrongWord);
			}
		}
	}
	
	private void generateBestGuessHTML() {
		for (int i = 0; i < this.suggestedWords.size(); i++) {
			this.suggHTML.replaceFirst(this.misspelledWords.get(i), this.suggestedWords.get(i));
		}
	}
	
	
	
	
	/* Getters and Setters */
	
	public ArrayList<String> getMisspelledWords() {
		return this.misspelledWords;
	}
	
	public ArrayList<String> getSuggestedWords() {
		return this.suggestedWords;
	}
	
	public String getSuggestedHTML() {
		return this.suggHTML;
	}
	


	
}
