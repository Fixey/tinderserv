package tinder.tindesrv.service.impl;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinder.tindesrv.dto.PersonCrushDto;
import tinder.tindesrv.entity.PersonCrush;
import tinder.tindesrv.exceptions.CreatePersonException;
import tinder.tindesrv.exceptions.NotFoundException;
import tinder.tindesrv.mapping.PersonCrushMapper;
import tinder.tindesrv.repository.PersonCrushRepository;
import tinder.tindesrv.service.PersonCrushService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonCrushServiceImpl implements PersonCrushService {
    private final PersonCrushRepository personCrushRepository;
    private final PersonCrushMapper mapper;

    /**
     * Возвращает клиента по его ID
     *
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    @Override
    public PersonCrushDto read(Long id) {
        return personCrushRepository
                .findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    @Override
    public @NotNull List<PersonCrushDto> readAll() {
        return personCrushRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Достать связи для клиента и любимца
     *
     * @param userId  - id клиента
     * @param crushId - id любимца
     */
    public List<PersonCrushDto> getUserAndCrush(Long userId, Long crushId) {
        List<Long> listid = List.of(userId, crushId);
        return personCrushRepository.findByUserIdInAndCrushIdIn(listid, listid)
                .stream()
                .map(personCrush -> personCrush.orElseThrow(NotFoundException::new))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Существует уже лайк на клиенте?
     *
     * @param userId  id пользователя
     * @param crushId id любимца
     * @return - true если запись уже есть, иначе false
     */
    public Boolean existLikeByCrush(Long userId, Long crushId) {
        return personCrushRepository.existsByUserIdAndCrushId(userId, crushId);
    }

    /**
     * Создает нового клиента
     *
     * @param personCrushDto - клиент для создания
     * @return PersonCrushDto - новый клиент
     * @throws CreatePersonException - если не смог создать клиента
     */
    @Override
    public PersonCrushDto upsert(PersonCrushDto personCrushDto) {
        try {
            PersonCrushDto savingPersonCrushDto = null;
            if (!existLikeByCrush(personCrushDto.getUserId(), personCrushDto.getCrushId())) {
                PersonCrush personCrush = mapper.toEntity(personCrushDto);
                PersonCrush savingPersonCrush = personCrushRepository.save(personCrush);
                savingPersonCrushDto = mapper.toDto(savingPersonCrush);
            }
            return savingPersonCrushDto;
        } catch (RuntimeException e) {
            throw new CreatePersonException();
        }
    }

    /**
     * Удаляет клиента с заданным ID
     *
     * @param id - id клиента, которого нужно удалить
     */
    @Override
    public void delete(Long id) {
        personCrushRepository.deleteById(id);
    }

    /**
     * Удалить связь клиента и пользователя
     *
     * @param userId  id пользователя
     * @param crushId id любимца
     */
    public void deleteLike(Long userId, Long crushId) {
        personCrushRepository.deleteByUserIdAndCrushId(userId, crushId);
    }
}
