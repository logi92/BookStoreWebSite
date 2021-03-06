package com.bookstore.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	// ================================================================== LIST
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

	// ================================================================== CREATE
	public void createCustomer() throws IOException, ServletException {
		String email = request.getParameter("email");

		Customer existedCustomer = customerDAO.findByEmail(email);

		if (existedCustomer != null) {
			String message = "Could not create Customer. A Customer with email \"" + email + "\" already exists!";
			listCustomers(message);
		} else {
			Customer newCustomer = new Customer();
			readCustomerFields(newCustomer);

			customerDAO.create(newCustomer);

			String message = "A new Customer has been created successfully!";
			listCustomers(message);
		}
	}

	// ================================================================== UPDATE
	public void updateCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		int customerId = Integer.parseInt(request.getParameter("customerId"));

		Customer customerByEmail = customerDAO.findByEmail(email);
		Customer customerById = customerDAO.get(customerId);

		String message = null;
		if (customerByEmail != null && !customerByEmail.equals(customerById)) {
			message = "Could not update the Customer, because there's an existing customer having the same email";
		} else {
			readCustomerFields(customerById);

			customerDAO.update(customerById);

			message = "The Customer has been updated successfully!";
		}

		listCustomers(message);
	}

	// ================================================================== EDIT
	public void editCustomer() throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("id"));

		Customer customer = customerDAO.get(customerId);

		request.setAttribute("customer", customer);

		String editPage = "customer_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	// ================================================================== READ ALL
	// FIELDS
	public void readCustomerFields(Customer customer) {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipCode");
		String country = request.getParameter("country");
		
		if(email!=null && !email.equals("")) {
			customer.setEmail(email);
		}
		
		customer.setFullname(fullName);
		
		if(password!=null && !password.equals("")) {
			customer.setPassword(password);
		}
		
		customer.setPhoneNumber(phone);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setZipCode(zipCode);
		customer.setCountry(country);
	}

	// ================================================================== DELETE
	public void deleteCustomer() throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("id"));

		customerDAO.delete(customerId);

		String message = "The Customer has been delete successfully";
		listCustomers(message);
	}

	// ================================================================== REGISTER
	public void registerCustomer() throws IOException, ServletException {
		String email = request.getParameter("email");

		Customer existedCustomer = customerDAO.findByEmail(email);

		String message = null;

		if (existedCustomer != null) {
			message = "Could not register Customer. A Customer with email \"" + email + "\" already exists!";
		} else {
			Customer newCustomer = new Customer();
			readCustomerFields(newCustomer);

			customerDAO.create(newCustomer);

			message = "You have registered successfully!</br> <a href='login'> Click here</a> to login";
		}

		request.setAttribute("message", message);

		String messagePage = "frontend/message.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
	}

	// ================================================================== Show Login
	// Page

	public void showLogin() throws ServletException, IOException {
		String loginPath = "frontend/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPath);
		dispatcher.forward(request, response);
	}

	// ================================================================== DO LOGIN
	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Customer customer = customerDAO.checkLogin(email, password);

		String message = null;
		if (customer == null) {
			message = "Login Failed , please check your email and password";
			request.setAttribute("message", message);
			showLogin();
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loggedCustomer", customer);

			Object objRedirectURL = session.getAttribute("redirectURL");

			if(objRedirectURL!=null) {
				String redirectURL = (String) objRedirectURL;

				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			} else {
				showCustomerProfile();
			}
		}
	}
	
	// ================================================================== ShowCustmerProfile
	public void showCustomerProfile() throws ServletException, IOException {
		String profilePath = "frontend/customer_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(profilePath);
		dispatcher.forward(request, response);
	}

	// ================================================================== ShowCustomerProfileEditForm
	public void showCustomerProfileEditForm() throws ServletException, IOException {
		String editePath = "frontend/edit_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editePath);
		dispatcher.forward(request, response);
	}

	// ================================================================== UpdateCustomerProfile
	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");	
		
		readCustomerFields(customer);
		
		customerDAO.update(customer);
		
		showCustomerProfile();
	}

}
