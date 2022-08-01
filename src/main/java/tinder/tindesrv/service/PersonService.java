package tinder.tindesrv.service;

import tinder.tindesrv.dto.PersonDto;

import java.util.List;

public interface PersonService {
    /**
     * Возвращает клиента по его ID
     *
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    PersonDto read(Long id);

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    List<PersonDto> readAll();

    /**
     * Создает нового клиента
     *
     * @param person - клиент для создания
     */
    PersonDto create(PersonDto person);


    /**
     * Удаляет клиента с заданным ID
     *
     * @param id - id клиента, которого нужно удалить
     */
    void delete(Long id);
}
