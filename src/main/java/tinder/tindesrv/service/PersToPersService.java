package tinder.tindesrv.service;

import tinder.tindesrv.service.dto.PersToPersDto;

import java.util.List;

public interface PersToPersService {
    /**
     * Создает нового клиента
     *
     * @param persToPers - клиент для создания
     */
    void create(PersToPersDto persToPers);

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    List<PersToPersDto> readAll();

    /**
     * Возвращает клиента по его ID
     *
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    PersToPersDto read(Long id);

    /**
     * Удаляет клиента с заданным ID
     *
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(Long id);
}
