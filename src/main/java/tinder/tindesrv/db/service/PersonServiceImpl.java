package tinder.tindesrv.db.service;

import org.springframework.stereotype.Service;
import tinder.tindesrv.entity.Person;
import tinder.tindesrv.repository.PersonRepository;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void create(Person person) {
        personRepository.save(person);
    }

    @Override
    public List<Person> readAll() {
        return personRepository.findAll();
    }

    @Override
    public Person read(int id) {
        return personRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean update(Person person, int id) {
        if (personRepository.existsById(id)) {
            person.setId(id);
            personRepository.save(person);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }
}