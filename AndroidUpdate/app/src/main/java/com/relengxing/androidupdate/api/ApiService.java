package com.relengxing.androidupdate.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by relengxing on 2016/8/25.
 */
public interface ApiService {

    @GET
    Observable<ResponseBody> getPatchFile(@Url String url);

}
