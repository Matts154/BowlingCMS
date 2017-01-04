package local.matt.bowlingcms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the zip database table.
 * 
 */
@Entity
@NamedQuery(name="Zip.findAll", query="SELECT z FROM Zip z")
public class Zip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int zipcode;

	//bi-directional many-to-one association to Place
	@OneToMany(mappedBy="zip")
	private List<Place> places;

	public Zip() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public List<Place> getPlaces() {
		return this.places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public Place addPlace(Place place) {
		getPlaces().add(place);
		place.setZip(this);

		return place;
	}

	public Place removePlace(Place place) {
		getPlaces().remove(place);
		place.setZip(null);

		return place;
	}

}