package tinder.tindesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tinder.tindesrv.entity.Person;

import java.util.List;
import java.util.Set;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByIdIn(Set<Integer> idList);
}
