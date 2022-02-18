package com.ems.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.app.bean.EmployeeBean;
import com.ems.app.exception.ResourceNotFoundException;
import com.ems.app.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = {"http:localhost:4200","*"})
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get all employees
	@GetMapping("/viewall")
	public List<EmployeeBean> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	//get employee by ID
	@GetMapping("/employee/{id}")
	public ResponseEntity<EmployeeBean> getEmployeeById(@PathVariable Long id) {
		EmployeeBean emp = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee does not exit wih given ID: "+id)); 
		
		return ResponseEntity.ok(emp);
	}
	
	//create a new employee
	@PostMapping("/create")
	public EmployeeBean createEmployee(@RequestBody EmployeeBean employee) {
		return employeeRepository.save(employee);
	}
	
	
	
	//update
	@PutMapping("/update/{id}")
	public ResponseEntity<EmployeeBean> updateEmployee(@PathVariable Long id, @RequestBody EmployeeBean employee){
		//find employee in db
		EmployeeBean emp = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee does not exit wih given ID: "+id)); 
		
		//insert new record here emp is old and employee is new
		emp.setFullname(employee.getFullname());
		emp.setEmail(employee.getEmail());
		
		EmployeeBean updatedEmployee = employeeRepository.save(emp);
		
		return ResponseEntity.ok(updatedEmployee);
	}
	
	//delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String , Boolean>> deleteEmployee(@PathVariable Long id){
		EmployeeBean emp = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee does not exit wih given ID: "+id)); 
		
		employeeRepository.delete(emp);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
