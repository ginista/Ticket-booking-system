package dao;

import java.util.List;

import entity.Booking;
import entity.Customer;

public interface IBookingSystemServiceProvider {

	void bookTickets(String eventName, int numTickets, List<Customer> customers);

	void cancelBooking(String eventName, int numTickets);

	Booking getBookingDetails(int bookingId);

	void calculateBookingCost(int numTickets, String eventName);
}
