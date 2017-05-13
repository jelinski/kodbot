package pl.yeloon.magisterium.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "kodbot_user")
public class User extends BaseEntity {

	@Email
	@NotBlank
	@Size(min = 6, max = 100)
	private String email;

	@NotBlank
	@Size(min = 6)
	private String password;

	@Size(max = 32)
	@Column(name = "first_name")
	private String firstName;

	@Size(max = 32)
	@Column(name = "last_name")
	private String lastName;

	@Size(max = 32)
	@Column(name = "nickname", nullable = false)
	private String nickname;

	@Column(name = "referer_id", nullable = true)
	private Integer refererId;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private UserStatistic statistic;

	@NotNull
	private Boolean enabled;

	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "email", referencedColumnName = "userid", updatable = false, insertable = false)
	private UserConnection userConnection;

	// TODO indeks na user_id
	@OrderBy(value = "id")
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_badge", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "badge_id") })
	private List<Badge> badges = new ArrayList<>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Badge> getBadges() {
		return badges;
	}

	public void setBadges(List<Badge> badges) {
		this.badges = badges;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", nickname=" + nickname + ", refererId=" + refererId + ", statistic=" + statistic + ", enabled=" + enabled
				+ ", userConnection=" + userConnection + ", badges=" + badges + "]";
	}

	public Integer getRefererId() {
		return refererId;
	}

	public void setRefererId(Integer refererId) {
		this.refererId = refererId;
	}

	public UserStatistic getStatistic() {
		return statistic;
	}

	public void setStatistic(UserStatistic statistic) {
		this.statistic = statistic;
	}

	public UserConnection getUserConnection() {
		return userConnection;
	}

	public void setUserConnection(UserConnection userConnection) {
		this.userConnection = userConnection;
	}

}
