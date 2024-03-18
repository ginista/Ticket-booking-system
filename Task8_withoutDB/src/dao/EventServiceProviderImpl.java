package dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import entity.Event;
import entity.Venue;

public class EventServiceProviderImpl implements IEventServiceProvider {
    private static List<Event> events = new ArrayList<>();

    public EventServiceProviderImpl() {
    }

    @Override
    public Event createEvent(String eventName, LocalDate date, LocalTime time, int totalSeats, double ticketPrice, String eventType, Venue venue) {
        Event event = new Event(eventName, date, time, totalSeats, ticketPrice, eventType, venue);
        events.add(event);
        return event;
    }

    @Override
    public List<Event> getEventDetails() {
        return events;
    }

    @Override
    public int getAvailableNoOfTickets() {
        int availableTickets = 0;
        for (Event event : events) {
            availableTickets += event.getAvailableSeats();
        }
        return availableTickets;
    }
}
