package tinder.tindesrv.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinder.tindesrv.entity.PersToPers;
import tinder.tindesrv.repository.PersToPersRepository;

import java.util.List;

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
}
