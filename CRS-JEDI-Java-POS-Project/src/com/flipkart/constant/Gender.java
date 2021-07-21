package com.flipkart.constant;

public enum Gender {
	MALE(1), FEMALE(2), OTHER(3);

	private final int gender;

	private Gender(int gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		final String name = name();
		return name;
	}

	public static Gender getName(int val) {
		return Gender.MALE;
	}
}
