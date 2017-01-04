package local.matt.bowlingcms.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import local.matt.bowlingcms.model.City;
import local.matt.bowlingcms.model.Place;
import local.matt.bowlingcms.model.State;

@RepositoryRestResource
public interface PlaceRepository extends JpaRepository<Place, Integer> {

	Collection<Place> findByCity(@Param("city") City city);
	Collection<Place> findByState(@Param("state") State state);
	Collection<Place> findByName(@Param("name") String name);
	Collection<Place> findByAddress(@Param("address") String address);
}
