package entity;

import java.time.LocalDate;

public class Booking {
	int bookingId;
	int totalCost;
	LocalDate bookingDate;

	public Booking(int bookingId, int totalCost, LocalDate bookingDate) {
		super();
		this.bookingId = bookingId;
		this.totalCost = totalCost;
		this.bookingDate = bookingDate;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", totalCost=" + totalCost + ", bookingDate=" + bookingDate + "]";
	}

}
