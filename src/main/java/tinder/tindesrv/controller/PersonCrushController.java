package tinder.tindesrv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinder.tindesrv.service.dto.PersonCrushDto;
import tinder.tindesrv.service.impl.PersonCrushServiceImpl;
import tinder.tindesrv.service.impl.PersonServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonCrushController {

    private final PersonServiceImpl personService;
    private final PersonCrushServiceImpl personCrushService;

    /**
     * Добавляет связь межеду клиентами
     *
     * @param personCrushDto сущность интерсект таблицы
     * @return HttpStatus.CREATED - если все создалось без ошибок
     */
    @PostMapping(value = "/crushes")
    public ResponseEntity<?> createCrush(@RequestBody PersonCrushDto personCrushDto) {
        if (!personCrushService.existLikeByCrush(personCrushDto.getUserId(), personCrushDto.getCrushId())) {
            personCrushService.create(personCrushDto);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Вернуть все связи интерсект таблицы клиентов
     *
     * @return List<personCrushDtos> сущности клинетов
     */
    @GetMapping(value = "/crushes")
    public ResponseEntity<?> getCrushes() {
        final List<PersonCrushDto> personCrushDtos = personCrushService.readAll();
        return personCrushDtos != null && !personCrushDtos.isEmpty()
                ? new ResponseEntity<>(personCrushDtos, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Любимцы. Если ли у человека лайк
     *
     * @return ResponseEntity<List < Person>> список людей кого любит пользователь
     */
    @GetMapping(value = "/crushes/{userId}/{crushId}")
    public ResponseEntity<Boolean> existLikeByCrush(@PathVariable(name = "userId") Long userId,
                                                    @PathVariable(name = "crushId") Long crushId) {
        return new ResponseEntity<>(personCrushService.existLikeByCrush(userId, crushId), HttpStatus.OK);

    }

    /**
     * Найти связь по id в таблице persons_to_persons
     *
     * @param id связи
     * @return personCrushDto связи. HttpStatus.NOT_FOUND - если свзяи нет.
     */
    @GetMapping(value = "/crushes/{id}")
    public ResponseEntity<PersonCrushDto> readCrush(@PathVariable(name = "id") Long id) {
        final PersonCrushDto personCrushDto = personCrushService.read(id);
        return personCrushDto != null
                ? new ResponseEntity<>(personCrushDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Найти все связи между клиентом и любимцем
     *
     * @param userId  связи
     * @param crushId связи
     * @return personCrushDto связи. HttpStatus.NOT_FOUND - если свзяи нет.
     */
    @GetMapping(value = "/lovers/{userId}/{crushId}")
    public ResponseEntity<List<PersonCrushDto>> getUserAndCrush(@PathVariable(name = "userId") Long userId,
                                                                @PathVariable(name = "crushId") Long crushId) {
        final List<PersonCrushDto> personCrushDtoList = personCrushService.getUserAndCrush(userId, crushId);
        return new ResponseEntity<>(personCrushDtoList, HttpStatus.OK);
    }

    /**
     * Удаление связи в интресект таблице клиентов
     *
     * @param userId  id пользователя
     * @param crushId id любимца
     * @return HttpStatus.OK - если все прошло без ошибок
     */
    @DeleteMapping(value = "/crushes/{userId}/{crushId}")
    public ResponseEntity<?> deleteCrush(@PathVariable(name = "userId") Long userId,
                                         @PathVariable(name = "crushId") Long crushId) {
        if (personCrushService.existLikeByCrush(userId, crushId)) {
            personCrushService.deleteLike(userId, crushId);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
