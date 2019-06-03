package com.bookstore.controller.frontend.shoppingcart;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

public class TestShoppingCart {
	private static ShoppingCart cart;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cart = new ShoppingCart();
		Book book = new Book(2, 10);

		cart.adddItem(book);
		cart.adddItem(book);
	}

	@Test
	public void testAddItem() {
		Map<Book, Integer> items = cart.getItems();

		int actual = items.get(new Book(2));

		assertEquals(2, actual);
	}

	@Test
	public void testRemoveItem() {
		cart.removeItem(new Book(2));

		assertTrue(cart.getItems().isEmpty());

	}

	@Test
	public void testRemoveItem2() {
		Book book2 = new Book(1);
		cart.adddItem(book2);

		cart.removeItem(new Book(1));

		assertNull(cart.getItems().get(book2));

	}

	@Test
	public void testGetTotalQuantity() {
		assertEquals(2, cart.getTotalQuantity());
	}

	@Test
	public void testGetTotalAmount() {
		assertEquals(0.0f, cart.getTotalAmount(), 0.0f);
	}

	@Test
	public void testGetTotalAmount2() {
		assertEquals(20.0f, cart.getTotalAmount(), 0.0f);
	}

	@Test
	public void testClearCart() {
		cart.clear();
		assertEquals(0, cart.getTotalQuantity());
	}

	@Test
	public void testUpdateCart() {
		ShoppingCart cart = new ShoppingCart();

		Book book1 = new Book(1);
		Book book2 = new Book(2);

		cart.adddItem(book1);
		cart.adddItem(book2);

		int[] bookIds = { 1, 2 };
		int[] quantities = { 3, 4 };

		cart.updateCart(bookIds, quantities);

		assertEquals(7, cart.getTotalQuantity());
	}

}
