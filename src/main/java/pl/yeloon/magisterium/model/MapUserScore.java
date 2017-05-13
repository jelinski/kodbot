package pl.yeloon.magisterium.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="map_user_score")
public class MapUserScore extends BaseEntity{

	@Column(name="user_id", nullable=false)
	private int userId;
	
	@Column(name="map_id", nullable=false)
	private int mapId;
	
	@Column(name="battery_level", nullable=false)
	private int batteryLevel;
	
	@Column(name="command_counter", nullable=false)
	private int commandCounter;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id", insertable=false, updatable=false ) //readonly mapping
	private User user;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
