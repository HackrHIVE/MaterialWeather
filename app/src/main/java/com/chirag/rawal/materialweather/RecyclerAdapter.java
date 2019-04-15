package com.chirag.rawal.materialweather;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chirag.rawal.materialweather.POJO10.List;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    ArrayList<List> dataList;
    String celcius = " " + (char) 0x00B0+"C";

    public RecyclerAdapter(ArrayList<List> listData) {
        this.dataList = null;
        this.dataList = listData;
    }

    private String convertEPOCH(Integer integer) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        TimeZone InTimezone = TimeZone.getTimeZone("Asia/Kolkata");
        long x = integer.intValue();
        x *= 1000;
        Log.i("EPOCH : ",String.valueOf(x));
        Date date = new Date(x);
//        simpleDateFormat.setTimeZone(InTimezone);
        DateFormat format = new SimpleDateFormat("dd/MM");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String formatted = format.format(date);
        Log.i("Time Asia/Kolkata",formatted);
        return formatted;
    }

    public static String theMonth(int month){
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        com.chirag.rawal.materialweather.POJO10.Temp tempObject = dataList.get(position).getTemp();

        String min = String.format("%.2f",(float)(Float.parseFloat(String.valueOf(tempObject.getMin()-273.15))));
        String max = String.format("%.2f",(float)(Float.parseFloat(String.valueOf(tempObject.getMax()-273.15))));

        String min_ = "";
        for(int j=0;j<min.length()-1;j++){
            min_ = min_+min.charAt(j);
        }
        String max_ = "";
        for(int j=0;j<max.length()-1;j++){
            max_ = max_+max.charAt(j);
        }
        holder.minTemp.setText(min_+celcius);
        holder.maxTemp.setText(max_+celcius);

        String date = convertEPOCH(dataList.get(position).getDt());
        int date_ = 0;
        int month_ = 0;
        String date__ = "";
        int i=0;
        for(i=0;date.charAt(i)!='/';i++){
            date__ = date__ + ""+date.charAt(i);
        }
        String month__ = "";
        for(i=i+1;i<date.length();i++){
            month__ = month__ + ""+date.charAt(i);
        }
        date_ = Integer.parseInt(date__);
        month_ = Integer.parseInt(month__);
        String monthToDisp = theMonth(month_-1);
        holder.date.setText(date_+" "+monthToDisp);

        com.chirag.rawal.materialweather.POJO10.Weather weatherObject = dataList.get(position).getWeather().get(0);
        switch (weatherObject.getIcon()){
            case "01d":
                Picasso.get()
                        .load(R.drawable.weather_clear)
                        .into(holder.forecastIcon);
                break;
            case "01n":
                Picasso.get()
                        .load(R.drawable.weather_clear)
                        .into(holder.forecastIcon);
                break;
            case "02d":
                Picasso.get()
                        .load(R.drawable.weather_clouds)
                        .into(holder.forecastIcon);
                break;
            case "02n":
                Picasso.get()
                        .load(R.drawable.weather_clouds_night)
                        .into(holder.forecastIcon);
                break;
            case "03d":
                Picasso.get()
                        .load(R.drawable.weather_few_clouds)
                        .into(holder.forecastIcon);
                break;
            case "03n":
                Picasso.get()
                        .load(R.drawable.weather_few_clouds_night)
                        .into(holder.forecastIcon);
                break;
            case "04d":
                Picasso.get()
                        .load(R.drawable.weather_haze)
                        .into(holder.forecastIcon);
                break;
            case "04n":
                Picasso.get()
                        .load(R.drawable.weather_haze)
                        .into(holder.forecastIcon);
                break;
                //
            case "09d":
                Picasso.get()
                        .load(R.drawable.weather_showers_day)
                        .into(holder.forecastIcon);
                break;
            case "09n":
                Picasso.get()
                        .load(R.drawable.weather_showers_night)
                        .into(holder.forecastIcon);
                break;
            case "10d":
                Picasso.get()
                        .load(R.drawable.weather_rain_day)
                        .into(holder.forecastIcon);
                break;
            case "10n":
                Picasso.get()
                        .load(R.drawable.weather_rain_night)
                        .into(holder.forecastIcon);
                break;
            case "11d":
                Picasso.get()
                        .load(R.drawable.weather_storm)
                        .into(holder.forecastIcon);
                break;
            case "11n":
                Picasso.get()
                        .load(R.drawable.weather_storm)
                        .into(holder.forecastIcon);
                break;
            case "13d":
                Picasso.get()
                        .load(R.drawable.weather_big_snow)
                        .into(holder.forecastIcon);
                break;
            case "13n":
                Picasso.get()
                        .load(R.drawable.weather_big_snow)
                        .into(holder.forecastIcon);
                break;
            case "50d":
                Picasso.get()
                        .load(R.drawable.weather_mist)
                        .into(holder.forecastIcon);
                break;
            case "50n":
                Picasso.get()
                        .load(R.drawable.weather_mist)
                        .into(holder.forecastIcon);
                break;
        }
        holder.description.setText(weatherObject.getMain());



    }

    @Override
    public int getItemCount() {
        if (dataList!=null)
            return dataList.size();
        else
            return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView description , date , minTemp , maxTemp;
        ImageView forecastIcon ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.Forecast_Description);
            date = itemView.findViewById(R.id.Forecast_Date);
            forecastIcon = itemView.findViewById(R.id.Forecast_Icon);
            minTemp = itemView.findViewById(R.id.Forecast_MinTemp);
            maxTemp = itemView.findViewById(R.id.Forecast_MaxTemp);

        }
    }

}
