package tinder.tindesrv.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinder.tindesrv.entity.PersonCrush;
import tinder.tindesrv.repository.PersonCrushRepository;
import tinder.tindesrv.service.PersonCrushService;
import tinder.tindesrv.service.dto.PersonCrushDto;
import tinder.tindesrv.service.mapping.PersonCrushMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonCrushServiceImpl implements PersonCrushService {
    @Autowired
    private final PersonCrushRepository personCrushRepository;
    private final PersonCrushMapper mapper;

    /**
     * Создает нового клиента
     *
     * @param personCrushDto - клиент для создания
     */
    @Override
    public void create(PersonCrushDto personCrushDto) {
        PersonCrush personCrush = new PersonCrush().builder()
                .crushId(personCrushDto.getCrushId())
                .userId(personCrushDto.getUserId())
                .build();
        personCrushRepository.save(personCrush);
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
                .orElseThrow(RuntimeException::new);
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
        return personCrushRepository.findByUserIdAndCrushIdOrUserIdAndCrushId(userId, crushId, crushId, userId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
