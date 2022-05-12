package com.itesm.azul.controllers;

import com.itesm.azul.models.Employee;
import com.itesm.azul.dto.EmployeeDTO;
import com.itesm.azul.repositories.EmployeeRepository;
import com.itesm.azul.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.List;


@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    //https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/java_dynamodb_code_examples.html

    //Create
    //insert a tuple
    @PostMapping("/save")
    public EmployeeDTO save(@RequestBody EmployeeDTO e) throws Exception{
        employeeService.createEmployee(e);
        return e;
    }
    //select all tuples
    @GetMapping("/all")
    public ResponseEntity<Iterable<Employee>> getAll(){
        return ResponseEntity.ok(employeeService.lista());
    }

    //Read
    //select 1 tuple
    @GetMapping("/get/{id}")
    public ResponseEntity<Iterable<Employee>> getOne(@PathVariable("id") String id){
        return ResponseEntity.ok(employeeService.get(id));
    }

    @GetMapping("/getone/{id}/{name}")
    public ResponseEntity<Employee> getOne(@PathVariable("id") String id, @PathVariable("name") String name){
        return ResponseEntity.ok(employeeService.getOne(id, name));
    }

    //Delete a tuple
    @DeleteMapping("/delete/{id}/{name}")
    public ResponseEntity<?> delete(@PathVariable("id") String id, @PathVariable("name") String name){
        employeeService.delete(id, name);
        return new ResponseEntity("employee removed", HttpStatus.OK);
    }

    //Update a tuple
    @PutMapping("/update")
    public ResponseEntity<Employee> update(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.update(employeeDTO));
    }
}
