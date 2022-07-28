package tinder.tindesrv.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinder.tindesrv.dto.PersonDto;
import tinder.tindesrv.entity.Person;
import tinder.tindesrv.enums.CrushTypeEnum;
import tinder.tindesrv.exceptions.NotFountException;
import tinder.tindesrv.mapping.PersonMapper;
import tinder.tindesrv.repository.PersonCrushRepository;
import tinder.tindesrv.repository.PersonRepository;
import tinder.tindesrv.service.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonCrushRepository personCrushRepository;
    private final PersonMapper personMapper;


    /**
     * Создает нового клиента
     *
     * @param personDto - клиент для создания
     */
    @Override
    public PersonDto create(PersonDto personDto) {
        Person person = personMapper.fromDto(personDto);
        Person savingPerson = personRepository.save(person);
        personMapper.toDto(savingPerson);
        return personMapper.toDto(savingPerson);
    }

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    @Override
    public List<PersonDto> readAll() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает клиента по его ID
     *
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    @Override
    public PersonDto read(Long id) {
        return personRepository
                .findById(id)
                .map(personMapper::toDto)
                .orElseThrow(NotFountException::new);
    }

    /**
     * Удаляет клиента с заданным ID
     *
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    @Override
    public boolean delete(Long id) {
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
    public List<PersonDto> getPersonsByListId(Set<Long> idList) {
        return personRepository
                .findByIdIn(idList)
                .stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список клиентов которые ищут пару по гендеру
     *
     * @param person пол по которому осуществляется поиск
     * @return List<Person> список клиентов по списку
     */
    public List<PersonDto> getPersonsByGender(PersonDto person) {
        List<String> crushTypeList = new ArrayList<>();
        CrushTypeEnum personCrush = CrushTypeEnum.valueOf(person.getCrush());
        if (personCrush.equals(CrushTypeEnum.ALL)) {
            crushTypeList.add(CrushTypeEnum.MEN.name());
            crushTypeList.add(CrushTypeEnum.WOMEN.name());
        } else {
            crushTypeList.add(person.getCrush());
        }
        return personRepository
                .findByGender(crushTypeList, person.getGender())
                .stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Список любимцев, которые нравились клиенту, выбрали клиента или был взаимный выбор.
     *
     * @param userId клиента
     * @return Список любимцев
     */
    public List<PersonDto> getPersonsForLovers(Long userId) {
        return personRepository.getLovers(userId)
                .stream()
                .filter(person -> !person.getId().equals(userId))
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }
}