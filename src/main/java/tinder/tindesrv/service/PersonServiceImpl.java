package tinder.tindesrv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinder.tindesrv.entity.Person;
import tinder.tindesrv.enums.CrushType;
import tinder.tindesrv.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Создает нового клиента
     *
     * @param person - клиент для создания
     */
    @Override
    public void create(Person person) {
        personRepository.save(person);
    }

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    @Override
    public List<Person> readAll() {
        return personRepository.findAll();
    }

    /**
     * Возвращает клиента по его ID
     *
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    @Override
    public Person read(int id) {
        return personRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    /**
     * Обновляет клиента с заданным ID,
     * в соответствии с переданным клиентом
     *
     * @param person - клиент в соответсвии с которым нужно обновить данные
     * @param id     - id клиента которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    @Override
    public boolean update(Person person, int id) {
        if (personRepository.existsById(id)) {
            person.setId(id);
            personRepository.save(person);
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
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Возвращает список клиентов по списку id
     *
     * @param idList список id клиентов
     * @return List<Person> список клиентов по списку
     */
    public List<Person> getPersonsByListId(Set<Integer> idList) {
        return personRepository.findByIdIn(idList);
    }

    /**
     * Возвращает список клиентов которые ищут пару по гендеру
     *
     * @param person пол по которому осуществляется поиск
     * @return List<Person> список клиентов по списку
     */
    public List<Person> getPersonsByGender(Person person) {
        List<String> crushTypeList = new ArrayList<>();
        CrushType personCrush = CrushType.valueOf(person.getCrush());
        if (personCrush.equals(CrushType.ALL)) {
            crushTypeList.add(CrushType.MEN.name());
            crushTypeList.add(CrushType.WOMEN.name());
        } else {
            crushTypeList.add(person.getCrush());
        }
        return personRepository.findByGender(crushTypeList, person.getGender());
    }
}