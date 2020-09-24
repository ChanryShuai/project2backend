package com.revature.repositories;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.User;

@Repository
@Transactional
public class UserDAO implements IUserDAO {
	

	@Autowired
	private SessionFactory sf;
	

	@Override
	public User findByUsername(String username) {
		Session s = sf.getCurrentSession();
		
		return s.createQuery("FROM User WHERE username = '" + username + "'", User.class).uniqueResult();
	}

	@Override
	public User insert(User u) {
		Session s = sf.getCurrentSession(); 
		
		s.saveOrUpdate(u);
		return u;
	}

	@Override
	public User updateUser(User u) {

		Session s = sf.getCurrentSession();

		s.update(u);
		return u;
	}

	@Override
	public User selectByUserId(int userId) {
		Session s = sf.getCurrentSession();

		return s.get(User.class, userId);
	}

	@Override
	public List<User> findAllUsers() {
		
		Session s = sf.getCurrentSession();
		
		CriteriaQuery<User> cq = s.getCriteriaBuilder().createQuery(User.class);
		cq.from(User.class);
		return s.createQuery(cq).getResultList();
	}
	
	

}
