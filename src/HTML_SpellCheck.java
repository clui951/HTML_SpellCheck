import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class HTML_SpellCheck {

	
	public static void main(String[] args) {
//		System.out.println("Running\n\n");
//		WebPage pageTest = new WebPage("../sample_small.html", WebPage.LOCAL);
//		System.out.println("Mispelled Words: " + pageTest.getMisspelledWords());
//		System.out.println("Suggested Words: " + pageTest.getSuggestedWords() + "\n");
//		System.out.println(pageTest.getCorrectHTML());
//		pageTest.writeNewHTML("NEW_HTML.html");
		
		
		Scanner in = new Scanner(System.in);
		System.out.print("\nSpellCheck HTML (F)ile or (W)ebsite: ");
		String sourceType = in.nextLine();
		int sourceIndic;
		if (sourceType.equals("F")) {
			sourceIndic = WebPage.LOCAL;
			System.out.print("Enter file to spellcheck: \n > ");
		} else {
			sourceIndic = WebPage.WEB;
			System.out.print("Enter website to spellcheck: \n >");
		}
		String sourcePath = in.nextLine();
		WebPage page = new WebPage(sourcePath, sourceIndic);
		System.out.println("\n\nMispelled Words Found: " + page.getMisspelledWords());
		System.out.println("Suggested Words Found: " + page.getSuggestedWords() + "\n");
		System.out.println("--- New Generated HTML Begin --- \n\n");
		System.out.println(page.getCorrectHTML() + "\n\n--- New Generated HTML End --- \n\n");
		
		System.out.print("Write to file ('yes' or 'no')? ");
		String writeOpt = in.nextLine();
		if (writeOpt.equals("yes")) {
			System.out.print("Specify file name or path: \n > ");
			String writeDest = in.nextLine();
			page.writeNewHTML(writeDest);
		}
		System.out.println();
		
		
		
	}

	
	
}
