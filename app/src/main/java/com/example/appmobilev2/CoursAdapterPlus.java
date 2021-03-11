package com.example.appmobilev2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CoursAdapterPlus extends ArrayAdapter<Cours> {

    private Context mContext;
    private List<Cours> moviesList = new ArrayList<>();

    public CoursAdapterPlus(@NonNull Context context, @NonNull List<Cours> objects) {
        super(context,0, objects);
        mContext = context;
        moviesList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.informationscours, parent, false);


        return listItem;
    }
}
