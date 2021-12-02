package dev.kruhlmann.crud.controller;

import dev.kruhlmann.crud.exception.HTTP404NotFoundException;
import dev.kruhlmann.crud.model.Person;
import dev.kruhlmann.crud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/person")
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personId) throws HTTP404NotFoundException {
        Person person = personRepository.findById(personId).orElseThrow(() -> new HTTP404NotFoundException("Person<@" + personId + "> not found."));
        return ResponseEntity.ok().body(person);
    }

    @PostMapping("/person")
    public Person createPerson(@Valid @RequestBody Person person){
        return personRepository.save(person);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long personId, @Valid @RequestBody Person personDetails) throws HTTP404NotFoundException{
        Person person = personRepository.findById(personId).orElseThrow(() -> new HTTP404NotFoundException("Person<@" + personId + "> not found."));
        person.setEmailAddress(personDetails.getEmailAddress());
        person.setFirstName((personDetails.getFirstName()));
        person.setLastName(personDetails.getLastName());
        final Person updatedPerson = personRepository.save(person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/person/{id}")
    public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId) throws HTTP404NotFoundException {
        Person person = personRepository.findById(personId).orElseThrow(() -> new HTTP404NotFoundException("Person<@" + personId + "> not found."));
        personRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
