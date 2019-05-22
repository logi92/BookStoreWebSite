package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("")
public class HomeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO(entityManager);
		BookDAO bookDAO = new BookDAO(entityManager);
		
		List<Book> listBooks = bookDAO.listNewBooks();
		List<Category> listCategory = categoryDAO.listAll();

		request.setAttribute("listCategory", listCategory);
		request.setAttribute("listBooks", listBooks);

		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/index.jsp");
		dispatcher.forward(request, response);
	}
}
