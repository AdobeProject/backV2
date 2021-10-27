package com.example.demo.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePasswordRequestModel {
	@JsonProperty("oldPassword")
	private String oldPassword;

	@JsonProperty("newPassword")
	private String newPassword;

	public ChangePasswordRequestModel() {
	}

	public ChangePasswordRequestModel(String oldPassword, String newPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

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
}
