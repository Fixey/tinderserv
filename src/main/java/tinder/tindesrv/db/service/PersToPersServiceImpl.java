package tinder.tindesrv.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinder.tindesrv.entity.PersToPers;
import tinder.tindesrv.repository.PersToPersRepository;

import java.util.List;
import java.util.Set;

@Service
public class PersToPersServiceImpl implements PersToPersService {
    @Autowired
    private final PersToPersRepository persToPersRepository;

    public PersToPersServiceImpl(PersToPersRepository persToPersRepository) {
        this.persToPersRepository = persToPersRepository;
    }

    /**
     * Создает нового клиента
     *
     * @param persToPers - клиент для создания
     */
    @Override
    public void create(PersToPers persToPers) {
        persToPersRepository.save(persToPers);
    }

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    @Override
    public List<PersToPers> readAll() {
        return persToPersRepository.findAll();
    }

    /**
     * Возвращает клиента по его ID
     *
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    @Override
    public PersToPers read(int id) {
        return persToPersRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    /**
     * Обновляет клиента с заданным ID,
     * в соответствии с переданным клиентом
     *
     * @param persToPers - клиент в соответсвии с которым нужно обновить данные
     * @param id         - id клиента которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    @Override
    public boolean update(PersToPers persToPers, int id) {
        if (persToPersRepository.existsById(id)) {
            persToPers.setId(id);
            persToPersRepository.save(persToPers);
            return true;
        }
        return false;
    }

    /**
     * Удаляет клиента с заданным ID
     *
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    @Override
    public boolean delete(int id) {
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
     * @return - Set<Integer> клиентов
     */
    public Set<Integer> getCrushesIdByUserId(int id) {
        return persToPersRepository.getDistinctCrushIdByUserId(id);
    }


    /**
     * Возвращает id клиентов, которым понравился пользователь
     *
     * @param id - id пользователя
     * @return - Set<Integer> клиентов
     */
    public Set<Integer> getUsersIdByCrushId(int id) {
        return persToPersRepository.getDistinctUserIdByCrushId(id);
    }

    /**
     * Возвращает id клиентов, которым понравился пользователь и пользователю понравился клиент.
     *
     * @param id - id пользователя
     * @return - Set<Integer> клиентов
     */
    public Set<Integer> getMatchesByUserId(int id) {
        return persToPersRepository.getMatchesId(id);
    }

    /**
     * Существует уже лайк на клиента.
     *
     * @param persToPers - связь между клиентами
     * @return - true если запись уже есть, иначе false
     */
    public Boolean existLikeByCrush(PersToPers persToPers) {
        List<PersToPers> persToPersList = persToPersRepository.getByUserIdAndCrushId(persToPers.getUserId(), persToPers.getCrushId());
        return persToPersList != null && !persToPersList.isEmpty();
    }

    /**
     * Удалить связь клиента и пользователя
     *
     * @param persToPers - сущность связи клиентов
     */
    public void deleteLike(PersToPers persToPers) {
        persToPersRepository.deleteByUserIdAndCrushId(persToPers.getUserId(), persToPers.getCrushId());
    }


}
