package com.sample;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sample.entity.Department;
import com.sample.services.DepartmentServiceImpl;

public class SampleClass {

	private static Logger LOG = LoggerFactory.getLogger(SampleClass.class);
	
	@Autowired
	private DepartmentServiceImpl service;

	public void printValue() {
		LOG.info("@@@@@@@@@@@@@@@@@@@@@@@");
	}

	public void printValue2() {
		LOG.info("############");
	}

	/*
	 * public int add(int a, int b) { if(a==5) { throw new
	 * RuntimeException("Choose other number"); } return a+b; }
	 */

	public SystemEvent add(int a, int b) {
		List<Department> d = service.getAllDept();
		SystemEvent se = new SystemEvent();
		List<Long> ids = new ArrayList<>();
		ids.add(1l);
		ids.add(2l);
		ids.add(3l);
		se.setIds(ids);
		se.setEvent("EUR");
		se.setMessage("EURO");
		return se;
	}

}
