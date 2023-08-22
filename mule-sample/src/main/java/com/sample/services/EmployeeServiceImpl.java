package com.sample.services;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.sample.dao.EmployeeDaoImpl;
import com.sample.entity.Department;
import com.sample.entity.Employee;

@Component
@Transactional
public class EmployeeServiceImpl {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeDaoImpl empDao;
	
	public Integer createEmployee(String firstName, String lastName, int salary, Integer deptId) {
		Random r = new Random();
		int id = r.nextInt(1000);
		Employee emp = new Employee(id, firstName, lastName, salary);
		return empDao.createEmployee(emp, deptId);
		
	}
	
	public void printEmployee(Department dept) {
		try {
			log.info("Transaction active {}",  TransactionSynchronizationManager.isActualTransactionActive());
			log.info("printEmployee {}", Thread.currentThread().getName());
		if(dept !=null) {
			dept.getEmps().forEach(e ->log.info(e.toString()));
		}
		}catch(Exception e) {
			log.error("Error occured {}", e);
			throw e;
		}
	}
	
	public List<Department> printEmployee(List<Department> list) {
		log.info("printEmployee {}", Thread.currentThread().getName());
		if(list != null) {
			for(Department d : list) {
				printEmployee(d);
			}
		}
		return list;
	}

}
