package tinder.tindesrv.service;

import tinder.tindesrv.dto.PersonCrushDto;

import java.util.List;

public interface PersonCrushService {
    /**
     * Возвращает клиента по его ID
     *
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    PersonCrushDto read(Long id);

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    List<PersonCrushDto> readAll();

    /**
     * Создает нового клиента
     *
     * @param personCrushDto - клиент для создания
     */
    PersonCrushDto create(PersonCrushDto personCrushDto);

    /**
     * Удаляет клиента с заданным ID
     *
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(Long id);
}
