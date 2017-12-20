package com.example.balaji.weatherdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.balaji.weatherdemo.dagger.component.AppComponent;
import com.example.balaji.weatherdemo.dagger.component.DaggerAppComponent;
import com.example.balaji.weatherdemo.dagger.module.NetworkModule;

import java.io.File;

/**
 * Created by balaji on 20/12/17.
 */

public class BaseActivity extends AppCompatActivity {

    AppComponent component;
    public static final String BASE_URL = "https://api.darksky.net/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = DaggerAppComponent.builder()
                .networkModule(new NetworkModule(BASE_URL))
                .build();    }

    public AppComponent getComponent() {
        return component;
    }
}
