package pl.yeloon.magisterium.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "map")
public class Map extends BaseEntity {

	@NotBlank
	@Size(max = 32)
	private String key;

	@NotBlank
	private String data;

	@Min(0)
	@Max(9)
	@Column(name = "start_row", nullable = false)
	private Integer startRow;

	@Min(0)
	@Max(9)
	@Column(name = "start_col", nullable = false)
	private Integer startCol;

	@Min(0)
	@Max(3)
	@Column(name = "bot_direction", nullable = false)
	private Integer botDirection;

	@Min(0)
	@Max(100)
	@Column(name = "battery_level", nullable = false)
	private Integer batteryLevel;
	
	@Column(name="best_battery_level")
	private Integer bestBatteryLevel;
	
	@Column(name="best_command_counter")
	private Integer bestCommandCounter;
	
	@Column(name="map_slides")
	private String mapSlides;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getStartCol() {
		return startCol;
	}

	public void setStartCol(Integer startCol) {
		this.startCol = startCol;
	}

	public Integer getBotDirection() {
		return botDirection;
	}

	public void setBotDirection(Integer botDirection) {
		this.botDirection = botDirection;
	}

	public Integer getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(Integer batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public Integer getBestBatteryLevel() {
		return bestBatteryLevel;
	}

	public void setBestBatteryLevel(Integer bestBatteryLevel) {
		this.bestBatteryLevel = bestBatteryLevel;
	}

	public Integer getBestCommandCounter() {
		return bestCommandCounter;
	}

	public void setBestCommandCounter(Integer bestCommandCounter) {
		this.bestCommandCounter = bestCommandCounter;
	}

	public String getMapSlides() {
		return mapSlides;
	}

	public void setMapSlides(String mapSlides) {
		this.mapSlides = mapSlides;
	}

	@Override
	public String toString() {
		return "Map [key=" + key + ", data=" + data + ", startRow=" + startRow + ", startCol=" + startCol + ", botDirection=" + botDirection + ", batteryLevel=" + batteryLevel + ", bestBatteryLevel=" + bestBatteryLevel
				+ ", bestCommandCounter=" + bestCommandCounter + ", mapSlides=" + mapSlides + "]";
	}
}
