package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Customer;

public class CustomerServices {
	private CustomerDAO customerDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.customerDAO = new CustomerDAO();
	}

	public void listCustomers() throws ServletException, IOException {
		listCustomers(null);
	}

	public void listCustomers(String message) throws ServletException, IOException {
		List<Customer> listCustomer = customerDAO.listAll();

		if (message != null) {
			request.setAttribute("message", message);
		}

		request.setAttribute("listCustomer", listCustomer);

		String listPage = "customer_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void createCustomer() throws IOException, ServletException {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipCode");
		String country = request.getParameter("country");

		Customer existedCustomer = customerDAO.findByEmail(email);

		if (existedCustomer != null) {
			String message = "Could not create Customer. A Customer with email \"" + email + "\" already exists!";
			listCustomers(message);
		} else {
			Customer newCustomer = new Customer();

			newCustomer.setEmail(email);
			newCustomer.setFullname(fullName);
			newCustomer.setPassword(password);
			newCustomer.setPhoneNumber(phone);
			newCustomer.setAddress(address);
			newCustomer.setCity(city);
			newCustomer.setZipCode(zipCode);
			newCustomer.setCountry(country);

			customerDAO.create(newCustomer);

			String message = "A new Customer has been created successfully!";
			listCustomers(message);
		}
	}

}
