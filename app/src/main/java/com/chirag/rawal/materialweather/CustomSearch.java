package com.chirag.rawal.materialweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.chirag.rawal.materialweather.POJO.CurrentDayData;
import com.chirag.rawal.materialweather.POJO.Main;
import com.chirag.rawal.materialweather.POJO.Weather;
import com.chirag.rawal.materialweather.POJO.Wind;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CustomSearch extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "WeatherWeatherMaterialWeather";
    private String APIkey = "ebfcac32bda131ed5a160f2757938396";

    String lat, lng;
    String celcius = " " + (char) 0x00B0+"C";

    ImageView saveLocationButton;
    TextView cityName , temp , minTemp , maxTemp , forecast , dateTime;
    LottieAnimationView animationView;
    ProgressBar reloadProgressBar;
    LinearLayout linearLayout;
    DrawerLayout drawer;
    ImageView trigger;
    String city_NameSTR;
    private Integer cityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_custom);

        cityName = findViewById(R.id.cityNameCustom);
        temp = findViewById(R.id.temperatureCustom);
        minTemp = findViewById(R.id.minTempCustom);
        maxTemp = findViewById(R.id.maxTempCustom);
        forecast = findViewById(R.id.weatherForecastCustom);
        trigger = findViewById(R.id.triggerForDrawerCustom);
        animationView = findViewById(R.id.loadingAnimCustom);
        animationView.setRepeatCount(100);
        linearLayout = findViewById(R.id.linearLayoutCustom);
        dateTime = findViewById(R.id.dateTimeCustom);
        reloadProgressBar = findViewById(R.id.reloadProgressCustom);
        saveLocationButton = findViewById(R.id.saveCity);

        drawer = findViewById(R.id.drawer_layout_custom);
        NavigationView navigationView =  findViewById( R.id.nav_view_custom );
        navigationView.setNavigationItemSelectedListener( this );
    }

    @Override
    protected void onStart() {
        super.onStart();

        animationView.playAnimation();
        //Check
        lat = getIntent().getStringExtra("lat");
        lng = getIntent().getStringExtra("lng");

        try {
            startAPIcall(lat,lng);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void openDrawer_(View view) {
        drawer.openDrawer(GravityCompat.START);
    }

    private void startAPIcall(final String lat_coord, final String lng_coord) throws IOException {

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    final String data;
                    data = fetchDataCurrent(lat_coord, lng_coord);
                    Log.i("STUNNxData",data);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final CurrentDayData dataSmall = new Gson().fromJson(data, CurrentDayData.class);
                            cityName.setText(dataSmall.getName());
                            city_NameSTR = dataSmall.getName();
                            cityID = dataSmall.getId();
                            dateTime.setText(convertEPOCH(dataSmall.getDt()));
                            convertEPOCH(dataSmall.getDt());
                            Weather weather = dataSmall.getWeather().get(0);
//                            weather1.setText(weather.getMain());
                            forecast.setText(weather.getDescription());
                            Wind wind = dataSmall.getWind();
//                            winddegree.setText(wind.getDeg().toString());
//                            windspeed.setText(wind.getSpeed().toString());
                            Main main = dataSmall.getMain();
//                            pressure.setText(main.getPressure().toString());
//                            humidity.setText(main.getHumidity().toString());
                            float temp_ = (float) (Float.parseFloat(main.getTemp().toString())-273.15);
                            temp.setText(String.format("%.2f",temp_)+ celcius);

                            String weatherIconCode = weather.getIcon();

                            float minTemp_ = (float) (Float.parseFloat(main.getTemp_min().toString())-273.15);
                            float maxTemp_ = (float) (Float.parseFloat(main.getTemp_max().toString())-273.15);

                            minTemp.setText(String.format("%.2f",minTemp_)+ celcius);
                            maxTemp.setText(String.format("%.2f",maxTemp_)+ celcius);

                            animationView.setVisibility(View.INVISIBLE);
                            linearLayout.setVisibility(View.VISIBLE);
                            animationView.cancelAnimation();
                            trigger.setVisibility(View.VISIBLE);
                            reloadProgressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                } catch (IOException e) {
                    Log.i("Response from Server : ", "Exception Occured");
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private String convertEPOCH(Integer integer) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        TimeZone InTimezone = TimeZone.getTimeZone("Asia/Kolkata");
        long x = integer.intValue();
        x *= 1000;
        Log.i("EPOCH : ",String.valueOf(x));
        Date date = new Date(x);
//        simpleDateFormat.setTimeZone(InTimezone);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String formatted = format.format(date);
        Log.i("Time Asia/Kolkata",formatted);
        return formatted;
    }

    private String fetchDataCurrent(String lat_coord, String lng_coord) throws IOException {

        String ur = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat_coord + "&lon=" + lng_coord + "&appid="+APIkey;
        final URL url = new URL(ur);
        StringBuffer toReturn = new StringBuffer();
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            Log.i("STUNNx", "Success!");
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String temp;
            StringBuffer buffer = new StringBuffer();
            while ((temp = bufferedReader.readLine()) != null) {
                buffer.append(temp + "\n");
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            toReturn.append(buffer.toString());
        } else {

            Log.i("STUNNx", "Failure!");
        }
        return toReturn.toString();
    }

    public void reloadData(View view) {

        try {
            reloadPlease();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String lat_coord = data.getStringExtra("lat");
                String lng_coord = data.getStringExtra("lng");

                try {
                    startAPIcall(lat_coord, lng_coord);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    public void reloadPlease()throws IOException {
        reloadProgressBar.setVisibility(View.VISIBLE);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(200);

        Toast.makeText(this, "Reloading Data!", Toast.LENGTH_SHORT).show();
        startAPIcall(lat,lng);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.reload_data:
                try {
                    reloadPlease();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.location_current:
                parseLocation();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.forecast_sixteen:
                Intent intent = new Intent(this,Forecast10Days.class);
                intent.putExtra("lat_coord",lat);
                intent.putExtra("lng_coord",lng);
                drawer.closeDrawer(GravityCompat.START);
                startActivity(intent);
                break;
            case R.id.search_city:
                Intent intent_ = new Intent(this,SearchCity.class);
                drawer.closeDrawer(GravityCompat.START);
                startActivity(intent_);
                break;
            case R.id.savedPlaces:
                Intent intent1_ = new Intent(this,SavedLocations.class);
                drawer.closeDrawer(GravityCompat.START);
                startActivity(intent1_);
                break;

        }
        return false;
    }

    private void parseLocation() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(200);

        Toast.makeText(this, "Getting Current Location!", Toast.LENGTH_SHORT).show();
        startActivityForResult(new Intent(this, LocationAct.class), 1);
    }

    public void SaveKroGuys_(View view) {

        //SharedPrefs
        SharedPreferences.Editor savedLocations = getSharedPreferences("saved_location",MODE_PRIVATE).edit();
        savedLocations.putInt(city_NameSTR,cityID);
        savedLocations.apply();
        Resources res = this.getResources();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            saveLocationButton.setForeground(ResourcesCompat.getDrawable(res,R.drawable.ic_baseline_save,null));
        }
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

    }
}
