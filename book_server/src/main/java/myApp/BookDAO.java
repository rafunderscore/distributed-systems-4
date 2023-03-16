package myApp;

import java.lang.invoke.TypeDescriptor;
import java.sql.*;
import java.util.*;

import javax.print.CancelablePrintJob;

public enum BookDAO {
  instance;

  private Map<Integer, Book> booksMap = new HashMap<Integer, Book>();

  private BookDAO() {
    Book book1 = new Book();
    book1.setId(1);
    book1.setTitle("Book 1 Title");
    book1.setAuthor("Author 1");
    book1.setYear(2002);

    booksMap.put(1, book1);

    Book book2 = new Book();
    book2.setId(2);
    book2.setTitle("Book 2 Title");
    book2.setAuthor("Author 2");
    book2.setYear(1988);

    booksMap.put(2, book2);
  }

  public List<Book> getBooks() {
    List<Book> books = new ArrayList<Book>();
    books.addAll(booksMap.values());
    return books;
  }

  public Book getBook(int id) {
    return booksMap.get(id);
  }

  void create(Book book) {
    booksMap.put(book.getId(), book);
  }

  void update(Book book) {
    booksMap.put(book.getId(), book);
  }

  void delete(int id) {
    System.out.println("Deleting the book with the id of: " + id);
    booksMap.remove(id);
  }
  
  public String testDB() throws SQLException{
    String name = null; 
    StringBuilder sb = new StringBuilder();

    Connection connection = null;

    try{
      Class.forName("org.hsqldb.jdbcDriver");
      connection = DriverManager.getConnection("jdbc:hsqldb:hsql//localhost/oneDB", "SA", "Passw0rd");
      Statement statement = connection.createStatement();
      ResultSet resultSet =  statement.executeQuery("select * from user;");
      while(resultSet.next()){
        sb.append(resultSet.getString("name")).append("\n");
      }
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
	return name;
  }

  @SuppressWarnings("unused")
	private Connection getConnection() throws SQLException {
	    try {
	      // driver
	      Class.forName("org.hsqldb.jdbcDriver");
	    } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    }
	    return DriverManager.getConnection(
	        "jdbc:hsqldb:hsql//localhost/oneDB", "SA", "Passw0rd");
	  }
}
