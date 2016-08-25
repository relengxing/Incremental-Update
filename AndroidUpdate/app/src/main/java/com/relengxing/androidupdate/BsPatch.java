package com.relengxing.androidupdate;

/**
 * Created by relengxing on 2016/8/25.
 */

public class BsPatch {

    static {
        System.loadLibrary("bspatch");
    }

    public native static void patch(String oldFile,String newFile,String patchFile);
}
