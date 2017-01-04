package local.matt.bowlingcms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the event database table.
 * 
 */
@Entity
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="event_date")
	private Date eventDate;

	private String name;

	//bi-directional many-to-one association to Place
	@ManyToOne
	private Place place;

	//bi-directional many-to-one association to Score
	@OneToMany(mappedBy="event")
	private List<Score> scores;

	public Event() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public List<Score> getScores() {
		return this.scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public Score addScore(Score score) {
		getScores().add(score);
		score.setEvent(this);

		return score;
	}

	public Score removeScore(Score score) {
		getScores().remove(score);
		score.setEvent(null);

		return score;
	}

}