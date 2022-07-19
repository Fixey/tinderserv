package tinder.tindesrv.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinder.tindesrv.db.service.PersToPersService;
import tinder.tindesrv.db.service.PersonService;
import tinder.tindesrv.entity.PersToPers;
import tinder.tindesrv.entity.Person;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;
    private final PersToPersService persToPersService;

    @Autowired
    public PersonController(PersonService personService, PersToPersService persToPersService) {
        this.personService = personService;
        this.persToPersService = persToPersService;
    }

    @PostMapping(value = "/persons")
    public ResponseEntity<?> create(@RequestBody Person person) {
        personService.create(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/persons")
    public ResponseEntity<List<Person>> read() {
        final List<Person> persons = personService.readAll();
        return persons != null && !persons.isEmpty()
                ? new ResponseEntity<>(persons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/persons/{id}")
    public ResponseEntity<Person> read(@PathVariable(name = "id") int id) {
        final Person person = personService.read(id);
        return person != null
                ? new ResponseEntity<>(person, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/persons/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Person person) {
        final boolean updated = personService.update(person, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/persons/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = personService.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/crushes")
    public ResponseEntity<?> createCrush(@RequestBody PersToPers persToPers) {
        persToPersService.create(persToPers);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/crushes")
    public ResponseEntity<List<PersToPers>> readCrush() {
        final List<PersToPers> persToPers = persToPersService.readAll();
        return persToPers != null && !persToPers.isEmpty()
                ? new ResponseEntity<>(persToPers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/crushes/{id}")
    public ResponseEntity<PersToPers> readCrush(@PathVariable(name = "id") int id) {
        final PersToPers persToPers = persToPersService.read(id);
        return persToPers != null
                ? new ResponseEntity<>(persToPers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/crushes/{id}")
    public ResponseEntity<?> updateCrush(@PathVariable(name = "id") int id, @RequestBody PersToPers persToPers) {
        final boolean updated = persToPersService.update(persToPers, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/crushes/{id}")
    public ResponseEntity<?> deleteCrush(@PathVariable(name = "id") int id) {
        final boolean deleted = persToPersService.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}