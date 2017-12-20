package com.example.balaji.weatherdemo.network;

import com.example.balaji.weatherdemo.model.WeatherPOJO;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by balaji on 19/12/17.
 */

public class WeatherService {

    private final NetworkService networkService;

    @Inject
    public WeatherService(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getWeatherList(final GetWeatherListCallback callback) {

        return networkService.getWeatherList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends WeatherPOJO>>() {
                    @Override
                    public Observable<? extends WeatherPOJO> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<WeatherPOJO>() {
                    @Override
                    public void onCompleted() {
                        callback.onError("completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("error");
                    }

                    @Override
                    public void onNext(WeatherPOJO cityListResponse) {
                        callback.onSuccess(cityListResponse);

                    }
                });
    }

    public interface GetWeatherListCallback {
        void onSuccess(WeatherPOJO cityListResponse);

        void onError(String networkError);
    }
}
