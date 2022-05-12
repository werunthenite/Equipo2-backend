package com.itesm.azul.repositories;


import com.itesm.azul.models.Persona;
import com.itesm.azul.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public UserRepository(){
        super();
    }

    private DynamoDbTable<User> getTable() {
        DynamoDbTable<User> userTable = dynamoDbEnhancedClient.table("User", TableSchema.fromBean(User.class));
        return userTable;
    }

    public User findByUsername(String username){
        DynamoDbTable<User> userTable = getTable();
        Key key = Key.builder().partitionValue(username).build();
        User result = userTable.getItem(key);

        return result;
    }

    /*public Persona findById(final String personaID, final String timestamp) {
        DynamoDbTable<Persona> personTable = getTable();
        Key key = Key.builder().partitionValue(personaID).sortValue(timestamp).build();

        Persona result = personTable.getItem(key);
        return result;
    }*/
}
