package com.example.balaji.weatherdemo.dagger.component;

import com.example.balaji.weatherdemo.activity.WeatherActivity;
import com.example.balaji.weatherdemo.dagger.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by balaji on 18/12/17.
 */

@Singleton
@Component(modules = {NetworkModule.class,})
public interface AppComponent {
    void inject(WeatherActivity target);
}