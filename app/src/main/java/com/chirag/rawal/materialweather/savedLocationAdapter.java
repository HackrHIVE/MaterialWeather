package com.chirag.rawal.materialweather;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class savedLocationAdapter extends RecyclerView.Adapter<savedLocationAdapter.ViewHolder>{

    ArrayList<StoredSampleData> data;
    Context context;

    savedLocationAdapter(Context context, ArrayList<StoredSampleData> data){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public savedLocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_locationsaved,parent,false);
        return new savedLocationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.cityName.setText(data.get(position).cityName);

        holder.cityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ActFromID.class);
                intent.putExtra("cityID",data.get(position).cityID);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView cityName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityNameLocationSaved);
        }
    }

}
