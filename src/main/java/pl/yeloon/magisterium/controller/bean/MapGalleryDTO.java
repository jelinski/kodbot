package pl.yeloon.magisterium.controller.bean;

import pl.yeloon.magisterium.resolver.statistic.MapRankingInfoDTO;

public class MapGalleryDTO implements Comparable<MapGalleryDTO> {

	private int id;
	private boolean finished;
	private String gameUrl;
	private String imageUrl;
	private Integer userScore;
	private Integer bestScore;
	private Integer userBatteryLevel;
	private Integer bestBatteryLevel;
	private MapRankingInfoDTO mapRankingInfo;

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

	public Integer getUserScore() {
		return userScore;
	}

	public void setUserScore(Integer userScore) {
		this.userScore = userScore;
	}

	public Integer getBestScore() {
		return bestScore;
	}

	public void setBestScore(Integer bestScore) {
		this.bestScore = bestScore;
	}

	public Integer getUserBatteryLevel() {
		return userBatteryLevel;
	}

	public void setUserBatteryLevel(Integer userBatteryLevel) {
		this.userBatteryLevel = userBatteryLevel;
	}

	public Integer getBestBatteryLevel() {
		return bestBatteryLevel;
	}

	public void setBestBatteryLevel(Integer bestBatteryLevel) {
		this.bestBatteryLevel = bestBatteryLevel;
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

	public boolean getFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public MapRankingInfoDTO getMapRankingInfo() {
		return mapRankingInfo;
	}

	public void setMapRankingInfo(MapRankingInfoDTO mapRankingInfo) {
		this.mapRankingInfo = mapRankingInfo;
	}

	@Override
	public String toString() {
		return "MapGalleryDTO [id=" + id + ", finished=" + finished + ", gameUrl=" + gameUrl + ", imageUrl=" + imageUrl + ", userScore=" + userScore + ", bestScore=" + bestScore + ", userBatteryLevel=" + userBatteryLevel
				+ ", bestBatteryLevel=" + bestBatteryLevel + ", mapRankingInfo=" + mapRankingInfo + "]";
	}

}
