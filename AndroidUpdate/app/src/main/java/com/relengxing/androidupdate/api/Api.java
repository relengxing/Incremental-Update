package com.relengxing.androidupdate.api;



import android.os.Environment;
import android.provider.SyncStateContract;
import android.util.Log;

import com.relengxing.androidupdate.C;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by relengxing on 2016/7/30.
 */

public class Api {
    private static final String TAG = "===Api===";
    private static final String BASE_URL = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;

    private ApiService apiService;

    /**
     * 无参构造函数
     */
    private Api() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build())
                .build();

        apiService = retrofit.create(ApiService.class);

    }

    /**
     * 单例辅助创建内部类
     */
    private static class SingletonHolder{
        private static final Api Intance = new Api();
    }

    /** 获取API类的单例
     * @return
     */
    public static Api getIntance(){
        return SingletonHolder.Intance;
    }

    public Observable<String> getPatchFile(String url){
        return apiService.getPatchFile(url).
                subscribeOn(Schedulers.io()).
                map(new Func1<ResponseBody, String>() {
            @Override
            public String call(ResponseBody responseBody) {
                File patchfile = new File(Environment.getExternalStorageDirectory(), C.PATCH_FILE);
                if (patchfile.exists()) {
                    patchfile.delete();
                }
                try (
                        InputStream is = responseBody.byteStream();
                        FileOutputStream fos = new FileOutputStream(patchfile);
                ) {

                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }
                    Log.d(TAG, "onNext: 下载成功");
                    return patchfile.getAbsolutePath();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
