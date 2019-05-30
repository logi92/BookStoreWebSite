package com.bookstore.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Review;

public class BookGetAverageRatingTest {

	@Test
	public void testGetAverageRating() {
		Book book = new Book();

		Set<Review> reviews = new HashSet<Review>();

		float averageRating = book.getAverageRating();

		assertEquals(0.0, averageRating, 0.0);
	}
	
	@Test
	public void testGetAverageRating1() {
		Book book = new Book();

		Set<Review> reviews = new HashSet<Review>();

		Review review1 = new Review();
		review1.setRating(5);
		
		reviews.add(review1);
		
		book.setReviews(reviews);
		
		float averageRating = book.getAverageRating();

		assertEquals(5.0, averageRating, 0.0);
	}
	
	@Test
	public void testGetAverageRating2() {
		Book book = new Book();

		Set<Review> reviews = new HashSet<Review>();

		Review review1 = new Review();
		review1.setRating(5);
		
		Review review2 = new Review();
		review2.setRating(2);
		
		reviews.add(review1);
		reviews.add(review2);
		
		book.setReviews(reviews);
		
		float averageRating = book.getAverageRating();

		assertEquals(3.5, averageRating, 0.0);
	}
	
	@Test
	public void testGetAverageRating3() {
		Book book = new Book();

		Set<Review> reviews = new HashSet<Review>();

		Review review1 = new Review();
		review1.setRating(5);
		
		Review review2 = new Review();
		review2.setRating(2);
		
		Review review3 = new Review();
		review3.setRating(2);
		
		reviews.add(review1);
		reviews.add(review2);
		reviews.add(review3);
		
		book.setReviews(reviews);
		
		float averageRating = book.getAverageRating();

		assertEquals(3.0, averageRating, 0.0);
	}

}
