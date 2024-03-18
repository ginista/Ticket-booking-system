package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie extends Event {

	private String actorName;
	private String actressName;
	private String eventGenre;

	public Movie() {

	}

	public Movie(String eventName, LocalDate eventDate, LocalTime eventTime, int totalSeats, double ticketPrice,
			String eventType, Venue venue, String actorName, String actressName, String eventGenre) {
		super(eventName, eventDate, eventTime, totalSeats, ticketPrice, eventType, venue);
		this.actorName = actorName;
		this.actressName = actressName;
		this.eventGenre = eventGenre;
	}

	public Movie(String actorName, String actressName, String eventGenre) {
		super();
		this.actorName = actorName;
		this.actressName = actressName;
		this.eventGenre = eventGenre;
	}

	public void setGenre(String genre) {
		this.eventGenre = genre;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public void setActressName(String actressName) {
		this.actressName = actressName;
	}

	public String getGenre() {
		return eventGenre;
	}

	public String getActorName() {
		return actorName;
	}

	public String getActressName() {
		return actressName;
	}

	public void displayEventDetails() {
		System.out.println("Event Name: " + getEventName() + "\nEvent Date: " + getEventDate() + "\nEvent Time:"
				+ getEventTime() + "\nSeats Available:" + getAvailableSeats() + "\nGenre" + eventGenre);
	}

}