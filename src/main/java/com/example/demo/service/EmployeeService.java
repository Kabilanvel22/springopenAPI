package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository EmployeeRepository;
	
	public Employee create(Employee Employee) 
	{
		return EmployeeRepository.save(Employee);
		
	}
	public Optional<Employee> read(Long id)
	{
		return EmployeeRepository.findById(id);
	}
	public void delete(Long id) {
		EmployeeRepository.deleteById(id);
	}
	public Employee update(Employee Employee) {
	
		return EmployeeRepository.save(Employee);
		
	}
}