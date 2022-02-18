package com.ems.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.EmployeeBean;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeBean,Long> {
	
}
