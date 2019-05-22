package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaDAO<E> {
	protected EntityManager entityManager;

	public JpaDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public E create(E entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	public E update(E entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	public E find(Class<E> type, Object id) {
		E entity = entityManager.find(type, id);
		if (entity != null) {// ====================>Проверка что бы не выдавал исключение
			entityManager.refresh(entity);
		}
		return entity;
	}

	public void delete(Class<E> type, Object id) {
		entityManager.getTransaction().begin();

		Object reference = entityManager.getReference(type, id);
		entityManager.remove(reference);

		entityManager.getTransaction().commit();
	}

	public List<E> findWithNamedQuery(String queryName) {
		Query query = entityManager.createNamedQuery(queryName);
		return query.getResultList();
	}

	public List<E> findWithNamedQuery(String queryName, String paramName, Object paramValue) {
		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		return query.getResultList();
	}

	public List<E> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		Query query = entityManager.createNamedQuery(queryName); //Создаем объект query

		Set<Entry<String, Object>> setParameters = parameters.entrySet(); //Перекидываем все значения Map в Set, с помощью метода entrySet(); Для метода For each
		for (Entry<String, Object> entry : setParameters) {//Передаем каждый параметр ключ - имя параметра, значение - значение параметра в query.
			query.setParameter(entry.getKey(), entry.getValue());  // Первый будет email, а на втором круге это будет password
		}

		return query.getResultList(); //Возвращаем список-результат
	}

	public long countWithNamedQuery(String queryName) {
		Query query = entityManager.createNamedQuery(queryName);
		return (long) query.getSingleResult();
	}
}
