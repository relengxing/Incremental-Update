package com.relengxing;

public class BsDiff {
	static{
		System.loadLibrary("BsDiffTest");
	}
	/**
	 * ���ñ��ط������ɲ����
	 * @param oldfile
	 * @param newfile
	 * @param patchfile
	 */
	public native static void diff(String oldfile,String newfile,String patchfile);
}
