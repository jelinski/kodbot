package pl.yeloon.magisterium.controller.bean;

public class MapRankDTO {

	private String username;
	private String imageUrl;
	private int overall;
	private int batteryLevel;
	private int commandCounter;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public int getCommandCounter() {
		return commandCounter;
	}

	public void setCommandCounter(int commandCounter) {
		this.commandCounter = commandCounter;
	}

	@Override
	public String toString() {
		return "MapRankDTO [username=" + username + ", imageUrl=" + imageUrl + ", overall=" + overall + ", batteryLevel=" + batteryLevel + ", commandCounter=" + commandCounter + "]";
	}

	public int getOverall() {
		return overall;
	}

	public void setOverall(int overall) {
		this.overall = overall;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public MapRankDTO(String username, String imageUrl, int overall, int batteryLevel, int commandCounter) {
		this.username = username;
		this.imageUrl = imageUrl;
		this.overall = overall;
		this.batteryLevel = batteryLevel;
		this.commandCounter = commandCounter;
	}
}
