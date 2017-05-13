package pl.yeloon.magisterium.resolver.statistic;

import pl.yeloon.magisterium.model.MapUserScore;

public class MapUserScoreDTO implements Comparable<MapUserScoreDTO> {

	private Integer batteryLevel;
	private Integer commandCounter;

	public MapUserScoreDTO(MapUserScore mapUserScore) {
		if (mapUserScore != null) {
			this.batteryLevel = mapUserScore.getBatteryLevel();
			this.commandCounter = mapUserScore.getCommandCounter();
		}
	}

	@Override
	public String toString() {
		return "MapUserScoreDTO [batteryLevel=" + batteryLevel + ", commandCounter=" + commandCounter + "]";
	}

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

	@Override
	public int compareTo(MapUserScoreDTO o){
		if (getCommandCounter() < o.getCommandCounter()) {
			return 1; // lepsze
		} else if (getCommandCounter() == o.getCommandCounter()) {
			if (getBatteryLevel() > o.getBatteryLevel()) {
				return 1;
			} else if (getBatteryLevel() == o.getBatteryLevel()) {
				return 0;
			} else
				return -1;
		} else
			return -1;
	}

}
