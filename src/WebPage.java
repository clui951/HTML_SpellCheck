//import java.io.IOException;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.*;
//
//
//public class WebPage {
//
//	private String fileName;
//	private String html;
//	
//	// Constructor takes in file name or path to file 
//	public WebPage( String fileName ) {
//		this.fileName = fileName;
//	}
//	
//	public void readHTML() {
//		try {
//			Document doc = Jsoup.connect("http://example.com/").get();
//			System.out.println(doc.html());
//		} catch (IOException e) {
//			this.html = null;
//			e.printStackTrace();
//		}
//		
//	}
//	
//	
//	
//	
//	/* Getters & Setters */
//	
//	public String getFileName() {
//		return this.fileName;
//	}
//	
//}
