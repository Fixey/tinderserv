package tinder.tindesrv.service.impl;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinder.tindesrv.dto.PersonDto;
import tinder.tindesrv.entity.Person;
import tinder.tindesrv.enums.CrushTypeEnum;
import tinder.tindesrv.enums.GenderTypeEnum;
import tinder.tindesrv.exceptions.NotFoundException;
import tinder.tindesrv.mapping.PersonMapper;
import tinder.tindesrv.repository.PersonRepository;
import tinder.tindesrv.service.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

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
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    @Override
    public @NotNull List<PersonDto> readAll() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список клиентов, которые ищут пару по гендеру
     *
     * @param id клиента
     * @return List<Person> список клиентов по списку
     */
    public List<PersonDto> getPersonsByGender(Long id) {
        PersonDto person = read(id);
        List<GenderTypeEnum> crushTypeList = new ArrayList<>();
        if (person.getCrush().equals(CrushTypeEnum.ALL)) {
            crushTypeList.add(GenderTypeEnum.MEN);
            crushTypeList.add(GenderTypeEnum.WOMEN);
        } else {
            crushTypeList.add(GenderTypeEnum.valueOf(person.getCrush().name()));
        }
        return personRepository
                .findByGender(crushTypeList, CrushTypeEnum.valueOf(person.getGender().name()))
                .stream()
                .map(per -> per.orElseThrow(NotFoundException::new))
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
        return personRepository.getLovers(userId, userId)
                .stream()
                .map(person -> person.orElseThrow(NotFoundException::new))
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Создает нового клиента
     *
     * @param personDto - клиент для создания
     */
    @Override
    public PersonDto upsert(PersonDto personDto) {
        Person person = personMapper.toEntity(personDto);
        Person savingPerson = personRepository.save(person);
        return personMapper.toDto(savingPerson);
    }

    /**
     * Удаляет клиента с заданным ID
     *
     * @param id - id клиента, которого нужно удалить
     */
    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}