package com.flipkart.constant;

public enum Gender {
	MALE(1), FEMALE(2), OTHER(3);

	Gender(int gender) {
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
		Gender genderObj = Gender.OTHER;
		if (val.equalsIgnoreCase("male"))
			genderObj = Gender.MALE;
		else if (val.equalsIgnoreCase("female"))
			genderObj = Gender.FEMALE;
		return genderObj;
	}
}
