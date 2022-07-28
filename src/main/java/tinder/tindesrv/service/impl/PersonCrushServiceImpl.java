package tinder.tindesrv.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinder.tindesrv.dto.PersonCrushDto;
import tinder.tindesrv.entity.PersonCrush;
import tinder.tindesrv.exceptions.NotFountException;
import tinder.tindesrv.mapping.PersonCrushMapper;
import tinder.tindesrv.repository.PersonCrushRepository;
import tinder.tindesrv.service.PersonCrushService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonCrushServiceImpl implements PersonCrushService {
    private final PersonCrushRepository personCrushRepository;
    private final PersonCrushMapper mapper;

    /**
     * Создает нового клиента
     *
     * @param personCrushDto - клиент для создания
     */
    @Override
    public PersonCrushDto create(PersonCrushDto personCrushDto) {
        PersonCrush personCrush = mapper.fromDto(personCrushDto);
        PersonCrush savingPersonCrush = personCrushRepository.save(personCrush);
        return mapper.toDto(savingPersonCrush);
    }

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    @Override
    public List<PersonCrushDto> readAll() {
        return personCrushRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

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
                .orElseThrow(NotFountException::new);
    }

    /**
     * Удаляет клиента с заданным ID
     *
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    @Override
    public boolean delete(Long id) {
        if (personCrushRepository.existsById(id)) {
            personCrushRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Возвращает id клиентов которые нравятся пользователю
     *
     * @param id - id пользователя
     * @return - Set<Long> клиентов
     */
    public Set<PersonCrush> getCrushesIdByUserId(Long id) {
        return personCrushRepository.getCrushIdByUserId(id);
    }


    /**
     * Возвращает id клиентов, которым понравился пользователь
     *
     * @param id - id пользователя
     * @return - Set<Long> клиентов
     */
    public Set<PersonCrush> getUsersIdByCrushId(Long id) {
        return personCrushRepository.getUserIdByCrushId(id);
    }

    /**
     * Возвращает id клиентов, которым понравился пользователь и пользователю понравился клиент.
     *
     * @param id - id пользователя
     * @return - Set<Long> клиентов
     */
    public Set<Long> getMatchesByUserId(Long id) {
        return personCrushRepository.getMatchesId(id);
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
     * Удалить связь клиента и пользователя
     *
     * @param userId  id пользователя
     * @param crushId id любимца
     */
    public void deleteLike(Long userId, Long crushId) {
        personCrushRepository.deleteByUserIdAndCrushId(userId, crushId);
    }

    /**
     * Достать связи для клиента и любимца
     *
     * @param userId  - id клиента
     * @param crushId - id любимца
     */
    public List<PersonCrushDto> getUserAndCrush(Long userId, Long crushId) {
        List<PersonCrushDto> personCrushDtoList = new ArrayList<>();
        personCrushDtoList.addAll(personCrushRepository.findByUserIdAndCrushId(userId, crushId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
        personCrushDtoList.addAll(personCrushRepository.findByUserIdAndCrushId(crushId, userId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
        return personCrushDtoList;
    }
}
