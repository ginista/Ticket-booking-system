package dao;

import java.util.List;
import entity.Booking;
import entity.Customer;
import entity.Event;

public interface IBookingSystemRepository {

	Event createEvent(Event event);

	List<Event> getEventDetails();

	int getAvailableNoOfTickets(String eventName);

	void calculateBookingCost(int numTickets, String eventName);

	void bookTickets(String eventName, int numTickets, List<Customer> listOfCustomers);

	void cancelBooking(String eventName, int numTickets);

	Booking getBookingDetails(int bookingId);

}
