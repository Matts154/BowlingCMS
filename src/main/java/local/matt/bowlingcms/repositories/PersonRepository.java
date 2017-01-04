package local.matt.bowlingcms.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import local.matt.bowlingcms.model.Person;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, Integer> {

	Collection<Person> findByFirstName(@Param("fname") String fname);
	Collection<Person> findByLastName(@Param("lname") String lname);
	
}
