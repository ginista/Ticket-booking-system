package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import entity.Booking;
import entity.Concert;
import entity.Customer;
import entity.Event;
import entity.Movie;
import entity.Sports;
import entity.Venue;
import exception.EventNotFoundException;
import exception.InvalidBookingIdException;
import util.DBConnection;

public class BookingSystemRepositoryImpl implements IBookingSystemRepository {

	private Connection connection;
	private static final String GET_ALL_EVENTS = "select * from event as e inner join venue as v on e.venue_id = v.venue_id ;";
	private static final String CREATE_MOVIE = "INSERT INTO event (event_name, event_date, event_time, total_seats, ticket_price, event_type, venue_id,available_seats,actorName,actressName,genre) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
	private static final String CREATE_CONCERT = "INSERT INTO event (event_name, event_date, event_time, total_seats, ticket_price, event_type, venue_id,available_seats,artist,type) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";
	private static final String CREATE_SPORTS = "INSERT INTO event (event_name, event_date, event_time, total_seats, ticket_price, event_type, venue_id,available_seats,sport_name,team_name) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";

	public BookingSystemRepositoryImpl() {
		connection = DBConnection.createConnection();
	}

	@Override
	public Event createEvent(Event event1) {

		Event event = null;
		try {
			String createevent = null;
			if (event1 instanceof Movie) {
				createevent = CREATE_MOVIE;
			} else if (event1 instanceof Concert) {
				createevent = CREATE_CONCERT;
			} else if (event1 instanceof Sports) {
				createevent = CREATE_SPORTS;
			}
			PreparedStatement statement = connection.prepareStatement(createevent);

			statement.setString(1, event1.getEventName());
			statement.setDate(2, Date.valueOf(event1.getEventDate()));
			statement.setTime(3, Time.valueOf(event1.getEventTime()));
			statement.setInt(4, event1.getTotalSeats());
			statement.setDouble(5, event1.getTicketPrice());
			statement.setString(6, event1.getEventType());
			statement.setInt(7, event1.getVenue().getVenueId());
			statement.setInt(8, event1.getTotalSeats());

			if (event1 instanceof Movie) {
				Movie movie = (Movie) event1;
				statement.setString(9, movie.getActorName());
				statement.setString(10, movie.getActressName());
				statement.setString(11, movie.getGenre());
				event = new Movie(event1.getEventName(), event1.getEventDate(), event1.getEventTime(),
						event1.getTotalSeats(), event1.getTicketPrice(), event1.getEventType(), event1.getVenue(),
						movie.getActorName(), movie.getActressName(), movie.getGenre());
			} else if (event1 instanceof Concert) {
				Concert concert = (Concert) event1;
				statement.setString(9, concert.getArtist());
				statement.setString(10, concert.getType());

				event = new Concert(event1.getEventName(), event1.getEventDate(), event1.getEventTime(),
						event1.getTotalSeats(), event1.getTicketPrice(), event1.getEventType(), event1.getVenue(),
						concert.getArtist(), concert.getType());
			} else if (event1 instanceof Sports) {
				Sports sport = (Sports) event1;
				statement.setString(9, sport.getSportName());
				statement.setString(10, sport.getTeamName());

				event = new Sports(event1.getEventName(), event1.getEventDate(), event1.getEventTime(),
						event1.getTotalSeats(), event1.getTicketPrice(), event1.getEventType(), event1.getVenue(),
						sport.getSportName(), sport.getTeamName());
			}
			statement.executeUpdate();

		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return event;
	}

	@Override
	public List<Event> getEventDetails() {
		List<Event> events = new ArrayList<>();
		Event event = null;
		try {

			PreparedStatement statement = connection.prepareStatement(GET_ALL_EVENTS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int eventId = resultSet.getInt("event_id");
				String eventName = resultSet.getString("event_name");
				LocalDate eventDate = resultSet.getDate("event_date").toLocalDate();
				LocalTime eventTime = resultSet.getTime("event_time").toLocalTime();
				int venueId = resultSet.getInt("venue_Id");
				int totalSeats = resultSet.getInt("total_seats");
				int availableSeats = resultSet.getInt("available_seats");
				int ticketPrice = resultSet.getInt("ticket_price");
				String eventType = resultSet.getString("event_type");
				String venueName = resultSet.getString("venue_name");
				String address = resultSet.getString("address");

				Venue venue = new Venue(venueId, venueName, address);
				event = new Event(eventName, eventDate, eventTime, totalSeats, ticketPrice, eventType, venue);
				events.add(event);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return events;
	}

	@Override
	public int getAvailableNoOfTickets(String eventName) {
		int availableSeats = 0;
		try {
			// Query to retrieve available seats for the specified event
			String query = "SELECT available_seats FROM event WHERE event_name = ?";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, eventName);
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					availableSeats = resultSet.getInt("available_seats");
				}
			}
		} catch (SQLException e) {
			// Handle SQL exceptions
			e.printStackTrace();
		}
		return availableSeats;
	}

	@Override
	public void calculateBookingCost(int numTickets, String eventName) {
		double ticketPrice = fetchTicketPrice(eventName);
		double totalCost = numTickets * ticketPrice;
		System.out.println("Total cost of booking for " + numTickets + " tickets: " + totalCost);
	}

	public double fetchTicketPrice(String eventName) {
		double ticketPrice = 0.0;
		try {
			String query = "SELECT ticket_price FROM event WHERE event_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, eventName);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				ticketPrice = resultSet.getDouble("ticket_price");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticketPrice;
	}

	@Override
	public void bookTickets(String eventName, int numTickets, List<Customer> listOfCustomers) {
	    try {
	        Event event = fetchEventDetails(eventName);

	        if (event != null) {
	            if (event.getAvailableSeats() >= numTickets) {
	                int newAvailableSeats = event.getAvailableSeats() - numTickets;
	                updateAvailableSeats(eventName, newAvailableSeats);

	                for (Customer customer : listOfCustomers) {
	                    createCustomerRecord(customer);
	                }

	                System.out.println(numTickets + " tickets booked successfully for the event: " + eventName);
	            } else {
	                System.out.println("Not enough available seats for booking " + numTickets + " tickets.");
	            }
	        } else {
	            System.out.println("Event with name " + eventName + " not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (EventNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
	}


	// Method to retrieve event details from the database based on the event name
	private Event fetchEventDetails(String eventName) throws SQLException,EventNotFoundException {
		Event event = null;
		String query = "SELECT * FROM event WHERE event_name = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, eventName);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {

				int eventId = resultSet.getInt("event_id");
				LocalDate eventDate = resultSet.getDate("event_date").toLocalDate();
				LocalTime eventTime = resultSet.getTime("event_time").toLocalTime();
				int venueId = resultSet.getInt("venue_Id");
				int totalSeats = resultSet.getInt("total_seats");
				int availableSeats = resultSet.getInt("available_seats");
				int ticketPrice = resultSet.getInt("ticket_price");
				String eventType = resultSet.getString("event_type");

				event = new Event(eventName, eventDate, eventTime, totalSeats, ticketPrice, eventType);
			} else {
				throw new EventNotFoundException("Event not found with name: " + eventName);
			}
		}
		return event;
	}

	// Method to update available seats in the events table
	private void updateAvailableSeats(String eventName, int newAvailableSeats) throws SQLException {
		String query = "UPDATE event SET available_seats = ? WHERE event_name = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, newAvailableSeats);
			statement.setString(2, eventName);
			statement.executeUpdate();
		}
	}

	// Method to insert customer details into the database
	private void createCustomerRecord(Customer customer) throws SQLException {
		String query = "INSERT INTO customer (customer_name, email, phone_number) VALUES (?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, customer.getCustomerName());
			statement.setString(2, customer.getEmail());
			statement.setInt(3, customer.getPhoneNumber());
			statement.executeUpdate();
		}
	}

	@Override
	public void cancelBooking(String eventName, int numTickets) {
		try {
			// Query to update the available seats by increasing them
			String query = "UPDATE event SET available_seats = available_seats + ? WHERE event_name = ?";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setInt(1, numTickets);
				statement.setString(2, eventName);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Booking getBookingDetails(int bookingId) {
		Booking booking = null;
		try {
			String query = "SELECT * FROM booking WHERE booking_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, bookingId);
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next()) {
	            throw new InvalidBookingIdException("Booking ID not found: " + bookingId);
	        }

	        // Reset cursor to before the first row
	        resultSet.beforeFirst();
			while (resultSet.next()) {
				int totalCost = resultSet.getInt("total_cost");
				LocalDate bookingDate = resultSet.getDate("booking_date").toLocalDate();
				booking = new Booking(bookingId, totalCost, bookingDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(InvalidBookingIdException e) {
			System.out.println(e.getMessage());
		}
		return booking;
	}

}
