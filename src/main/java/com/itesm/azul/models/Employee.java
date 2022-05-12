package com.itesm.azul.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class Employee {
    private String id;
    private String name;
    private Integer role;
    private String email;
    private String password;
    private Integer employee_num;
    private Integer num_calls;
    private Integer suc_tickets;
    private Integer unsuc_tickets;
    private Integer supervisor;
    private String call_reason;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId(){
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("name")
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    //@DynamoDbAttribute("role")
    public Integer getRole() {
        return role;
    }
    public void setRole(Integer role) {
        this.role = role;
    }

    //@DynamoDbAttribute("nombre")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //@DynamoDbAttribute("nombre")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //@DynamoDbAttribute("nombre")
    public Integer getEmployee_num() {
        return employee_num;
    }
    public void setEmployee_num(Integer employee_num) {
        this.employee_num = employee_num;
    }

    //@DynamoDbAttribute("nombre")
    public Integer getNum_calls() {
        return num_calls;
    }
    public void setNum_calls(Integer num_calls) {
        this.num_calls = num_calls;
    }

    //@DynamoDbAttribute("nombre")
    public Integer getSuc_tickets() {
        return suc_tickets;
    }
    public void setSuc_tickets(Integer suc_tickets) {
        this.suc_tickets = suc_tickets;
    }

    //@DynamoDbAttribute("nombre")
    public Integer getUnsuc_tickets() {
        return unsuc_tickets;
    }
    public void setUnsuc_tickets(Integer unsuc_tickets) {
        this.unsuc_tickets = unsuc_tickets;
    }

    //@DynamoDbAttribute("nombre")
    public Integer getSupervisor() {
        return supervisor;
    }
    public void setSupervisor(Integer supervisor) {
        this.supervisor = supervisor;
    }

    //@DynamoDbAttribute("nombre")
    public String getCall_reason() { return call_reason; }
    public void setCall_reason(String call_reason) { this.call_reason = call_reason; }
}
