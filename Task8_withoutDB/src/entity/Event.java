package entity;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
	private String eventName;
	private LocalDate eventDate;
	private LocalTime eventTime;
	private int totalSeats;
	private int availableSeats;
	private double ticketPrice;
	private String eventType;
	private Venue venue;

	public Event() {

	}

	public Event(String eventName, LocalDate eventDate, LocalTime eventTime, int totalSeats,
			double ticketPrice, String eventType,Venue venue) {
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.totalSeats = totalSeats;
		this.availableSeats = totalSeats;
		this.ticketPrice = ticketPrice;
		this.eventType = eventType;
		this.venue = venue;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public void setEventTime(LocalTime eventTime) {
		this.eventTime = eventTime;
	}

	
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getEventName() {
		return eventName;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public LocalTime getEventTime() {
		return eventTime;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	public String getEventType() {
		return eventType;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	@Override
	public String toString() {
		return "Event [eventName=" + eventName + ", eventDate=" + eventDate + ", eventTime=" + eventTime
				+ ", totalSeats=" + totalSeats + ", availableSeats=" + availableSeats + ", ticketPrice=" + ticketPrice
				+ ", eventType=" + eventType + ", venue=" + venue + "]";
	}



}