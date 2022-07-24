package tinder.tindesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tinder.tindesrv.entity.Person;

import java.util.List;
import java.util.Set;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByIdIn(Set<Integer> idList);

    @Query(value = "select p from Person p where p.gender in ?1 " +
            "and (p.crush = ?2 or p.crush = 'ALL')")
    List<Person> findByGender(List<String> crushTypeList, String crushType);
}
