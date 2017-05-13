package pl.yeloon.magisterium.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="map_access_token")
public class MapAccessToken extends BaseEntity {

	@Column(name="user_id", nullable=false)
	private int userId;
	
	@Column(name="map_id", nullable=false)
	private int mapId;
	
	@Column(name="access_token", nullable=false)
	private String accessToken;

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

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public MapAccessToken(){
		super();
	}
	
	public MapAccessToken(int userId, int mapId, String accessToken) {
		this.userId = userId;
		this.mapId = mapId;
		this.accessToken = accessToken;
	}
}
