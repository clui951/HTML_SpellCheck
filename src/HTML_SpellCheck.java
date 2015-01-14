import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class HTML_SpellCheck {

	
	public static void main(String[] args) {
//		System.out.println("Running\n");
//		WebPage page = new WebPage("../sample.html", WebPage.LOCAL);
//		System.out.println(page.getHTML());
//		System.out.println(page.getBodyText() + "\n\n");
//		page.collectWordCount();
//		page.printWordMap();
		System.out.println("\n");
		JazzySpellChecker spellCheck = new JazzySpellChecker("This is the input string rerer");
//		spellCheck.runTest();
		System.out.println(spellCheck.getMisspelledWords());
		
	}

	
	
}
