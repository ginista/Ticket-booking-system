package dao;

import entity.Booking;
import entity.Customer;
import entity.Event;
import java.util.ArrayList;
import java.util.List;

public class BookingSystemServiceProviderImpl implements IBookingSystemServiceProvider {
    private ArrayList<Event> events;
    private static BookingSystemRepositoryImpl bookingsystemrepo = new BookingSystemRepositoryImpl();

    public BookingSystemServiceProviderImpl() {
        this.events = new ArrayList<>();
    }


    @Override
    public void calculateBookingCost(int numTickets,String eventName) {
    	bookingsystemrepo.calculateBookingCost(numTickets, eventName);
       
    }

    @Override
    public void bookTickets(String eventName, int numTickets, List<Customer> listOfCustomers) {
    	bookingsystemrepo.bookTickets(eventName, numTickets, listOfCustomers);
    }
    

    @Override
    public void cancelBooking(String eventName, int numTickets) {
    	bookingsystemrepo.cancelBooking(eventName, numTickets);
    }
    

    @Override
    public Booking getBookingDetails(int bookingId) {
        return bookingsystemrepo.getBookingDetails(bookingId);
    }
}