import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class HTML_SpellCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Running");
		WebPage page = new WebPage("../sample.html");
		System.out.println(page.getHTML());
	}

	
	
}
