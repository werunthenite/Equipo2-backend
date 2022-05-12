package com.itesm.azul.services;


import com.itesm.azul.models.Persona;
import com.itesm.azul.dto.PersonDTO;
import com.itesm.azul.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    //CREATE
    public void createPersona(final PersonDTO persona){
        Persona p = new Persona();
        p.setPersonaID(persona.getPersonaID());
        p.setTimestamp(persona.getTimestamp());
        p.setEdad(persona.getEdad());
        p.setNombre(persona.getNombre());
        personaRepository.save(p);
    }

    //READ
    public Iterable<Persona> lista(){
        return personaRepository.findAll();
    }
    public Iterable<Persona> get(String personaID){
        return personaRepository.findPerson(personaID);
    }
    public Persona getOne(String personaID, String timestamp){
        return personaRepository.findById(personaID,timestamp);
    }

    //DELETE
    public void delete(String personID, String timestamp){
        personaRepository.deleteById(personID,timestamp);
    }

    //UPDATE
    public Persona update(PersonDTO dto){
        Persona persona = new Persona();
        persona.setPersonaID(dto.getPersonaID());
        persona.setTimestamp(dto.getTimestamp());
        persona.setNombre(dto.getNombre());
        persona.setEdad(dto.getEdad());
        return personaRepository.update(persona);
    }
    /*

    public boolean existsId(String personaID){
        return personaRepository.existsById(personaID);
    }



*/

}
