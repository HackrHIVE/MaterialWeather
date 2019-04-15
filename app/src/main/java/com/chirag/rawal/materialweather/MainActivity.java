package com.chirag.rawal.materialweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
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

public class MainActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "WeatherWeatherMaterialWeather";
    private String APIkey = "ebfcac32bda131ed5a160f2757938396";

    SharedPreferences locationDetails;
    String lat, lng;
    String locationName;
    private final String key_locationName = "location_name";
    private final String key_lat = "lat";
    private final String key_lon = "lon";
    String celcius = " " + (char) 0x00B0+"C";

    TextView cityName , temp , minTemp , maxTemp , forecast , dateTime;
    LottieAnimationView animationView;
    ProgressBar reloadProgressBar;
    LinearLayout linearLayout;
    DrawerLayout drawer;
    ImageView trigger;
    private ImageView saveLocationButton;
    String city_NameSTR;
    Integer cityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName = findViewById(R.id.cityName);
        temp = findViewById(R.id.temperature);
        minTemp = findViewById(R.id.minTemp);
        maxTemp = findViewById(R.id.maxTemp);
        forecast = findViewById(R.id.weatherForecast);
        trigger = findViewById(R.id.triggerForDrawer);
        animationView = findViewById(R.id.loadingAnim);
        animationView.setRepeatCount(100);
        linearLayout = findViewById(R.id.linearLayout);
        dateTime = findViewById(R.id.dateTime);
        reloadProgressBar = findViewById(R.id.reloadProgress);
        saveLocationButton = findViewById(R.id.saveCity_main);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView =  findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(this, "You touched me!", Toast.LENGTH_SHORT).show();
        // Pass activity on touch event to the gesture detector.
        // Return true to tell android OS that event has been consumed, do not pass it to other event listeners.
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        animationView.playAnimation();
        //Check
        if (!checkSharedPref()) {
            startActivityForResult(new Intent(this, LocationAct.class), 1);
        } else {
            String lat_coord = locationDetails.getString(key_lat, "");
            String lng_coord = locationDetails.getString(key_lon, "");
            try {
                startAPIcall(lat_coord, lng_coord);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkSharedPref() {
        locationDetails = getSharedPreferences("location_details", Context.MODE_PRIVATE);
        lat = locationDetails.getString(key_lat, "");
        lng = locationDetails.getString(key_lon, "");
        if (!TextUtils.isEmpty(String.valueOf(lat)) && !TextUtils.isEmpty(String.valueOf(lng)))
            return true;
        else
            return false;
    }

    private boolean checkSharedPrefForLocationName() {
        locationDetails = getSharedPreferences("location_details", Context.MODE_PRIVATE);
        if (!TextUtils.isEmpty(locationName))
            return true;
        else
            return false;
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

    public void SaveKroGuys(View view) {

        Log.i("TAG","SaveKroGuys() Called");
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
                            city_NameSTR = dataSmall.getName().toString();
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
                            saveLocationButton.setVisibility(View.VISIBLE);
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

    public void getLocation(View view) {
        parseLocation();
    }

    private void parseLocation() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(200);

        Toast.makeText(this, "Getting Current Location!", Toast.LENGTH_SHORT).show();
        startActivityForResult(new Intent(this, LocationAct.class), 1);
    }

    public void reloadData(View view) {

        try {
            reloadPlease();
        } catch (IOException e) {
            e.printStackTrace();
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
    public void onBackPressed() {
        super.onBackPressed();
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    public void openDrawer(View view) {
        drawer.openDrawer(GravityCompat.START);
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
                Intent intent1 = new Intent(this,SavedLocations.class);
                drawer.closeDrawer(GravityCompat.START);
                startActivity(intent1);
                break;

        }
        return false;
    }

}
