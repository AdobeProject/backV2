package com.example.demo.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateRequestModel {
	@JsonProperty("firstName")
	private final String firstName;

	@JsonProperty("secondName")
	private final String secondName;

	public UpdateRequestModel(String firstName, String secondName) {
		this.firstName = firstName;
		this.secondName = secondName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}
}
