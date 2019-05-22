package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest {

	private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		categoryDAO = new CategoryDAO(entityManager);
	}

	@Test
	public void testCreateCategory() {
		Category category = new Category("Health");
		Category testCat = categoryDAO.create(category);
		assertTrue(testCat != null && testCat.getCategoryId() > 0);
	}

	@Test
	public void testUpdateCategory() {
		Category cat = new Category("Java Core");
		cat.setCategoryId(1);

		Category category = categoryDAO.update(cat);

		assertEquals(cat.getName(), category.getName());
	}

	@Test
	public void testGet() {
		int id = 1;
		Category cat = categoryDAO.get(id);
		System.out.println(cat.getName());
		assertNotNull(cat);
	}

	@Test
	public void testDeleteCategory() {
		int id = 6;
		categoryDAO.delete(id);
		Category cat = categoryDAO.get(id);
		assertNull(cat);
	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDAO.listAll();
		listCategory.forEach(c -> System.out.println(c.getName()));
		assertTrue(listCategory.size() > 0);
	}

	@Test
	public void testCount() {
		long count = categoryDAO.count();
		assertEquals(4, count);
	}

	@Test
	public void testFindByName() {
		String name = "Python";
		Category category = categoryDAO.findByName(name);
		assertNotNull(category);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}
}
