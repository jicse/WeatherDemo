package com.example.balaji.weatherdemo.presenter;

import com.example.balaji.weatherdemo.model.WeatherPOJO;
import com.example.balaji.weatherdemo.network.WeatherService;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by balaji on 19/12/17.
 */

public class WeatherPresenter {

    private final WeatherService service;
    private final WeatherView view;
    private CompositeSubscription subscriptions;

    public WeatherPresenter(WeatherService service, WeatherView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getWeatherList() {
        view.showLoader();

        Subscription subscription = service.getWeatherList(new WeatherService.GetWeatherListCallback() {

            @Override
            public void onSuccess(WeatherPOJO weatherList) {
                view.hideLoader();
                view.getWeatherData(weatherList);
            }

            @Override
            public void onError(String networkError) {
                view.hideLoader();
                view.onFailure("error");
            }

        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }
}
