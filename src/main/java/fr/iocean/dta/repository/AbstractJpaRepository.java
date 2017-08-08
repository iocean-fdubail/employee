package fr.iocean.dta.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class AbstractJpaRepository {
	
	@PersistenceContext
	protected EntityManager em;
	

	protected Session getSession() {
		return em.unwrap(Session.class);
	}
	
	public void persist(Object entity) {
		em.persist(entity);
	}

	public Object update(Object entity) {
		return em.merge(entity);
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}

}
