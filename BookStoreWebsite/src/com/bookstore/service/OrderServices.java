package com.bookstore.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderServices {
	private OrderDAO orderDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.orderDAO = new OrderDAO();
	}

	public void listOrders() throws ServletException, IOException {
		listOrders(null);
	}

	public void listOrders(String message) throws ServletException, IOException {
		List<BookOrder> listAllOrder = orderDAO.listAll();

		if (message != null) {
			request.setAttribute("message", message);
		}

		request.setAttribute("listAllOrder", listAllOrder);

		String listPage = "order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));

		BookOrder order = orderDAO.get(orderId);

		request.setAttribute("order", order);

		String detailPage = "order_detail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(detailPage);
		dispatcher.forward(request, response);
	}

	public void showCkeckoutForm() throws ServletException, IOException {
		String checkOutPage = "frontend/checkout.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(checkOutPage);
		dispatcher.forward(request, response);
	}

	public void placeOrder() throws ServletException, IOException {
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipCode");
		String country = request.getParameter("country");
		String paymentMethod = request.getParameter("paymentMethod");

		String shippingAddress = address + ", " + city + ", " + zipCode + ", " + country;

		BookOrder order = new BookOrder();
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(paymentMethod);

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");

		order.setCustomer(customer);

		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
		Map<Book, Integer> items = shoppingCart.getItems();

		Iterator<Book> iterator = items.keySet().iterator();

		Set<OrderDetail> orderDetails = new HashSet<>();

		while (iterator.hasNext()) {
			Book book = iterator.next();
			Integer quantity = items.get(book);
			float subTotal = quantity * book.getPrice();

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(book);
			orderDetail.setBookOrder(order);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubTotal(subTotal);

			orderDetails.add(orderDetail);
		}

		order.setOrderDetails(orderDetails);
		order.setOrderTotal(shoppingCart.getTotalAmount());

		orderDAO.create(order);

		shoppingCart.clear();

		String message = "Thank You! Your Order has been received." + "We will deliver your books withid a few days.";

		request.setAttribute("message", message);

		String messagePage = "frontend/message.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
	}

	public void listOrderByCustomer() throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");

		List<BookOrder> listOrders = orderDAO.listByCustomer(customer.getCustomerId());

		request.setAttribute("listOrders", listOrders);

		String historyPage = "frontend/order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(historyPage);
		dispatcher.forward(request, response);
	}

	public void showOrderDetailByCustomer() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");

		BookOrder order = orderDAO.get(orderId, customer.getCustomerId());

		request.setAttribute("order", order);

		String detailPage = "frontend/order_detail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(detailPage);
		dispatcher.forward(request, response);
	}

	public void showEditOrderForm() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession();
		Object isPendingBook = session.getAttribute("NewBookPendingToAddToorder");

		if (isPendingBook == null) {
			BookOrder order = orderDAO.get(orderId);
			session.setAttribute("order", order);
		} else {
			session.removeAttribute("NewBookPendingToAddToorder");
		}

		String editPage = "order_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	public void updateOrder() throws ServletException, IOException {
		HttpSession session = request.getSession();
		BookOrder order = (BookOrder) session.getAttribute("order");

		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String shippingAddress = request.getParameter("shippingAddress");
		String paymentMethod = request.getParameter("paymentMethod");
		String orderStatus = request.getParameter("orderStatus");

		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(paymentMethod);
		order.setOrderStatus(orderStatus);

		String[] arrayBookId = request.getParameterValues("bookId");
		String[] arrayPrice = request.getParameterValues("price");
		String[] arrayQuantity = new String[arrayBookId.length];

		for (int i = 1; i <= arrayQuantity.length; i++) {
			arrayQuantity[i - 1] = request.getParameter("quantity" + i);
		}

		Set<OrderDetail> orderDetails = order.getOrderDetails();
		orderDetails.clear();

		float totalAmount = 0.0f;

		for (int i = 0; i < arrayBookId.length; i++) {
			int bookId = Integer.parseInt(arrayBookId[i]);
			int quantity = Integer.parseInt(arrayQuantity[i]);
			float price = Float.parseFloat(arrayPrice[i]);

			float subTotal = price * quantity;

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(new Book(bookId));
			orderDetail.setQuantity(quantity);
			orderDetail.setSubTotal(subTotal);
			orderDetail.setBookOrder(order);

			orderDetails.add(orderDetail);

			totalAmount += subTotal;
		}

		order.setOrderTotal(totalAmount);

		orderDAO.update(order);

		String message = "The Order " + order.getOrderId() + " has been updated successfuly";

		listOrders(message);
	}

	public void deleteOrder() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		orderDAO.delete(orderId);

		String message = "Order has been deleted successfully";

		listOrders(message);

	}

}
