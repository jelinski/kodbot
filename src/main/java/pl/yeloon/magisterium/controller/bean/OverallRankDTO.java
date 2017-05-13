package pl.yeloon.magisterium.controller.bean;

public class OverallRankDTO {

	private String username;
	private String imageUrl;
	private int overall;
	private int overallScore;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getOverall() {
		return overall;
	}
	public void setOverall(int overall) {
		this.overall = overall;
	}
	public int getOverallScore() {
		return overallScore;
	}
	public void setOverallScore(int overallScore) {
		this.overallScore = overallScore;
	}
	@Override
	public String toString() {
		return "OverallRankDTO [username=" + username + ", imageUrl=" + imageUrl + ", overall=" + overall + ", overallScore=" + overallScore + "]";
	}
	public OverallRankDTO(String username, String imageUrl, int overall, int overallScore) {
		this.username = username;
		this.imageUrl = imageUrl;
		this.overall = overall;
		this.overallScore = overallScore;
	}
	
}
