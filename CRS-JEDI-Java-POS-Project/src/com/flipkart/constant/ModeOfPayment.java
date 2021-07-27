package com.flipkart.constant;

public enum ModeOfPayment {

	CREDIT_CARD, NET_BANKING, DEBIT_CARD;

	public static ModeOfPayment getModeOfPayment(int value) {
		return values()[value];
	}

}
