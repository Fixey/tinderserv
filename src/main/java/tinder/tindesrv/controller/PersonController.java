package tinder.tindesrv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinder.tindesrv.dto.PersonDto;
import tinder.tindesrv.service.impl.PersonServiceImpl;

import java.util.List;

/**
 * Person контроллер
 */
@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonServiceImpl personService;

    /**
     * Любимцы. Список любимцев, которые нравились клиенту, выбрали клиента или был взаимный выбор.
     *
     * @param id клиента
     * @return Список любимцев
     */
    @GetMapping(value = "/lovers/{id}")
    public List<PersonDto> getPersonsForLovers(@PathVariable Long id) {
        return personService.getPersonsForLovers(id);
    }

    /**
     * Возвращает всех клиентов
     *
     * @return List<Person> список клиентов
     */
    @GetMapping(value = "/persons")
    public List<PersonDto> read() {
        return personService.readAll();
    }

    /**
     * Возвращает клиента по id
     *
     * @param id клиента
     * @return Person сущность клиента
     */
    @GetMapping(value = "/person/{id}")
    public ResponseEntity<PersonDto> read(@PathVariable Long id) {
        PersonDto person = personService.read(id);
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
    @GetMapping(value = "/person/search/{id}")
    public List<PersonDto> searchCrush(@PathVariable Long id) {
        return personService.getPersonsByGender(id);
    }


    /**
     * Добавляет клиента в базу
     *
     * @param person клиент
     * @return PersonDto клиент, которого добавили
     */
    @PostMapping(value = "/person")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto upsertPerson(@RequestBody PersonDto person) {
        return personService.upsert(person);
    }

    /**
     * Удаление клиента
     *
     * @param id клиента
     */
    @DeleteMapping(value = "/person/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }
}