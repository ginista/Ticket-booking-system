package dao;

import entity.Event;
import entity.Venue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IEventServiceProvider {
	
	Event createEvent(String eventName, LocalDate date, LocalTime time, int totalSeats, double ticketPrice,
			String eventType, Venue venue);
	List<Event> getEventDetails();

	int getAvailableNoOfTickets();

	
}
