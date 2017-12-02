package pl.yeloon.magisterium.model;

public class GameMap {

	private final String key;

	private final String data;

	private final Integer startRow;

	private final Integer startCol;

	private final Integer botDirection;

	private final Integer batteryLevel;
	
	private String mapSlides;

	public GameMap(String key, Integer startRow, Integer startCol, Integer botDirection, Integer batteryLevel, String mapSlides, String data) {
		this.key = key;
		this.data = data;
		this.startRow = startRow;
		this.startCol = startCol;
		this.botDirection = botDirection;
		this.batteryLevel = batteryLevel;
		this.mapSlides = mapSlides;
	}

	public String getKey() {
		return key;
	}

	public String getData() {
		return data;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public Integer getStartCol() {
		return startCol;
	}

	public Integer getBotDirection() {
		return botDirection;
	}

	public Integer getBatteryLevel() {
		return batteryLevel;
	}

	public String getMapSlides() {
		return mapSlides;
	}

}
