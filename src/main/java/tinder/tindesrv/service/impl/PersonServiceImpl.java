package tinder.tindesrv.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinder.tindesrv.entity.Person;
import tinder.tindesrv.entity.PersonCrush;
import tinder.tindesrv.enums.CrushTypeEnum;
import tinder.tindesrv.repository.PersonCrushRepository;
import tinder.tindesrv.repository.PersonRepository;
import tinder.tindesrv.service.PersonService;
import tinder.tindesrv.service.dto.PersonDto;
import tinder.tindesrv.service.mapping.PersonMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    private final PersonCrushRepository personCrushRepository;
    private final PersonMapper personMapper;


    /**
     * Создает нового клиента
     *
     * @param personDto - клиент для создания
     */
    @Override
    public void create(PersonDto personDto) {
        Person person = new Person()
                .builder()
                .id(personDto.getId())
                .fullName(personDto.getFullName())
                .gender(personDto.getGender())
                .crush(personDto.getCrush())
                .birthdate(personDto.getBirthdate())
                .description(personDto.getDescription())
                .build();
        personRepository.save(person);
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
                .orElseThrow(RuntimeException::new);
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
        Set<Long> usersSet = personCrushRepository.findByUserIdAndIdNotOrCrushId(userId, userId, userId)
                .stream().map(PersonCrush::getId).collect(Collectors.toSet());
        return personRepository.findByIdIn(usersSet)
                .stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }
}