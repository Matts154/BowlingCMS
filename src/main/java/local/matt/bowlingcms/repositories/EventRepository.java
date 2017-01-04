package local.matt.bowlingcms.repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import local.matt.bowlingcms.model.Event;
import local.matt.bowlingcms.model.Place;

@RepositoryRestResource
public interface EventRepository extends JpaRepository<Event, Integer> {

	Collection<Event> findByName(@Param("name") String name);
	Collection<Event> findByEventDate(@Param("d") Date d);
	Collection<Event> findByPlace(@Param("p") Place p);
	
}
