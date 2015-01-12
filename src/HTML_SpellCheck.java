import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class HTML_SpellCheck {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Running\n");
		WebPage page = new WebPage("../sample.html", WebPage.LOCAL);
//		System.out.println(page.getHTML());
		System.out.println(page.getBodyText() + "\n\n");
		page.collectWords();
		page.printWordMap();
	}

	
	
}
