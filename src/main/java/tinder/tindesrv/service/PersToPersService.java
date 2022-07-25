package tinder.tindesrv.service;

import tinder.tindesrv.entity.PersToPers;

import java.util.List;

public interface PersToPersService {
    /**
     * Создает нового клиента
     *
     * @param persToPers - клиент для создания
     */
    void create(PersToPers persToPers);

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    List<PersToPers> readAll();

    /**
     * Возвращает клиента по его ID
     *
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    PersToPers read(int id);

    /**
     * Обновляет клиента с заданным ID,
     * в соответствии с переданным клиентом
     *
     * @param persToPers - клиент в соответсвии с которым нужно обновить данные
     * @param id         - id клиента которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(PersToPers persToPers, int id);

    /**
     * Удаляет клиента с заданным ID
     *
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(int id);
}
