import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.BaseDAOTest;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserDAOTest extends BaseDAOTest {
	private static UserDAO userDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		userDAO = new UserDAO(entityManager);
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("anton@mail.ru");
		user1.setFullName("Anton Klad");
		user1.setPassword("elf");

		user1 = userDAO.create(user1);

		assertTrue(user1.getUserId() > 0);
	}

	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("r@mail.ru");
		user.setFullName("Mahabna Dahabna");
		user.setPassword("1234");

		user = userDAO.update(user);

		String expected = "1234";
		String actual = user.getPassword();

		assertEquals(expected, actual);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		user1 = userDAO.create(user1);
	}

	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		Users user = userDAO.get(userId);

		assertNotNull(user);
	}

	@Test
	public void testGetUsersNotFound() {
		Integer userId = 100;
		Users user = userDAO.get(userId);

		assertNull(user);
	}

	@Test
	public void testDeleteUsers() {
		Integer id = 8;
		userDAO.delete(id);

		Users user = userDAO.get(id);

		assertNull(user);
	}

	@Test(expected = Exception.class)
	public void testDeleteNonExistUsers() {
		Integer id = 55;
		userDAO.delete(id);
	}

	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		for (Users user : listUsers) {
			System.out.println(user.getFullName());
		}
		assertTrue(listUsers.size() > 0);
	}

	@Test
	public void testCount() {
		Long count = userDAO.count();
		System.out.println(count);
		assertTrue(count > 0);
	}

	@Test
	public void testFindByEmail() {
		String email = "r_nazar@mail.ru";
		Users user = userDAO.findByEmail(email);
		assertNotNull(user);
	}

	@Test
	public void testCheckLoginSuccess() {
		String email = "barni@mail.ru";
		String password = "suitUp";

		Boolean actual = userDAO.checkLogin(email, password);

		assertTrue(actual);
	}

	@Test
	public void testCheckLoginFail() {
		String email = "bari@mail.ru";
		String password = "suitUp";

		Boolean actual = userDAO.checkLogin(email, password);

		assertFalse(actual);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

}
