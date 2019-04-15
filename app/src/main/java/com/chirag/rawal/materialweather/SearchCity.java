package com.chirag.rawal.materialweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchCity extends AppCompatActivity {

    ContentLoadingProgressBar contentLoadingProgressBar;
    EditText cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        contentLoadingProgressBar = findViewById(R.id.loadingCustom);
        cityName = findViewById(R.id.cityNameCustom);

    }

    public void goToCustomSearch(View view) {

        contentLoadingProgressBar.setVisibility(View.VISIBLE);
        String toSearch = cityName.getText().toString();
        Geocoder geocoder = new Geocoder(this);
        List<Address> addressList = new ArrayList<>();
        try{
            addressList = geocoder.getFromLocationName(toSearch,1);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(addressList.size()>0){
            double lat = addressList.get(0).getLatitude();
            double lng = addressList.get(0).getLongitude();
            Intent chaluKaro = new Intent(this,CustomSearch.class);
            chaluKaro.putExtra("lat",String.valueOf(lat));
            chaluKaro.putExtra("lng",String.valueOf(lng));
            contentLoadingProgressBar.setVisibility(View.INVISIBLE);
            startActivity(chaluKaro);
        }
        else{
            Toast.makeText(this, "Network Error\nTry Again!", Toast.LENGTH_SHORT).show();
        }
    }
}
