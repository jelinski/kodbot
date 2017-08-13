package pl.yeloon.magisterium.controller.bean;

public class RegisterUserBean {

    private String email;

    private String nickname;

    private String password;

    private String repeatedPassword;

    private String registrationCode;

    private Boolean acceptRegulation;
	
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

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public Boolean getAcceptRegulation() {
		return acceptRegulation;
	}

	public void setAcceptRegulation(Boolean acceptRegulation) {
		this.acceptRegulation = acceptRegulation;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "RegisterUserBean [email=" + email + ", nickname=" + nickname + ", password=" + password + ", repeatedPassword=" + repeatedPassword + ", registrationCode=" + registrationCode + ", acceptRegulation=" + acceptRegulation + "]";
	}
	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}
	
}
