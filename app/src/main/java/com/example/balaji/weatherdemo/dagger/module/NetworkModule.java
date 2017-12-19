package com.example.balaji.weatherdemo.dagger.module;

import com.example.balaji.weatherdemo.network.NetworkService;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by balaji on 18/12/17.
 */

@Module
public class NetworkModule {
    public String BASE_URL;

    public NetworkModule(String BASE_URL){
        this.BASE_URL = BASE_URL;
    }

    @Singleton
    @Provides
    public NetworkService provideApiService() {
        return provideRetrofit(BASE_URL).create(NetworkService.class);
    }

    public Retrofit provideRetrofit(String baseURL) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
