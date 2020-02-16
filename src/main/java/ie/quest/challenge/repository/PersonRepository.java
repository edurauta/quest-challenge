package ie.quest.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ie.quest.challenge.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	Person findByPps(String pps);
}
