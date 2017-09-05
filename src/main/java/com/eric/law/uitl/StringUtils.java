package com.eric.law.uitl;

public class StringUtils {
	public static boolean isBlank(String input) {
		if(input==null||input.isEmpty()) {
			return true;
		}else {
			for(int i = 0;i<input.length();i++) {
				if(Character.isWhitespace(input.charAt(i))==false&&160!=input.charAt(i)) {
					return false;
				}
			}
		}
		return true;
		
	}
	
	public static boolean isNotBlank(String input) {
		return !isBlank(input);
	}
}
