package local.matt.bowlingcms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the place database table.
 * 
 */
@Entity
@NamedQuery(name="Place.findAll", query="SELECT p FROM Place p")
public class Place implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String address;

	@Lob
	private String name;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="place")
	private List<Event> events;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="place")
	private List<Person> persons;

	//bi-directional many-to-one association to City
	@ManyToOne
	private City city;

	//bi-directional many-to-one association to State
	@ManyToOne
	private State state;

	//bi-directional many-to-one association to Zip
	@ManyToOne
	private Zip zip;

	public Place() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setPlace(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setPlace(null);

		return event;
	}

	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Person addPerson(Person person) {
		getPersons().add(person);
		person.setPlace(this);

		return person;
	}

	public Person removePerson(Person person) {
		getPersons().remove(person);
		person.setPlace(null);

		return person;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Zip getZip() {
		return this.zip;
	}

	public void setZip(Zip zip) {
		this.zip = zip;
	}

}