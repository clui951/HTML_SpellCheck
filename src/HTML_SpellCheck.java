import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class HTML_SpellCheck {

	
	public static void main(String[] args) {
		System.out.println("Running\n\n");
		WebPage page = new WebPage("../sample_small.html", WebPage.LOCAL);
		System.out.println("Mispelled Words: " + page.getMisspelledWords());
		System.out.println("Suggested Words: " + page.getSuggestedWords() + "\n");
		System.out.println(page.getCorrectHTML());
		page.writeNewHTML("NEW_HTML.html");
		
//		Scanner in = new Scanner(System.in);
//		System.out.println("SpellCheck HTML (F)ile or (W)ebsite: ");
//		String sourceType = in.nextLine();
//		int sourceIndic;
//		if (sourceType == "F") {
//			sourceIndic = WebPage.LOCAL;
//		} else {
//			sourceIndic = WebPage.WEB;
//		}
//		System.out.println("Enter file or website to spellcheck: ");
//		String sourcePath = in.nextLine();
//		WebPage page1 = new WebPage(sourcePath, sourceIndic);
		
	}

	
	
}
