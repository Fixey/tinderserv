package tinder.tindesrv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinder.tindesrv.service.dto.PersToPersDto;
import tinder.tindesrv.service.impl.PersToPersServiceImpl;
import tinder.tindesrv.service.impl.PersonServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersToPersController {

    private final PersonServiceImpl personService;
    private final PersToPersServiceImpl persToPersService;

    /**
     * Добавляет связь межеду клиентами
     *
     * @param persToPers сущность интерсект таблицы
     * @return HttpStatus.CREATED - если все создалось без ошибок
     */
    @PostMapping(value = "/crushes")
    public ResponseEntity<?> createCrush(@RequestBody PersToPersDto persToPers) {
        if (!persToPersService.existLikeByCrush(persToPers)) {
            persToPersService.create(persToPers);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Вернуть все связи интерсект таблицы клиентов
     *
     * @return List<Person> сущности клинетов
     */
    @GetMapping(value = "/crushes")
    public ResponseEntity<?> getCrushes() {
        final List<PersToPersDto> persToPers = persToPersService.readAll();
        return persToPers != null && !persToPers.isEmpty()
                ? new ResponseEntity<>(persToPers, HttpStatus.OK)
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
        return new ResponseEntity<>(persToPersService.existLikeByCrush(new PersToPersDto(userId, crushId)), HttpStatus.OK);

    }

    /**
     * Найти связь по id в таблице persons_to_persons
     *
     * @param id связи
     * @return persToPers связи. HttpStatus.NOT_FOUND - если свзяи нет.
     */
    @GetMapping(value = "/crushes/{id}")
    public ResponseEntity<PersToPersDto> readCrush(@PathVariable(name = "id") Long id) {
        final PersToPersDto persToPers = persToPersService.read(id);
        return persToPers != null
                ? new ResponseEntity<>(persToPers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Удаление связи в интресект таблице клиентов
     *
     * @param persToPers сущность связей
     * @return HttpStatus.OK - если все прошло без ошибок
     */
    @DeleteMapping(value = "/crushes")
    public ResponseEntity<?> deleteCrush(@RequestBody PersToPersDto persToPers) {
        if (persToPersService.existLikeByCrush(persToPers)) {
            persToPersService.deleteLike(persToPers);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
