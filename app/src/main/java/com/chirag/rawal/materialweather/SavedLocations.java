package com.chirag.rawal.materialweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Map;

public class SavedLocations extends AppCompatActivity {

    RecyclerView loadingLocationsRecycler;
    LottieAnimationView loadingLocationsProgress;
    ArrayList<StoredSampleData> listOfStoredData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_locations);
        loadingLocationsRecycler = findViewById(R.id.saveLocationsRecycler);
        loadingLocationsProgress = findViewById(R.id.loadingLocations);
        loadingLocationsProgress.setRepeatCount(100);
        loadingLocationsProgress.playAnimation();
        getAllKeys();

    }

    public void getAllKeys(){
        Log.i("TAG","in getAllKeys()");
        listOfStoredData = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("saved_location",MODE_PRIVATE);

        Map<String, ?> allEntries = sharedPreferences.getAll();
        Log.i("Size",String.valueOf(allEntries.size()));
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            listOfStoredData.add(new StoredSampleData(entry.getKey(),(Integer)entry.getValue()));
        }
        setAdapterAndETC();
    }

    public void setAdapterAndETC(){

        loadingLocationsRecycler.setLayoutManager(new LinearLayoutManager(getApplication()));
        savedLocationAdapter adapter = new savedLocationAdapter(this,listOfStoredData);
        loadingLocationsRecycler.setAdapter(adapter);

        loadingLocationsRecycler.setVisibility(View.VISIBLE);
        loadingLocationsProgress.setVisibility(View.INVISIBLE);
        loadingLocationsProgress.cancelAnimation();

    }

    public void goBackSaved(View view) {
        onBackPressed();
    }
}
