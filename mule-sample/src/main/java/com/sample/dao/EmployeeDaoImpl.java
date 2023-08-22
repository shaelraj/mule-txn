package com.sample.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.sample.entity.Department;
import com.sample.entity.Employee;

public class EmployeeDaoImpl extends HibernateDaoSupport {

	public Integer createEmployee(Employee emp, Integer deptId) {
		Session session = getSessionFactory().getCurrentSession();
		Department dept = session.get(Department.class, deptId);
		Set<Employee> emps = new HashSet<>();
		dept.setEmps(emps);
		emp.setDepartment(dept);
		session.save(emp);
		session.save(dept);
		return emp.getId();
	}

}
