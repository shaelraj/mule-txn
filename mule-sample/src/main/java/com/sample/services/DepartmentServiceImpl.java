package com.sample.services;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sample.dao.DepartmentDaoImpl;
import com.sample.entity.Department;

public class DepartmentServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	@Autowired
	private DepartmentDaoImpl deptDao;
	
	

	public Integer createDepartment(String name) {
		try {
			log.info("createDepartment {}", Thread.currentThread().getName());
			Department d = new Department();
			Random r = new Random();
			int id = r.nextInt(1000);
			d.setId(id);
			d.setName(name);
			return deptDao.createDeaprtment(d);
		} catch (Exception e) {
			log.info("error {}", e);
			throw e;
		}
	}

	public List<Department> getAllDept() {
		log.info("getAllDept {}", Thread.currentThread().getName());
		return deptDao.getAllDept();
	}

}
