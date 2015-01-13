import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.engine.Word;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;
import com.swabunga.spell.event.SpellChecker;
import com.swabunga.spell.event.StringWordTokenizer;
import com.swabunga.spell.event.TeXWordFinder;

public class JazzySpellChecker implements SpellCheckListener {

	private SpellChecker spellChecker;

	// set up the dictionary once for all future uses
	private static SpellDictionaryHashMap dictionaryHashMap;
	static {
		File dict = new File("dictionary/dictionary.txt");
		try {
			dictionaryHashMap = new SpellDictionaryHashMap(dict);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() {
//		StringWordTokenizer texTok = new StringWordTokenizer("hey",
//			    new TeXWordFinder());
		spellChecker = new SpellChecker();
		StringWordTokenizer texTok = new StringWordTokenizer("hey");
		System.out.println(spellChecker.checkSpelling(texTok));
	}

	@Override
	public void spellingError(SpellCheckEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
