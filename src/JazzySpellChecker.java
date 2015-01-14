import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.engine.Word;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;
import com.swabunga.spell.event.SpellChecker;
import com.swabunga.spell.event.StringWordTokenizer;
import com.swabunga.spell.event.TeXWordFinder;

public class JazzySpellChecker implements SpellCheckListener {

	private String inputText;
	private SpellChecker spellChecker;
	private ArrayList<String> misspelledWords;
	

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
	public JazzySpellChecker(String input) {
		this.inputText = input;
		spellChecker = new SpellChecker(dictionaryHashMap);
		misspelledWords = new ArrayList<String>();
		this.collectMisspelledWords();
	}
	
	// Called by Constructor to store all misspelled words into our ArrayList misspelledWords
	private void collectMisspelledWords() {
		for (String word : this.inputText.replaceAll("[^a-zA-z']", " ").split(" ")) {
			StringWordTokenizer wordTok = new StringWordTokenizer(word, new TeXWordFinder());
			if (spellChecker.checkSpelling(wordTok) == 1) {
				misspelledWords.add(word);
			}
		}  
	 }
	
	
	
	/* Getters and Setters */
	
	public ArrayList<String> getMisspelledWords() {
		return this.misspelledWords;
	}
	
	
	
	public void runTest() {
		StringWordTokenizer texTok = new StringWordTokenizer("hoey");
		// returns -1 if word exists; 1 otherwise
		System.out.println(spellChecker.checkSpelling(texTok));
	}

	@Override
	public void spellingError(SpellCheckEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
