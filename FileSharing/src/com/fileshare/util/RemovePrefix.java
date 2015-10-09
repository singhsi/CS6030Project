package com.fileshare.util;

public class RemovePrefix {
	
	public static String removePrefix(String prefix, String fullName){
		String outputStr = fullName.replace(prefix, "");
		return outputStr;
	}
}
