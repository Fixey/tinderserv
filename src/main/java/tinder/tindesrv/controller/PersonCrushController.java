package tinder.tindesrv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinder.tindesrv.dto.PersonCrushDto;
import tinder.tindesrv.service.impl.PersonCrushServiceImpl;
import tinder.tindesrv.service.impl.PersonServiceImpl;

import java.util.List;

/**
 * Persons_to_persons контроллер
 */
@RestController
@RequiredArgsConstructor
public class PersonCrushController {

    private final PersonServiceImpl personService;
    private final PersonCrushServiceImpl personCrushService;

    /**
     * Вернуть все связи интерсект таблицы клиентов
     *
     * @return List<personCrushDtos> сущности клинетов
     */
    @GetMapping(value = "/crushes")
    public List<PersonCrushDto> getCrushes() {
        return personCrushService.readAll();
    }

    /**
     * Любимцы. Если ли у человека лайк
     *
     * @return true - если лайк есть, false - если нет лайка
     */
    @GetMapping(value = "/crushes/{userId}/{crushId}")
    public Boolean existLikeByCrush(@PathVariable Long userId,
                                    @PathVariable Long crushId) {
        return personCrushService.existLikeByCrush(userId, crushId);
    }

    /**
     * Найти связь по id в таблице persons_to_persons
     *
     * @param id связи
     * @return personCrushDto связи. HttpStatus.NOT_FOUND - если свзяи нет.
     */
    @GetMapping(value = "/crushes/{id}")
    public ResponseEntity<PersonCrushDto> readCrush(@PathVariable Long id) {
        PersonCrushDto personCrushDto = personCrushService.read(id);
        return personCrushDto != null
                ? new ResponseEntity<>(personCrushDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Найти все связи между клиентом и любимцем
     *
     * @param userId  связи
     * @param crushId связи
     * @return List<PersonCrushDto> лист связей между клиентами
     */
    @GetMapping(value = "/lovers/{userId}/{crushId}")
    public List<PersonCrushDto> getUserAndCrush(@PathVariable Long userId,
                                                @PathVariable Long crushId) {
        return personCrushService.getUserAndCrush(userId, crushId);
    }

    /**
     * Добавляет связь между клиентами
     *
     * @param personCrushDto сущность интерсект таблицы
     * @return personCrushDto связь, которую создали
     */
    @PostMapping(value = "/crushes")
    public ResponseEntity<PersonCrushDto> createCrush(@RequestBody PersonCrushDto personCrushDto) {
        PersonCrushDto savingPersonCrushDto = personCrushService.create(personCrushDto);
        return new ResponseEntity<>(savingPersonCrushDto, HttpStatus.CREATED);
    }

    /**
     * Удаление связи в интресект таблице клиентов
     *
     * @param userId  id пользователя
     * @param crushId id любимца
     */
    @DeleteMapping(value = "/crushes/{userId}/{crushId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCrush(@PathVariable Long userId,
                            @PathVariable Long crushId) {
        personCrushService.deleteLike(userId, crushId);
    }
}
