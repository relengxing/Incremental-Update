package com.relengxing;

public class BsDiffTest {

	public static final String OLD_APK_PATH = "D:/test/apk/app-old.apk";
	
	public static final String NEW_APK_PATH = "D:/test/apk/app-new.apk";
	
	public static final String PATCH_PATH = "D:/test/apk/apk.patch";
	
	public static void main(String[] args) {
		
		BsDiff.diff(OLD_APK_PATH, NEW_APK_PATH, PATCH_PATH);
		
	}
}
