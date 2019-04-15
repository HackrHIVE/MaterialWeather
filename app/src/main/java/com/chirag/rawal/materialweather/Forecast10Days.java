package com.chirag.rawal.materialweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.chirag.rawal.materialweather.POJO10.City;
import com.chirag.rawal.materialweather.POJO10.List;
import com.chirag.rawal.materialweather.POJO10.Sizteen;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Forecast10Days extends AppCompatActivity {

    TextView cityName;
    String lat,lng;
    private String APIkey = "ebfcac32bda131ed5a160f2757938396";
    RecyclerView recyclerView;
    ArrayList<List> listData;
    private boolean stored=false;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast10_days);
        lat = getIntent().getStringExtra("lat_coord");
        lng = getIntent().getStringExtra("lng_coord");
        cityName = findViewById(R.id.cityNameForecast);
        lottieAnimationView = findViewById(R.id.Forecast_lottie);
        lottieAnimationView.setRepeatCount(100);
        lottieAnimationView.playAnimation();
        recyclerView = findViewById(R.id.recyclerView);
        startAPIcallFor10Days(lat,lng);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    private void runAnimation(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
//        LayoutAnimationController layoutAnimationController = null;

//        layoutAnimationController = AnimationUtils.loadLayoutAnimation(context,R.anim.item_anim_fall);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(listData);
        recyclerView.setAdapter(recyclerAdapter);
//        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
//        recyclerView.scheduleLayoutAnimation();


        lottieAnimationView.pauseAnimation();
        lottieAnimationView.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void startAPIcallFor10Days(final String lat, final String lon) {
        
        new Thread() {
            @Override
            public void run() {
                super.run();
                final String data;
                try {
                    data = fetchDataFor10Days(lat, lon);
                    final Sizteen data10Days = new Gson().fromJson(data, com.chirag.rawal.materialweather.POJO10.Sizteen.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            City city = data10Days.getCity();
                            cityName.setText(city.getName());
                            listData = data10Days.getList();
                            runAnimation(recyclerView);
                        }
                    });
                    stored= true;


                } catch (IOException e) {
                    e.printStackTrace();
                }

                //

//                    Log.i(TAG,data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }.start();

    }

    private String fetchDataFor10Days(String lat,String lon) throws IOException {
        String url = "https://api.openweathermap.org/data/2.5/forecast/daily?lat="+lat+"&lon="+lon+"&cnt=11&appid="+APIkey;
        final URL url1 = new URL(url);
        StringBuffer toReturn = new StringBuffer();
        HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
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

    public void goBack(View view) {
        onBackPressed();
    }
}
