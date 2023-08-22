package com.sample.entity;

import java.util.HashSet;
import java.util.Set;

public class Department {

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", emps=" + emps + "]";
	}

	private Integer id;

	private String name;

//	@Fetch(FetchMode.SUBSELECT)
	private Set<Employee> emps = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmps() {
		return emps;
	}

	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}

}
