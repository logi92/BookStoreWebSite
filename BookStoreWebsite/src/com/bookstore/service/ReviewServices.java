package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.ReviewDAO;
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

}
