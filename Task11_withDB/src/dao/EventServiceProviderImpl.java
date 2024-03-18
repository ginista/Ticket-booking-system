package dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import entity.Event;
import entity.Venue;

public class EventServiceProviderImpl implements IEventServiceProvider {

	private static BookingSystemRepositoryImpl bookingsystemrepo = new BookingSystemRepositoryImpl();

	public EventServiceProviderImpl() {

	}

	@Override
	public Event createEvent(Event event) {
		return bookingsystemrepo.createEvent(event);
	}

	@Override
	public List<Event> getEventDetails() {
		return bookingsystemrepo.getEventDetails();
	}

	@Override
	public int getAvailableNoOfTickets(String eventName) {
		return bookingsystemrepo.getAvailableNoOfTickets(eventName);
	}
}
