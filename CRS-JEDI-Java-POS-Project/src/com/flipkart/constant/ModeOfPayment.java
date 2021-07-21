package com.flipkart.constant;

public enum ModeOfPayment {

	CREDIT_CARD, NET_BANKING, DEBIT_CARD;

	public static ModeOfPayment getModeofPayment(int value) {
		System.out.println("Function ModeOfPayment called from ModeOfPayment");
		return ModeOfPayment.CREDIT_CARD;
	}

}
