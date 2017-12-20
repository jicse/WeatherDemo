package com.example.balaji.weatherdemo.presenter;

import com.example.balaji.weatherdemo.activity.WeatherActivity;
import com.example.balaji.weatherdemo.model.WeatherPOJO;
import com.example.balaji.weatherdemo.network.WeatherService;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by balaji on 19/12/17.
 */

public class WeatherPresenter {

    private WeatherService service;
    private WeatherMVPView weatherMVPview;
    private CompositeSubscription subscriptions;

    @Inject
    public WeatherPresenter(WeatherService service) {
        this.service = service;
        this.subscriptions = new CompositeSubscription();
    }

    public void getWeatherList() {
        weatherMVPview.showLoader();

        Subscription subscription = service.getWeatherList(new WeatherService.GetWeatherListCallback() {

            @Override
            public void onSuccess(WeatherPOJO weatherList) {
                weatherMVPview.hideLoader();
                weatherMVPview.getWeatherData(weatherList);
            }

            @Override
            public void onError(String networkError) {
                weatherMVPview.hideLoader();
                weatherMVPview.onFailure("error");
            }

        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

    public void setView(WeatherMVPView weatherMVPview) {
        this.weatherMVPview = weatherMVPview;
    }
}
