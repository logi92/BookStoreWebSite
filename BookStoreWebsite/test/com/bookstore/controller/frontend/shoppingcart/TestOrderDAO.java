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
		orderDetail.setQuantity(2);
		orderDetail.setSubTotal(87.72f);
		orderDetail.setBookOrder(order);

		orderDetails.add(orderDetail);

		order.setOrderDetails(orderDetails);

		BookOrder saveOrder = orderDAO.create(order);

		assertTrue(order.getOrderId() > 0);

	}

	@Test
	public void testCreateBookOrder2() {
		BookOrder order = new BookOrder();

		Customer customer = new Customer();
		customer.setCustomerId(24);

		order.setCustomer(customer);
		order.setRecipientName("Ruslan");
		order.setRecipientPhone("12345678");
		order.setShippingAddress("g Kulebaki mkr Zapad d21");

		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail1 = new OrderDetail();

		Book book1 = new Book(8);
		orderDetail1.setBook(book1);
		orderDetail1.setQuantity(1);
		orderDetail1.setSubTotal(33.29f);
		orderDetail1.setBookOrder(order);

		orderDetails.add(orderDetail1);

		OrderDetail orderDetail2 = new OrderDetail();

		Book book2 = new Book(7);
		orderDetail2.setBook(book2);
		orderDetail2.setQuantity(1);
		orderDetail2.setSubTotal(27.33f);
		orderDetail2.setBookOrder(order);

		orderDetails.add(orderDetail2);

		order.setOrderDetails(orderDetails);

		BookOrder saveOrder = orderDAO.create(order);

		assertTrue(order.getOrderId() > 0 && order.getOrderDetails().size() == 2);

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
