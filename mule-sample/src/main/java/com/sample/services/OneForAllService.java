package com.sample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.entity.Department;

public class OneForAllService {
	
	@Autowired
	private DepartmentServiceImpl departmentService;
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	public void doProcess() {
		Integer deptId = departmentService.createDepartment("HR");
		employeeService.createEmployee("Shael", "Raj", 14562, deptId);
		List<Department> list = departmentService.getAllDept();
		employeeService.printEmployee(list);
	}
	

}
