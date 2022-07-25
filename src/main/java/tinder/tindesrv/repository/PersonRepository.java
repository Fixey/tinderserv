package tinder.tindesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tinder.tindesrv.entity.Person;

import java.util.List;
import java.util.Set;

public interface PersonRepository extends JpaRepository<Person, Long> {
    /**
     * Поиск нескольких клиентов по id
     *
     * @param idSet Set<Long> сет id клиентов
     * @return List<PersonDto> список клиентов
     */
    List<Person> findByIdIn(Set<Long> idSet);

    /**
     * Поиск связей клиентов по гендерному признаку
     *
     * @param crushTypeList Лист полов клиентов, которых ищет пользователь
     * @param crushType     тип клиентов, которых ищут клиенты
     * @return List<PersonDto> список клиентов
     */
    @Query(value = "select p from Person p where p.gender in ?1 " +
            "and (p.crush = ?2 or p.crush = 'ALL')")
    List<Person> findByGender(List<String> crushTypeList, String crushType);
}
