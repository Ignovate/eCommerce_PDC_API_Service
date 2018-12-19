package com.gaia.common;

public class StringUtils {
	
	public static boolean isNullOREmpty(String value) {
		if(value != null || "".equals(value)) {
			return true;
		}
		return false;
	}

}
