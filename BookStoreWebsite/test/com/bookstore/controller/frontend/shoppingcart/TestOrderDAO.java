package com.bookstore.controller.frontend.shoppingcart;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderDetailId;

public class TestOrderDAO {
	private static OrderDAO orderDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder order = new BookOrder();

		Customer customer = new Customer();
		customer.setCustomerId(2);

		order.setCustomer(customer);
		order.setRecipientName("Ruslan");
		order.setRecipientPhone("12345678");
		order.setShippingAddress("g Kulebaki mkr Zapad d21");

		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail = new OrderDetail();

		Book book = new Book(2);
		orderDetail.setBook(book);

		OrderDetailId orderDetailId = new OrderDetailId();
		orderDetailId.setQuantity(2);
		orderDetailId.setSubTotal(87.72f);

		orderDetail.setId(orderDetailId);
		orderDetails.add(orderDetail);

		order.setOrderDetails(orderDetails);

		BookOrder saveOrder = orderDAO.create(order);

		assertNotNull(saveOrder);

	}

	@Test
	public void testUpdateBookOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}
}
