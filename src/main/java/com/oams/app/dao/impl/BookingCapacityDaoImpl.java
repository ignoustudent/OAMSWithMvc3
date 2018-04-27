/**
 * 
 */
package com.oams.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.oams.app.dao.BookingCapacityDao;
import com.oams.app.entities.BookingCapacity;

/**
 * @author RAKESH SINGH
 *
 * Apr 17, 2018
 */
@Repository
@Transactional
public class BookingCapacityDaoImpl implements BookingCapacityDao{

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public BookingCapacity getCapacityByHosAndDeptId(Integer hospitalId,Integer deptId) {
		List<BookingCapacity> capacities = entityManager.createNamedQuery("getCapacityByHosAndDeptId",BookingCapacity.class).setParameter(1, hospitalId).setParameter(2, deptId).getResultList();
		entityManager.clear();
		if(capacities != null && capacities.size()>0){
			return capacities.get(0);
		}
		return null;
	}

	@Override
	public void save(BookingCapacity capacity) {
		
		Session session = (Session) entityManager.getDelegate();
		session.saveOrUpdate(capacity);
		session.close();
		//entityManager.persist(capacity);
		
	}

	
	@Override
	public List<BookingCapacity> getAllCapacity() {

		return entityManager.createQuery("from BookingCapacity order by id desc").getResultList();
	}
	
	
}
