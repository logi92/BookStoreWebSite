package com.bookstore.controller.admin.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;

/**
 * Servlet implementation class AddBookToOrderServlet
 */
@WebServlet("/admin/add_book_to_order")
public class AddBookToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		BookDAO bookDAO = new BookDAO();
		
		Book book = bookDAO.get(bookId);
		
		HttpSession session = request.getSession();
		BookOrder order = (BookOrder) session.getAttribute("order");
		
		float subTotal = quantity * book.getPrice();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setBook(book);
		orderDetail.setQuantity(quantity);
		orderDetail.setSubTotal(subTotal);
		
		float total = order.getOrderTotal() + subTotal;
		order.setOrderTotal(total);
		
		order.getOrderDetails().add(orderDetail);
		
		request.setAttribute("book", book);
		session.setAttribute("NewBookPendingToAddToorder", true);
		
		String resultPage = "add_book_result.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage); 
		dispatcher.forward(request, response);
	}
	

}
