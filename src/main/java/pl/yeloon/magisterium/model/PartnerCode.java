package pl.yeloon.magisterium.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "partner_code")
public class PartnerCode extends BaseEntity {

	@Column(nullable = false)
	private String code;

	@Column(name = "referer_id", nullable = false)
	private Integer refererId;

	@NotBlank
	@Email
	@Size(min = 6, max=100)
	@Column(name = "friend_email", nullable = false)
	private String friendEmail;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getRefererId() {
		return refererId;
	}

	public void setRefererId(Integer refererId) {
		this.refererId = refererId;
	}

	@Override
	public String toString() {
		return "PartnerCode [code=" + code + ", refererId=" + refererId + ", friendEmail=" + friendEmail + "]";
	}

	public String getFriendEmail() {
		return friendEmail;
	}

	public void setFriendEmail(String friendEmail) {
		this.friendEmail = friendEmail;
	}
}
