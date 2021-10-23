package com.example.demo.entity;

public enum UserRoleType {
	TEACHER,
	STUDENT,
	ADMIN;

	public static UserRoleType getByName(String name) {
		name = name.toUpperCase();
		for (UserRoleType r : UserRoleType.values()) {
			if (name.equals(r.name())) return r;
			System.out.println(r);
		}
		throw new IllegalStateException("No such role");
	}
}
