package com.psy.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.psy.dto.BoardVO;

public class BoardProviderImpl implements BoardProvider {

	@Override
	public void insertBoard(BoardVO vo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(vo);
		
		tx.commit();
		session.close();
		
	}

}
