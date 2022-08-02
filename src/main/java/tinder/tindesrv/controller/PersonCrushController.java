package tinder.tindesrv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinder.tindesrv.dto.PersonCrushDto;
import tinder.tindesrv.service.impl.PersonCrushServiceImpl;

import java.util.List;

/**
 * Persons_to_persons контроллер
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/crushes")
public class PersonCrushController {

    private final PersonCrushServiceImpl personCrushService;

    /**
     * Вернуть все связи интерсект таблицы клиентов
     *
     * @return List<personCrushDtos> сущности клиентов
     */
    @GetMapping
    public List<PersonCrushDto> getCrushes() {
        return personCrushService.readAll();
    }

    /**
     * Любимцы. Если ли у человека лайк
     *
     * @return true - если лайк есть, false - если нет лайка
     */
    @GetMapping(value = "/{userId}/{crushId}")
    public Boolean existLikeByCrush(@PathVariable Long userId,
                                    @PathVariable Long crushId) {
        return personCrushService.existLikeByCrush(userId, crushId);
    }

    /**
     * Найти связь по id в таблице persons_to_persons
     *
     * @param id связи
     * @return personCrushDto связи. HttpStatus.NOT_FOUND - если связи нет.
     */
    @GetMapping(value = "/{id}")
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonCrushDto upsertCrush(@RequestBody PersonCrushDto personCrushDto) {
        return personCrushService.upsert(personCrushDto);
    }

    /**
     * Удаление связи в интресект таблице клиентов
     *
     * @param userId  id пользователя
     * @param crushId id любимца
     */
    @DeleteMapping(value = "/{userId}/{crushId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCrush(@PathVariable Long userId,
                            @PathVariable Long crushId) {
        personCrushService.deleteLike(userId, crushId);
    }
}
