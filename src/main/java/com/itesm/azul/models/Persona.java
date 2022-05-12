package com.itesm.azul.models;


import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class Persona {
    private String personaID;
    private String timestamp;
    private String nombre;
    private String edad;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("personaID")
    public String getPersonaID(){
        return personaID;
    }
    public void setPersonaID(String personaID) {
        this.personaID = personaID;
    }
    @DynamoDbSortKey
    @DynamoDbAttribute("timestamp")
    public String getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(String t){
        this.timestamp = t;
    }

    //@DynamoDbAttribute("nombre")
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //@DynamoDbAttribute("edad")
    public String getEdad() {
        return edad;
    }
    public void setEdad(String edad) {
        this.edad = edad;
    }
}
