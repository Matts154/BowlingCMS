package local.matt.bowlingcms.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the scores database table.
 * 
 */
@Entity
@Table(name="scores")
// @NamedQuery(name="Score.findAll", query="SELECT s FROM Score s")
public class Score implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int game1;

	private int game2;

	private int game3;

	//bi-directional many-to-one association to Event
	@ManyToOne
	private Event event;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	public Score() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGame1() {
		return this.game1;
	}

	public void setGame1(int game1) {
		this.game1 = game1;
	}

	public int getGame2() {
		return this.game2;
	}

	public void setGame2(int game2) {
		this.game2 = game2;
	}

	public int getGame3() {
		return this.game3;
	}

	public void setGame3(int game3) {
		this.game3 = game3;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}