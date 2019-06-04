package com.bookstore.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.bookstore.entity.Book;

public class ShoppingCart {
//Используем МАП , что бы сохранять книги которые добавил кастомер в шопинг карт
	private Map<Book, Integer> cart = new HashMap<Book, Integer>();

	public void adddItem(Book book) {
		// Проверяем, если книга уже содержится в мапе, то увеличивем количество на 1
		if (cart.containsKey(book)) {
			Integer quantity = cart.get(book) + 1;
			cart.put(book, quantity);
		} else { // if the cart doesn't content the book
			cart.put(book, 1);
		}
	}

	public Map<Book, Integer> getItems() {
		return this.cart;
	}

	public int getTotalQuantity() {
		int total = 0;

		Iterator<Book> iterator = cart.keySet().iterator();

		while (iterator.hasNext()) {
			Book next = iterator.next();
			Integer quantity = cart.get(next); // Returns the value to which the specified key is mapped
			total += quantity;
		}

		return total;
	}

	public float getTotalAmount() {
		float total = 0.0f;

		Iterator<Book> iterator = cart.keySet().iterator();

		while (iterator.hasNext()) {
			Book book = iterator.next();
			Integer quantity = cart.get(book); // Returns the value to which the specified key is mapped
			float subTotal = quantity * book.getPrice(); //
			total += subTotal;
		}

		return total;
	}

	public void removeItem(Book book) {
		cart.remove(book);
	}

	public int getTotalItems() {
		return cart.size();
	}

	public void updateCart(int[] bookIds, int[] quantities) {
		for (int i = 0; i < bookIds.length; i++) {
			Book key = new Book(bookIds[i]);
			Integer value = quantities[i];
			cart.put(key, value);
		}
	}

	public void clear() {
		cart.clear();
	}

}
