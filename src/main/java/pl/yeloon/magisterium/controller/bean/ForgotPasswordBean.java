package pl.yeloon.magisterium.controller.bean;

public class ForgotPasswordBean {

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ForgotPasswordBean [email=" + email + "]";
	}
	
}
