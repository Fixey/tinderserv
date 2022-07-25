package tinder.tindesrv.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinder.tindesrv.entity.PersToPers;
import tinder.tindesrv.repository.PersToPersRepository;
import tinder.tindesrv.service.PersToPersService;
import tinder.tindesrv.service.dto.PersToPersDto;
import tinder.tindesrv.service.mapping.PersToPersMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersToPersServiceImpl implements PersToPersService {
    @Autowired
    private final PersToPersRepository persToPersRepository;
    private final PersToPersMapper mapper;

    /**
     * Создает нового клиента
     *
     * @param persToPersDto - клиент для создания
     */
    @Override
    public void create(PersToPersDto persToPersDto) {
        PersToPers persToPers = new PersToPers()
                .builder()
                .id(persToPersDto.getId())
                .crushId(persToPersDto.getCrushId())
                .userId(persToPersDto.getCrushId())
                .build();
        persToPersRepository.save(persToPers);
    }

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    @Override
    public List<PersToPersDto> readAll() {
        return persToPersRepository.findAll()
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
    public PersToPersDto read(Long id) {
        return persToPersRepository
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
        if (persToPersRepository.existsById(id)) {
            persToPersRepository.deleteById(id);
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
    public Set<Long> getCrushesIdByUserId(Long id) {
        return persToPersRepository.getCrushIdByUserId(id);
    }


    /**
     * Возвращает id клиентов, которым понравился пользователь
     *
     * @param id - id пользователя
     * @return - Set<Long> клиентов
     */
    public Set<Long> getUsersIdByCrushId(Long id) {
        return persToPersRepository.getUserIdByCrushId(id);
    }

    /**
     * Возвращает id клиентов, которым понравился пользователь и пользователю понравился клиент.
     *
     * @param id - id пользователя
     * @return - Set<Long> клиентов
     */
    public Set<Long> getMatchesByUserId(Long id) {
        return persToPersRepository.getMatchesId(id);
    }

    /**
     * Существует уже лайк на клиенте?
     *
     * @param persToPers - связь между клиентами
     * @return - true если запись уже есть, иначе false
     */
    public Boolean existLikeByCrush(PersToPersDto persToPers) {
        return persToPersRepository.existsByUserIdAndCrushId(persToPers.getUserId(), persToPers.getCrushId());
    }

    /**
     * Удалить связь клиента и пользователя
     *
     * @param persToPers - сущность связи клиентов
     */
    public void deleteLike(PersToPersDto persToPers) {
        persToPersRepository.deleteByUserIdAndCrushId(persToPers.getUserId(), persToPers.getCrushId());
    }
}
