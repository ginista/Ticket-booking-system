package entity;

class Movie extends Event {
	private String actorName;
	private String actressName;
	private Genre eventGenre;

	private enum Genre {
		ACTION, COMEDY, HORROR
	}

	public Movie() {

	}

	
	public Movie(String actorName, String actressName, Genre eventGenre) {
		super();
		this.actorName = actorName;
		this.actressName = actressName;
		this.eventGenre = eventGenre;
	}

	public void setGenre(Genre genre) {
		this.eventGenre = genre;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public void setActressName(String actressName) {
		this.actressName = actressName;
	}

	public Genre getGenre() {
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