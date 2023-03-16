package myApp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType; 

@Path("/book")
public class BookResource {
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Book> getBooks() {
        return BookDAO.instance.getBooks();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{bookId}")
    public Book getBook(@PathParam("bookId") String id) {
        return BookDAO.instance.getBook(Integer.parseInt(id));
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void postBook(
    	@FormParam("id") String id,
        @FormParam("title") String title, 
        @FormParam("author") String author,
        @FormParam("year") String year,
        @Context HttpServletResponse servletResponse) throws IOException {
        System.out.println("Inside POST id = " + id);
        System.out.println("title = " + title);

        Book book = new Book();
        book.setId(Integer.parseInt(id));
        book.setTitle(title);
        book.setAuthor(author);
        book.setYear(Integer.parseInt(year));

        BookDAO.instance.create(book);

        servletResponse.sendRedirect("../createBook.html");
    }

    @PUT
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{bookId}")
    public void putBook(@PathParam("bookId") String id,
        @FormParam("title") String title, @FormParam("author") String author,
        @FormParam("year") String year,
        @Context HttpServletResponse servletResponse) throws IOException {
        System.out.println("PUT id = " + id);
        Book book = new Book();
        book.setId(Integer.parseInt(id));
        book.setTitle(title);
        book.setAuthor(author);
        book.setYear(Integer.parseInt(year));

        BookDAO.instance.create(book);
    }

	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("{bookId}")
	public void deleteBook(@PathParam("bookId") String id) throws IOException {
	  System.out.println("Delete id = " + id);
	  BookDAO.instance.delete(Integer.parseInt(id));
	}

    @GET
	@Produces(MediaType.TEXT_HTML)
	@Path("test")
	public void testDB( ) throws SQLException  {
	  System.out.println("TESTING DATABASE");
	  BookDAO.instance.testDB();
	}
	
 
}