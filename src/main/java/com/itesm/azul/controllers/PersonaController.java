package com.itesm.azul.controllers;

import com.itesm.azul.models.Persona;
import com.itesm.azul.dto.PersonDTO;
import com.itesm.azul.repositories.PersonaRepository;
import com.itesm.azul.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.List;


@RestController
@RequestMapping("/v1/persona")
public class PersonaController {

    @Autowired
    PersonaService personaService;
    //https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/java_dynamodb_code_examples.html
    //Create
    //insert a tuple
    @PostMapping("/save")
    public PersonDTO save(@RequestBody PersonDTO persona) throws Exception{
        personaService.createPersona(persona);
        return persona;
    }
    //select all tuples
    @GetMapping("/all")
    public ResponseEntity<Iterable<Persona>> getAll(){
        return ResponseEntity.ok(personaService.lista());
    }

    //Read
    //select 1 tuple
    @GetMapping("/get/{personaID}")
    public ResponseEntity<Iterable<Persona>> getOne(@PathVariable("personaID") String personaID){
        //if(!personaService.existsId(personaID))
        //    return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(personaService.get(personaID));
    }

    @GetMapping("/getone/{personaID}/{fecha}")
    public ResponseEntity<Persona> getOne(@PathVariable("personaID") String personaID, @PathVariable("fecha") String fecha){
        //if(!personaService.existsId(personaID))
        //    return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(personaService.getOne(personaID, fecha));
    }
    //Delete a tuple
    @DeleteMapping("/delete/{personaID}/{fecha}")
    public ResponseEntity<?> delete(@PathVariable("personaID") String personaID, @PathVariable("fecha") String fecha){
        /*if(!personaService.existsId(personaID))
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);

         */
        personaService.delete(personaID,fecha);
        return new ResponseEntity("persona eliminada", HttpStatus.OK);
    }


    //Update a tuple
    @PutMapping("/update")
    public ResponseEntity<Persona> update(@RequestBody PersonDTO personaDTO){
       /* if(!personaService.existsId(personaDTO.getPersonaID()))
            return new ResponseEntity("no existe, no se puede actualizar", HttpStatus.NOT_FOUND);

        */
        return ResponseEntity.ok(personaService.update(personaDTO));
    }
}
