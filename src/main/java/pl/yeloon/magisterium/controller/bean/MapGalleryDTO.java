package pl.yeloon.magisterium.controller.bean;

public class MapGalleryDTO implements Comparable<MapGalleryDTO> {

	private int id;
	private String gameUrl;
	private String imageUrl;
	private Integer userBatteryLevel;

	public String getGameUrl() {
		return gameUrl;
	}

	public void setGameUrl(String gameUrl) {
		this.gameUrl = gameUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getUserBatteryLevel() {
		return userBatteryLevel;
	}

	public void setUserBatteryLevel(Integer userBatteryLevel) {
		this.userBatteryLevel = userBatteryLevel;
	}

	@Override
	public int compareTo(MapGalleryDTO o) {
		return Integer.compare(id, o.id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
