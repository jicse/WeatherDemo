package com.example.balaji.weatherdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.balaji.weatherdemo.R;
import com.example.balaji.weatherdemo.model.Datum;
import com.example.balaji.weatherdemo.model.WeatherPOJO;

/**
 * Created by balaji on 18/12/17.
 */

public class WeatherAdapter  extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{
    private final OnItemClickListener listener;
    private WeatherPOJO data;
    private Context context;

    public WeatherAdapter(Context context,WeatherPOJO data, OnItemClickListener listener) {
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
      //  holder.click(data.get(position), listener);



        holder.tvCity.setText(data.getDaily().getData().get(position).getTime().toString());

        holder.tvDesc.setText(data.getDaily().getData().get(position).getSummary());

        /*String images = data.get(position).getBackground();

        Glide.with(context)
                .load(images)
                .into(holder.background);*/

    }


    @Override
    public int getItemCount() {
        return data.getDaily().getData().size();
    }


    public interface OnItemClickListener {
        void onClick(Datum Item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity, tvDesc;
        ImageView background;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.city);
            tvDesc = itemView.findViewById(R.id.hotel);
            background = itemView.findViewById(R.id.image);

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
