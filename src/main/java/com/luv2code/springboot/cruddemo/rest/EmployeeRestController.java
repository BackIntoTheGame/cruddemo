package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
    // quick and dirty: inject employee dao
    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){

        Employee employee =  employeeService.findById(employeeId);

        if(employee == null)
            throw new RuntimeException("Employee Id not found :" + employeeId);

        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){

        // also just in case they pass an Id in Json...
        // this is to force a save of new item ..  instead of update
        employee.setId(0);

        employeeService.save(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.save(employee);

        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        if(employeeService.findById(employeeId) == null)
            throw new RuntimeException("Not found Id = " +employeeId);

        employeeService.delete(employeeId);

        return "employee deleted with id : "+employeeId;
    }

}
