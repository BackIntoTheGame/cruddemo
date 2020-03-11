package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOJapImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJapImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> findAll() {

        // create a query
        Query query = entityManager.createQuery("from Employee");

        // get result list

        // return the result
        return (List<Employee>) query.getResultList();
    }

    @Override
    public Employee findById(int theId) {

        // get employee
        return entityManager.find(Employee.class, theId);
    }

    @Override
    public void save(Employee theEmployee) {
        // save or update the employee
        Employee dbEmployee = entityManager.merge(theEmployee);

        theEmployee.setId(dbEmployee.getId());
    }

    @Override
    public void deleteById(int theId) {

        // delete object with primary key
        Query query = entityManager.createQuery("delete from Employee where id =:employeeId");

        query.setParameter("employeeId", theId);

        query.executeUpdate();
    }
}
