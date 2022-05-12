package com.itesm.azul.repositories;


import com.itesm.azul.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;


@Repository
public class EmployeeRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public EmployeeRepository(){
        super();
    }

    public void save(final Employee employee) {
        DynamoDbTable<Employee> employeeTable = getTable();
        employeeTable.putItem(employee);
    }

    /**
     * @return
     */
    private DynamoDbTable<Employee> getTable() {
        DynamoDbTable<Employee> employeeTable = dynamoDbEnhancedClient.table("employee", TableSchema.fromBean(Employee.class));
        return employeeTable;
    }
    public Iterable<Employee> findAll() {
        DynamoDbTable<Employee> employeeTable = getTable();
        return employeeTable.scan().items();
    }

    public Iterable<Employee> findEmployee(final String id) {
        DynamoDbTable<Employee> employeeTable = getTable();


        // Create a QueryConditional object that is used in the query operation
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder().partitionValue(id)
                        .build());

        Iterable<Employee> result = employeeTable.query(r -> r.queryConditional(queryConditional)).items();
        return result;
    }

    public Employee findById(final String id, final String name) {
        DynamoDbTable<Employee> employeeTable = getTable();
        Key key = Key.builder().partitionValue(id).sortValue(name).build();

        Employee result = employeeTable.getItem(key);
        return result;
    }
    public Employee update(final Employee employee){
        DynamoDbTable<Employee> employeeTable = getTable();
        Key key = Key.builder().partitionValue(employee.getId()).sortValue(employee.getName()).build();
        Employee registroEmployee = employeeTable.getItem(r->r.key(key));
        registroEmployee.setName(employee.getName());
        registroEmployee.setRole(employee.getRole());
        employeeTable.updateItem(registroEmployee);
        return employee;

    }
    public void deleteById(final String id, final String name) {
        DynamoDbTable<Employee> employeeTable = getTable();

        Key key = Key.builder().partitionValue(id).sortValue(name).build();

        DeleteItemEnhancedRequest deleteRequest = DeleteItemEnhancedRequest
                .builder()
                .key(key)
                .build();

        employeeTable.deleteItem(deleteRequest);
    }
}
