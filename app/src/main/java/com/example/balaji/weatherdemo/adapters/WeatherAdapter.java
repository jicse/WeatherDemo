package com.example.balaji.weatherdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.balaji.weatherdemo.R;
import com.example.balaji.weatherdemo.model.Datum;
import com.example.balaji.weatherdemo.model.WeatherPOJO;
import com.example.balaji.weatherdemo.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by balaji on 18/12/17.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private WeatherPOJO data;
    private Context context;

    public WeatherAdapter(Context context, WeatherPOJO data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder holder, int position) {
        Datum datum = data.getDaily().getData().get(position);
        holder.click(datum, listener);
        holder.tvDate.setText(AppUtils.convertTimeStampToDate(datum.getTime()));
        holder.tvTempMin.setText(AppUtils.getTemp(context, datum.getTemperatureMin(), true));
        holder.tvTempMax.setText(AppUtils.getTemp(context, datum.getTemperatureMax(), false));
        holder.tvTimeZone.setText(data.getTimezone());
    }


    @Override
    public int getItemCount() {
        return data.getDaily().getData().size();
    }


    public interface OnItemClickListener {
        void onClick(Datum Item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        TextView tvDate;

        @BindView(R.id.temp_min)
        TextView tvTempMin;

        @BindView(R.id.temp_max)
        TextView tvTempMax;

        @BindView(R.id.time_zone)
        TextView tvTimeZone;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void click(final Datum datum, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(datum);
                }
            });
        }
    }

}
