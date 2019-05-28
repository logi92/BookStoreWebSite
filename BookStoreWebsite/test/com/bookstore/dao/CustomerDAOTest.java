package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDAOTest {
	private static CustomerDAO customerDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDAO = new CustomerDAO();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();

		customer.setEmail("billy.jane@test.ru");
		customer.setFullname("Jane Billy");
		customer.setAddress("Baker Street");
		customer.setCity("London");
		customer.setCountry("England");
		customer.setPhoneNumber("8-900-3000-500");
		customer.setZipCode("101021");
		customer.setPassword("sh");

		Customer savedCustomer = customerDAO.create(customer);

		assertTrue(savedCustomer.getCustomerId() > 0);
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = customerDAO.get(2);
		customer.setEmail("tom@mail.ru");
		customer.setFullname("Tommyr");
		customer.setAddress("100 North Av.");
		customer.setCity("New York");
		customer.setCountry("USA");
		customer.setPhoneNumber("8-951-910-57-57");
		customer.setZipCode("607010");
		customer.setPassword("123");
		Customer updatedCustomer = customerDAO.update(customer);

		assertTrue(updatedCustomer.getFullname().equals("Tom Eager"));
	}

	@Test
	public void testGet() {
		int customerId = 1;

		Customer customer = customerDAO.get(customerId);

		assertNotNull(customer);

	}

	@Test
	public void testDeleteObject() {
		int customerId = 1;

		customerDAO.delete(customerId);

		Customer customer = customerDAO.get(customerId);

		assertNull(customer);
	}

	@Test
	public void testListAll() {
		List<Customer> listCustomers = customerDAO.listAll();

		for (Customer item : listCustomers) {
			System.out.println(item.getFullname());
		}

		assertFalse(listCustomers.isEmpty());
	}
	
	@Test
	public void testCountAll() {
		long totalCustomers = customerDAO.count();
		assertEquals(2, totalCustomers);
	}
	
	@Test
	public void TestByEmailSuccessfull() {
		String email ="tom@mail.ru";
		Customer customer = customerDAO.findByEmail(email);
		
		System.out.println(customer.getFullname());
		
		assertNotNull(customer);
	}
	
	@Test
	public void TestByEmailFail() {
		String email ="tm@mail.ru";
		Customer customer = customerDAO.findByEmail(email);
		
		assertNull(customer);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDAO.close();
	}

}
