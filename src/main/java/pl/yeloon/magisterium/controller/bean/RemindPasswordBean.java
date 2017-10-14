package pl.yeloon.magisterium.controller.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RemindPasswordBean {

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
        return new ToStringBuilder(this).append("email", email).toString();
	}

}