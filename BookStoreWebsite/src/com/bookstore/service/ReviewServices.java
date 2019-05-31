package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewServices {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ReviewDAO reviewDAO;

	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		reviewDAO = new ReviewDAO();
	}

	public void listAllReviews() throws ServletException, IOException {
		listAllReviews(null);
	}

	public void listAllReviews(String message) throws ServletException, IOException {
		List<Review> listReviews = reviewDAO.listAll();

		if (message != null) {
			request.setAttribute("message", message);
		}

		request.setAttribute("listReviews", listReviews);

		String listPath = "review_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPath);
		dispatcher.forward(request, response);
	}

	public void editReview() throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("id"));

		Review review = reviewDAO.get(reviewId);

		request.setAttribute("review", review);

		String editPath = "review_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPath);
		dispatcher.forward(request, response);
	}

	public void updateReview() throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		String headLine = request.getParameter("headLine");
		String comment = request.getParameter("comment");

		Review review = reviewDAO.get(reviewId);

		review.setHeadline(headLine);
		review.setComment(comment);

		reviewDAO.update(review);

		String message = "Review has been updated successfully";
		listAllReviews(message);
	}

	public void deleteReview() throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("id"));

		reviewDAO.delete(reviewId);

		String message = "Review has been deleted successfully";
		listAllReviews(message);
	}

	public void showReviewForm() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("book_id"));

		BookDAO bookDAO = new BookDAO();

		Book book = bookDAO.get(bookId);

		HttpSession session = request.getSession();
		session.setAttribute("book", book);

		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");

		Review existReview = reviewDAO.findByCustomerAndBook(customer.getCustomerId(), bookId);

		String targetPage = "frontend/review_form.jsp";

		if (existReview != null) {
			request.setAttribute("review", existReview);
			targetPage = "frontend/review_info.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
		dispatcher.forward(request, response);
	}

	public void submitReview() throws IOException, ServletException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int rating = Integer.parseInt(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");

		Review review = new Review();
		review.setHeadline(headline);
		review.setComment(comment);
		review.setRating(rating);

		Book book = new Book();
		book.setBookId(bookId);

		review.setBook(book);

		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");

		review.setCustomer(customer);

		reviewDAO.create(review);

		String messagePage = "frontend/review_done.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
	}

}
