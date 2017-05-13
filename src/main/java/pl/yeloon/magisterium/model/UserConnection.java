package pl.yeloon.magisterium.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "userconnection")
public class UserConnection {

	@Id
	@NotNull
	@Size(max = 255)
	@Column(name = "userid", nullable = false, length = 255)
	private String userId;

	@NotNull
	@Size(max = 255)
	@Column(name = "providerid", nullable = false, length = 255)
	private String providerId;

	@NotNull
	@Size(max = 255)
	@Column(name = "provideruserid", nullable = false, length = 255)
	private String providerUserId;

	@NotNull
	@Column(nullable = false)
	private Integer rank;

	@Size(max = 255)
	@Column(name = "displayname", length = 255)
	private String displayName;

	@Size(max = 512)
	@Column(name = "profileurl", length = 512)
	private String profileUrl;

	@Size(max = 512)
	@Column(name = "imageurl", length = 512)
	private String imageUrl;

	@NotNull
	@Size(max = 255)
	@Column(name = "accesstoken", nullable = false, length = 255)
	private String accessToken;

	@Size(max = 255)
	@Column(name = "secret", length = 255)
	private String secret;

	@Size(max = 255)
	@Column(name = "refreshtoken", length = 255)
	private String refreshToken;

	@Column(name = "expiretime")
	private Long expireTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	@Override
	public String toString() {
		return "UserConnection [userId=" + userId + ", providerId=" + providerId + ", providerUserId=" + providerUserId + ", rank=" + rank + ", displayName=" + displayName + ", profileUrl=" + profileUrl + ", imageUrl=" + imageUrl
				+ ", accessToken=" + accessToken + ", secret=" + secret + ", refreshToken=" + refreshToken + ", expireTime=" + expireTime + "]";
	}

}
