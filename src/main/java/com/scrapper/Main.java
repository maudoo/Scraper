package com.scrapper;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
 import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class Main {
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        WebClient webClient = createWebClient();
		HtmlPage currentPage = webClient.getPage("https://www.abdalians.com");	

		// Navigate to a specific page within the website
		String specificPageUrl = "https://abdalians.com/component/comprofiler/login.html";
		HtmlPage productPage = webClient.getPage(specificPageUrl);

		HtmlInput usernameField = (HtmlInput) productPage.getElementById("mod_login_username");
		HtmlInput passwordField = (HtmlInput) productPage.getElementById("mod_login_password");

		System.out.println("enter username");
		String usernam = in.nextLine();
		System.out.println("enter pass");
		String pass = in.nextLine();

		usernameField.setValueAttribute(usernam);
		passwordField.setValueAttribute(pass);

		// Assuming the submit button has the ID "submitButton"
		HtmlButton submitButton = (HtmlButton) productPage.getElementByName("submit");
		HtmlPage loggedInPage = submitButton.click();

		System.out.println("Success!!");

	/*try {
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
		}	 */    
		

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