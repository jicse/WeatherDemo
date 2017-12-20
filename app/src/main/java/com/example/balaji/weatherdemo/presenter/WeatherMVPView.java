package com.example.balaji.weatherdemo.presenter;

import com.example.balaji.weatherdemo.model.WeatherPOJO;

/**
 * Created by balaji on 19/12/17.
 */

public interface WeatherMVPView {

    void showLoader();

    void hideLoader();

    void onFailure(String errorMessage);

    void getWeatherData(WeatherPOJO weatherList);
}
