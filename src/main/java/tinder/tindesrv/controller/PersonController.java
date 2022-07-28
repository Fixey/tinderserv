package tinder.tindesrv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinder.tindesrv.dto.PersonDto;
import tinder.tindesrv.service.impl.PersonCrushServiceImpl;
import tinder.tindesrv.service.impl.PersonServiceImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DB контроллер
 */
@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonServiceImpl personService;
    private final PersonCrushServiceImpl personCrushService;


    /**
     * Добавляет клиента в базу
     *
     * @param person клиент
     * @return PersonDto клиент, которого добавили
     */
    @PostMapping(value = "/persons")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto person) {
        PersonDto savingPersonDto = personService.create(person);
        return savingPersonDto != null
                ? new ResponseEntity<>(savingPersonDto, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Возвращает всех клиентов
     *
     * @return List<Person> список клиентов
     */
    @GetMapping(value = "/persons")
    public ResponseEntity<List<PersonDto>> read() {
        List<PersonDto> persons = personService.readAll();
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
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> searchCrush(@PathVariable Long id) {
        PersonDto person = personService.read(id);
        List<PersonDto> personList = personService.getPersonsByGender(person);
        return personList;
    }

    /**
     * Удаление клиента
     *
     * @param id клиента
     * @return id клиента которого удалили
     */
    @DeleteMapping(value = "/persons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Long delete(@PathVariable Long id) {
        personService.delete(id);
        return id;
    }

    /**
     * Любимцы. Список клиентов, кого ищет пользователь
     *
     * @return List < Person> список людей, кого любит пользователь
     */
    @GetMapping(value = "/personlove/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getPersonsFalling(@PathVariable Long id) {
        Set<Long> crushesIdList = personCrushService.getCrushesIdByUserId(id)
                .stream()
                .map(personCrush -> personCrush.getCrushId())
                .collect(Collectors.toSet());
        List<PersonDto> personList = personService.getPersonsByListId(crushesIdList);
        return personList;
    }

    /**
     * Кому нравится пользователь
     *
     * @return List < Person> список людей кому нравится пользователь
     */
    @GetMapping(value = "/loveperson/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getWhoLikePerson(@PathVariable Long id) {
        Set<Long> crushesIdList = personCrushService.getUsersIdByCrushId(id)
                .stream()
                .map(personCrush -> personCrush.getUserId())
                .collect(Collectors.toSet());
        List<PersonDto> personList = personService.getPersonsByListId(crushesIdList);
        return personList;
    }

    /**
     * Любимцы. Совпадения по поиску клиентов.
     *
     * @param id клиента
     * @return Список людей с которым произошел мэтч
     */
    @GetMapping(value = "/matches/{id}")
    public ResponseEntity<List<PersonDto>> getMatches(@PathVariable Long id) {
        Set<Long> crushesIdList = personCrushService.getMatchesByUserId(id);
        List<PersonDto> personList = personService.getPersonsByListId(crushesIdList);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    /**
     * Любимцы. Список любимцев, которые нравились клиенту, выбрали клиента или был взаимный выбор.
     *
     * @param id клиента
     * @return Список любимцев
     */
    @GetMapping(value = "/lovers/{id}")
    public ResponseEntity<List<PersonDto>> getPersonsForLovers(@PathVariable Long id) {
        List<PersonDto> personList = personService.getPersonsForLovers(id);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }
}