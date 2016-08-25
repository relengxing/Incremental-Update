package com.relengxing;

public class BsPatch {
	public native static void patch(String oldfile,String newfile,String patchfile);
	
	static{
		System.loadLibrary("BsPatchTest");
	}
}
