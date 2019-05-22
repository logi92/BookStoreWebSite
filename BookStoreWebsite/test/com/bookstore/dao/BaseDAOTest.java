package com.bookstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDAOTest {
	protected static EntityManagerFactory entityManagerFactory;
	protected static EntityManager entityManager;

	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebSite");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public static void tearDownAfterClass() throws Exception {
		entityManagerFactory.close();
		entityManager.close();
	}
}
