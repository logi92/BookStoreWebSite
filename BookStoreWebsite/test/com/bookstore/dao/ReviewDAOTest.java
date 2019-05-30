package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest {
	private static ReviewDAO reviewDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDAO = new ReviewDAO();
	}

	@Test
	public void testCreate() {
		Review review = new Review();

		Book book = new Book();
		Customer customer = new Customer();

		int bookId = 4;
		int customerId = 2;

		book.setBookId(bookId);
		customer.setCustomerId(customerId);

		review.setBook(book);
		review.setCustomer(customer);
		review.setComment("This is big comment! It'is true!");
		review.setHeadline("Second Headline, should be First");
		review.setRating(1);

		Review result = reviewDAO.create(review);

		assertNotNull(result);
	}

	@Test
	public void testGet() {
		int reviewId = 2;

		Review review = reviewDAO.get(reviewId);

		System.out.println(review.getComment());

		assertNotNull(review);
	}

	@Test
	public void testUpdateReview() {
		int reviewId = 1;

		Review review = reviewDAO.get(reviewId);
		System.out.println(review.getComment());
		String newComment = "I change this comment becase it is small comment!";

		review.setComment(newComment);
		System.out.println(review.getComment());

		Review updatedReview = reviewDAO.update(review);

		System.out.println(updatedReview.getComment());

		assertTrue(updatedReview.getComment().equals(newComment));
	}

	@Test
	public void testListAll() {
		List<Review> result = reviewDAO.listAll();

		for (Review item : result) {
			System.out.println(item.getHeadline() + " - " + item.getBook().getTitle());
		}

		assertTrue(result.size() > 0);
	}
	
	@Test
	public void testCountAll() {
		long result = reviewDAO.count();
		
		assertEquals(2, result);
	}
	
	@Test
	public void testDelete() {
		int reviewId = 2;
		
		reviewDAO.delete(reviewId);
		
		Review review = reviewDAO.get(reviewId);
		
		assertNull(review);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDAO.close();
	}

}
