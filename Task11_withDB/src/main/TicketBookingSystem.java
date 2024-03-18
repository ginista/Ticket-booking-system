package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entity.Movie;
import entity.Sports;
import entity.Concert;
import entity.Booking;
import entity.Customer;
import entity.Event;
import dao.BookingSystemServiceProviderImpl;
import dao.EventServiceProviderImpl;
import entity.Venue;

public class TicketBookingSystem {

	private static BookingSystemServiceProviderImpl bookingSystem = new BookingSystemServiceProviderImpl();
	private static EventServiceProviderImpl eventSystem = new EventServiceProviderImpl();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		while (true) {
			System.out.println("\n========== Ticket Booking System ==========");
			System.out.println("1. Create Event");
			System.out.println("2. Book Tickets");
			System.out.println("3. Cancel Tickets");
			System.out.println("4. Get Available Seats");
			System.out.println("5. Get Event Details");
			System.out.println("6. Get Booking Details");
			System.out.println("7. Calculate Booking cost");
			System.out.println("8. Exit");
			System.out.print("Enter your choice: ");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				createEvent();
				break;
			case 2:
				bookTickets();
				break;
			case 3:
				cancelTickets();
				break;
			case 4:
				getAvailableSeats();
				break;
			case 5:
				getEventDetails();
				break;
			case 6:
				getBookingDetails();
				break;
			case 7:
				calculateBookingCost();
				break;
			case 8:
				System.out.println("Exiting...");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please enter a number from 1 to 6.");
			}
		}
	}

	private static void createEvent() {
		Event event = null;
		System.out.println("Enter event name: ");
		String eventName = sc.next();
		System.out.println("Enter event date: ");
		LocalDate eventDate = LocalDate.parse(sc.next());
		System.out.println("Enter event time: ");
		LocalTime eventTime = LocalTime.parse(sc.next());
		System.out.println("Enter total seats: ");
		int totalSeats = sc.nextInt();
		System.out.println("Enter ticket price:");
		double ticketPrice = sc.nextDouble();
		System.out.println("Enter event type[movie,concert,sports]: ");
		String eventType = sc.next();
		System.out.println("Enter venue name:");
		String venueName = sc.next();
		System.out.println("Enter Venue Address: ");
		String address = sc.next();
		System.out.println("Enter venue Id:");
		int venueId = sc.nextInt();

		Venue venue = new Venue(venueId, venueName, address);
		if (eventType.equalsIgnoreCase("movie")) {
			System.out.println("Enter Actor name:");
			String actorName = sc.next();
			System.out.println("Enter Actress name:");
			String actressName = sc.next();
			System.out.println("Enter Genre:");
			String genre = sc.next();
			event = new Movie(eventName, eventDate, eventTime, totalSeats, ticketPrice, eventType, venue, actorName,
					actressName, genre);
		} else if (eventType.equalsIgnoreCase("concert")) {
			System.out.println("Enter Artist:");
			String artist = sc.next();
			System.out.println("Enter Type:");
			String type = sc.next();
			event = new Concert(eventName, eventDate, eventTime, totalSeats, ticketPrice, eventType, venue, artist,
					type);
		} else if (eventType.equalsIgnoreCase("sports")) {
			System.out.println("Enter Sport Name:");
			String sportName = sc.next();
			System.out.println("Enter Team Name:");
			String teamName = sc.next();
			event = new Sports(eventName, eventDate, eventTime, totalSeats, ticketPrice, eventType, venue, sportName,
					teamName);
		}

		Event event1 = eventSystem.createEvent(event);
		System.out.println(event1);
	}

	private static void bookTickets() {
		System.out.println("Enter event name: ");
		String eventName = sc.next();
		System.out.println("Enter number of tickets: ");
		int numTickets = sc.nextInt();
		// Customer details
		boolean isCustomer = true;
		List<Customer> customers = new ArrayList<>();
		while (isCustomer) {
			System.out.println("Add Customer[0 to finish]:");
			int input = sc.nextInt();
			if (input == 0) {
				isCustomer = false;
				break;
			}

			System.out.println("Enter Customer Name:");
			String customerName = sc.next();
			System.out.println("Enter email:");
			String email = sc.next();
			System.out.println("Enter phone number: ");
			int phoneNumber = sc.nextInt();
			Customer customer = new Customer(customerName, email, phoneNumber);
			customers.add(customer);
		}
		bookingSystem.bookTickets(eventName, numTickets, customers);

	}

	private static void cancelTickets() {
		System.out.println("Enter event name: ");
		String eventName = sc.next();
		System.out.println("Enter number of tickets: ");
		int numTickets = sc.nextInt();
		bookingSystem.cancelBooking(eventName, numTickets);
	}

	private static void getAvailableSeats() {

		System.out.println("Enter event name:");
		String eventName = sc.next();
		int availableSeats = eventSystem.getAvailableNoOfTickets(eventName);
		System.out.println("Available seats: " + availableSeats);
	}

	private static void getEventDetails() {
		List<Event> event = eventSystem.getEventDetails();
		System.out.println(event);

	}

	private static void getBookingDetails() {
		try {
			System.out.println("Enter Booking ID:");
			int bookingId = sc.nextInt();
			Booking booking = bookingSystem.getBookingDetails(bookingId);
			if (booking != null) {
				System.out.println(booking);
			}
			// booking.getBookingDate();
		} catch (NullPointerException e) {
			System.out.println("Error: Null pointer exception occurred while fetching booking details.");
			e.printStackTrace();
		}
	}

	private static void calculateBookingCost() {
		System.out.println("Enter number of tickets: ");
		int numTickets = sc.nextInt();
		System.out.println("Enter event name:");
		String eventName = sc.next();
		bookingSystem.calculateBookingCost(numTickets, eventName);

	}
}
