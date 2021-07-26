package com.flipkart.constant;

public enum Gender {
	MALE(1), FEMALE(2), OTHER(3);

	private final int gender;

	Gender(int gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return name();
	}

	public static Gender getName(int val) {
		switch (val) {
			case 1:
				return Gender.MALE;
			case 2:
				return Gender.FEMALE;
			default:
				return Gender.OTHER;
		}
	}

	public static Gender stringToGender(String val) {
		Gender gender = Gender.OTHER;
		if (val.equalsIgnoreCase("male"))
			gender = Gender.MALE;
		else if (val.equalsIgnoreCase("female"))
			gender = Gender.FEMALE;
		return gender;
	}
}
