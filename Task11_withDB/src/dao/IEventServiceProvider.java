package dao;

import entity.Event;
import entity.Venue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IEventServiceProvider {

	Event createEvent(Event event);

	List<Event> getEventDetails();

	int getAvailableNoOfTickets(String eventName);

}
