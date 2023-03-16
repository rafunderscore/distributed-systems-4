package question_one;

import java.io.InputStream;
import java.net.URI;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HelloClient {
	public static void main(String[] args) {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        try {
            URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
            						  .setPath("/j2BookServer/rest/book").build();

			System.out.println(uri.toString());

			HttpGet httpGet = new HttpGet(uri);
			httpClient = HttpClients.createDefault();
			response = httpClient.execute(httpGet);

			String text = null;
			try {
			    HttpEntity entity = response.getEntity();
			    InputStream instream = entity.getContent();
			    try {
			        try (Scanner sc = new Scanner(instream)) {
						while (sc.hasNextLine()) { 
						  System.out.println(sc.nextLine());        
						}
					}
			    }finally {
			    	instream.close();
			    }
			} finally {
				response. close();
			}
			System.out.println("REPLY:"+ text);
        }
        catch (Exception e) {
        	e.printStackTrace(); }
        }
}
