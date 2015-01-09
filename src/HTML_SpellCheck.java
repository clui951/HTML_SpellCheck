import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class HTML_SpellCheck {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		System.out.println("Running");
//		WebPage page = new WebPage("file");
//		page.readHTML();
//	}

	public static void main(String[] args) {
		System.out.println("RUNNING MAIN \n");
		try {
			Document doc = Jsoup.connect("http://example.com/").get();
			System.out.println("SUCCEED");
		} catch (IOException e) {
			System.out.println("\n\nFAIL");
			e.printStackTrace();
		}
	}
	
}
