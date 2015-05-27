package com.aptikraft.common.utils;

public final class StringUtils {

	/**
	 * Checks the string is not null, not contains white spaces, not empty
	 * string
	 * 
	 * @param s
	 * @return
	 */
	public static final boolean isNotBlank(String s) {
		if (s == null)
			return false;
		s = s.trim();
		return s.length() > 0;
	}
}
