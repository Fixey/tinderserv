package tinder.tindesrv.service;

import tinder.tindesrv.service.dto.PersonCrushDto;

import java.util.List;

public interface PersonCrushService {
    /**
     * Создает нового клиента
     *
     * @param personCrushDto - клиент для создания
     */
    void create(PersonCrushDto personCrushDto);

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    List<PersonCrushDto> readAll();

    /**
     * Возвращает клиента по его ID
     *
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    PersonCrushDto read(Long id);

    /**
     * Удаляет клиента с заданным ID
     *
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(Long id);
}
