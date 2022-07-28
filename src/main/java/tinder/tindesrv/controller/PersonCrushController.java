package tinder.tindesrv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinder.tindesrv.dto.PersonCrushDto;
import tinder.tindesrv.service.impl.PersonCrushServiceImpl;
import tinder.tindesrv.service.impl.PersonServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonCrushController {

    private final PersonServiceImpl personService;
    private final PersonCrushServiceImpl personCrushService;

    /**
     * Добавляет связь между клиентами
     *
     * @param personCrushDto сущность интерсект таблицы
     * @return personCrushDto связь, которую создали
     */
    @PostMapping(value = "/crushes")
    public ResponseEntity<PersonCrushDto> createCrush(@RequestBody PersonCrushDto personCrushDto) {
        PersonCrushDto savingPersonCrushDto = null;
        if (!personCrushService.existLikeByCrush(personCrushDto.getUserId(), personCrushDto.getCrushId())) {
            savingPersonCrushDto = personCrushService.create(personCrushDto);
        }
        return savingPersonCrushDto != null
                ? new ResponseEntity<>(personCrushDto, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Вернуть все связи интерсект таблицы клиентов
     *
     * @return List<personCrushDtos> сущности клинетов
     */
    @GetMapping(value = "/crushes")
    public ResponseEntity<?> getCrushes() {
        List<PersonCrushDto> personCrushDtos = personCrushService.readAll();
        return personCrushDtos != null && !personCrushDtos.isEmpty()
                ? new ResponseEntity<>(personCrushDtos, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Любимцы. Если ли у человека лайк
     *
     * @return true - если лайк есть, false - если нет лайка
     */
    @GetMapping(value = "/crushes/{userId}/{crushId}")
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
    public List<PersonCrushDto> getUserAndCrush(@PathVariable Long userId,
                                                @PathVariable Long crushId) {
        List<PersonCrushDto> personCrushDtoList = personCrushService.getUserAndCrush(userId, crushId);
        return personCrushDtoList;
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
        if (personCrushService.existLikeByCrush(userId, crushId)) {
            personCrushService.deleteLike(userId, crushId);
        }
    }
}
