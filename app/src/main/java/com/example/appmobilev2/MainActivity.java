package com.example.appmobilev2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private TextView txt;
    private Button btn, btn_param;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_main);

        this.txt = findViewById(R.id.textView);
        this.btn = findViewById(R.id.button_edt);
        btn_param = findViewById(R.id.button_param);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CalendarJourActivity = new Intent(getApplicationContext(), com.example.appmobilev2.CalendarJour.class);
                startActivity(CalendarJourActivity);
            }
        });
        btn_param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ParametresActivity= new Intent(getApplicationContext(), com.example.appmobilev2.SettingsActivity.class);
                startActivity(ParametresActivity);
            }
        });
    }

}