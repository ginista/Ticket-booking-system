package entity;

class Sports extends Event {
	private String sportName;
	private String teamName;

	public Sports() {

	}

	public Sports(String sportName, String teamName) {
		super();
		this.sportName = sportName;
		this.teamName = teamName;
	}

	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public void displayEventDetails() {
		System.out.println("Event Name: " + getEventName() + "\nEvent Date: " + getEventDate() + "\nEvent Time:"
				+ getEventTime() + "\nSeats Available:" + getAvailableSeats() + "Sport Name:" + sportName
				+ "\nteam Name: " + teamName);
	}
}