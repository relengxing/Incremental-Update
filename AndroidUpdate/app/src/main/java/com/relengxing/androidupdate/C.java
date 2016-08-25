package com.relengxing.androidupdate;

import android.os.Environment;

import java.io.File;

/**
 * Created by relengxing on 2016/8/25.
 */

public class C {
    public static final String PATCH_FILE = "apk.patch";

    //模拟器访问本机tomcat中的patch文件
    public static final String URL_PATCH_DOWNLOAD = "http://10.0.2.2:8080/"+PATCH_FILE;

    public static final String PACKAGE_NAME = "com.relengxing.AndroidUpdate";

    public static final String SD_CARD = Environment.getExternalStorageDirectory() + File.separator;

    //新版本apk的目录
    public static final String NEW_APK_PATH = SD_CARD+"apk_patch_new.apk";

    public static final String PATCH_FILE_PATH = SD_CARD+PATCH_FILE;
}
