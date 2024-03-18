package entity;

class Concert extends Event {
	private String artist;
    private String type;

	public Concert() {

	}
	public Concert(String artist, String type) {
		super();
		this.artist = artist;
		this.type = type;
	}

	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public void displayEventDetails() {
		System.out.println("Event Name: " + getEventName() + "\nEvent Date: " + getEventDate() + "\nEvent Time:" + getEventTime()
				+ "\nSeats Available:" + getAvailableSeats() + "\nArtist" + artist+"type:"+type);
	}
}