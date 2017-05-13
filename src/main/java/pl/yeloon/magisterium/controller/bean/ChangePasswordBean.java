package pl.yeloon.magisterium.controller.bean;

public class ChangePasswordBean {
	
	private String oldPassword;
	private String newPassword;
	private String repeatedNewPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRepeatedNewPassword() {
		return repeatedNewPassword;
	}
	public void setRepeatedNewPassword(String repeatedNewPassword) {
		this.repeatedNewPassword = repeatedNewPassword;
	}
	
	@Override
	public String toString() {
		return "ChangePasswordBean [oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", repeatedNewPassword=" + repeatedNewPassword + "]";
	}

}
