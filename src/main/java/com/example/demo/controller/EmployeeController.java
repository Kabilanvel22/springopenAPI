package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/Employees")
public class EmployeeController {

	
	@Autowired
	EmployeeService EmployeeService;
	
	@Operation(summary = "Create a new Employee")
	@ApiResponses(value =  {@ApiResponse(responseCode = "201",description = "Employee created successfully"),
    @ApiResponse(responseCode = "400" , description = "Employee is invalid")})
	@ResponseStatus(HttpStatus.CREATED)
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Employee> create(final @RequestBody Employee Employee){
		Employee createEmployee = EmployeeService.create(Employee);
		return ResponseEntity.ok(createEmployee);
}
	@Operation (summary = "Get an Employee with given id")
	@ApiResponses (value = {@ApiResponse(responseCode = "200",
	description = "getting Employee successfully"),
	@ApiResponse(responseCode ="401", description = "invalid credentials"),
	@ApiResponse(responseCode = "404", description = "Employee not found")})
	@GetMapping(value ="/{id}" , produces = "application/json") 
	public ResponseEntity<Optional<Employee>> read(final @PathVariable Long id) {
	   Optional<Employee> createdEmployee = EmployeeService.read(id);
	return ResponseEntity.ok(createdEmployee);
}
	@Operation (summary = "Deletes the Employee by given id")
	@ApiResponses (value = {@ApiResponse (responseCode = "200",
	description = "Employee deleted successfully"),
	@ApiResponse(responseCode = "401", description = "invalid credentials"),
	@ApiResponse (responseCode = "404",description = "Employee not found")})
	@DeleteMapping("/{id}")
	public void delete(final @PathVariable Long id) {
	EmployeeService.delete(id);
}
	@Operation(summary="Updates the Employee by given id")
	@ApiResponses(value= {@ApiResponse(responseCode="200",description="Employee updated succesfully"),
			@ApiResponse(responseCode="400",description="Employee is invalid"),
			@ApiResponse(responseCode="401",description="invalid credentials"),
			@ApiResponse(responseCode="404",description="Employee not found")})
	@PutMapping(value="/{id}",produces ="application/json",consumes="application/json")
	public ResponseEntity<Employee> update(final @RequestBody Employee Employee)
	
		throws JsonProcessingException{
			final Employee updatedEmployee=
					EmployeeService.update(Employee);
			return ResponseEntity.ok(updatedEmployee);
	}
	
}