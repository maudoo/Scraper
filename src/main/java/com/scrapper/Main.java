package com.scrapper;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException{
        
        WebClient webClient = createWebClient();
	    
		try {
			String link = "https://www.ebay.com/itm/332852436920?epid=108867251&hash=item4d7f8d1fb8:g:cvYAAOSwOIlb0NGY";
			HtmlPage page = webClient.getPage(link);
			
			System.out.println(page.getTitleText());
			
			String xpath = "//*[@id=\"mm-saleDscPrc\"]";			
			HtmlSpan priceDiv = (HtmlSpan) page.getByXPath(xpath).get(0);			
			System.out.println(priceDiv.asNormalizedText());
			
			CsvWriter.writeCsvFile(link, priceDiv.asNormalizedText());
			
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		} finally {
			webClient.close();
		}	

    }

	public static void writeCsvFile(String link, String price) throws IOException {
		
		FileWriter recipesFile = new FileWriter("export.csv", true);

		recipesFile.write("link, price\n");

		recipesFile.write(link + ", " + price);

		recipesFile.close();
	}
	
    private static WebClient createWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setCssEnabled(false);
      webClient.getOptions().setJavaScriptEnabled(false);
        return webClient;
    }
}