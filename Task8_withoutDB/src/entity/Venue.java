package entity;

public class Venue {
	

	private String venueName;
	private String address;

	public Venue() {

	}

	public Venue(String venueName, String address) {
		super();
		this.venueName = venueName;
		this.address = address;
	}
	
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVenueName() {
		return venueName;
	}

	public String getAddress() {
		return address;
	}

	public void displayVenueDetails() {
		System.out.println("Venue Name: " + venueName + "\nAddress: " + address);
	}
}