
package com.example.balaji.weatherdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.balaji.weatherdemo.R;
import com.example.balaji.weatherdemo.adapters.WeatherAdapter;
import com.example.balaji.weatherdemo.model.Datum;
import com.example.balaji.weatherdemo.model.WeatherPOJO;
import com.example.balaji.weatherdemo.presenter.WeatherMVPView;
import com.example.balaji.weatherdemo.presenter.WeatherPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends BaseActivity implements WeatherMVPView {

    @BindView(R.id.weather_list)
    RecyclerView weatherList;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Inject
    WeatherPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        getComponent().inject(WeatherActivity.this);
        ButterKnife.bind(this);

        presenter.setView(this);
        presenter.getWeatherList();

        weatherList.setLayoutManager(new LinearLayoutManager(this));
        weatherList.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

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
        Toast.makeText(this, appErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getWeatherData(WeatherPOJO weatherList) {
        WeatherAdapter adapter = new WeatherAdapter(getApplicationContext(), weatherList, new WeatherAdapter.OnItemClickListener() {
            @Override
            public void onClick(Datum Item) {

            }
        });

        this.weatherList.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
