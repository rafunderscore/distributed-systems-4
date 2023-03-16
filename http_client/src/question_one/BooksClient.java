package question_one;

import java.net.URI;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class BooksClient {
    public static void main(String[] args) throws Exception {
        CloseableHttpResponse response = null;
        try {
            URI uri = new URIBuilder()
                          .setScheme("http")
                          .setHost("localhost")
                          .setPort(8080)
                          .setPath("/j2BookServer/rest/book")
                          .build();
            
            System.out.println(uri.toString());
            
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("Accept", "application/xml");
            
            CloseableHttpClient httpClient = HttpClients.createDefault();
            response = httpClient.execute(httpGet);
           
            HttpEntity entity = response.getEntity();
            String text = EntityUtils.toString(entity);
            System.out.println(text);
            
            List<Book> bookList = new ParseBooks().doParseBooks(text);
            System.out.println(" ");
            for (Book book : bookList) {
                System.out.println("ID: " + book.getId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Year: " + book.getYear());
                System.out.println(" ");
            }
        } finally {
            response.close();
        }
    }
}
