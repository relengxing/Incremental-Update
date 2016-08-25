package com.relengxing;

public class BsDiff {
	static{
		System.loadLibrary("BsDiffTest");
	}
	/**
	 * 调用本地方法生成差异包
	 * @param oldfile
	 * @param newfile
	 * @param patchfile
	 */
	public native static void diff(String oldfile,String newfile,String patchfile);
}
