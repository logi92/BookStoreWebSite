package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO(EntityManager entityManager) {
		super(entityManager);
	}

	public Users create(Users user) {
		return super.create(user);
	}

	@Override
	public Users update(Users user) {
		return super.update(user);
	}

	@Override
	public Users get(Object userId) {
		return super.find(Users.class, userId);
	}

	@Override
	public void delete(Object userId) {
		super.delete(Users.class, userId);

	}

	public Users findByEmail(String email) {
		List<Users> listUsers = super.findWithNamedQuery("Users.findByEmail", "email", email);
		if (listUsers != null && listUsers.size() > 0) {
			return listUsers.get(0);
		}
		return null;
	}

	public boolean checkLogin(String email, String password) {
		Map<String, Object> parameters = new HashMap<>(); //Эта мапа передастся в метод findWithNamedQuery
		parameters.put("email", email);
		parameters.put("password", password); //Суем в нее ключ - имя параметра, значение - значение параметра

		List<Users> listUsers = super.findWithNamedQuery("Users.checkLogin", parameters); //Создаем список юзеров и присваиваем результат метода
		
		if (listUsers.size() == 1) {// Если данные ввели верно, то мы должны будем получить только одного юзера
			return true;
		}
		
		return false;
	}

	@Override
	public List<Users> listAll() {
		return super.findWithNamedQuery("Users.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Users.countAll");
	}

}
