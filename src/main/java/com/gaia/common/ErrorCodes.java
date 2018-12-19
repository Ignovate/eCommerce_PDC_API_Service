package com.gaia.common;

public interface ErrorCodes {

	public static final String CODE_INVALID_OTP="001";
	public static final String MSG_INVALID_OTP="Invalid OTP";	
	
	public static final String CODE_INVALID_CRED="001";
	public static final String MSG_INVALID_CRED="Invalid Credentials";	
	
	public static final String CODE_NO_DATA="002";
	public static final String MSG_NO_DATA="NO DATA";	
	
	public static final String CODE_INCORRECT_USER="003";
	public static final String MSG_INCORRECT_USER="Incorrect UserName";	
	
	public static final String CODE_USER_BLOCK="004";
	public static final String MSG_USER_BLOCK="User is Blocked";	
	
	public static final String CODE_USER_LOCKED="005";
	public static final String MSG_USER_LOCKED="User is Locked";	
	
	public static final String CODE_USERNAME_PRESENT = "006";
	public static final String MSG_USERNAME_PRESENT = "Username Already present";
	
	public static final String CODE_CONFIRM_PASS_MISMATCH = "007";
	public static final String MSG_CONFIRM_PASS_MISMATCH = "Confirm Password mismatch";
	
	

}
