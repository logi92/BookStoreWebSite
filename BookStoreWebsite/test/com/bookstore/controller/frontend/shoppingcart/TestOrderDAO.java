package com.bookstore.controller.frontend.shoppingcart;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
		order.setRecipientName("Andrey");
		order.setRecipientPhone("987654321");
		order.setShippingAddress("NiNo Big House 001");

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
	public void testUpdateBookOrderShippingAddress() {
		Integer orderId = 7;
		BookOrder order = orderDAO.get(orderId);
		order.setShippingAddress("New Address");

		BookOrder updatedOrder = orderDAO.update(order);

		assertEquals(order.getShippingAddress(), updatedOrder.getShippingAddress());
	}

	@Test
	public void testUpdateBookOrderDetail() {
		Integer orderId = 8;
		BookOrder order = orderDAO.get(orderId);

		Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();

		while (iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if (orderDetail.getBook().getBookId() == 7) {
				orderDetail.setQuantity(3);
				orderDetail.setSubTotal(81.99f);
			}
		}

		orderDAO.update(order);

		BookOrder updatedOrder = orderDAO.get(orderId);

		iterator = order.getOrderDetails().iterator();

		int expectedQuantity = 3;
		float expectedSubtotal = 81.99f;
		int actualQuantity = 0;
		float actualSubtotal = 0;

		while (iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if (orderDetail.getBook().getBookId() == 7) {
				actualQuantity = orderDetail.getQuantity();
				actualSubtotal = orderDetail.getSubTotal();
			}
		}

		assertEquals(expectedQuantity, actualQuantity);
		assertEquals(expectedSubtotal, actualSubtotal, 0.0f);
	}

	@Test
	public void testGet() {
		Integer orderId = 7;
		BookOrder order = orderDAO.get(orderId);

		assertEquals(1, order.getOrderDetails().size());
	}

	@Test
	public void testDeleteObject() {
		Integer orderId = 7;

		orderDAO.delete(orderId);

		long actual = orderDAO.count();

		assertEquals(1, actual);
	}

	@Test
	public void testListAll() {
		List<BookOrder> listOrders = orderDAO.listAll();

		for (BookOrder order : listOrders) {
			System.out.println(order.getOrderId() + " - " + order.getCustomer().getFullname());

			for (OrderDetail detail : order.getOrderDetails()) {
				Book book = detail.getBook();
				int quantity = detail.getQuantity();
				float subtotal = detail.getSubTotal();

				System.out.println("\t" + book.getTitle() + " - " + quantity + " - " + subtotal);
			}
		}

		assertTrue(listOrders.size() == 2);
	}

	@Test
	public void testCount() {
		long result = orderDAO.count();

		assertEquals(2, result);
	}

	@Test
	public void testListByCustomer() {
		Integer customerId = 2;
		List<BookOrder> listOrders = orderDAO.listByCustomer(customerId);
		assertTrue(listOrders.size() > 0);
	}

	@Test
	public void testGetByIdAndCustomer() {
		Integer orderId = 8;
		Integer customerId = 24;

		BookOrder order = orderDAO.get(orderId, customerId);

		assertNotNull(order);
	}

	@Test
	public void testListMostRecentSales() {
		List<BookOrder> order = orderDAO.listMostRecentSales();

		assertNotNull(order.size() == 3);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}
}
