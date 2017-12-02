package pl.yeloon.magisterium.resolver.statistic;

public class MapUserScoreDTO {

	private Integer batteryLevel;
	private Integer commandCounter;

	public MapUserScoreDTO(Integer batteryLevel, Integer commandCounter) {
		this.batteryLevel = batteryLevel;
		this.commandCounter = commandCounter;
	}

	public Integer getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(Integer batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public Integer getCommandCounter() {
		return commandCounter;
	}

	public void setCommandCounter(Integer commandCounter) {
		this.commandCounter = commandCounter;
	}

}
