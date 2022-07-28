package tinder.tindesrv.service;

import tinder.tindesrv.dto.PersonDto;

import java.util.List;

public interface PersonService {
    /**
     * Создает нового клиента
     *
     * @param person - клиент для создания
     */
    PersonDto create(PersonDto person);

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    List<PersonDto> readAll();


    /**
     * Возвращает клиента по его ID
     *
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    PersonDto read(Long id);

    /**
     * Удаляет клиента с заданным ID
     *
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(Long id);
}
