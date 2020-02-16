package ie.quest.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.quest.challenge.model.Person;
import ie.quest.challenge.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person findByPps(String pps){
        return personRepository.findByPps(pps);
    }

    public Person save(Person register){
        return personRepository.save(register);
    }

	public List<Person> findAll() {
		return personRepository.findAll();
	}

}
