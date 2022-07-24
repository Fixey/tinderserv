package tinder.tindesrv.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinder.tindesrv.db.service.PersToPersServiceImpl;
import tinder.tindesrv.db.service.PersonServiceImpl;
import tinder.tindesrv.entity.PersToPers;
import tinder.tindesrv.entity.Person;

import java.util.List;
import java.util.Set;

/**
 * DB контроллер
 */
@RestController
public class PersonController {
    private final PersonServiceImpl personService;
    private final PersToPersServiceImpl persToPersService;

    @Autowired
    public PersonController(PersonServiceImpl personService, PersToPersServiceImpl persToPersService) {
        this.personService = personService;
        this.persToPersService = persToPersService;
    }

    /**
     * Добаваляет клинета
     *
     * @param person клиент
     * @return HttpStatus.CREATED если сохранился
     */
    @PostMapping(value = "/persons")
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        personService.create(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Возвращает всех клиентов
     *
     * @return List<Person> и HttpStatus.OK если все Ок, иначе NOT_FOUND
     */
    @GetMapping(value = "/persons")
    public ResponseEntity<List<Person>> read() {
        final List<Person> persons = personService.readAll();
        return persons != null && !persons.isEmpty()
                ? new ResponseEntity<>(persons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Возвращает клиента по id
     *
     * @param id клиента
     * @return Person сущность клиента
     */
    @GetMapping(value = "/persons/{id}")
    public ResponseEntity<Person> read(@PathVariable(name = "id") int id) {
        final Person person = personService.read(id);
        return person != null
                ? new ResponseEntity<>(person, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Поиск. Найти клиента по его гендорным предпочтениям
     *
     * @param id клиента
     * @return List<Person> список клиентов
     */
    @GetMapping(value = "/persons_crush/{Id}")
    public ResponseEntity<List<Person>> searchPersonsByGender(@PathVariable(name = "id") int id) {
        Person person = personService.read(id);
        final List<Person> personList = personService.getPersonsByGender(person);
        return new ResponseEntity<>(personList, HttpStatus.OK);
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
        if (!persToPersService.existLikeByCrush(persToPers.getUserId(), persToPers.getCrushId())) {
            persToPersService.create(persToPers);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/crushes")
    public ResponseEntity<?> getCrushes() {
        final List<PersToPers> persToPers = persToPersService.readAll();
        return persToPers != null && !persToPers.isEmpty()
                ? new ResponseEntity<>(persToPers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Кого любит пользователь
     *
     * @return ResponseEntity<List < Person>> список людей кого любит пользователь
     */
    @GetMapping(value = "/personlove/{id}")
    public ResponseEntity<List<Person>> getPersonsFalling(@PathVariable(name = "id") int id) {
        final Set<Integer> crushesIdList = persToPersService.getCrushesIdByUserId(id);
        final List<Person> personList = personService.getPersonsByListId(crushesIdList);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    /**
     * Кому нравится пользователь
     *
     * @return ResponseEntity<List < Person>> список людей кому нравится пользователь
     */
    @GetMapping(value = "/loveperson/{id}")
    public ResponseEntity<List<Person>> getWhoLikePerson(@PathVariable(name = "id") int id) {
        final Set<Integer> crushesIdList = persToPersService.getUsersIdByCrushId(id);
        final List<Person> personList = personService.getPersonsByListId(crushesIdList);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping(value = "/matches/{id}")
    public ResponseEntity<List<Person>> getMatches(@PathVariable(name = "id") int id) {
        final Set<Integer> crushesIdList = persToPersService.getMatchesByUserId(id);
        final List<Person> personList = personService.getPersonsByListId(crushesIdList);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @PostMapping(value = "/crushes/{id}/{crushId}")
    public ResponseEntity<HttpStatus> getMatches(@PathVariable(name = "id") int id,
                                                 @PathVariable(name = "crushId") int crushId) {
        if (!persToPersService.existLikeByCrush(id, crushId)) {
            persToPersService.create(new PersToPers(id, crushId));
        }
        return new ResponseEntity<>(HttpStatus.OK);
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

    @DeleteMapping(value = "/crushes")
    public ResponseEntity<?> deleteCrush(@RequestBody PersToPers persToPers) {
        final Integer id = persToPers.getUserId();
        final Integer crushId = persToPers.getCrushId();
        if (persToPersService.existLikeByCrush(id, crushId)) {
            persToPersService.deleteLike(id, crushId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}