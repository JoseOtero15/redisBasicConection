package com.example.guiaRedis.Controller;

import com.example.guiaRedis.model.Persona;
import com.example.guiaRedis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private UserRepository repo;

    @GetMapping()
    public String comprobar() {

        return "CONECTADO";
    };


    @PostMapping("/save")
    public ResponseEntity<Persona> createUser(@RequestBody Persona persona) {
        repo.save(persona);
        return new ResponseEntity<>(persona, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getUserById(@PathVariable String id) {
        Persona persona = repo.findById(id);
        if (persona != null) {
            return new ResponseEntity<>(persona, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<Persona>> getAllUsers() {
        List<Persona> personas = repo.findAll();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updateUser(@PathVariable String id, @RequestBody Persona persona) {
        persona.setId(id);
        repo.update(persona);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        repo.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
