package tinder.tindesrv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinder.tindesrv.service.dto.PersonDto;
import tinder.tindesrv.service.impl.PersToPersServiceImpl;
import tinder.tindesrv.service.impl.PersonServiceImpl;

import java.util.List;
import java.util.Set;

/**
 * DB контроллер
 */
@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonServiceImpl personService;
    private final PersToPersServiceImpl persToPersService;


    /**
     * Добаваляет клинета
     *
     * @param person клиент
     * @return HttpStatus.CREATED если сохранился
     */
    @PostMapping(value = "/persons")
    public ResponseEntity<?> createPerson(@RequestBody PersonDto person) {
        personService.create(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Возвращает всех клиентов
     *
     * @return List<Person> и HttpStatus.OK если все Ок, иначе NOT_FOUND
     */
    @GetMapping(value = "/persons")
    public ResponseEntity<List<PersonDto>> read() {
        final List<PersonDto> persons = personService.readAll();
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
    public ResponseEntity<PersonDto> read(@PathVariable(name = "id") Long id) {
        final PersonDto person = personService.read(id);
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
    @GetMapping(value = "/persons_crush/{id}")
    public ResponseEntity<List<PersonDto>> searchPersonsByGender(@PathVariable(name = "id") Long id) {
        PersonDto person = personService.read(id);
        final List<PersonDto> personList = personService.getPersonsByGender(person);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    /**
     * Удаление клиента
     *
     * @param id клиента
     * @return HttpStatus.OK - если удалился, иначе HttpStatus.NOT_MODIFIED
     */
    @DeleteMapping(value = "/persons/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Любимцы. Список клиентов, кого ищет пользователь
     *
     * @return ResponseEntity<List < Person>> список людей кого любит пользователь
     */
    @GetMapping(value = "/personlove/{id}")
    public ResponseEntity<List<PersonDto>> getPersonsFalling(@PathVariable(name = "id") Long id) {
        final Set<Long> crushesIdList = persToPersService.getCrushesIdByUserId(id);
        final List<PersonDto> personList = personService.getPersonsByListId(crushesIdList);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    /**
     * Кому нравится пользователь
     *
     * @return ResponseEntity<List < Person>> список людей кому нравится пользователь
     */
    @GetMapping(value = "/loveperson/{id}")
    public ResponseEntity<List<PersonDto>> getWhoLikePerson(@PathVariable(name = "id") Long id) {
        final Set<Long> crushesIdList = persToPersService.getUsersIdByCrushId(id);
        final List<PersonDto> personList = personService.getPersonsByListId(crushesIdList);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    /**
     * Любимцы. Совпадения по поиску клиентов.
     *
     * @param id клиента
     * @return Список людей с которым произошел мэтч
     */
    @GetMapping(value = "/matches/{id}")
    public ResponseEntity<List<PersonDto>> getMatches(@PathVariable(name = "id") Long id) {
        final Set<Long> crushesIdList = persToPersService.getMatchesByUserId(id);
        final List<PersonDto> personList = personService.getPersonsByListId(crushesIdList);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }
}