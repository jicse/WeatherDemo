
package com.example.balaji.weatherdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.balaji.weatherdemo.R;
import com.example.balaji.weatherdemo.adapters.WeatherAdapter;
import com.example.balaji.weatherdemo.dagger.component.DaggerNetworkComponent;
import com.example.balaji.weatherdemo.dagger.component.NetworkComponent;
import com.example.balaji.weatherdemo.dagger.module.NetworkModule;
import com.example.balaji.weatherdemo.model.Datum;
import com.example.balaji.weatherdemo.model.WeatherPOJO;
import com.example.balaji.weatherdemo.network.WeatherService;
import com.example.balaji.weatherdemo.presenter.WeatherPresenter;
import com.example.balaji.weatherdemo.presenter.WeatherView;

import javax.inject.Inject;

public class WeatherActivity extends AppCompatActivity implements WeatherView {

    private RecyclerView list;

    @Inject
    public WeatherService weatherService;

    ProgressBar progressBar;


    private NetworkComponent component;
    public final String BASE_URL = "https://api.darksky.net/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(BASE_URL))
                .build();
        component.inject(WeatherActivity.this);
        renderView();
        init();

    WeatherPresenter presenter = new WeatherPresenter(weatherService, this);
        presenter.getWeatherList();
    }

    public void renderView() {
        setContentView(R.layout.activity_weather);
        list = findViewById(R.id.list);
        progressBar = findViewById(R.id.progress);
    }

    public void init() {
        list.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getWeatherData(WeatherPOJO weatherList) {
        WeatherAdapter adapter = new WeatherAdapter(getApplicationContext(), weatherList, new WeatherAdapter.OnItemClickListener() {
            @Override
            public void onClick(Datum Item) {

            }
        });

        list.setAdapter(adapter);
    }

}
