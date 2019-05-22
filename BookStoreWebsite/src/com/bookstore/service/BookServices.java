package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {
	private EntityManager entityManager;
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public BookServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		super();
		this.entityManager = entityManager;
		this.request = request;
		this.response = response;
		bookDAO = new BookDAO(entityManager);
		categoryDAO = new CategoryDAO(entityManager);
	}

//================================================================== LIST
	public void listBooks() throws ServletException, IOException {
		listBooks(null);
	}

//================================================================== LIST
	public void listBooks(String message) throws ServletException, IOException {
		List<Book> listBooks = bookDAO.listAll();
		request.setAttribute("listBooks", listBooks);

		if (message != null) {
			request.setAttribute("message", message);
		}

		String listPage = "book_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

//================================================================== showBookNewForm
	public void showBookNewForm() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();

		request.setAttribute("listCategory", listCategory);

		String newPage = "book_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(newPage);
		dispatcher.forward(request, response);
	}

//================================================================== CREATE
	public void createBook() throws ServletException, IOException {
		String title = request.getParameter("title");
		Book newBook = new Book();
		readBookFields(newBook);

		Book existBook = bookDAO.findByTitle(title);
		if (existBook != null) {
			String message = "Could not create new book because this title: \"" + title + "\" already exists!";
			listBooks(message);
			return;
		}
		Book createdBook = bookDAO.create(newBook);
		if (createdBook != null) {
			String message = "A new Book has been created successfully!";
			listBooks(message);
		}

	}

//================================================================== READ
	public void readBookFields(Book book) throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = null;
		try {
			publishDate = dateFormat.parse(request.getParameter("publishDate"));
		} catch (ParseException ex) {
			ex.getStackTrace();
			throw new ServletException("Error parsing publish date");
		}

		float price = Float.parseFloat(request.getParameter("price"));
		String description = request.getParameter("description");

		book.setAuthor(author);
		book.setTitle(title);
		book.setIsbn(isbn);
		book.setPrice(price);
		book.setDescription(description);
		book.setPublishDate(publishDate);

		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		Category category = categoryDAO.get(categoryId);
		book.setCategory(category);

		Part part = request.getPart("bookImage");

		if (part != null && part.getSize() > 0) {
			int size = (int) part.getSize();
			byte[] imageBytes = new byte[size];

			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();

			book.setImage(imageBytes);
		}
	}

//================================================================== EDIT
	public void editBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));

		Book existBook = bookDAO.get(bookId);
		request.setAttribute("book", existBook);

		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);

		String formPage = "book_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(formPage);
		dispatcher.forward(request, response);
	}

//================================================================== UPDATE
	public void updateBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");

		Book existBook = bookDAO.get(bookId);
		Book bookByTitle = bookDAO.findByTitle(title);

		if (!existBook.equals(bookByTitle)) {
			String message = "Could not update Book because there's another book has same title";
			listBooks(message);
			return;
		}

		readBookFields(existBook);

		bookDAO.update(existBook);

		String message = "The Book has been updated successfully!";
		System.out.println(message);
		listBooks(message);

	}

//================================================================== DELETE
	public void deleteBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		bookDAO.delete(bookId);

		String message = "The Book has been deleted successfully!";
		listBooks(message);
	}

//================================================================== ListBooksByCategory
	public void listBooksByCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));

		Category category = categoryDAO.get(categoryId);
		List<Book> listBooks = bookDAO.listByCategory(categoryId);
		List<Category> listCategory = categoryDAO.listAll();

		request.setAttribute("listCategory", listCategory);
		request.setAttribute("category", category);
		request.setAttribute("listBooks", listBooks);

		String categoryPage = "frontend/book_list_by_category.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(categoryPage);
		dispatcher.forward(request, response);
	}

}
