package com.base.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitClient {

    @GET("/")
    Call<RetrofitBaseResponse> callRetrofitUrl();
}
