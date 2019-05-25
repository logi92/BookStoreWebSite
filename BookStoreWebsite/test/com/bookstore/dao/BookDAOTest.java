package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest {

	private static BookDAO bookDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDAO = new BookDAO();
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook = new Book();

		Category category = new Category("Advanced Java");
		category.setCategoryId(2);

		newBook.setCategory(category);
		newBook.setAuthor("Raoul-Gabriel Urma");
		newBook.setTitle("Java 8 in Action: Lambdas");
		newBook.setDescription(
				"ava 8 in Action is a clearly written guide to the new features of Java 8. The book covers lambdas, streams, and functional-style programming."
						+ " With Java 8's functional features you can now write more concise code in less time, and also automatically benefit from multicore architectures."
						+ " It's time to dig in!\r\n"
						+ "Purchase of the print book includes a free eBook in PDF, Kindle, and ePub formats from Manning Publications.\r\n"
						+ "About the Boo");
		newBook.setIsbn("1617291994");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("08/28/2014");
		newBook.setPublishDate(publishDate);

		byte[] image = Files.readAllBytes(Paths.get("C:\\Users\\Ruslan\\Desktop\\books\\Java In Action.JPG"));
		newBook.setImage(image);

		newBook.setPrice(29.75f);

		Book createdBook = bookDAO.create(newBook);

		assertTrue(createdBook.getBookId() > 0);
	}

	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book existBook = new Book();
		existBook.setBookId(5);

		Category category = new Category("Advanced Java");
		category.setCategoryId(2);

		existBook.setCategory(category);
		existBook.setAuthor("Joshua Bloch");
		existBook.setTitle("Effective Java (3rd edition)");
		existBook.setDescription(
				"Java has changed dramatically since the previous edition of Effective Java was published shortly "
						+ "after the release of Java 6. This Jolt award-winning classic has now been thoroughly updated to take full advantage of the "
						+ "latest language and library features. The support in modern Java for multiple paradigms increases the need for specific "
						+ "best-practices advice, and this book delivers.");
		existBook.setIsbn("0134685997");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2018");
		existBook.setPublishDate(publishDate);

		byte[] image = Files.readAllBytes(Paths.get("C:\\Users\\Ruslan\\Desktop\\books\\Effective java.JPG"));
		existBook.setImage(image);

		existBook.setPrice((float) 43);

		Book createdBook = bookDAO.update(existBook);

		assertEquals(createdBook.getTitle(), "Effective Java (3rd edition)");
	}

	@Test
	public void testDeleteBookSuccess() {
		Integer bookId = 1;
		bookDAO.delete(bookId);

		Book expectedBook = bookDAO.get(bookId);

		assertNull(expectedBook);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookId = 100;
		bookDAO.delete(bookId);
	}

	@Test
	public void testGetBookSuccess() {
		Integer bookId = 2;
		Book expectedBook = bookDAO.get(bookId);

		assertNotNull(expectedBook);
	}

	@Test
	public void testGetBookFail() {
		Integer bookId = 100;
		Book expectedBook = bookDAO.get(bookId);

		assertNull(expectedBook);
	}

	@Test
	public void testListAll() {
		List<Book> listBooks = bookDAO.listAll();

		for (Book aBook : listBooks) {
			System.out.println(aBook.getTitle());
		}
		assertTrue(listBooks.size() > 0);
	}

	@Test
	public void testFindByTitleExist() {
		String title = "Effective java";
		Book book = bookDAO.findByTitle(title);

		System.out.println(book.getAuthor());

		assertNotNull(book);
	}

	@Test
	public void testFindByTitleNotExist() {
		String title = "Effect j";
		Book book = bookDAO.findByTitle(title);

		assertNull(book);
	}

	@Test
	public void testCountAll() {
		int totalBooks = (int) bookDAO.count();
		assertEquals(2, totalBooks);

	}

	@Test
	public void testListByCategory() {
		int categoryId = 1;
		List<Book> listBooks = bookDAO.listByCategory(categoryId);
		for (Book book : listBooks) {
			System.out.println(book.getTitle());
		}

		assertTrue(listBooks.size() > 0);
	}

	@Test
	public void testListNewBooks() {
		List<Book> result = bookDAO.listNewBooks();

		for (Book item : result) {
			System.out.println(item.getTitle());
		}

		assertEquals(4, result.size());
	}

	@Test
	public void testSearchByTitle() {
		String keyword = "Java";

		List<Book> result = bookDAO.search(keyword);
		for (Book item : result) {
			System.out.println(item.getTitle());
		}

		assertTrue(result.size() > 0);
	}

	@Test
	public void testSearchByAuthor() {
		String keyword = "Coward";

		List<Book> result = bookDAO.search(keyword);
		for (Book item : result) {
			System.out.println(item.getTitle());
		}

		assertTrue(result.size() > 0);
	}

	@Test
	public void testSearchBy¬ÛDescription() {
		String keyword = "concise";

		List<Book> result = bookDAO.search(keyword);
		for (Book item : result) {
			System.out.println(item.getTitle());
		}

		assertTrue(result.size() > 0);
	}

	@Test
	public void testCountByCategory() {
		int categoryId = 8;
		long result = bookDAO.countByCategory(categoryId);

		System.out.println(result);
		assertEquals(1, result);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDAO.close();
	}

}
