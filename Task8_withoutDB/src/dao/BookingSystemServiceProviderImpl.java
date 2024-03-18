package dao;

import entity.Customer;
import entity.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import entity.Venue;

public class BookingSystemServiceProviderImpl implements IBookingSystemServiceProvider {
    private IEventServiceProvider eventServiceProvider;

    public BookingSystemServiceProviderImpl() {
        this.eventServiceProvider = new EventServiceProviderImpl();
    }


    public List<Event> getEventDetails() {
        return eventServiceProvider.getEventDetails();
    }

    public int getAvailableNoOfTickets(String eventName) {
        for (Event event : eventServiceProvider.getEventDetails()) {
            if (event.getEventName().equals(eventName)) {
                return event.getAvailableSeats();
            }
        }
        // If the event with the specified name is not found, return -1 or throw an exception
        return -1;
    }

    @Override
    public void calculateBookingCost(int numTickets) {
        // Implementation to calculate and set the total cost of the booking
    	 double totalCost = numTickets * eventServiceProvider.getEventDetails().get(0).getTicketPrice(); // Assuming only one event is available
         System.out.println("Total cost of booking for " + numTickets + " tickets: $" + totalCost);
    }

    @Override
    public void bookTickets(String eventName, int numTickets, List<Customer> listOfCustomers) {
        // Implementation to book tickets for an event
        for (Event event : eventServiceProvider.getEventDetails()) {
            if (event.getEventName().equals(eventName)) {
                if (event.getAvailableSeats() >= numTickets) {
                    // Creating customer objects and adding them to the list
                    for (int i = 0; i < numTickets; i++) {
                        Customer customer = new Customer("Customer " + (i + 1), "customer" + (i + 1) + "@example.com", 1234567890);
                        listOfCustomers.add(customer);
                    }
                    // Reduce available seats
                    event.setAvailableSeats(event.getAvailableSeats() - numTickets);
                    System.out.println("Successfully booked " + numTickets + " tickets for the event: " + eventName);
                    return;
                } else {
                    System.out.println("Not enough available seats for booking " + numTickets + " tickets.");
                    return;
                }
            }
        }
        System.out.println("Event not found: " + eventName);
    }
    

    @Override
    public void cancelBooking(String eventName, int numTickets) {
        // Implementation to book tickets for an event
        for (Event event : eventServiceProvider.getEventDetails()) {
            if (event.getEventName().equals(eventName)) {
                    // Add available seats
                    event.setAvailableSeats(event.getAvailableSeats() + numTickets);
                    System.out.println("Successfully cancelled " + numTickets + " tickets for the event: " + eventName);
                    return;
                } 
            }
        
        System.out.println("Event not found: " + eventName);
    }
    

    @Override
    public void getBookingDetails(int bookingId) {
        // Implementation to get booking details
    	   System.out.println("Booking details for ID " + bookingId + ":");
    }
}