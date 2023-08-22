package com.sample.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.sample.entity.Department;

public class DepartmentDaoImpl extends HibernateDaoSupport {

	public Integer createDeaprtment(Department d) {
		Session session= getSessionFactory().getCurrentSession();
		session.save(d);
		return d.getId();
	}

	public List<Department> getAllDept() {
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Department d");
		
		List<Department> list = query.getResultList();
//		session.close();
		if(list==null || list.size() == 0) {
			return new ArrayList<Department>();
		}
		return list;
	}

}
