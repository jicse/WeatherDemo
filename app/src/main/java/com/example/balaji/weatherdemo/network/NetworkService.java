package com.example.balaji.weatherdemo.network;

import com.example.balaji.weatherdemo.model.WeatherPOJO;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by balaji on 19/12/17.
 */

public interface NetworkService {

    @GET("forecast/3db0e239e0002981f8aac41cb85b36a1/13.0827,80.2707")
    Observable<WeatherPOJO> getWeatherList();

}
