package dao;
import java.util.List;
import entity.Customer;

public interface IBookingSystemServiceProvider {

	/*
	 * calculate_booking_cost(num_tickets): Calculate and set the total cost of the
	 * booking. • book_tickets(eventname:str, num_tickets, arrayOfCustomer): Book a
	 * specified number of tickets for an event. for each tickets customer object
	 * should be created and stored in array also should update the attributes of
	 * Booking class. • cancel_booking(booking_id): Cancel the booking and update
	 * the available seats. • get_booking_details(booking_id):get the booking
	 * details.
	 */

	void calculateBookingCost(int numTickets);

	void bookTickets(String eventName, int numTickets, List<Customer> customers);

	void cancelBooking(String eventName, int numTickets);

	void getBookingDetails(int bookingId);
}
