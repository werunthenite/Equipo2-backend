package com.itesm.azul.repositories;


import com.itesm.azul.models.Persona;
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
public class PersonaRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public PersonaRepository(){
        super();
    }

    public void save(final Persona persona) {
        DynamoDbTable<Persona> personaTable = getTable();
        personaTable.putItem(persona);
    }

    /**
     * @return
     */
    private DynamoDbTable<Persona> getTable() {
        DynamoDbTable<Persona> personaTable = dynamoDbEnhancedClient.table("Persona", TableSchema.fromBean(Persona.class));
        return personaTable;
    }
    public Iterable<Persona> findAll() {
        DynamoDbTable<Persona> personaTable = getTable();
        return personaTable.scan().items();
    }

    public Iterable<Persona> findPerson(final String personaID) {
        DynamoDbTable<Persona> personTable = getTable();


        // Create a QueryConditional object that is used in the query operation
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder().partitionValue(personaID)
                        .build());

        Iterable<Persona> result = personTable.query(r -> r.queryConditional(queryConditional)).items();
        return result;
    }


    public Persona findById(final String personaID, final String timestamp) {
        DynamoDbTable<Persona> personTable = getTable();
        Key key = Key.builder().partitionValue(personaID).sortValue(timestamp).build();

        Persona result = personTable.getItem(key);
        return result;
    }
    public Persona update(final Persona persona){
        DynamoDbTable<Persona> personTable = getTable();
        Key key = Key.builder().partitionValue(persona.getPersonaID()).sortValue(persona.getTimestamp()).build();
        Persona registroPersona = personTable.getItem(r->r.key(key));
        registroPersona.setNombre(persona.getNombre());
        registroPersona.setEdad(persona.getEdad());
        personTable.updateItem(registroPersona);
        return persona;

    }
    public void deleteById(final String personID, final String timestamp) {
        DynamoDbTable<Persona> personTable = getTable();

        Key key = Key.builder().partitionValue(personID).sortValue(timestamp).build();

        DeleteItemEnhancedRequest deleteRequest = DeleteItemEnhancedRequest
                .builder()
                .key(key)
                .build();

        personTable.deleteItem(deleteRequest);
    }
}
/*
    public List<Persona> findAll(){
        Persona p1= new Persona();
        p1.setNombre("Andres");
        p1.setEdad(32);
        Persona p2= new Persona();
        p2.setNombre("Paola");
        p2.setEdad(21);
        List<Persona> personas= new ArrayList<>();
        personas.add(p1);
        personas.add(p2);
        return personas;
    }

}

 */
