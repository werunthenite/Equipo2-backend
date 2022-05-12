package com.itesm.azul.services;

import com.itesm.azul.models.Employee;
import com.itesm.azul.dto.EmployeeDTO;
import com.itesm.azul.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    //CREATE
    public void createEmployee(final EmployeeDTO employee){
        Employee e = new Employee();
        e.setId(employee.getId());
        e.setName(employee.getName());
        e.setRole(employee.getRole());
        e.setEmail(employee.getEmail());
        e.setPassword(employee.getPassword());
        e.setEmployee_num(employee.getEmployee_num());
        e.setNum_calls(employee.getNum_calls());
        e.setSuc_tickets(employee.getSuc_tickets());
        e.setUnsuc_tickets(employee.getUnsuc_tickets());
        e.setSupervisor(employee.getSupervisor());
        e.setCall_reason(employee.getCall_reason());
        employeeRepository.save(e);
    }

    //READ
    public Iterable<Employee> lista(){
        return employeeRepository.findAll();
    }
    public Iterable<Employee> get(String id){
        return employeeRepository.findEmployee(id);
    }
    public Employee getOne(String id, String name){
        return employeeRepository.findById(id, name);
    }

    //DELETE
    public void delete(String id, String name){
        employeeRepository.deleteById(id, name);
    }

    //UPDATE
    public Employee update(EmployeeDTO dto){
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setRole(dto.getRole());
        employee.setEmail(dto.getEmail());
        employee.setPassword(dto.getPassword());
        employee.setEmployee_num(dto.getEmployee_num());
        employee.setNum_calls(dto.getNum_calls());
        employee.setSuc_tickets(dto.getSuc_tickets());
        employee.setUnsuc_tickets(dto.getSuc_tickets());
        employee.setSupervisor(dto.getSupervisor());
        employee.setCall_reason(dto.getCall_reason());

        return employeeRepository.update(employee);
    }
}
