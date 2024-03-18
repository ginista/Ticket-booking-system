package entity;

public class Customer {
	private String customerName;
	private String email;
	private int phoneNumber;

	public Customer() {

	}

	public Customer(String customerName, String email, int phoneNumber) {
		this.customerName = customerName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getEmail() {
		return email;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void displayCustomerDetails() {
		System.out.println(customerName + " " + email + " " + phoneNumber);
	}
}