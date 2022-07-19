package tinder.tindesrv.db.service;

import tinder.tindesrv.entity.Person;

import java.util.List;

public interface PersonService {
    /**
     * Создает нового клиента
     *
     * @param person - клиент для создания
     */
    void create(Person person);

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    List<Person> readAll();


    /**
     * Возвращает клиента по его ID
     *
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    Person read(int id);

    /**
     * Обновляет клиента с заданным ID,
     * в соответствии с переданным клиентом
     *
     * @param person - клиент в соответсвии с которым нужно обновить данные
     * @param id     - id клиента которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Person person, int id);

    /**
     * Удаляет клиента с заданным ID
     *
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(int id);
}
