package com.base.service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.base.bean.RetrofitProperty;
import com.base.jackson.JacksonConverterFactory;
import com.base.retrofit.BaseRetrofitService;
import com.base.retrofit.RetrofitBaseResponse;
import com.base.retrofit.RetrofitClient;
import com.base.retrofit.RetrofitException;
import com.base.utils.HTTPLogger;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Retrofit;


@Service
@Slf4j
public class RetrofitService {

    private static final String ERROR_MSG = "Error in Paas Service Call";

    @Autowired
    RetrofitProperty properties;

    @Autowired
    BaseRetrofitService retrofitService;

    private RetrofitClient retrofitClient ;




    @PostConstruct
    public void postConstruct() {
       OkHttpClient httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain
                                .request()
                                .newBuilder()
                                //.addHeader("Authorization", paasProperties.getBasicAuth())
                                .addHeader("Content-Type","application/json")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(new HttpLoggingInterceptor(HTTPLogger.DEFAULT).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(1000, TimeUnit.MILLISECONDS) // 1 second
                .readTimeout(5000, TimeUnit.MILLISECONDS) // 5 seconds
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(properties.getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient)
                .build();

        retrofitClient = retrofit.create(RetrofitClient.class);
    }


    public RetrofitBaseResponse getRetrofitData(){
        Call<RetrofitBaseResponse> responseCall = retrofitClient.callRetrofitUrl();
        RetrofitBaseResponse retrofitBaseResponse = null;
        try {
            retrofitBaseResponse = retrofitService.getResponseOrLogWarning(responseCall, ERROR_MSG);
        } catch (RetrofitException e) {
            log.error(ERROR_MSG, e);
        }
        return retrofitBaseResponse;
    }


}
