package com.relengxing.androidupdate;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.relengxing.androidupdate.api.Api;
import com.relengxing.androidupdate.utils.ApkUtils;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "===MainActivity===";

    private String mVersionName;
    private int mVersionCode;
    private TextView tv_version;
    private Button btn_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getLocalVersionInfo();
    }

    private void initView() {
        tv_version = (TextView) findViewById(R.id.tv_version);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);
    }
    /**
     * 获取本地版本信息
     */
    public void getLocalVersionInfo() {
        PackageManager manager = getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mVersionName = info.versionName;        //版本名称
        mVersionCode = info.versionCode;        //版本号
        Log.d(TAG, "checkVersion: versionName" + mVersionName);
        Log.d(TAG, "checkVersion: versionCode" + mVersionCode);
        //显示本地版本信息
        tv_version.setText("版本名称: " + mVersionName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update:
                update();
                break;
        }
    }

    /**
     * 更新应用程序
     */
    private void update() {
        Api.getIntance().getPatchFile(C.URL_PATCH_DOWNLOAD).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                ApkUtils.installApk(MainActivity.this, C.NEW_APK_PATH);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                //获取当前应用的apk文件/data/app/app
                String oldfile = ApkUtils.getSourceApkPath(MainActivity.this, getPackageName());
//                String oldfile = C.SD_CARD + "app-old.apk";
                //2.合并得到最新版本的APK文件
                String newfile = C.NEW_APK_PATH;
                String patchfile = s;
                BsPatch.patch(oldfile, newfile, patchfile);

                Log.d("jason", "oldfile:"+oldfile);
                Log.d("jason", "newfile:"+newfile);
                Log.d("jason", "patchfile:"+patchfile);
            }
        });
    }
}
