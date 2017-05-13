package pl.yeloon.magisterium.resolver.statistic;

public class MapRankingInfoDTO {
	private int userPosition;
	private int total;
	
	public int getUserPosition() {
		return userPosition;
	}
	public void setUserPosition(int userPosition) {
		this.userPosition = userPosition;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "MapRankingInfoDTO [userPosition=" + userPosition + ", total=" + total + "]";
	}
	public MapRankingInfoDTO(int userPosition, int total) {
		this.userPosition = userPosition;
		this.total = total;
	}
	public MapRankingInfoDTO() {
		super();
	}
}
