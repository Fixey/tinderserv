package tinder.tindesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tinder.tindesrv.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
