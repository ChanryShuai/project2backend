package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Battle;
import com.revature.models.BattleDTO;
import com.revature.models.User;

@Repository
@Transactional
public class BattleDAO implements IBattleDAO {
	
	@Autowired
	private SessionFactory sf;
	
	@Autowired
	private IUserDAO uDao;
	
	@Override
	public String findOutcome(int battleId) {
		
		Session s = sf.getCurrentSession();
		
			BattleDTO b = s.get(BattleDTO.class, battleId);
			if(b != null) {
			
			String outcomes = b.getOutcomes();
			return outcomes;
			}
			
			return null;
		}
		
	
	@Override
	public boolean updateBattle(List<Battle> b) {
		Session s = sf.getCurrentSession();
		s.update(b);
		 
		return true;
	}


	@Override
	public List<Battle> findAllBattles() {
		Session s = sf.getCurrentSession();
		
		CriteriaQuery<Battle> cq = s.getCriteriaBuilder().createQuery(Battle.class);
		cq.from(Battle.class); 
		return s.createQuery(cq).getResultList();
	}

	@Override
	public List<Battle> findBattlesByUser(String username) { 	
		
		Session s = sf.getCurrentSession();
		User u = uDao.findByUsername(username);
		if (u == null) {
			return new ArrayList<Battle>();
		} else {
			List<Battle> bList = s.createQuery("From battle WHERE user_id=" + u.getUserId()).list();	
			return bList;
		}
	}
	
	@Override
	public Battle addBattle(Battle b) {
		
		Session s = sf.getCurrentSession(); 
		s.saveOrUpdate(b);
		return b;
	}
	
	@Override
	public List<Battle> getBattleById(int id) {
		Session s = sf.getCurrentSession();
		
		List<Battle> bList = new ArrayList<Battle>();
		Battle b = s.get(Battle.class, id);
		bList.add(b);
		return bList;
	}	
	
	
}